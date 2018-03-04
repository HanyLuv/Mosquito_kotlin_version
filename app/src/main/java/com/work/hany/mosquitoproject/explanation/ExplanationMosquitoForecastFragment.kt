package com.work.hany.mosquitoproject.explanation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Situation
import com.work.hany.mosquitoproject.explanation.tabs.ExplanationRecyclerAdapter
import com.work.hany.mosquitoproject.explanation.tabs.situation.SituationTabFragment
import com.work.hany.mosquitoproject.explanation.tabs.video.VideoTabFragment
import com.work.hany.mosquitoproject.util.actionBarHeight
import com.work.hany.mosquitoproject.util.replaceFragmentInActivity
import com.work.hany.mosquitoproject.util.toDoWithActivity

/**
 * Created by hany on 2018. 2. 25..
 *
 * 모기예보제란 fragment
 */
class ExplanationMosquitoForecastFragment: Fragment(), ExplanationMosquitoForecastContract.View {

    override lateinit var presenter: ExplanationMosquitoForecastContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var behaviorTabView: View
    private lateinit var videoTabView: View
    private lateinit var situationTabView: View
    private lateinit var tabViewList: Set<View>

    //어댑터 만들어주고 view에서 어댑터에 들어갈 데이터를 셋팅해준다
    private  var adapter: ExplanationRecyclerAdapter = ExplanationRecyclerAdapter()

    companion object {
        fun newInstance() = ExplanationMosquitoForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_explanation,container,false)

        toDoWithActivity {
            var actionBarHeight = actionBarHeight()

            recyclerView = root.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            recyclerView.addOnScrollListener(ToolbarHidingOnScrollListener(
                    actionBarHeight,
                    root.findViewById(R.id.toolbar_container),
                    root.findViewById(R.id.colored_background_view)))
        }

        behaviorTabView = root.findViewById<View>(R.id.behaviortab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
        }

        videoTabView = root.findViewById<View>(R.id.videotab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
        }

        situationTabView = root.findViewById<View>(R.id.situationtab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
        }

        tabViewList = setOf(videoTabView,situationTabView,behaviorTabView)

        return root
    }

    override fun showVideoTab() {
        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        videoTabView.isSelected = true
        Toast.makeText(context,"모기예보제란",Toast.LENGTH_SHORT).show()

        toDoWithActivity { replaceFragmentInActivity(VideoTabFragment(),R.id.main_fragment_container) }
    }

    override fun showSituationTab(items: List<Situation>) {

        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        situationTabView.isSelected = true

        Toast.makeText(context, "상황별", Toast.LENGTH_SHORT).show()
        toDoWithActivity {
            replaceFragmentInActivity(SituationTabFragment(), R.id.main_fragment_container)
        }
    }


    override fun showBehaviorTab(items: List<Behavior>) {
        Toast.makeText(context,"행동수칙 및 방제법",Toast.LENGTH_SHORT).show()

        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        behaviorTabView.isSelected = true

        adapter.addAll(items)

    }



}
