package com.work.hany.mosquitoproject

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.work.hany.mosquitoproject.data.MosquitoStatus
import com.work.hany.mosquitoproject.http.Requester
import com.work.hany.mosquitoproject.util.setupActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Requester.RequesterResponse {

//    private lateinit var To
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }


        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
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

        drawerLayout.addDrawerListener(drawerToggle)


    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
        Log.d("HANY_TAG", "onPrepareOptionsMenu")
        drawerLayout.closeDrawers()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //결과값을 받아온다.
    //일주일간 데이터를 반환함.
    // 간혹가다 데이터가 없는경우가 있음..
    override fun receivedResult(photos: List<MosquitoStatus>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
