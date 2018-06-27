package com.work.hany.mosquitoproject.today

import android.util.Log
import com.work.hany.mosquitoproject.BaseRequester
import com.work.hany.mosquitoproject.http.MosquitoResult
import com.work.hany.mosquitoproject.http.MosquitoRetrofit
import com.work.hany.mosquitoproject.http.MosquitoService
import com.work.hany.mosquitoproject.util.todayDate
import com.work.hany.mosquitoproject.util.weekDate
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import kotlin.collections.ArrayList

class TodayMosquitoForecastRequester : TodayMosquitoForecastContract.Requester {
    private var callList: ArrayList<Call<MosquitoResult>> = arrayListOf()

    fun allRequestCallCancel() {
        callList.forEach { it.cancel() }
    }

    override fun requestMosquitoToday(responseListener: BaseRequester.OnRequesterResponseListener<Map<String, Float>>) {

        var service = MosquitoRetrofit.instance.create(MosquitoService::class.java)
        var todayDate = Date().todayDate()
        var mosquitoes = mutableMapOf<String, Float>()

        var call: Call<MosquitoResult> = service.getMosquito(todayDate)
        callList.add(call)

        call.enqueue(object : Callback<MosquitoResult> {
            override fun onFailure(call: Call<MosquitoResult>, t: Throwable?) {
                if (!call.isCanceled) {
                    responseListener.failed(t.toString())
                }
            }

            override fun onResponse(call: Call<MosquitoResult>, response: Response<MosquitoResult>?) {
                Log.d("HANY_TAG", "requestMosquitoToday call.isCanceled ? " + call.isCanceled)
                Log.d("HANY_TAG", "call.delete ? " + callList.remove(call))
                if (!call.isCanceled) {
                    response?.body()?.let {
                        if (it.mosquitoStatus != null && it.mosquitoStatus.row.size > 0) {
                            var key = it.mosquitoStatus.row[0].mosquitoDate
                            var value = it.mosquitoStatus.row[0].mosquitoValue
                            mosquitoes[key] = value

                        } else {
                            mosquitoes[todayDate] = 0.0f

                        }

                        responseListener.received(mosquitoes)
                    }
                }
            }
        })


    }

    override fun requestMosquitoWeekend(responseListener: BaseRequester.OnRequesterResponseListener<Map<String, Float>>) {

        var service = MosquitoRetrofit.instance.create(MosquitoService::class.java)
        var weekDate = Date().weekDate()
        var mosquitoes = mutableMapOf<String, Float>()
//
        for (date in weekDate) {


            var call: Call<MosquitoResult> = service.getMosquito(date)
            callList.add(call)

            call.enqueue(object : Callback<MosquitoResult> {
                override fun onFailure(call: Call<MosquitoResult>, t: Throwable?) {
                    if (!call.isCanceled) {
                        responseListener.failed(t.toString())
                        //connect timed out... TODO 실패하면 다시 시도하도록 한다.
                    }
                }

                override fun onResponse(call: Call<MosquitoResult>, response: Response<MosquitoResult>?) {
                    Log.d("HANY_TAG", "requestMosquitoWeekend call.isCanceled ? " + call.isCanceled)
                    Log.d("HANY_TAG", "call.delete ? " + callList.remove(call))

                    if (!call.isCanceled) {
                        response?.body()?.let {
                            if (it.mosquitoStatus != null && it.mosquitoStatus.row.size > 0) {
                                var key = it.mosquitoStatus.row[0].mosquitoDate
                                var value = it.mosquitoStatus.row[0].mosquitoValue
                                mosquitoes[key] = value

                            } else {
                                mosquitoes[date] = 0.0f

                            }
                        }

                        if (mosquitoes.size >= 7) { // 일주일
                            /** 값이 비동기로 들어와서 여기서 한번 정렬해서 넣어줘야한다.*/
                            var sortMosquitoes = mutableMapOf<String, Float>()
                            weekDate.forEach { date ->
                                mosquitoes[date]?.let {
                                    sortMosquitoes[date] = it
                                }
                            }

                            responseListener.received(sortMosquitoes)
                        }
                    }
                }
            })

        }
//

    }

}