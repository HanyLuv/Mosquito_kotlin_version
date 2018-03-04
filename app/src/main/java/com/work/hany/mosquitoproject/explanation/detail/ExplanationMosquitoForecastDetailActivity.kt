package com.work.hany.mosquitoproject.explanation.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import com.work.hany.mosquitoproject.R


/**
 * Created by hany on 2018. 3. 1..
 */
class ExplanationMosquitoForecastDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_explanation_detail)
        window.enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade_in)


    }


}