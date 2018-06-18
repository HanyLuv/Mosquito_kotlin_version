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
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.http.Mosquito
import com.work.hany.mosquitoproject.util.dateFormatKorea
import com.work.hany.mosquitoproject.util.dateFormatMMDD
import com.work.hany.mosquitoproject.util.toDp
import com.work.hany.mosquitoproject.util.toPx

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment : Fragment(), TodayMosquitoForecastContract.View, ViewTreeObserver.OnGlobalLayoutListener {
    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var parentLayout: FrameLayout
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
        stageInformationLayout.findViewById<TextView>(R.id.today_mosquito_value_text_view).text = todayMosquito.mosquitoValue.toString()

        // TODO 애니메이션 넣어주어 자연스럽게 그리도록하자
        var stateBarBackgroundWidth = stageInformationLayout.findViewById<FrameLayout>(R.id.today_mosquito_value_bar_bg_view).width
        var result = todayMosquito.mosquitoValue * stateBarBackgroundWidth / 1000 //정밀하게 그릴수있도록 수정 작업 필요함.. 뷰를 하나 커스텀해서 만들어야할듯.
        var stateBarView = stageInformationLayout.findViewById<View>(R.id.today_mosquito_value_bar_view)
        var stateBarViewParams = stageInformationLayout.findViewById<FrameLayout>(R.id.today_mosquito_value_bar_view).layoutParams
        stateBarView.layoutParams = RelativeLayout.LayoutParams(Math.floor(result.toDouble()).toInt() ,stateBarViewParams.height)

    }


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        var graphParentLayout = LinearLayout(context)
        graphParentLayout.orientation = LinearLayout.HORIZONTAL
        graphParentLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

//        var dateTextViewHeight = 20f.toPx()
        var graphPointViewHeight = 12f.toPx() // 12dp
        var graphPointTextHeight = 10f.toPx() // 10dp
//        var graphHeight = parentLayout.height - dateTextViewHeight
        var graphHeight = parentLayout.height

//        Log.e("HANY_TAG","graphHeight : "+graphHeight)

        mosquitoes.forEach { (date, value) ->
//            var result = (value * graphHeight) / 1000
            var result = (10.0f * graphHeight) / 1000
            Log.e("HANY [MainActivity] ", "DATE : $date : 그래프최대 높이 " + graphHeight + " 오리지날값 " + value + " 그 값에 대한 비율: " + result)

            var graphView = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false)
            var graphViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
//            graphViewParams.leftMargin = 3
//            graphViewParams.rightMargin = 3
            graphView.layoutParams = graphViewParams

            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
            var graphPointTextView: TextView = graphView.findViewById(R.id.graph_point_value_text_view)
            var graphDateTextView: TextView = graphView.findViewById(R.id.date_text_view)

            graphDateTextView.text = date.dateFormatMMDD()


            if (result > 0.0f) {
//                graphPointView.translationY = (graphHeight - result) - (graphPointViewHeight + graphPointTextHeight) //600이어야 보이네.
                graphPointView.translationY = 800f
                Log.e("HANY [MainActivity] ", "graphPointView.translationY  : "+   graphPointView.translationY )
                Log.e("HANY [MainActivity] ", "translationY "+  (graphHeight - result))
                Log.e("HANY [MainActivity] ", "translationY original "+  (result))
                Log.e("HANY [MainActivity] ", "translationY original to px "+  (result.toPx()))
                Log.e("HANY [MainActivity] ", "translationY original to dp "+  (result.toDp()))
                //TODO 그래프 위치에 따라 텍스트 위치 변경해줘야한다. 그래프가 낮으면 안보이기 때문이다 ㅇㅁㅇ
                graphPointTextView.text = StringBuilder().append(value).toString()
                graphPointTextView.translationY =  graphPointView.translationY - 10f.toDp()
            } else {
                graphPointView.visibility = View.GONE
                graphPointTextView.visibility = View.GONE
            }

            graphParentLayout.addView(graphView)

        }

        parentLayout.addView(graphParentLayout)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.framgnet_today, container, false)
        parentLayout = root.findViewById(R.id.parent_layout)
        stageInformationLayout = root.findViewById(R.id.view_mosquito_stage_layout)

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
        presenter.createMosquitoStageLayout()
        presenter.createMosquitoChartLayout()
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }


}