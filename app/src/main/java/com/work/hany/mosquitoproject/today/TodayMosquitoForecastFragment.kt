package com.work.hany.mosquitoproject.today

import android.content.res.Resources
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.mosquitoproject.R

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment: Fragment(),TodayMosquitoForecastContract.View {


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        var deviceHeight =   Resources.getSystem().displayMetrics.heightPixels
        mosquitoes.forEach { date, value ->
            var baseValue = 1000.0f
            var result =  ( baseValue / value ) * 100
            var proportion = (deviceHeight / result) * 100
            Log.e("HANY [MainActivity] ", "DATE : $date : 화면 "+deviceHeight+" 비율: " + proportion)


            var graphView = View.inflate(context, R.layout.view_graph,null)
            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
            graphPointView.translationY = proportion
            graphView.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT)
            graphParentLayout.addView(graphView)

        }
    }

    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var graphParentLayout: ViewGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today,container,false)

        graphParentLayout = root.findViewById(R.id.graph_parent_layout)
        presenter.createMosquitoChart()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }



}