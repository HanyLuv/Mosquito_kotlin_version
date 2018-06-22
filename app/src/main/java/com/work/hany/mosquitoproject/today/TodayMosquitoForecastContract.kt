package com.work.hany.mosquitoproject.today

import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.http.Mosquito

/**
 * Created by hany on 2018. 2. 25..
 *
 * View와 presenter간의 특별한 계약 (Contract) ... (?)
 */
interface TodayMosquitoForecastContract {
    interface View: BaseView<Presenter> {
        fun createMosquitoChart(mosquitoes: Map<String, Float>)
        fun createMosquitoStageLayout(todayMosquito: Mosquito, step: Step)
        fun createMosquitoTodayGraphLayout(todayMosquito: Mosquito)


    }

    interface Presenter: BasePresenter {
        fun createMosquitoChartLayout() //모기 그래프를 만든다.
        fun createMosquitoStageLayout() //모기 상태에 따른 안내 레이아웃을 만든다.
        fun createMosquitoTodayGraphLayout()
    }
}