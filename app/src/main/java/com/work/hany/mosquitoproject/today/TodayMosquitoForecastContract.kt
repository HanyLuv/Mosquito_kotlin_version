package com.work.hany.mosquitoproject.today

import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseRequester
import com.work.hany.mosquitoproject.BaseView
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.http.Mosquito

/**
 * Created by hany on 2018. 2. 25..
 *
 */
interface TodayMosquitoForecastContract {
    interface View: BaseView<Presenter> {
        fun createMosquitoChart(mosquitoes: Map<String, Float>)
        fun createMosquitoStageLayout(todayMosquito: Mosquito, step: Step)
        fun createMosquitoTodayGraphLayout(todayMosquito: Mosquito)
        fun createMosquitoFailedLayout(msg: String) //요청이 실패한것에대하여 화면을 만든다.


    }

    interface Presenter: BasePresenter {
        fun createMosquitoStageLayout() //모기 상태에 따른 안내 레이아웃을 만든다.
        fun createMosquitoTodayGraphAndChartLayout()
        fun createMosquitoChart()
        fun onDetach()
    }

    interface Requester : BaseRequester { // 각 프레젠터마다 요청하는게 다를거라서 각자 선언
        fun requestMosquitoWeekend(responseListener: BaseRequester.OnRequesterResponseListener<Map<String,Float>>)
        fun requestMosquitoToday(responseListener: BaseRequester.OnRequesterResponseListener<Map<String,Float>>)
    }
}