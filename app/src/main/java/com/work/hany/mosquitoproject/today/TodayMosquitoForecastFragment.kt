package com.work.hany.mosquitoproject.today

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.http.MosquitoStatus
import com.work.hany.mosquitoproject.http.Requester

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment: Fragment(),TodayMosquitoForecastContract.View{
    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override lateinit var presenter: TodayMosquitoForecastContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today,container,false)
        presenter.createMosquitoChart()

        return root
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }



}