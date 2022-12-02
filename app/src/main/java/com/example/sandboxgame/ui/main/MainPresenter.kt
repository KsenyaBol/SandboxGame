package com.example.sandboxgame.ui.main

import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.example.sandboxgame.ui.game.GamePresenter
import com.example.sandboxgame.ui.name.NameActivity
import com.example.sandboxgame.ui.settings.SettingsActivity
import com.example.sandboxgame.ui.size.SizeFragment
import com.omega_r.base.mvp.presenters.OmegaPresenter

class MainPresenter: OmegaPresenter<MainView>() {


    fun onButtonStartClicked() {
        NameActivity.createLauncher().launch()
    }

    fun onButtonContinueClicked() {
        ContinueActivity.createLauncher().launch()
    }

    fun onButtonSettingsClicked() {
        SettingsActivity.createLauncher().launch()
    }
}