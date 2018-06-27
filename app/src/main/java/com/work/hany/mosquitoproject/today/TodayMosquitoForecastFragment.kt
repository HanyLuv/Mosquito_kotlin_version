package com.work.hany.mosquitoproject.today

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.data.Step
import com.work.hany.mosquitoproject.http.Mosquito
import com.work.hany.mosquitoproject.util.createStringLikeList
import com.work.hany.mosquitoproject.util.dateFormatKorea
import com.work.hany.mosquitoproject.util.dateFormatMMDD
import com.work.hany.mosquitoproject.util.dpToPx
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by hany on 2018. 2. 25..
 * 오늘의모기예보
 */
class TodayMosquitoForecastFragment : Fragment(), TodayMosquitoForecastContract.View, ViewTreeObserver.OnGlobalLayoutListener {
    override lateinit var presenter: TodayMosquitoForecastContract.Presenter
    private lateinit var graphParentLayout: LinearLayout
    private lateinit var stageInformationLayout: ViewGroup
    private lateinit var progressbar: ProgressBar

    /*TODO 프로그래스바 처리하기. 타임아웃 났을때 어떻게하지? */
    // TODO 프로그래스바 처리하기. 요청이 여러개인데 어떻게 프로그레스바를 처리해야할까?
    // TODO 각 화면마다 프로그래스바를 두고 화면을 보이지않게 했다 보이도록 하는게 맞는거 같다... 일단은 ?

    override fun createMosquitoFailedLayout(msg: String) {
        progressbar.visibility = View.GONE

        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()

    }

    override fun createMosquitoStageLayout(todayMosquito: Mosquito, step: Step) {
        progressbar.visibility = View.GONE

        var stringBuilder = StringBuilder()

        stageInformationLayout.findViewById<TextView>(R.id.personal_behavior_information_text_view).text = stringBuilder.toString()
        stageInformationLayout.findViewById<TextView>(R.id.today_date_text_view).text = todayMosquito.mosquitoDate.dateFormatKorea()

        var behaviorInfoList = StringBuffer().append(step.activeBehaviorItems.createStringLikeList())
                .append("\n").append(step.publicBehaviorItems.createStringLikeList()).toString()

        stageInformationLayout.findViewById<TextView>(R.id.personal_behavior_information_text_view).text = behaviorInfoList
        stageInformationLayout.findViewById<TextView>(R.id.public_behavior_information_text_view).text = step.publicBehaviorItems.createStringLikeList()

        val mosquitoValue =   StringBuilder().append(todayMosquito.mosquitoValue.toString()).append(" " +
                "(").append(DataManager.instance.mosquitoStage(todayMosquito.mosquitoValue)).append(")").toString()
        stageInformationLayout.findViewById<TextView>(R.id.today_mosquito_value_text_view).text = mosquitoValue

    }


    override fun createMosquitoChart(mosquitoes: Map<String, Float>) {
        progressbar.visibility = View.GONE

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

            if (result < 500) {
                graphPointTextView.y = graphPointView.y -  graphPointViewHeight - margin

            } else {
                graphPointTextView.y = graphPointView.y + graphPointViewHeight + margin

            }

            graphPointTextView.text = value.toString()

            graphParentLayout.addView(graphView)

        }

    }

    override fun createMosquitoTodayGraphLayout(todayMosquito: Mosquito) {
        progressbar.visibility = View.GONE

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
        progressbar = root.findViewById(R.id.progressbar)

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
        presenter.createMosquitoTodayGraphAndChartLayout()
        presenter.createMosquitoChart()
    }

    companion object {
        fun newInstance() = TodayMosquitoForecastFragment()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }


}