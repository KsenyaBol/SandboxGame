package com.example.sandboxgame.ui.main

import android.content.Intent
import com.example.sandboxgame.ui.music.MusicService
import com.example.sandboxgame.ui.size.SizeFragment
import com.omega_r.base.mvp.presenters.OmegaPresenter

class MainPresenter: OmegaPresenter<MainView>() {

    fun onButtonStartClicked() {
        SizeFragment.createLauncher().launch()
    }
}