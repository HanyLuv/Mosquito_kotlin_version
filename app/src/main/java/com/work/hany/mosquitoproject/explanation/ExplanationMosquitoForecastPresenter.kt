package com.work.hany.mosquitoproject.explanation

import android.util.Log
import android.view.View
import com.work.hany.mosquitoproject.R
import kotlinx.android.synthetic.main.fragment_explanation.view.*

/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastPresenter(var view: ExplanationMosquitoForecastContract.View) : ExplanationMosquitoForecastContract.Presenter {

    init {
        view.presenter = this
    }

    override fun onTabbBarMenuTapped(tabbarItem: View) {
        when (tabbarItem.id) {
            R.id.behaviortab ->view.showBehaviorTab()
            R.id.videotab -> view.showVideoTab()
            R.id.situationtab -> view.showSituationTab()
        }
    }


}
