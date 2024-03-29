package com.victor.clips.ui.widget

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.LinearInterpolator
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FABBehaviour.java
 * Author: Victor
 * Date: 2019/11/8 10:05
 * Description:
 * -----------------------------------------------------------------
 */
class FABBehaviour(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        //child -> Floating Action Button
        val translation: ViewPropertyAnimator? = when {
            dyConsumed > 0 -> {
                val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
                val fab_bottomMargin = layoutParams.bottomMargin
                child.animate().translationY((child.height + fab_bottomMargin * 2).toFloat())
            }
            dyConsumed < 0 -> child.animate().translationY(0f)
            else -> null
        }
        translation?.multiplyDuration()?.setInterpolator(LinearInterpolator())?.start()
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }
}

private fun ViewPropertyAnimator.multiplyDuration(): ViewPropertyAnimator {
    return setDuration(duration)
}