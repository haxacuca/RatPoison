package rat.poison.scripts

import com.badlogic.gdx.math.MathUtils.clamp
import rat.poison.curSettings
import rat.poison.game.*
import rat.poison.game.CSGO.csgoEXE
import rat.poison.game.CSGO.engineDLL
import rat.poison.game.entity.*
import rat.poison.game.netvars.NetVarOffsets
import rat.poison.game.netvars.NetVarOffsets.flSimulationTime
import rat.poison.game.offsets.ClientOffsets.dwIndex
import rat.poison.game.offsets.EngineOffsets.dwGlobalVars
import rat.poison.scripts.aim.*
import rat.poison.scripts.misc.gvars
import rat.poison.scripts.misc.haveGvars
import rat.poison.scripts.misc.sendPacket
import rat.poison.scripts.userCmd.ap
import rat.poison.scripts.userCmd.cmdShoot
import rat.poison.scripts.userCmd.nextCMD
import rat.poison.utils.Structs.GlobalVars
import rat.poison.utils.Structs.UserCMD
import rat.poison.utils.Structs.memToGlobalVars
import rat.poison.utils.common.*
import rat.poison.utils.extensions.uint
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.tan

var btRecords = Array(64) { Array(13) { BacktrackTable() } }
data class BacktrackTable(var simtime: Float = 0f, var headPos: Angle = Angle(), var absPos: Angle = Angle(), var alpha: Float = 100f, var entity: Long = -1)

var bestBacktrackTarget = -1L

private var inBacktrack = false

fun setupBacktrack() = every(10, true, inGameCheck = true) {
    if (!curSettings.bool["ENABLE_BACKTRACK"] || me <= 0 || !haveGvars) return@every

    constructRecords()
}

fun attemptBacktrack(userCMD: UserCMD?): Boolean {
    if (((curSettings.bool["BACKTRACK_SPOTTED"] && bestBacktrackTarget.spotted()) || !curSettings.bool["BACKTRACK_SPOTTED"]) && bestBacktrackTarget > 0L && haveGvars) {
        inBacktrack = true

        //Get/set vars
        if (!meCurWep.gun || !meCurWepEnt.canFire()) { inBacktrack = false; return false }

        val bestTime = bestSimTime()

        if (bestTime <= 0F) {
            sendPacket(true)
            inBacktrack = false
            bestBacktrackTarget = -1L
            return false
        }

        if (keyPressed(1) && !ap) { //TODO backtrack on key???
            cmdShoot(null)
            nextCMD.iTickCount = timeToTicks(bestTime)
        }

        inBacktrack = false
        return true
    }

    inBacktrack = false
    return false
}

private const val boneMemorySize = 3984
private val boneMemory = threadLocalPointer(boneMemorySize)
private val meAngVec = Vector()
private var bestFov = 5F
private val boneVec = Vector()
private val forEnts = arrayOf(EntityType.CCSPlayer)

