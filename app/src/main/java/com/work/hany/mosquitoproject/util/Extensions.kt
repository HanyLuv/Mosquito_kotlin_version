package com.work.hany.mosquitoproject.util

import android.support.v7.app.ActionBar
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by hany on 2018. 2. 26..
 */


/** AppCompatActivity Ext */
fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }

}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}


fun AppCompatActivity.actionBarHeight(): Int {
    var actionBarHeight = 0
    val styledAttributes = theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.actionBarSize)
    )
    actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
    styledAttributes.recycle()
    return actionBarHeight
}



fun Fragment.toDoWithActivity(action: AppCompatActivity.() -> Unit) {
    (context as AppCompatActivity)?.run {
        action()
    }
}


/** fragment 사용하면 add(...)가 자주 불리니깐 inline 처리한듯 부담되지않도록 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}