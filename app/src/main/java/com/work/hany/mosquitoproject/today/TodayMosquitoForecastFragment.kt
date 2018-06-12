package com.work.hany.mosquitoproject.today

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.util.toDp

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment : Fragment(), TodayMosquitoForecastContract.View, ViewTreeObserver.OnGlobalLayoutListener {
    override fun createMosquitoStageLayout(behavior: Behavior) {
        behavior.steps
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        var graphParentLayout = LinearLayout(context)
        graphParentLayout.orientation = LinearLayout.HORIZONTAL
        graphParentLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        var dateTextViewHeight = 20.toDp()
        var graphHeight = parentLayout.height - dateTextViewHeight

        mosquitoes.forEach { date, value ->

            var result = value * graphHeight / 1000
            Log.e("HANY [MainActivity] ", "DATE : $date : 그래프최대 높이 " + graphHeight + " 오리지날값 " + value + " 그 값에 대한 비율: " + result)

            var graphView = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false)
            var graphViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
//            graphViewParams.leftMargin = 3
//            graphViewParams.rightMargin = 3
            graphView.layoutParams = graphViewParams

            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
            var graphPointTextView: TextView = graphView.findViewById(R.id.graph_point_value_text_view)
            graphPointView.translationY = graphHeight - result
            graphPointTextView.translationY =  graphPointView.translationY + dateTextViewHeight
            graphPointTextView.text = StringBuilder().append(value).toString()

            var graphPointTextVieWParams = graphPointTextView.layoutParams as RelativeLayout.LayoutParams
            graphPointTextVieWParams.addRule(RelativeLayout.BELOW, R.id.graph_point_view)

            graphParentLayout.addView(graphView)

        }

        parentLayout.addView(graphParentLayout)
    }

    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var parentLayout: FrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today, container, false)

        parentLayout = root.findViewById(R.id.parent_layout)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentLayout.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            parentLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        } else {
            parentLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
        presenter.createMosquitoChartLayout()
        presenter.createMosquitoStageLayout()
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }


}