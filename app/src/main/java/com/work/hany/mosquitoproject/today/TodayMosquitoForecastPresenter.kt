package com.work.hany.mosquitoproject.today

/**
 * Created by hany on 2018. 2. 26..
 */
class TodayMosquitoForecastPresenter(var todayMosquitoForecastView: TodayMosquitoForecastContract.View,
                                     private val mosquitoes: Map<String, Float>): TodayMosquitoForecastContract.Presenter {

    init {
        createMosquitoChart()
    }

    override fun createMosquitoChart() {
        todayMosquitoForecastView.createMosquitoChart(mosquitoes)
    }

    /** 뷰에게 무언가를 표시하는 방법을 지시하는 대신, 표시할 내용만 전달합니다. */





}