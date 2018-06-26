package com.work.hany.mosquitoproject

import android.app.Application
import android.content.Context


class MosquitoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        mosquitoApplication = this
    }

    companion object {
        private lateinit var mosquitoApplication: MosquitoApplication
        fun context(): Context = mosquitoApplication.baseContext

    }


}
