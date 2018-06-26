package com.work.hany.mosquitoproject.behvior

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.explanation.ToolbarHidingOnScrollListener
import com.work.hany.mosquitoproject.explanation.detail.ExplanationMosquitoForecastDetailFragment
import com.work.hany.mosquitoproject.explanation.tabs.ExplanationRecyclerAdapter
import com.work.hany.mosquitoproject.util.actionBarHeight
import com.work.hany.mosquitoproject.util.addBackStackFragmentInActivity

//TODO MVP 적용해야함. 으아아으앙 ㅠㅡㅠ 이것도 일이다 진짜 흥허엏어
class ExplanationMosquitoBehaviorFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_behavior, container, false)
        var recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)


        var adapter = ExplanationRecyclerAdapter(object : ExplanationRecyclerAdapter.ClickListener {
            override fun onClick(view: View, behavior: Behavior) {
                (activity as AppCompatActivity).let {
                    it.addBackStackFragmentInActivity(ExplanationMosquitoForecastDetailFragment.newInstance(behavior), R.id.main_fragment_container)
                }

            }
        })

        var parallaxScrollingView = root.findViewById<View>(R.id.colored_background_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            private val parallaxScrollingFactor = 0.7f

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollColoredViewParallax(dy)

            }

            private fun scrollColoredViewParallax(dy: Int) {
                val absoluteTranslationY = (parallaxScrollingView.translationY - dy * parallaxScrollingFactor).toInt()
                parallaxScrollingView.translationY = Math.min(absoluteTranslationY, 0).toFloat()

            }

        })

        adapter.addAll(DataManager.instance.createBehaviorItems(context))

        return root
    }

    companion object {
        fun newInstance() = ExplanationMosquitoBehaviorFragment()
    }


}