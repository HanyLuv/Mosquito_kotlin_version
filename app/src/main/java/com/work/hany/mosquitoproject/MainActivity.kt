package com.work.hany.mosquitoproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.work.hany.mosquitoproject.explanation.ExplanationMosquitoForecastFragment
import com.work.hany.mosquitoproject.explanation.ExplanationMosquitoForecastPresenter
import com.work.hany.mosquitoproject.precaution.MosquitoPrecautionFragment
import com.work.hany.mosquitoproject.precaution.MosquitoPrecautionPresenter
import com.work.hany.mosquitoproject.today.TodayMosquitoForecastFragment
import com.work.hany.mosquitoproject.today.TodayMosquitoForecastPresenter
import com.work.hany.mosquitoproject.today.TodayMosquitoForecastRequester
import com.work.hany.mosquitoproject.util.replaceFragmentInActivity
import com.work.hany.mosquitoproject.util.setupActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this,resources.getString(R.string.add_mob_key))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        setupDrawerContent()
        setupDrawerToggle()

        TodayMosquitoForecastFragment.newInstance().also{
            replaceFragmentInActivity(it, R.id.main_fragment_container)
            TodayMosquitoForecastPresenter(it, TodayMosquitoForecastRequester())
        }

        /**드로우 메뉴 막을때*/
//        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }


    /** Drawer Content 설정 */
    private fun setupDrawerContent() {
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.today_navigation_menu_item -> {
                    Toast.makeText(this, getString(R.string.today_title), Toast.LENGTH_SHORT).show()
                    TodayMosquitoForecastFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.main_fragment_container)
                        TodayMosquitoForecastPresenter(it, TodayMosquitoForecastRequester())
                    }


                }

                R.id.explanation_navigation_menu_item -> {
                    Toast.makeText(this, getString(R.string.explanation_title), Toast.LENGTH_SHORT).show()
                    ExplanationMosquitoForecastFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.main_fragment_container)
                        ExplanationMosquitoForecastPresenter(it)
                    }

                }

                R.id.precaution_navigation_menu_item -> {
                    Toast.makeText(this, getString(R.string.precaution_title), Toast.LENGTH_SHORT).show()
                    MosquitoPrecautionFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.main_fragment_container)
                        MosquitoPrecautionPresenter(it)
                    }

                    replaceFragmentInActivity(MosquitoPrecautionFragment.newInstance(), R.id.main_fragment_container)
                }

            }

            drawer_layout.closeDrawers()
            true
        }

    }

    /** Toogle 설정 */
    private fun setupDrawerToggle() {
        drawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

        }.apply {
            this.isDrawerIndicatorEnabled = true

        }

        drawer_layout.addDrawerListener(drawerToggle)
    }


    //결과값을 받아온다.
    //일주일간 데이터를 반환함.
    // 간혹가다 데이터가 없는경우가 있음..

}
