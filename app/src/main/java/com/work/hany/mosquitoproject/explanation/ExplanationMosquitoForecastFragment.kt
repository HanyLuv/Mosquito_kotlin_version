package com.work.hany.mosquitoproject.explanation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.explanation.tabs.behavior.BehaviorTabFragment
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
    private var tabViewList: Set<View> = setOf()

    companion object {
        fun newInstance() = ExplanationMosquitoForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_explanation,container,false)

        toDoWithActivity {
            var actionBarHeight = actionBarHeight()
            recyclerView = root.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            populateRecyclerView(recyclerView)

            recyclerView.addOnScrollListener(ToolbarHidingOnScrollListener(
                    actionBarHeight,
                    root.findViewById(R.id.toolbar_container),
                    root.findViewById(R.id.colored_background_view)))
        }

        behaviorTabView = root.findViewById<View>(R.id.behaviortab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
            tabViewList.plus(it)
        }

        videoTabView = root.findViewById<View>(R.id.videotab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
            tabViewList.plus(it)
        }

        situationTabView = root.findViewById<View>(R.id.situationtab).also{
            it.setOnClickListener{ presenter.onTabbBarMenuTapped(it) }
            tabViewList.plus(it)
        }

        return root
    }

    private fun populateRecyclerView(recyclerView: RecyclerView) {

        recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            private var DUMMY_ITEM_COUNT = 40

            override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
                val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_behavior_item, viewGroup, false)
                return TextHolder(itemView)
            }

            override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                // We are too lazy for this by now ;-)
            }

            override fun getItemCount(): Int {
                return DUMMY_ITEM_COUNT
            }


            internal inner class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        }

    }



    override fun showVideoTab() {
        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        videoTabView.isSelected = true
        Toast.makeText(context,"모기예보제란",Toast.LENGTH_SHORT).show()
    }

    override fun showSituationTab() {

        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        situationTabView.isSelected = true

        Toast.makeText(context,"상황별",Toast.LENGTH_SHORT).show()

    }

    override fun showBehaviorTab() {
        Toast.makeText(context,"행동수칙 및 방제법",Toast.LENGTH_SHORT).show()

        tabViewList.iterator().forEach {
            it.isSelected = false
        }

        behaviorTabView.isSelected = true
//        toDoWithActivity {
//            replaceFragmentInActivity(BehaviorTabFragment(),R.id.mainFragmentContainer)
//        }
    }


}
