package com.work.hany.mosquitoproject.explanation

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.data.Situation

/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastPresenter(var view: ExplanationMosquitoForecastContract.View) : ExplanationMosquitoForecastContract.Presenter {

    private var dataManager = DataManager()

    init {
        view.presenter = this
    }

    override fun onTabbBarMenuTapped(tabbarItem: View) {
        when (tabbarItem.id) {
            R.id.behaviortab -> view.showBehaviorTab(dataManager.createBehaviorItems(tabbarItem.context))
            R.id.videotab -> view.showVideoTab()
            R.id.situationtab -> view.showSituationTab(dataManager.createSituationItems(tabbarItem.context))
        }
    }


}
