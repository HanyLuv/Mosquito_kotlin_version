package com.work.hany.mosquitoproject.today

import com.work.hany.mosquitoproject.data.DataManager

/**
 * Created by hany on 2018. 2. 26..
 */
class TodayMosquitoForecastPresenter(var view: TodayMosquitoForecastContract.View,
                                     private val mosquitoes: Map<String, Float>): TodayMosquitoForecastContract.Presenter {
    override fun createMosquitoStageLayout() {
        DataManager.instance.createBehaviorItems(view)
        view.createMosquitoStageLayout()
    }

    init {
        view.presenter = this
    }

    override fun createMosquitoChartLayout() {
        view.createMosquitoChart(mosquitoes)
    }

    /** 뷰에게 무언가를 표시하는 방법을 지시하는 대신, 표시할 내용만 전달합니다. */





}