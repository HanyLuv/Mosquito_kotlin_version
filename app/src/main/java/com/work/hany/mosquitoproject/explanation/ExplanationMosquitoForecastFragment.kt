package com.work.hany.mosquitoproject.explanation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.explanation.detail.ExplanationMosquitoForecastDetailActivity

/**
 * Created by hany on 2018. 2. 25..
 *
 * 모기예보제란 fragment
 */
class ExplanationMosquitoForecastFragment: Fragment(), ExplanationMosquitoForecastContract.View {
    override lateinit var presenter: ExplanationMosquitoForecastContract.Presenter


    companion object {
        fun newInstance() = ExplanationMosquitoForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_explanation,container,false)


        root.findViewById<Button>(R.id.firstButton).setOnClickListener {
            presenter.onMenuButtonTapped()

//            showExplanationDetail()
            Toast.makeText(context,"hi!!!",Toast.LENGTH_SHORT).show()
        }

        return root
    }


    override fun showExplanationDetail() {
        var intent = Intent(context,ExplanationMosquitoForecastDetailActivity::class.java)
        startActivity(intent)
    }

}
