package com.work.hany.mosquitoproject


interface BaseRequester {

    interface OnRequesterResponseListener<T> {
        fun received(result: T)
        fun failed(errorMsg: String)
    }

}
