package com.victor.clips.flutter

import com.victor.clips.util.DeviceUtils
import com.victor.clips.util.WebConfig

/**
 * @author: archko 2019/11/30 :8:21 PM
 */
public final class FlutterUrl {

    companion object {
        /**
         * get discovery category list
         */
        fun getCategoryUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.FIND_CATEGORIES_URL),
                    DeviceUtils.getUDID(), DeviceUtils.getPhoneModel())
        }

        /**
         * get category detail,return a list
         */
        fun getCategoryById(categoriId: Int): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.CATEGORY_DETAIL_URL),
                    categoriId, DeviceUtils.getPhoneModel())
        }

        /**
         * get video by id,return video and a list,the same as getCategoryById
         */
        fun getVideoById(videoId: Int): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.RELATED_VIDEO_URL),
                    videoId, DeviceUtils.getPhoneModel())
        }
    }
}