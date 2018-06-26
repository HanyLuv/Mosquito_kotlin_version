package com.work.hany.mosquitoproject.http

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by hany on 2018. 2. 25..
 *
 *
 * 결과를 액티비티에 받아서 결과값이 왔을때 프레그먼트를 만들어서 붙이자.
 * 그전까지는 로딩바 돌돌돌 돌고있음....
 *
 *
 * 데이터가 안내려올때가있다..... -_-
 * 그것도 연달아서 ㅋㅋㅋㅋ 그경우 데이터가 있는날까지 역순으로 조회한다.
 * 데이터는 제일 처음 한번 7번 요청하고 내부적으로 저장해서 쓸데 없이 요청하지 않도록한다. (SqlLite..? Realm 사용해야징ㅎ)
 * 데이터 있는것만 저장한다. 실패한 데이터에 대해서는 재시도 하지않는다.
 */


//class Requester {
//
//    private var okHttpClient: OkHttpClient
//    private var requestURI: String
//    private var retrofit: MosquitoRetrofit
//
//    init {
//        okHttpClient = createOkHttpClient()
//        requestURI = StringBuffer().append(baseURL).append("/").toString()
//        retrofit = MosquitoRetrofit.Builder()
//                .baseUrl(requestURI)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create()).build()
//
//    }
//
//    companion object {
//        const val baseURL = "http://openapi.seoul.go.kr:8088"
//        const val apiKey = "476e58535872757a31313154704f6753"
//        const val MOSQUITO_COUNT_WEEK = 7
//    }
//
//
//    /**@description for widget. */
//    //TODO 요청하는데 있어서. 중복 코드 정리 필요함
//    fun requestToday(){
//
//        var service = retrofit.create(MosquitoService::class.java)
//        var todayDate = Date().todayDate()
//        var mosquitoes = mutableMapOf<String, Float>()
//
//
//        service.getMosquito(apiKey, todayDate).enqueue(object : Callback<MosquitoResult> {
//            override fun onFailure(call: Call<MosquitoResult>?, t: Throwable?) {
//                responseListener.failedResult(t.toString())
//            }
//
//            override fun onResponse(call: Call<MosquitoResult>?, response: Response<MosquitoResult>?) {
//                response?.body()?.let {
//                    if (it.mosquitoStatus != null && it.mosquitoStatus.row.size > 0) {
//                        var key = it.mosquitoStatus.row[0].mosquitoDate
//                        var value = it.mosquitoStatus.row[0].mosquitoValue
//                        mosquitoes[key] = value
//
//                    } else {
//                        mosquitoes[todayDate] = 0.0f
//
//                    }
//
//                    responseListener.receivedResult(mosquitoes)
//                }
//
//            }
//        })
//    }
//
//    fun request() {
//        //http://openapi.seoul.go.kr:8088/476e58535872757a31313154704f6753/json/MosquitoStatus/1/5/2018-02-24
//        var okHttpClient = createOkHttpClient()
//        var requestURI = StringBuffer()
//                .append(baseURL).append("/").toString()
//
//        var retrofit = MosquitoRetrofit.Builder()
//                .baseUrl(requestURI)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create()).build()
//
//        var service = retrofit.create(MosquitoService::class.java)
//        var mosquitoes = mutableMapOf<String, Float>()
//
//        var weekDate = Date().weekDate()
//
//        for (date in weekDate) {
//            service.getMosquito(apiKey, date).enqueue(object : Callback<MosquitoResult> {
//                override fun onFailure(call: Call<MosquitoResult>?, t: Throwable?) {
//                    responseListener.failedResult(t.toString())
//                    //connect timed out... TODO 실패하면 다시 시도하도록 한다.
//                }
//
//                override fun onResponse(call: Call<MosquitoResult>?, response: Response<MosquitoResult>?) {
//                    response?.body()?.let {
//                        if (it.mosquitoStatus != null && it.mosquitoStatus.row.size > 0) {
//                            var key = it.mosquitoStatus.row[0].mosquitoDate
//                            var value = it.mosquitoStatus.row[0].mosquitoValue
//                            mosquitoes[key] = value
//
//                        } else {
//                            mosquitoes[date] = 0.0f
//
//                        }
//                    }
//
//                    if (mosquitoes.size >= MOSQUITO_COUNT_WEEK) {
//                        /** 값이 비동기로 들어와서 여기서 한번 정렬해서 넣어줘야한다.*/
//                        var sortMosquitoes = mutableMapOf<String, Float>()
//                        weekDate.forEach { date ->
//                            mosquitoes[date]?.let {
//                                sortMosquitoes[date] = it
//                            }
//                        }
//
//                        responseListener.receivedResult(sortMosquitoes)
//                    }
//
//                }
//            })
//
//        }
//
//
//    }
//
//    //데이터 여러번 보내고 7개를 만들어서 보내야함..
//
//
//    private fun createOkHttpClient(): OkHttpClient { //데이터 로그찍는것.
//        val builder = OkHttpClient.Builder()
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        builder.addInterceptor(interceptor)
//        return builder.build()
//    }
//
//
//}



//apiKey = "476e58535872757a31313154704f6753"
interface MosquitoService {
    @GET("476e58535872757a31313154704f6753/json/MosquitoStatus/1/5/{date}")
    fun getMosquito(@Path("date") date: String): Call<MosquitoResult>

}

data class MosquitoResult(@SerializedName("MosquitoStatus") var mosquitoStatus: MosquitoStatus)

data class MosquitoStatus(@SerializedName("list_total_count") var listTotalCount: Int,
                          @SerializedName("RESULT") var result: Result,
                          @SerializedName("row") var row: ArrayList<Mosquito>)

data class Result(
        @SerializedName("CODE") var code: String,
        @SerializedName("MESSAGE") var Message: String)

data class Mosquito(
        @SerializedName("MOSQUITO_DATE") var mosquitoDate: String,
        @SerializedName("MOSQUITO_VALUE") var mosquitoValue: Float)
