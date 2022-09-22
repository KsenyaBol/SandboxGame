package com.example.sandboxgame.ui.settings

import com.omega_r.base.mvp.presenters.OmegaPresenter

class SettingsPresenter: OmegaPresenter<SettingsView>() {

    fun onButtonBackClicked() {
        exit()
    }
}