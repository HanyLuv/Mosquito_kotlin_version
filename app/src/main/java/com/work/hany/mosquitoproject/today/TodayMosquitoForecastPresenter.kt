package com.work.hany.mosquitoproject.today

import android.content.Context
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.util.dateFormatKorea
import com.work.hany.mosquitoproject.util.todayDate
import java.util.*

/**
 * Created by hany on 2018. 2. 26..
 */
/** 뷰에게 무언가를 표시하는 방법을 지시하는 대신, 표시할 내용만 전달합니다. */

class TodayMosquitoForecastPresenter(context: Context, var view: TodayMosquitoForecastContract.View, private var mosquitoes: Map<String, Float>): TodayMosquitoForecastContract.Presenter {
    private var behaviors: List<Behavior>
    private var todayDate: String

    init {
        view.presenter = this
        behaviors = DataManager.instance.createBehaviorItems(context)
        todayDate = Date().todayDate()
    }


    override fun createMosquitoStageLayout() {
        mosquitoes[todayDate]?.let {
            var stage = DataManager.instance.mosquitoStage(it)
            var stageIndex = DataManager.instance.mosquitoStageIndex(it)

            for (behavior in behaviors) {
                if (behavior.levelTitle == stage) {
                    behavior.steps[stageIndex].let {
                        view.createMosquitoStageLayout(todayDate.dateFormatKorea(),it)
                    }

                    break
                }
            }

        }

    }

    override fun createMosquitoChartLayout() {
        view.createMosquitoChart(mosquitoes)
    }


}