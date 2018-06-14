package com.work.hany.mosquitoproject.util

import android.app.Activity
import android.content.res.Resources
import android.support.v7.app.ActionBar
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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


fun Activity.actionBarHeight(): Int {
    val styledAttributes = theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.actionBarSize)
    )
    var actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
    styledAttributes.recycle()
    return actionBarHeight
}


/**
 * @description 오늘의 날짜를 기본 포멧형식으로 가져옴.
 * */
fun Date.todayDate(): String {
    return mosquitoDateFormatting(this)
}

//TODO 월, 일만 반환. 이 함수를 Date에 넣어야할지 String에 넣어야할지 고민중..
fun String.dateFormatMMDD(): String {

    var inputPattern = "yyyy-MM-dd"
    var outputPattern = "MM-dd"

    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)
    var date = inputFormat.parse(this)

    return  outputFormat.format(date)
}

/**@description  년,월,일로 반환한다. */
fun String.dateFormatKorea(): String {

    var inputPattern = "yyyy-MM-dd"
    var outputPattern = "yyyy년 MM월 dd일"

    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)
    var date = inputFormat.parse(this)

    return  outputFormat.format(date)
}

private fun mosquitoDateFormatting(date: Date): String {
    var formatter = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    return formatter.format(date)
}

/**
 *
 * @author hany
 * @description 오늘의 날짜를 기준으로 7일전의 날짜리스트를 반환한다.
 */

fun Date.weekDate(): ArrayList<String> {
    val calender = Calendar.getInstance()
    var weekData = arrayListOf<String>()
    weekData.add(todayDate())

    for ( count in 1 until 7 ) {
        calender.add(Calendar.DAY_OF_MONTH, -1)
        weekData.add(mosquitoDateFormatting(calender.time))
    }

    calender.add(Calendar.DAY_OF_MONTH,7)
    return weekData
}

/** fragment 사용하면 add(...)가 자주 불리니깐 inline 처리한듯 부담되지않도록 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density.toInt())

