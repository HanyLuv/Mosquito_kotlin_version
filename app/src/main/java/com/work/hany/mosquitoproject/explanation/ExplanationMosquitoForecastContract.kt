package com.work.hany.mosquitoproject.explanation

import android.view.View
import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView
import com.work.hany.mosquitoproject.data.Behavior

/**
 * Created by hany on 2018. 3. 1..
 */
interface ExplanationMosquitoForecastContract {
    interface View: BaseView<Presenter> {
//        fun showExplanationDetail()
        fun showVideoTab()
        fun showSituationTab()
        fun showBehaviorTab(items: List<Behavior>)
    }

    interface Presenter: BasePresenter {
        /** tabbar clicked */
        fun onTabbBarMenuTapped(tabbarItem :android.view.View)
    }
}