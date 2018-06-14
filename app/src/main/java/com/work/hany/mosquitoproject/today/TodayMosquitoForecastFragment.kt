package com.work.hany.mosquitoproject.today

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.util.dateFormatMMDD
import com.work.hany.mosquitoproject.util.toDp

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment : Fragment(), TodayMosquitoForecastContract.View, ViewTreeObserver.OnGlobalLayoutListener {
    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var parentLayout: FrameLayout
    private lateinit var stageInformationLayout: ViewGroup

    override fun createMosquitoStageLayout(todayDate: String, step: Step) {
        var stringBuilder = StringBuilder()
        /** /n 달아주는 함수를 익스텐션으로 빼자.. */
        step.publicBehaviorItems.forEachIndexed { index, stringBehavior ->
            stringBuilder.append("- ").append(stringBehavior)
            if( index != step.publicBehaviorItems.lastIndex ) { stringBuilder.append("\n")}
        }

        stageInformationLayout.findViewById<TextView>(R.id.public_behavior_information_text_view).text = stringBuilder.toString()

        stringBuilder.setLength(0)

        step.activeBehaviorItems.forEach {
            stringBuilder.append("- ").append(it).append("\n")
        }

        step.defensiveBehaviorItems.forEachIndexed { index, stringBehavior ->
            stringBuilder.append("- ").append(stringBehavior)
            if( index != step.publicBehaviorItems.lastIndex ) { stringBuilder.append("\n")}
        }

        stageInformationLayout.findViewById<TextView>(R.id.personal_behavior_information_text_view).text = stringBuilder.toString()

        stageInformationLayout.findViewById<TextView>(R.id.today_date_text_view).text = todayDate

    }


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        var graphParentLayout = LinearLayout(context)
        graphParentLayout.orientation = LinearLayout.HORIZONTAL
        graphParentLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        var dateTextViewHeight = 15.toDp()
        var graphHeight = parentLayout.height - dateTextViewHeight

        mosquitoes.forEach { date, value ->
            var result = value * graphHeight / 1000
//            Log.e("HANY [MainActivity] ", "DATE : $date : 그래프최대 높이 " + graphHeight + " 오리지날값 " + value + " 그 값에 대한 비율: " + result)

            var graphView = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false)
            var graphViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
//            graphViewParams.leftMargin = 3
//            graphViewParams.rightMargin = 3
            graphView.layoutParams = graphViewParams

            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
            var graphPointTextView: TextView = graphView.findViewById(R.id.graph_point_value_text_view)
            var graphDateTextView: TextView = graphView.findViewById(R.id.date_text_view)

            graphDateTextView.text = date.dateFormatMMDD()
            graphPointView.translationY = graphHeight - result
            graphPointTextView.translationY =  graphPointView.translationY + dateTextViewHeight
            graphPointTextView.text = StringBuilder().append(value).toString()

            var graphPointTextVieWParams = graphPointTextView.layoutParams as RelativeLayout.LayoutParams
            graphPointTextVieWParams.addRule(RelativeLayout.BELOW, R.id.graph_point_view)

            graphParentLayout.addView(graphView)

        }

        parentLayout.addView(graphParentLayout)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today, container, false)
        parentLayout = root.findViewById(R.id.parent_layout)
        stageInformationLayout = root.findViewById(R.id.view_mosquito_stage_layout)
        presenter.createMosquitoStageLayout()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stageInformationLayout.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stageInformationLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        } else {
            stageInformationLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
        presenter.createMosquitoChartLayout()
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }


}