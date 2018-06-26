package com.work.hany.mosquitoproject.explanation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Situation
import com.work.hany.mosquitoproject.explanation.detail.ExplanationMosquitoForecastDetailFragment
import com.work.hany.mosquitoproject.explanation.tabs.ExplanationRecyclerAdapter
import com.work.hany.mosquitoproject.explanation.tabs.ExplanationRecyclerAdapter.ClickListener
import com.work.hany.mosquitoproject.util.actionBarHeight
import com.work.hany.mosquitoproject.util.addBackStackFragmentInActivity

/**
 * Created by hany on 2018. 2. 25..
 *
 * 모기예보제란 fragment
 */
class ExplanationMosquitoForecastFragment : Fragment(), ExplanationMosquitoForecastContract.View {

    override lateinit var presenter: ExplanationMosquitoForecastContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var behaviorTabView: View
    private lateinit var videoTabView: View
    private lateinit var situationTabView: View
    private lateinit var tabViewList: Set<View>

    //어댑터 만들어주고 view에서 어댑터에 들어갈 데이터를 셋팅해준다
    private lateinit var adapter: ExplanationRecyclerAdapter

    companion object {
        fun newInstance() = ExplanationMosquitoForecastFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_explanation, container, false)

        var actionBarHeight = activity.actionBarHeight()
        //어댑터 반드시 수정필요한듯
        //과거의 나야. 왜 수정 필요하다고 햇니 ? ㅠㅡㅠ

        adapter = ExplanationRecyclerAdapter(object : ClickListener {
            override fun onClick(view: View, behavior: Behavior) {
                presenter.onListItemTapped(view, behavior)
            }
        })

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

//        recyclerView.addOnScrollListener(ToolbarHidingOnScrollListener(
//                actionBarHeight,
//               null,
//                root.findViewById(R.id.colored_background_view)))

        behaviorTabView = root.findViewById<View>(R.id.behaviortab).also {
            it.setOnClickListener { presenter.onTabbBarMenuTapped(it) }
        }

        videoTabView = root.findViewById<View>(R.id.videotab).also {
            it.setOnClickListener { presenter.onTabbBarMenuTapped(it) }
        }

        situationTabView = root.findViewById<View>(R.id.situationtab).also {
            it.setOnClickListener { presenter.onTabbBarMenuTapped(it) }
        }

        tabViewList = setOf(videoTabView, situationTabView, behaviorTabView)

        retainInstance = true
        return root
    }

    override fun showVideoTab() {
        tabSelected(videoTabView)

        Toast.makeText(context, "모기예보제란", Toast.LENGTH_SHORT).show()

        //비디오를 넣을까 말까 고민.
//        activity.replaceFragmentInActivity(VideoTabFragment(), R.id.main_fragment_container)
    }

    override fun showSituationTab(items: List<Situation>) {
        tabSelected(situationTabView)
        adapter.addAll(items)
//        recyclerView.scheduleLayoutAnimation()
    }


    override fun showBehaviorTab(items: List<Behavior>) {
        tabSelected(behaviorTabView)
        adapter.addAll(items)
//        recyclerView.scheduleLayoutAnimation()
    }


    private fun tabSelected(selectedTab: View) {
        tabViewList.iterator().forEach { it.isSelected = false }
        selectedTab.isSelected = true
    }



    override fun showExplanationDetail(listItemView: View, behavior: Behavior) {
        (activity as AppCompatActivity).let {
            it.addBackStackFragmentInActivity(ExplanationMosquitoForecastDetailFragment.newInstance(behavior), R.id.main_fragment_container)
        }

    }


}
