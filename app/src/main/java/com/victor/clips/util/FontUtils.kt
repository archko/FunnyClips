package com.victor.clips.util

import android.content.res.AssetManager
import android.graphics.Typeface

/**
 * @author: archko 2019/11/30 :7:22 PM
 */
class FontUtils {
    companion object {

        fun getTypeface(assets: AssetManager): Typeface? {
            try {
                var typeface = Typeface.createFromAsset(assets, "fonts/ZuoAnLianRen.ttf")
                return typeface
            } catch (e: Exception) {
            }
            return Typeface.DEFAULT
        }
    }
}