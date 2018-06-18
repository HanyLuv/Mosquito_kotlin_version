package com.work.hany.mosquitoproject.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import android.widget.Toast
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.DataManager
import com.work.hany.mosquitoproject.http.Mosquito
import com.work.hany.mosquitoproject.http.Requester
import com.work.hany.mosquitoproject.util.dateFormatKorea
import com.work.hany.mosquitoproject.util.todayDate
import java.util.*

class TodayMosquitoProvider : AppWidgetProvider(), Requester.RequesterResponse {

    private lateinit var context: Context

    override fun receivedResult(mosquitoes: Map<String, Float>) {
        mosquitoes[Date().todayDate()]?.let { mosquitoValue ->
            updateWidget(context, Mosquito(Date().todayDate(), mosquitoValue))
        }

    }

    override fun failedResult(errorMsg: String) {
        //TODO 데이터 안넘어온것이니 업데이트 안됐다는 화면 띄워주자
    }


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        this.context = context;
        Toast.makeText(context, "update", Toast.LENGTH_SHORT).show()
        Requester(this).requestToday()
        // There may be multiple widgets active, so update all of them
    }

    override fun onEnabled(context: Context) {
        this.context = context

        Requester(this).requestToday()
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        //TODO 리퀘스트 바꿔야할듯..

        fun updateWidget(context: Context, todayMosquito: Mosquito) {

            var mosquitoWidgetView = RemoteViews(context.packageName, R.layout.widget_mosquito)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val widgetComponent = ComponentName(context, TodayMosquitoProvider::class.java)
            val widgetIds = appWidgetManager.getAppWidgetIds(widgetComponent)

//            mosquitoWidgetView.set(R.id.today_mosquito_value_bar_view , "width",400.0f)
//            mosquitoWidgetView.get
            var mosquitoStep = DataManager.instance.mosquitoStage(todayMosquito.mosquitoValue)
            mosquitoWidgetView.setTextViewText(R.id.widget_today_mosquito_text_view, todayMosquito.mosquitoDate.dateFormatKorea())
            mosquitoWidgetView.setTextViewText(R.id.widget_today_mosquito_value_text_view, todayMosquito.mosquitoValue.toString())
            mosquitoWidgetView.setTextViewText(R.id.widget_today_mosquito_step_text_view, mosquitoStep)


//            mosquitoWidgetView.
            appWidgetManager.updateAppWidget(widgetIds, mosquitoWidgetView)
        }

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val mosquitoWidgetView = RemoteViews(context.packageName, R.layout.widget_mosquito)
            appWidgetManager.updateAppWidget(appWidgetId, mosquitoWidgetView)
        }
    }
}
