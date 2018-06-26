package com.work.hany.mosquitoproject.explanation.detail

import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.Behavior
import com.work.hany.mosquitoproject.data.Common
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.data.TabMenu
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_explanation_detail.*

/**
 * Created by hany on 2018. 3. 1..
 * 데이터 노출밖에 없으니 MVP하지 않았음.
 */

class ExplanationMosquitoForecastDetailFragment : Fragment() {


    private lateinit var viewpager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var stepTitleTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var root = inflater.inflate(R.layout.fragment_explanation_detail, container, false)
        var imageView = root.findViewById<ImageView>(R.id.detail_image_view)


        viewpager = root.findViewById(R.id.pager)
        tabLayout = root.findViewById(R.id.detail_tab_layout)
        stepTitleTextView = root.findViewById(R.id.detail_title_text_view)
        // behavior.steps[] 0하, 1중, 2상
        var behavior = arguments.getParcelable(Common.EXTRA_KEY) as Behavior

        var strStepTitle = StringBuffer().append(DataManager.instance.mosquitoLevel(behavior.levelTitle))
                .append("단계 ").append(behavior.levelTitle).toString()


        var pictureName = StringBuffer().append("stage_").append(DataManager.instance.mosquitoLevel(behavior.levelTitle)).toString()
        val id = context.resources.getIdentifier(pictureName, "drawable", context.packageName)
        imageView.setImageResource(id)

        imageView.post {
            Blurry.with(context)
                    .sampling(1)
                    .async()
                    .animate(700)
                    .capture(imageView)
                    .into(imageView)
        }

        stepTitleTextView.text = strStepTitle
        viewpager.adapter = ExplanationMosquitoTabFragmentPagerAdapter(activity.supportFragmentManager, tabLayout.tabCount, behavior)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position

            }

        })

        return root
    }


    companion object {
        fun newInstance(behavior: Behavior): ExplanationMosquitoForecastDetailFragment {
            val fragment = ExplanationMosquitoForecastDetailFragment()
            val args = Bundle()
            args.putParcelable(Common.EXTRA_KEY, behavior as Parcelable)
            fragment.arguments = args
            return fragment
        }
    }
}


private class ExplanationMosquitoTabFragmentPagerAdapter(fragmentManager: FragmentManager, var tabCount: Int, var behavior: Behavior) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var tabMenu: TabMenu = when (position) {
            0 -> TabMenu.PERSONAL
            else -> TabMenu.PUBLIC
        }

        return ExplanationMosquitoForecastDetailTabFragment.newInstance(behavior, tabMenu)
    }

    override fun getCount(): Int = tabCount


}