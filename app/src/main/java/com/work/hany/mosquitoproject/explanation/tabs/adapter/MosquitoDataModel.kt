package com.work.hany.mosquitoproject.explanation.tabs.adapter

import com.work.hany.mosquitoproject.data.MosquitoStatus

/**
 * Created by hany on 2018. 3. 2..
 */


interface MosquitoDataModel {
    fun add(item :MosquitoStatus)
    fun remove(position: Int): MosquitoStatus
}