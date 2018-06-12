package com.work.hany.mosquitoproject.precaution

import android.support.v4.app.Fragment

/**
 * Created by hany on 2018. 3. 1..
 */
class MosquitoPrecautionPresenter(view: MosquitoPrecautionContract.View): MosquitoPrecautionContract.Presenter {

    init {
        view.presenter = this
    }

}