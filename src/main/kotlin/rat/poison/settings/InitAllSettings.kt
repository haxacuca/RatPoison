package rat.poison.settings

import rat.poison.game.CSGO.clientDLL
import rat.poison.game.offsets.ClientOffsets.dwSensitivity
import rat.poison.game.offsets.ClientOffsets.dwSensitivityPtr
import rat.poison.utils.extensions.uint
import com.sun.jna.platform.win32.WinNT
import rat.poison.game.Color
import java.awt.event.KeyEvent

//Idc about info

var GAME_PITCH = 0.022 // m_pitch
var GAME_YAW = 0.022 // m_yaw
val GAME_SENSITIVITY by lazy(LazyThreadSafetyMode.NONE) {
    val pointer = clientDLL.address + dwSensitivityPtr
    val value = clientDLL.uint(dwSensitivity) xor pointer

    java.lang.Float.intBitsToFloat(value.toInt()).toDouble()
}
var HEAD_BONE = 8
var NECK_BONE = 7
var CHEST_BONE = 6
var STOMACH_BONE = 5
var NEAREST_BONE = -1
var SERVER_TICK_RATE = 64
var MAX_ENTITIES = 4096
var CLEANUP_TIME = 10_000
var GARBAGE_COLLECT_ON_MAP_START = true
var PROCESS_NAME = "csgo.exe"
var PROCESS_ACCESS_FLAGS = WinNT.PROCESS_QUERY_INFORMATION or WinNT.PROCESS_VM_READ or WinNT.PROCESS_VM_WRITE
var CLIENT_MODULE_NAME = "client_panorama.dll"
var ENGINE_MODULE_NAME = "engine.dll"
var FACTOR_RECOIL = true
var AIM_BONE = HEAD_BONE
var AIM_FOV = 40
var AIM_SPEED = 16
var AIM_STRICTNESS = 1.0
var PERFECT_AIM = false
var PERFECT_AIM_FOV = 4
var PERFECT_AIM_CHANCE = 100
var AIM_ASSIST_MODE = true
var BONE_TRIGGER_PISTOLS_FOV = 15
var BONE_TRIGGER_RIFLES_FOV = 15
var BONE_TRIGGER_SHOTGUNS_FOV = 15
var BONE_TRIGGER_SMGS_FOV = 15
var BONE_TRIGGER_SNIPERS_FOV = 15
var BONE_TRIGGER_HB = true
var BONE_TRIGGER_BB = true
var BONE_TRIGGER_PISTOLS = false
var BONE_TRIGGER_RIFLES = false
var BONE_TRIGGER_SHOTGUNS = false
var BONE_TRIGGER_SMGS = false
var BONE_TRIGGER_SNIPERS = false
var BONE_TRIGGER_PISTOLS_AIMBOT = true
var BONE_TRIGGER_RIFLES_AIMBOT = true
var BONE_TRIGGER_SHOTGUNS_AIMBOT = true
var BONE_TRIGGER_SMGS_AIMBOT = true
var BONE_TRIGGER_SNIPERS_AIMBOT = true
var AIM_ON_BONE_TRIGGER = true
var BONE_TRIGGER_ENABLE_KEY = false
var BONE_TRIGGER_KEY = 6
var BONE_TRIGGER_SHOT_DELAY = 8
var ENABLE_BOX_ESP = false
var BOX_ESP_DETAILS = false
var BOX_ESP_HEALTH = true
var BOX_ESP_ARMOR = true
var BOX_ESP_NAME = true
var BOX_ESP_WEAPON = true
var BOX_ESP_HEALTH_POS = "L"
var BOX_ESP_ARMOR_POS = "R"
var BOX_ESP_NAME_POS = "T"
var BOX_ESP_WEAPON_POS = "B"
var BOX_SHOW_TEAM = false
var BOX_SHOW_ENEMIES = true
var BOX_SHOW_DEFUSERS = true
var BOX_TEAM_COLOR = Color(red=0, green=0, blue=255, alpha=1.0)
var BOX_ENEMY_COLOR = Color(red=255, green=0, blue=0, alpha=0.6)
var BOX_DEFUSER_COLOR = Color(red=145, green=0, blue=90, alpha=1.0)
var BUNNY_HOP_KEY = KeyEvent.VK_SPACE
var CHAMS_ESP = false
var CHAMS_SHOW_HEALTH = false
var CHAMS_BRIGHTNESS = 0
var CHAMS_SHOW_TEAM = false
var CHAMS_SHOW_ENEMIES = true
var CHAMS_SHOW_SELF = true
var CHAMS_TEAM_COLOR = Color(red=0, green=0, blue=255, alpha=1.0)
var CHAMS_ENEMY_COLOR = Color(red=255, green=0, blue=0, alpha=0.6)
var CHAMS_SELF_COLOR = Color(red=255, green=255, blue=255, alpha=1.0)
var RADAR_ESP = true
var SNAPLINES = false
var SNAPLINES_COLOR = Color(red=255, green=255, blue=255, alpha=1.0)
var ENABLE_HITSOUND = true
var ENABLE_HITMARKER = true
var ENABLE_ADRENALINE = true
var ENABLE_NADE_HELPER = true
var NADE_HELPER_TOGGLE_KEY = 113
var NADE_TRACER = true
var NADE_TRACER_UPDATE_TIME = 15
var NADE_TRACER_TIMEOUT = 1.0
var NADE_TRACER_COLOR = Color(red=0, green=0, blue=255, alpha=1.0)
var HITMARKER_RECOIL_POSITION = true
var HITMARKER_COMBO = true
var HITMARKER_OUTLINE = true
var HITMARKER_SPACING = 0
var HITMARKER_LENGTH = 20
var HITMARKER_WIDTH = 3F
var HITMARKER_COMBO_COLOR = Color(red=255, green=255, blue=255, alpha=1.0)
var HITMARKER_COLOR = Color(red=255, green=255, blue=255, alpha=1.0)
var HITMARKER_OUTLINE_COLOR = Color(red=0, green=0, blue=0, alpha=1.0)
var HITSOUND_VOLUME = 0.2
var HITSOUND_FILE_NAME = "COD.wav"
var DEBUG = false
var MENU = true
var OVERLAY_WIDTH = 1920
var OVERLAY_HEIGHT = 1080
var ACTIVATE_FROM_AIM_KEY = true
var TEAMMATES_ARE_ENEMIES = false
var AUTOMATIC_WEAPONS = true
var AUTO_WEP_DELAY = 16
var FORCE_AIM_KEY = 5
var AIM_DURATION = 1
var DANGER_ZONE = false
var AIM_KEY = 1
var VISUALS_TOGGLE_KEY = 71
var MENU_KEY = 112
var MENUTOG = false
var ENABLE_AIM = true
var ENABLE_OVERRIDE = true
var GLOW_ESP = false
var INV_GLOW_ESP = false
var MODEL_ESP = false
var MODEL_AND_GLOW = false
var GLOW_SHOW_TEAM = false
var GLOW_SHOW_ENEMIES = true
var GLOW_SHOW_BOMB = true
var GLOW_SHOW_BOMB_CARRIER = true
var GLOW_SHOW_WEAPONS = true
var GLOW_SHOW_GRENADES = false
var GLOW_SHOW_TARGET = true
var GLOW_TEAM_COLOR = Color(red=0, green=0, blue=255, alpha=1.0)
var GLOW_ENEMY_COLOR = Color(red=255, green=0, blue=0, alpha=0.6)
var GLOW_BOMB_COLOR = Color(red=255, green=255, blue=0, alpha=1.0)
var GLOW_BOMB_CARRIER_COLOR = Color(red=255, green=255, blue=0, alpha=1.0)
var GLOW_DEFUSER_COLOR = Color(red=145, green=0, blue=90, alpha=1.0)
var GLOW_WEAPON_COLOR = Color(red=251, green=0, blue=255, alpha=0.5)
var GLOW_GRENADE_COLOR = Color(red=0, green=255, blue=0, alpha=1.0)
var GLOW_HIGHLIGHT_COLOR = Color(red=0, green=174, blue=255, alpha=1.0)
var OPENGL_VSYNC = false
var OPENGL_FPS = 60
var OPENGL_MSAA_SAMPLES = 8
var PISTOL_ENABLE_FLAT_AIM = false
var PISTOL_ENABLE_PATH_AIM = true
var PISTOL_FACTOR_RECOIL = true
var PISTOL_AIM_BONE = HEAD_BONE
var PISTOL_AIM_FOV = 40
var PISTOL_AIM_SPEED = 1
var PISTOL_AIM_SMOOTHNESS = 1.0
var PISTOL_AIM_STRICTNESS = 1.0
var PISTOL_PERFECT_AIM = false
var PISTOL_PERFECT_AIM_FOV = 4
var PISTOL_PERFECT_AIM_CHANCE = 100
var RCROSSHAIR_WIDTH = 3
var RCROSSHAIR_LENGTH = 10
var RCROSSHAIR_ALPHA = .5
var RCROSSHAIR_COLOR = Color(255, 255, 255, 1.0)
var RCS_SMOOTHING_X = 1.0
var RCS_SMOOTHING_Y = 1.0
var RCS_RETURNAIM = true
var FLASH_MAX_ALPHA = 200F
var RIFLE_ENABLE_FLAT_AIM = false
var RIFLE_ENABLE_PATH_AIM = true
var RIFLE_FACTOR_RECOIL = true
var RIFLE_AIM_BONE = HEAD_BONE
var RIFLE_AIM_FOV = 40
var RIFLE_AIM_SPEED = 1
var RIFLE_AIM_SMOOTHNESS = 1.0
var RIFLE_AIM_STRICTNESS = 1.0
var RIFLE_PERFECT_AIM = false
var RIFLE_PERFECT_AIM_FOV = 4
var RIFLE_PERFECT_AIM_CHANCE = 100
var ENABLE_BUNNY_HOP = false
var ENABLE_RCS = false
var ENABLE_RECOIL_CROSSHAIR = true
var ENABLE_SNIPER_CROSSHAIR = true
var ENABLE_ESP = false
var ENABLE_FLAT_AIM = false
var ENABLE_PATH_AIM = true
var ENABLE_SCOPED_ONLY = true
var ENABLE_BONE_TRIGGER = false
var ENABLE_REDUCED_FLASH = false
var ENABLE_BOMB_TIMER = false
var BOMB_TIMER_BARS = true
var BOMB_TIMER_MENU = true
var BOMB_TIMER_X = 0.0
var BOMB_TIMER_Y = 0.0
var BOMB_TIMER_ALPHA = 1.0
var SHOTGUN_ENABLE_FLAT_AIM = false
var SHOTGUN_ENABLE_PATH_AIM = true
var SHOTGUN_FACTOR_RECOIL = true
var SHOTGUN_AIM_BONE = HEAD_BONE
var SHOTGUN_AIM_FOV = 40
var SHOTGUN_AIM_SPEED = 1
var SHOTGUN_AIM_SMOOTHNESS= 1.0
var SHOTGUN_AIM_STRICTNESS = 1.0
var SHOTGUN_PERFECT_AIM = false
var SHOTGUN_PERFECT_AIM_FOV = 4
var SHOTGUN_PERFECT_AIM_CHANCE = 100
var SKELETON_ESP = false
var SKELETON_SHOW_TEAM = false
var SKELETON_SHOW_ENEMIES = true
var SMG_ENABLE_FLAT_AIM = false
var SMG_ENABLE_PATH_AIM = true
var SMG_FACTOR_RECOIL = true
var SMG_AIM_BONE = HEAD_BONE
var SMG_AIM_FOV = 40
var SMG_AIM_SPEED = 1
var SMG_AIM_SMOOTHNESS = 1.0
var SMG_AIM_STRICTNESS = 1.0
var SMG_PERFECT_AIM = false
var SMG_PERFECT_AIM_FOV = 4
var SMG_PERFECT_AIM_CHANCE = 100
var SNIPER_ENABLE_FLAT_AIM = false
var SNIPER_ENABLE_PATH_AIM = true
var SNIPER_ENABLE_SCOPED_ONLY = true
var SNIPER_FACTOR_RECOIL = false
var SNIPER_AIM_BONE = HEAD_BONE
var SNIPER_AIM_FOV = 40
var SNIPER_AIM_SPEED = 1
var SNIPER_AIM_SMOOTHNESS = 1.0
var SNIPER_AIM_STRICTNESS = 1.0
var SNIPER_PERFECT_AIM = false
var SNIPER_PERFECT_AIM_FOV = 4
var SNIPER_PERFECT_AIM_CHANCE = 100
var INDICATOR_ESP = true
var INDICATOR_SHOW_ONSCREEN = true
var INDICATOR_DISTANCE = 3.0
var INDICATOR_OVAL = true
var INDICATOR_SHOW_TEAM = false
var INDICATOR_SHOW_ENEMIES = true
var INDICATOR_SHOW_BOMB = true
var INDICATOR_SHOW_DEFUSERS = true
var INDICATOR_SHOW_BOMB_CARRIER = true
var INDICATOR_SHOW_WEAPONS = true
var INDICATOR_SHOW_GRENADES = false
var INDICATOR_TEAM_COLOR = Color(red=0, green=0, blue=255, alpha=1.0)
var INDICATOR_ENEMY_COLOR = Color(red=255, green=0, blue=0, alpha=0.6)
var INDICATOR_BOMB_COLOR = Color(red=255, green=255, blue=0, alpha=1.0)
var INDICATOR_BOMB_CARRIER_COLOR = Color(red=255, green=255, blue=0, alpha=1.0)
var INDICATOR_DEFUSER_COLOR = Color(red=145, green=0, blue=90, alpha=1.0)
var INDICATOR_WEAPON_COLOR = Color(red=251, green=0, blue=255, alpha=0.5)
var INDICATOR_GRENADE_COLOR = Color(red=0, green=255, blue=0, alpha=1.0)
var ENABLE_AUTO_KNIFE = false
var SPECTATOR_LIST = true
var SPECTATOR_LIST_X = 0.0
var SPECTATOR_LIST_Y = 0.0
var SPECTATOR_LIST_ALPHA = 1.0
var WARNING = true
var AUTO_STRAFE = true
var STRAFE_BHOP_ONLY = true
var FAST_STOP = true
var HEAD_WALK = false
var HEAD_WALK_KEY = 81
var MENU_APP = "Counter-Strike: Global Offensive"
var RCROSSHAIR_XOFFSET = 0
var RCROSSHAIR_YOFFSET = 0
//Pistols
var DESERT_EAGLE = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var DUAL_BERRETA = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var FIVE_SEVEN = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var GLOCK = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var USP_SILENCER = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var CZ75A = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var R8_REVOLVER = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var P2000 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var TEC9 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
var P250 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 8.0, 30.0, 12.0, 2.5, 2.0, 1.0, 10.0, 15.0)
//Smgs
var MAC10 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var P90 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var MP5 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var UMP45 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var MP7 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var MP9 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
var PP_BIZON = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 60.0, 12.0, 2.5, 3.5, 1.0, 15.0, 10.0)
//Rifles
var AK47 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var AUG = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var FAMAS = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var SG553 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var GALIL = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var M4A4 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var M4A1_SILENCER = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var NEGEV = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
var M249 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 80.0, 16.0, 1.0, 2.0, 1.0, 5.0, 10.0)
//Snipers
var AWP = doubleArrayOf(0.0, 0.0, 1.0, 0.0, 6.0, 80.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0)
var G3SG1 = doubleArrayOf(0.0, 0.0, 1.0, 0.0, 6.0, 80.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0)
var SCAR20 = doubleArrayOf(0.0, 0.0, 1.0, 0.0, 6.0, 80.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0)
var SSG08 = doubleArrayOf(0.0, 0.0, 1.0, 0.0, 6.0, 80.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0)
//Shotguns
var XM1014 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 70.0, 4.0, 1.0, 1.5, 1.0, 20.0, 40.0)
var MAG7 = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 70.0, 4.0, 1.0, 1.5, 1.0, 20.0, 40.0)
var SAWED_OFF = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 70.0, 4.0, 1.0, 1.5, 1.0, 20.0, 40.0)
var NOVA = doubleArrayOf(0.0, 1.0, 0.0, 1.0, 6.0, 70.0, 4.0, 1.0, 1.5, 1.0, 20.0, 40.0)