package com.work.hany.mosquitoproject.explanation

import android.content.Context
import com.work.hany.mosquitoproject.BasePresenter
import com.work.hany.mosquitoproject.BaseView

/**
 * Created by hany on 2018. 3. 1..
 */
interface ExplanationMosquitoForecastContract {
    interface View: BaseView<Presenter> {
        fun showExplanationDetail()

    }

    interface Presenter: BasePresenter {
        fun onMenuButtonTapped()
    }
}