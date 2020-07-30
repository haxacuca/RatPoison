package rat.poison.ui

import rat.poison.overlay.App.uiAimOverridenWeapons
import rat.poison.overlay.App.uiBombWindow
import rat.poison.overlay.App.uiMenu
import rat.poison.overlay.App.uiSpecList
import rat.poison.overlay.opened
import rat.poison.ui.tabs.*
import rat.poison.ui.tabs.visualstabs.*
import rat.poison.ui.uiPanelTables.OverridenWeapons
import rat.poison.ui.uiPanelTables.overridenWeaponsUpdate
import rat.poison.ui.uiPanels.*

fun uiUpdate() {
    if (!opened) return

    overridenWeaponsUpdate()
    visualsTabUpdate()
    glowEspTabUpdate()
    chamsEspTabUpdate()
    indicatorEspTabUpdate()
    boxEspTabUpdate()
    hitMarkerTabUpdate()
    skinChangerTabUpdate()
    nadesVTUpdate()
    snaplinesEspTabUpdate()
    footStepsEspTabUpdate()
    headLevelTabUpdate()
    miscTabUpdate()
    rcsTabUpdate()
    nadeHelperTabUpdate()
    updateBacktrack()
    updateTrig()
    updateAim()
    updateDisableEsp()

    //Update windows
    uiAimOverridenWeapons.setPosition(uiMenu.x+uiMenu.width+4F, uiMenu.y)
}

fun refreshMenu() {
    mainTabbedPane.removeAll()

    aimTab = AimTab()
    visualsTab = VisualsTab()
    rcsTab = RcsTab()
    miscTab = MiscTab()
    ranksTab = RanksTab()
    nadeHelperTab = NadeHelperTab()
    skinChangerTab = SkinChangerTab()
    optionsTab = OptionsTab()

    mainTabbedPane.add(aimTab)
    mainTabbedPane.add(visualsTab)
    mainTabbedPane.add(rcsTab)
    mainTabbedPane.add(miscTab)
    mainTabbedPane.add(ranksTab)
    mainTabbedPane.add(nadeHelperTab)
    mainTabbedPane.add(skinChangerTab)
    mainTabbedPane.add(optionsTab)

    espTabbedPane.removeAll()

    glowEspTab = GlowEspTab()
    chamsEspTab = ChamsEspTab()
    indicatorEspTab = IndicatorEspTab()
    boxEspTab = BoxEspTab()
    snaplinesEspTab = SnaplinesEspTab()
    footStepsEspTab = FootstepsEspTab()
    hitMarkerTab = HitMarkerTab()
    nadesTab = NadesVT()
    headLevelTab = HeadLevelTab()

    espTabbedPane.add(glowEspTab)
    espTabbedPane.add(chamsEspTab)
    espTabbedPane.add(indicatorEspTab)
    espTabbedPane.add(boxEspTab)
    espTabbedPane.add(snaplinesEspTab)
    espTabbedPane.add(footStepsEspTab)
    espTabbedPane.add(hitMarkerTab)
    espTabbedPane.add(nadesTab)
    espTabbedPane.add(headLevelTab)

    uiAimOverridenWeapons.removeActor(overridenWeapons)
    uiAimOverridenWeapons.remove()
    overridenWeapons = OverridenWeapons()
    uiAimOverridenWeapons = UIAimOverridenWeapons()


    uiSpecList.remove()
    uiSpecList = UISpectatorList()

    uiBombWindow.remove()
    uiBombWindow = UIBombTimer()
}