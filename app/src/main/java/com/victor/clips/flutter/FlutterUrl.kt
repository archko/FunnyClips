package com.victor.clips.flutter

import com.victor.clips.util.DeviceUtils
import com.victor.clips.util.WebConfig

/**
 * @author: wushuyong 2019/11/30 :8:21 PM
 */
public class FlutterUrl {

    companion object {
        fun getCategoryUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.FIND_CATEGORIES_URL),
                    DeviceUtils.getUDID(), DeviceUtils.getPhoneModel())
        }

        fun getCategoryById(categoriId: Int): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.CATEGORY_DETAIL_URL),
                    categoriId, DeviceUtils.getPhoneModel())
        }
    }
}