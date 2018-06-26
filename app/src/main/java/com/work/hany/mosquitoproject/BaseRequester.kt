package com.work.hany.mosquitoproject


interface BaseRequester {
//    val requester: Requester
//        get() = Requester.RETOROFIT

    interface OnRequesterResponseListener<T> {
        fun received(result: T)
        fun failed(errorMsg: String)
    }

}
