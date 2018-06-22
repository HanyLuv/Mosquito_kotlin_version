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
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.http.Mosquito
import com.work.hany.mosquitoproject.util.dateFormatKorea
import com.work.hany.mosquitoproject.util.dateFormatMMDD
import com.work.hany.mosquitoproject.util.dpToPx

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment : Fragment(), TodayMosquitoForecastContract.View, ViewTreeObserver.OnGlobalLayoutListener {
    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var graphParentLayout: LinearLayout
    private lateinit var stageInformationLayout: ViewGroup

    override fun createMosquitoStageLayout(todayMosquito: Mosquito, step: Step) {
        var stringBuilder = StringBuilder()
        /** /n 달아주는 함수를 익스텐션으로 빼자.. */
        step.publicBehaviorItems.forEachIndexed { index, stringBehavior ->
            stringBuilder.append("- ").append(stringBehavior)
            if( index != step.publicBehaviorItems.lastIndex ) { stringBuilder.append("\n")}
        }

        stageInformationLayout.findViewById<TextView>(R.id.public_behavior_information_text_view).text = stringBuilder.toString()

        stringBuilder.setLength(0)

        step.activeBehaviorItems.forEach { stringBuilder.append("- ").append(it).append("\n")  }

        step.defensiveBehaviorItems.forEachIndexed { index, stringBehavior ->
            stringBuilder.append("- ").append(stringBehavior)
            if( index != step.publicBehaviorItems.lastIndex ) { stringBuilder.append("\n")}
        }

        stageInformationLayout.findViewById<TextView>(R.id.personal_behavior_information_text_view).text = stringBuilder.toString()
        stageInformationLayout.findViewById<TextView>(R.id.today_date_text_view).text = todayMosquito.mosquitoDate.dateFormatKorea()
        stageInformationLayout.findViewById<TextView>(R.id.today_mosquito_value_text_view).text =
                StringBuilder().append(todayMosquito.mosquitoValue.toString()).append("(").append(DataManager.instance.mosquitoStage(todayMosquito.mosquitoValue)).append(")").toString()

    }


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {

        var graphDateTextViewHeight = 20f.dpToPx()
        var graphPointViewHeight = 10f.dpToPx() //text size 10sp
        var graphHeight =  graphParentLayout.height - graphDateTextViewHeight

        mosquitoes.forEach { date, value ->
            var result = value * graphHeight / 1000

            var graphView = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false)
            var graphViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            graphView.layoutParams = graphViewParams


            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
            var graphDateTextView: TextView = graphView.findViewById(R.id.date_text_view)
            graphDateTextView.text = date.dateFormatMMDD()

            var pointerY = (graphHeight - result) - (graphPointViewHeight / 2)/*포인터 중앙에 위치*/
            graphPointView.y = pointerY

            var margin = 1f.dpToPx()
            var graphPointTextView: TextView = graphView.findViewById(R.id.graph_point_value_text_view)
            if (result > 500) {
                graphPointTextView.y = graphPointView.y -  graphPointViewHeight - margin

            } else {
                graphPointTextView.y = graphPointView.y + graphPointViewHeight + margin
            }
            graphPointTextView.text = value.toString()

            graphParentLayout.addView(graphView)

        }

    }

    override fun createMosquitoTodayGraphLayout(todayMosquito: Mosquito) {
        // TODO 애니메이션 넣어주어 자연스럽게 그리도록하자
        var stateBarBackgroundWidth = stageInformationLayout.findViewById<FrameLayout>(R.id.today_mosquito_value_bar_bg_view).width
        var result = todayMosquito.mosquitoValue * stateBarBackgroundWidth / 1000 //정밀하게 그릴수있도록 수정 작업 필요함.. 뷰를 하나 커스텀해서 만들어야할듯.

        var stateBarView = stageInformationLayout.findViewById<View>(R.id.today_mosquito_value_bar_view)
        var stateBarViewHeight = 35f.dpToPx()
        stateBarView.layoutParams = RelativeLayout.LayoutParams(Math.floor(result.toDouble()).toInt() ,stateBarViewHeight.toInt())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today, container, false)
        graphParentLayout = root.findViewById(R.id.graph_parent_layout)
        stageInformationLayout = root.findViewById(R.id.view_mosquito_stage_layout)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stageInformationLayout.viewTreeObserver.addOnGlobalLayoutListener(this)
        presenter.createMosquitoStageLayout()

    }

    override fun onGlobalLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stageInformationLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        } else {
            stageInformationLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
        /** 행동 수칙 마다 결과값 길이가 다르다보니 행동수칙먼저 그리고 나서 모기 차트를 그려야한다.*/
        presenter.createMosquitoChartLayout()
        presenter.createMosquitoTodayGraphLayout()
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }


}