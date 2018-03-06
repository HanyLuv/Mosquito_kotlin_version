package com.work.hany.mosquitoproject.http

import android.app.Activity
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


class Requester(listeningActivity: Activity) {

    interface RequesterResponse {
        fun receivedResult(photos: List<MosquitoStatus>)
    }

    private val responseListener: RequesterResponse

    init {
        responseListener = listeningActivity as RequesterResponse
    }

    companion object {
        var baseURL = "http://openapi.seoul.go.kr:8088"
        var
    }

    private fun request(): Mosquito{


        //http://openapi.seoul.go.kr:8088/476e58535872757a31313154704f6753/json/MosquitoStatus/1/5/2018-02-24
        var okHttpClient = createOkHttpClient()
        var requestURI = StringBuffer().append(baseURL)
        var retrofit = Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()


        return Mosquito()
    }

    //데이터 여러번 보내고 7개를 만들어서 보내야함..


    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }


}


/*
 *
 * {
    "MosquitoStatus": {
        "list_total_count": 1,
        "RESULT": {
            "CODE": "INFO-000",
            "MESSAGE": "정상 처리되었습니다"
        },
        "row": [
            {
                "MOSQUITO_DATE": "2018-02-24",
                "MOSQUITO_VALUE": 5.5
            }
        ]
    }
}
 *
 */


class RequesterResponse {

}

data class MosquitoStatus(@SerializedName("list_total_count") var listTotalCount: Int,
                          @SerializedName("RESULT") var result: Result,
                          @SerializedName("row") var row: ArrayList<Mosquito>)

data class Result(
        @SerializedName("CODE") var code: String,
        @SerializedName("MESSAGE") var Message: String)

data class Mosquito(
        @SerializedName("MOSQUITO_DATE") var mosquitoDate: String,
        @SerializedName("MOSQUITO_VALUE") var mosquitoValue: String)
