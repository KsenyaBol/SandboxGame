package com.example.sandboxgame.ui.size

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SizePresenter: OmegaPresenter<SizeView>() {
    private val spaceToJson = Json.encodeToString(Space())
    val spaceFromJson = Json.decodeFromString<Space>(spaceToJson)

    fun onButtonBigClicked() {
        GameActivity.createLauncher(50, spaceToJson).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(20, spaceToJson).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(10, spaceToJson).launch()
        exit()
    }
}