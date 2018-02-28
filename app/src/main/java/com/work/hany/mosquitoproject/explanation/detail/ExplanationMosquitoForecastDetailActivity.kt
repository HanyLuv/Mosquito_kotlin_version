package com.work.hany.mosquitoproject.explanation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_explanation_detail.*

/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastDetailActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation_detail)

        replaceFragmentInActivity(ExplanationMosquitoForecastDetailFragment.newInstance(),R.id.detailFragmentContainer)
    }
}