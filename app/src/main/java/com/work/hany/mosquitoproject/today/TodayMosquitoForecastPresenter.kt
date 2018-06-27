package com.work.hany.mosquitoproject.today

import android.app.Activity
import android.support.v4.app.Fragment
import com.work.hany.mosquitoproject.BaseRequester
import com.work.hany.mosquitoproject.MosquitoApplication
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.http.Mosquito
import com.work.hany.mosquitoproject.util.todayDate
import java.util.*

/**
 * Created by hany on 2018. 2. 26..*/
/** 뷰에게 무언가를 표시하는 방법을 지시하는 대신, 표시할 내용만 전달. */

class TodayMosquitoForecastPresenter(var view: TodayMosquitoForecastContract.View, var requester: TodayMosquitoForecastRequester) : TodayMosquitoForecastContract.Presenter {


    private var behaviors: List<Behavior>
    private var todayDate: String

    init {
        view.presenter = this
        behaviors = DataManager.instance.createBehaviorItems(MosquitoApplication.context())
        todayDate = Date().todayDate()

    }


    override fun onDetach(){
        requester.allRequestCallCancel()
    }

    override fun createMosquitoStageLayout() {
        requester.requestMosquitoWeekend(object : BaseRequester.OnRequesterResponseListener<Map<String, Float>> {
            override fun failed(errorMsg: String) {
                view.createMosquitoFailedLayout(errorMsg)
            }

            override fun received(result: Map<String, Float>) {
                    var mosquitoes = result
                    mosquitoes[todayDate]?.let { mosquitoValue ->
                        var stage = DataManager.instance.mosquitoStage(mosquitoValue)
                        var stageIndex = DataManager.instance.mosquitoStageIndex(mosquitoValue)

                        for (behavior in behaviors) {
                            if (behavior.levelTitle == stage) {
                                behavior.steps[stageIndex].let {
                                    view.createMosquitoStageLayout(Mosquito(todayDate, mosquitoValue), it)
                                }

                                break
                            }
                        }

                    }
                }
        })

    }

    //TODO createMosquitoStageLayout 와 같은 데이터 량을 가져온다 ㅠ 중복요청이니 어케해결을 해봅시다.
    override fun createMosquitoChart() {
        requester.requestMosquitoWeekend(object : BaseRequester.OnRequesterResponseListener<Map<String, Float>> {
            override fun failed(errorMsg: String) {
                view.createMosquitoFailedLayout(errorMsg)
            }

            override fun received(result: Map<String, Float>) {
//              TODO 정렬 안되는 문제
                view.createMosquitoChart(result)
            }
        })
    }

    override fun createMosquitoTodayGraphAndChartLayout() {
        requester.requestMosquitoToday(object : BaseRequester.OnRequesterResponseListener<Map<String, Float>> {
            override fun failed(errorMsg: String) {
                view.createMosquitoFailedLayout(errorMsg)
            }

            override fun received(result: Map<String, Float>) {
                var mosquitoes = result
                mosquitoes[todayDate]?.let {
                    view.createMosquitoTodayGraphLayout(Mosquito(todayDate, it))
                }
            }
        })

    }

}