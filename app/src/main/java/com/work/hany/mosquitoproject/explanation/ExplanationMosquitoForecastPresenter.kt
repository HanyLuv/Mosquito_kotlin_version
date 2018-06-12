package com.work.hany.mosquitoproject.explanation

import android.app.ActivityOptions
import android.util.Pair
import android.view.View
import android.widget.ImageView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.DataManager

/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastPresenter(var view: ExplanationMosquitoForecastContract.View) : ExplanationMosquitoForecastContract.Presenter {


    init {
        view.presenter = this
    }


    override fun onListItemTapped(listItemView: View, behavior: Behavior) {
        view.showExplanationDetail(listItemView, behavior)
    }


    override fun onTabbBarMenuTapped(tabBarItemView: View) {
        when (tabBarItemView.id) {
            R.id.behaviortab -> view.showBehaviorTab(DataManager.instance.createBehaviorItems(tabBarItemView.context))
            R.id.videotab -> view.showVideoTab()
            R.id.situationtab -> view.showSituationTab(DataManager.instance.createSituationItems(tabBarItemView.context))
        }
    }


}
