package com.work.hany.mosquitoproject.explanation

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * Created by hany on 2018. 3. 1..
 */


class ToolbarHidingOnScrollListener(var titleBarHeight: Int,
                                    var toolbarContainer: View,
                                    private var parallaxScrollingView: View) : RecyclerView.OnScrollListener() {

    private val parallaxScrollingFactor = 0.7f


    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        Log.d("HANY_TAG","titleBarHeight : " +titleBarHeight)
        Log.d("HANY_TAG","toolbarContainer.translationY : " +toolbarContainer.translationY)

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (Math.abs(toolbarContainer.translationY) > titleBarHeight) {
                hideToolbar()
            } else {
                showToolbar()
            }
        } else {
            toolbarContainer.clearAnimation()
        }
    }

    private fun showToolbar() {
        toolbarContainer.clearAnimation()
        toolbarContainer
                .animate()
                .translationY(0f)
                .start()

    }

    private fun hideToolbar() {
        toolbarContainer.clearAnimation()
        toolbarContainer
                .animate()
                .translationY((-toolbarContainer.bottom).toFloat())
                .start()
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        scrollColoredViewParallax(dy)

        if (dy > 0) {
            hideToolbarBy(dy)
        } else {
            showToolbarBy(dy)
        }
    }

    private fun scrollColoredViewParallax(dy: Int) {
        val absoluteTranslationY = (parallaxScrollingView.translationY - dy * parallaxScrollingFactor).toInt()
        parallaxScrollingView.translationY = Math.min(absoluteTranslationY, 0).toFloat()

    }


    private fun hideToolbarBy(dy: Int) {
        if (cannotHideMore(dy)) {
            toolbarContainer.translationY = (-toolbarContainer.bottom).toFloat()
        } else {
            toolbarContainer.translationY = toolbarContainer.translationY - dy
        }
    }

    private fun cannotHideMore(dy: Int): Boolean {
        return Math.abs(toolbarContainer.translationY - dy) > toolbarContainer.bottom
    }


    protected fun showToolbarBy(dy: Int) {
        if (cannotShowMore(dy)) {
            toolbarContainer.translationY = 0f
        } else {
            toolbarContainer.translationY = toolbarContainer.translationY - dy
        }
    }

    private fun cannotShowMore(dy: Int): Boolean {
        return toolbarContainer.translationY - dy > 0
    }

}