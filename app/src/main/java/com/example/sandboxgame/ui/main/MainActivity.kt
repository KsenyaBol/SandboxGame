package com.example.sandboxgame.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

open class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: MainPresenter by providePresenter()
    private val buttonStart: Button by bind(R.id.button_start)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart.setOnClickListener {
           presenter.onButtonStartClicked()
        }

    }




}