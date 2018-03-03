package com.work.hany.mosquitoproject.explanation

import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Situation

/**
 * Created by hany on 2018. 3. 1..
 */
interface ExplanationMosquitoForecastContract {
    interface View: BaseView<Presenter> {
//        fun showExplanationDetail()
        fun showVideoTab()
        fun showSituationTab(items: List<Situation>)
        fun showBehaviorTab(items: List<Behavior>)
    }

    interface Presenter: BasePresenter {
        /** tabbar clicked */
        fun onTabbBarMenuTapped(tabbarItem :android.view.View)
    }
}