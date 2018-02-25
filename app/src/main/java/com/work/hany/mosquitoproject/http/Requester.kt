package com.work.hany.mosquitoproject.http

import android.app.Activity
import com.work.hany.mosquitoproject.data.MosquitoStatus

/**
 * Created by hany on 2018. 2. 25..
 *
 *
 * 결과를 액티비티에 받아서 결과값이 왔을때 프레그먼트를 만들어서 붙이자.
 * 그전까지는 로딩바 돌돌돌 돌고있음....
 *
 */


class Requester(listeningActivity: Activity) {

    interface RequesterResponse {
        fun receivedResult(photos: List<MosquitoStatus>)
    }

    private val responseListener: RequesterResponse

    init {

        responseListener = listeningActivity as RequesterResponse

    }


    //데이터 여러번 보내고 7개를 만들어서 보내야함..

}