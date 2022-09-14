package com.example.sandboxgame.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

open class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: MainPresenter by providePresenter()
    private val buttonStart: Button by bind(R.id.button_start)
//    private val buttonSound: Button by bind(R.id.button_sound)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, MusicService::class.java))

        buttonStart.setOnClickListener {
           presenter.onButtonStartClicked()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopService(Intent(this, MusicService::class.java))
    }


}