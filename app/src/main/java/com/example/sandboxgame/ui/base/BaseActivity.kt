package com.example.sandboxgame.ui.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.example.sandboxgame.R
import com.omega_r.base.components.OmegaActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

abstract class BaseActivity : OmegaActivity, BaseView {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract override val presenter: OmegaPresenter<out BaseView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setBackgroundDrawable(ColorDrawable(R.attr.activityBackground))
    }
}