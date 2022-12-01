package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.StateStrategyType

interface GameView: BaseView {
    var size: Int
    var planet: ArrayList<Planet>

}