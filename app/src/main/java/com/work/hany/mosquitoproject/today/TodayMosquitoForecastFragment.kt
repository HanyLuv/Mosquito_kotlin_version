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

//        step.activeBehaviorItems.forEach { stringBuilder.append("- ").append(it).append("\n")  }
//
//        step.defensiveBehaviorItems.forEachIndexed { index, stringBehavior ->
//            stringBuilder.append("- ").append(stringBehavior)
//            if( index != step.publicBehaviorItems.lastIndex ) { stringBuilder.append("\n")}
//        }

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

//        var graphDateTextViewHeight = 130f.dpToPx()
        var graphPointViewHeight = 10f.dpToPx()
//        var graphHeight =  parentLayout.height

//        mosquitoes.forEach { date, value ->
//            var result = 0 * graphHeight / 1000
//            android.util.Log.e("HANY [MainActivity] ", "DATE : $date : 그래프최대 높이 " + graphHeight + " 오리지날값 " + value + " 그 값에 대한 비율: " + result)
//
//            var graphView = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false)
//            var graphViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
//            graphView.layoutParams = graphViewParams
//            var graphPointTextView: TextView = graphView.findViewById(R.id.graph_point_value_text_view)
//
//            var graphPointView: View = graphView.findViewById(R.id.graph_point_view)
//            var graphDateTextView: TextView = graphView.findViewById(R.id.date_text_view)
//
//            graphDateTextView.text = date.dateFormatMMDD()
//            var pointerY = (graphHeight - result ) - ( graphPointViewHeight / 2) //포인터 중앙에 위치하게 하려고
//            graphPointView.y = graphHeight /2f
//            android.util.Log.e("HANY [MainActivity] ", "pointerY : "+pointerY+ " / graphPointViewHeight "+graphPointViewHeight)
//
//            //TODO 그래프 위치에 따라 텍스트 위치 변경해줘야한다. 그래프가 낮으면 안보이기 때문이다 ㅇㅁㅇ
////            graphPointTextView.text = StringBuilder().append(value).toString()
////            graphPointTextView.translationY =  graphPointView.translationY - dateTextViewHeight
//            graphParentLayout.addView(graphView)
//
//        }

        var graphView: RelativeLayout = LayoutInflater.from(context).inflate(R.layout.view_graph, null, false) as RelativeLayout
        var graphViewParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        graphView.layoutParams = graphViewParams

        var graphHeight =  parentLayout.height
        var graphPointView = LayoutInflater.from(context).inflate(R.layout.view_graph_point, null, false)
        graphPointView.y =(graphHeight / 2f)
        graphView.addView(graphPointView)


        parentLayout.addView(graphView)
        parentLayout.layoutParams
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