package com.work.hany.mosquitoproject.explanation

import android.util.Pair
import android.view.View
import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Situation
import com.work.hany.mosquitoproject.data.Step

/**
 * Created by hany on 2018. 3. 1..
 */
interface ExplanationMosquitoForecastContract {
    interface View: BaseView<Presenter> {
        fun showExplanationDetail(listItemView: android.view.View, behavior: Behavior)
        fun showVideoTab()
        fun showSituationTab(items: List<Situation>)
        fun showBehaviorTab(items: List<Behavior>)
    }

    interface Presenter: BasePresenter {
        /** tabbar clicked */
        fun onTabbBarMenuTapped(tabBarItemView :android.view.View)
        fun onListItemTapped(listItemView :android.view.View, behavior: Behavior)
    }
}