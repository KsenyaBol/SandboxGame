package com.example.sandboxgame.ui.continueGame

import com.example.sandboxgame.ui.base.BaseView

import com.omegar.mvp.viewstate.strategy.StateStrategyType
import com.omegar.mvp.viewstate.strategy.StrategyType

interface ContinueView: BaseView {

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun showGameInfo(command: ContinuePresenter.ContinueCommand)
}