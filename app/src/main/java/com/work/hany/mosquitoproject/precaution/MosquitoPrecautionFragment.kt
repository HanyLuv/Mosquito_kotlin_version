package com.work.hany.mosquitoproject.precaution

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.mosquitoproject.R

/**
 * Created by hany on 2018. 2. 25..
 * 모기 예방법
 */
class MosquitoPrecautionFragment: Fragment(), MosquitoPrecautionContract.View {

    override lateinit var presenter: MosquitoPrecautionContract.Presenter


    companion object {
        fun newInstance() = MosquitoPrecautionFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_precaution,container,false)
        return root
    }
}