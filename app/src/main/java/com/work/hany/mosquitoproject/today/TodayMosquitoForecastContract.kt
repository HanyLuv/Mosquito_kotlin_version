package com.work.hany.mosquitoproject.today

import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView

/**
 * Created by hany on 2018. 2. 25..
 *
 * View와 presenter간의 특별한 계약 (Contract) ... (?)
 */
interface TodayMosquitoForecastContract {
    interface View: BaseView<Presenter> {
        fun createMosquitoChart(mosquitoes: Map<String, Float>)

    }

    interface Presenter: BasePresenter {
        fun createMosquitoChart()
    }
}