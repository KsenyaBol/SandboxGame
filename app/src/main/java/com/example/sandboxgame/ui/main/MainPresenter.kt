package com.example.sandboxgame.ui.main

import com.example.sandboxgame.ui.settings.SettingsActivity
import com.example.sandboxgame.ui.size.SizeFragment
import com.omega_r.base.mvp.presenters.OmegaPresenter

class MainPresenter: OmegaPresenter<MainView>() {

    fun onButtonStartClicked() {
        SizeFragment.createLauncher().launch()
    }

    fun onButtonSettingsClicked() {
        SettingsActivity.createLauncher().launch()
    }
}