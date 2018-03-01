package com.work.hany.mosquitoproject.explanation.tabs.behavior

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.mosquitoproject.R

/**
 * Created by hany on 2018. 3. 1..
 */
class BehaviorTabFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_tab_behavior,container,false)
        return root
    }

}