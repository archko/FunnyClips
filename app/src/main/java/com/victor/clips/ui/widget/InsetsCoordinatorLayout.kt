package com.victor.clips.ui.widget

import android.content.Context
import android.graphics.Rect
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.util.AttributeSet
import androidx.core.view.ViewCompat
import android.view.View
import android.os.Build
import androidx.core.view.WindowInsetsCompat
import com.victor.clips.interfaces.WindowInsetsHandlingBehavior


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: InsetsCoordinatorLayout.java
 * Author: Victor
 * Date: 2019/10/14 16:22
 * Description:
 * -----------------------------------------------------------------
 */
class InsetsCoordinatorLayout: CoordinatorLayout {
    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        ViewCompat.setOnApplyWindowInsetsListener(this, object : androidx.core.view.OnApplyWindowInsetsListener {
            override fun onApplyWindowInsets(v: View, insets: WindowInsetsCompat): WindowInsetsCompat {
                if (insets.isConsumed) {
                    return insets
                }

                var i = 0
                val count = childCount
                while (i < count) {
                    var child = getChildAt(i)
                    var b = getBehavior(child)

                    if (b == null) {
                        i++
                        continue
                    }
//                    b!!.onApplyWindowInsets(this@InsetsCoordinatorLayout, child, WindowInsetsCompatUtil.copy(insets))
                    i++
                }

                return insets
            }
        })
    }

    fun getBehavior(child: View): Behavior<*>? {
        val lp = child.layoutParams as LayoutParams
        return lp.behavior
    }

    protected override fun fitSystemWindows(insets: Rect): Boolean {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(insets)
        }

        var consumed = false

        var i = 0
        val count = childCount
        while (i < count) {
            val child = getChildAt(i)
            val b = getBehavior(child)

            if (b == null) {
                i++
                continue
            }

            if (b !is WindowInsetsHandlingBehavior) {
                i++
                continue
            }

            if ((b as WindowInsetsHandlingBehavior).onApplyWindowInsets(this, child, insets)) {
                consumed = true
            }
            i++
        }

        return consumed
    }


}

