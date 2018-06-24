package com.work.hany.mosquitoproject.explanation.detail

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Common
import com.work.hany.mosquitoproject.data.TabMenu

//TODO 네이밍 다시하자... 모기 예보제 보기예보발생 단계에서 상세화면 내 페이저의 아이템이다.
class ExplanationMosquitoForecastDetailTabFragment: Fragment() {

    private lateinit var explanationRecyclerView: RecyclerView

    companion object {
        fun newInstance(behavior: Behavior,tabMenu: TabMenu): ExplanationMosquitoForecastDetailTabFragment {
            val fragment = ExplanationMosquitoForecastDetailTabFragment()
            val args = Bundle()
            args.putParcelable(Common.EXTRA_KEY, behavior as Parcelable)
            args.putInt(Common.EXTRA_KEY_TABMENU, tabMenu.code)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.pager_row_explanation, container, false)

        var behavior = arguments.get(Common.EXTRA_KEY) as Behavior
        var tabCode = arguments.getInt(Common.EXTRA_KEY_TABMENU)

        var tabMenu = if (tabCode != TabMenu.PUBLIC.code) TabMenu.PERSONAL else TabMenu.PUBLIC
        //        ?  TabMenu.PERSONAL : TabMenu.PUBLIC

        Log.d("HANY_TAG","behavior : "+behavior.toString())

        explanationRecyclerView = root.findViewById(R.id.explanation_step_info_recycler_view)
        explanationRecyclerView.layoutManager = LinearLayoutManager(context)
        explanationRecyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder> (){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                var inflater = LayoutInflater.from(parent.context)
                return ViewHolder(inflater.inflate(R.layout.row_step_explanation,null))
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                var stepList = arrayListOf<String>()

                if (tabMenu == (TabMenu.PERSONAL)) {
                    stepList.addAll(behavior.steps[position].activeBehaviorItems)
                    stepList.addAll(behavior.steps[position].defensiveBehaviorItems)
                } else {
                    stepList.addAll(behavior.steps[position].publicBehaviorItems)
                }

                (holder as ViewHolder).bind(behavior.steps[position].title, stepList)

            }

            override fun getItemCount(): Int {
                return 3
            }

            private inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

                fun bind(stepTitle: String, stepInfoList: ArrayList<String> ){

                    var infoList = ""
                    stepInfoList.forEach {
                        infoList = StringBuffer().append(infoList).append(it).append("\n").toString()
                    }


                    itemView.findViewById<TextView>(R.id.step_text_view).text = stepTitle
                    itemView.findViewById<TextView>(R.id.step_info_text_view).text = infoList




                }

            }
        }

        return root
    }

}
