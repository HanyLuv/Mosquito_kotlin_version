package com.work.hany.mosquitoproject.explanation

import android.util.Log

/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastPresenter(var view: ExplanationMosquitoForecastContract.View): ExplanationMosquitoForecastContract.Presenter {


    init {
        view.presenter = this
    }

    override fun onMenuButtonTapped() {

        Log.d("hany_tag_hany","onMenuButtonTapped !!!!!!!!!")
        view.showExplanationDetail()

    }


}