fun constructRecords() {
    bestFov = 5F
    val clientAngle = clientState.angle(meAngVec)

    val boneMemory = boneMemory.get()

    bestBacktrackTarget = -1L
    forEntities(forEnts) {
        val ent = it.entity

        if (ent.dead() || ent == me || ent.team() == meTeam) return@forEntities

        if (ent.dormant()) { //Reset that bitch
            val entID = (csgoEXE.uint(ent + dwIndex) - 1).toInt()

            if (entID < 0 || entID > 63) return@forEntities

            for (i in 0 until 13) {
                val record = btRecords[entID][i]

                record.simtime = 0f
                record.alpha = 100f

                btRecords[entID][i] = record
            }

            return@forEntities
        }

        //Best target shit
        val pos = ent.bones(6, boneVec)
        val fov = calcTarget(bestFov, bestBacktrackTarget, pos, clientAngle, 5F, 6, ovrStatic = true).fov
        if (fov < bestFov && fov > 0) {
            bestFov = fov
            bestBacktrackTarget = ent
        }

        //Create records
        val entSimTime = csgoEXE.float(ent + flSimulationTime)
        val entID = (csgoEXE.uint(ent + dwIndex) - 1).toInt()
        val tick = gvars.tickCount % 13

        if (entID in 0..63 && tick < 13) {
            val record = btRecords[entID][tick]

            csgoEXE.read(ent.boneMatrix(), boneMemory, boneMemorySize)
            record.headPos = boneMemory.bones(8, record.headPos).apply {
                z += 5
            }
            record.absPos = ent.absPosition(record.absPos).apply {
                z -= 5
            }

            record.alpha = 100f
            record.simtime = entSimTime
            record.entity = ent

            btRecords[entID][tick] = record
        }

        if (bestFov >= 5F) {
            bestBacktrackTarget = -1L
        }

        return@forEntities
    }
}
private val minHeadPos = Vector(); private val maxHeadPos = Vector(); private val minAbsPos = Vector(); private val maxAbsPos = Vector()
private val w2s = Vector()
private val punchVec = Vector()
fun bestSimTime(): Float {
    if (bestBacktrackTarget <= 0L) {
        return -1f
    }

    var best = -1f
    val targetID = (csgoEXE.uint(bestBacktrackTarget + dwIndex)-1).toInt()

    if (targetID < 0 || targetID > 63) return -1f

    val validRecords = getValidRecords(targetID)
    val minMaxIDX = getRangeRecords(targetID)

    if (minMaxIDX[0] == Int.MAX_VALUE || minMaxIDX[1] == -1) return -1f

    val minRecord = btRecords[targetID][minMaxIDX[0]]
    val maxRecord = btRecords[targetID][minMaxIDX[1]]

    if (worldToScreen(minRecord.headPos, minHeadPos) && worldToScreen(minRecord.absPos, minAbsPos) && worldToScreen(maxRecord.headPos, maxHeadPos) && worldToScreen(maxRecord.absPos, maxAbsPos)) {
        val w = (minAbsPos.y - minHeadPos.y) / 4F
        val minMidX = (minAbsPos.x + minHeadPos.x) / 2F
        val maxMidX = (maxAbsPos.x + maxAbsPos.x) / 2F

        var sign = -1

        if (minMidX > maxMidX) {
            sign = 1
        }

        val topLeft = Vector(minHeadPos.x - (w / 3F) * sign, minHeadPos.y, minHeadPos.z)
        val topRight = Vector(maxHeadPos.x + (w / 3F) * sign, maxHeadPos.y, maxHeadPos.z)

        val bottomLeft = Vector(minMidX - (w / 2F) * sign, minAbsPos.y+8F, minAbsPos.z)
        //val bottomRight = Vector(maxMidX + (w / 2F) * sign, maxAbsPos.y+8F, maxAbsPos.z)

        val punch = me.punch(punchVec)
        val curFov = csgoEXE.int(me + NetVarOffsets.m_iDefaultFov)
        val rccFov1 = atan((CSGO.gameWidth.toFloat()/ CSGO.gameHeight.toFloat()) * 0.75 * tan(Math.toRadians(curFov/2.0)))
        val rccFov2 = (CSGO.gameWidth /2) / tan(rccFov1).toFloat()

        val centerX = (CSGO.gameWidth / 2) - tan(Math.toRadians(punch.y.toDouble())).toFloat() * rccFov2
        val centerY = (CSGO.gameHeight / 2) - tan(Math.toRadians(punch.x.toDouble())).toFloat() * rccFov2

        if (inRange(centerX, topLeft.x, topRight.x) && inRange(centerY, topLeft.y, bottomLeft.y)) {//If middle of screen + recoil is inside polygon
            var bestMinX = Float.MAX_VALUE

            for (i in validRecords) {
                val record = btRecords[targetID][i]
                worldToScreen(record.headPos, w2s)

                val centerDist = abs(centerX - w2s.x)
                if (centerDist < bestMinX) {
                    bestMinX = centerDist

                    best = record.simtime
                }
            }
        }
    }

    return best
}

fun isValidTick(tick: Int): Boolean {
    if (!haveGvars) return false

    val delta = gvars.tickCount - tick
    val deltaTime = delta * gvars.intervalPerTick

    var backtrackMS = curSettings.float[curWepCategoryBacktrackMs]
    if (curWepOverride && curWepSettings.tBacktrack) {
        backtrackMS = curWepSettings.tBTMS.toFloat()
    }

    val max = clamp(backtrackMS/1000f, 0F, .19F)

    return abs(deltaTime) <= max
}

fun timeToTicks(time: Float): Int {
    return (.5f + time / gvars.intervalPerTick).toInt()
}

val minMaxIDX = intArrayOf(Int.MAX_VALUE, -1)
fun getRangeRecords(entID: Int, minIDX: Int = 0, maxIDX: Int = 13): IntArray {
    var youngestSimtime = Float.MAX_VALUE
    var oldestSimtime = 0F
    minMaxIDX[0] = Int.MAX_VALUE //reset
    minMaxIDX[1] = -1

    for (i in minIDX until maxIDX) {
        val record = btRecords[entID][i]

        if (isValidTick(timeToTicks(record.simtime))) {
            if (record.simtime > oldestSimtime) {
                oldestSimtime = record.simtime
                minMaxIDX[1] = i
            }

            if (record.simtime < youngestSimtime) {
                youngestSimtime = record.simtime
                minMaxIDX[0] = i
            }
        }
    }

    return minMaxIDX
}

fun getValidRecords(entID: Int): List<Int> {
    val recordsList = mutableListOf<Int>()

    if (entID in 0..63) {
        for (i in 0 until 13) {
            if (isValidTick(timeToTicks(btRecords[entID][i].simtime))) {
                recordsList.add(i)
            }
        }
    }

    return recordsList
}
private const val gVarsMemorySize = 64
private val gVarsMemory = threadLocalPointer(gVarsMemorySize)

fun getGlobalVars(): GlobalVars? {
    val memory = gVarsMemory.get()
    csgoEXE.read(engineDLL.address + dwGlobalVars, memory, gVarsMemorySize)
    if (memory != null) {
        return memToGlobalVars(memory)
    }

    return null
}

fun inRange(value: Float, num1: Float, num2: Float): Boolean {
    val min: Float
    val max: Float

    if (num1 > num2) {
        max = num1
        min = num2
    } else {
        max = num2
        min = num1
    }

    return value > min && value < max
}