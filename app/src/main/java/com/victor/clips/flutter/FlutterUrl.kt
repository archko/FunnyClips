package com.victor.clips.flutter

import com.victor.clips.util.DeviceUtils
import com.victor.clips.util.WebConfig

/**
 * @author: archko 2019/11/30 :8:21 PM
 */
public final class FlutterUrl {

    companion object {
        /**
         * get discovery category list url
         */
        fun getCategoryUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.FIND_CATEGORIES_URL),
                    DeviceUtils.getUDID(), DeviceUtils.getPhoneModel())
        }

        /**
         * get category detail,the url return a list
         */
        fun getCategoryByIdUrl(categoriId: Int): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.CATEGORY_DETAIL_URL),
                    categoriId, DeviceUtils.getPhoneModel())
        }

        /**
         * get video by id,the url return a video list,the same as getCategoryById
         */
        fun getVideoByIdUrl(videoId: Int): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.RELATED_VIDEO_URL),
                    videoId, DeviceUtils.getPhoneModel())
        }

        fun getHotWeeklyUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.HOT_WEEKLY_URL),
                    DeviceUtils.getPhoneModel())
        }

        fun getHotMonthlyUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.HOT_MONTHLY_URL),
                    DeviceUtils.getPhoneModel())
        }

        fun getHotTotalRankingUrl(): String {
            return String.format(WebConfig.getRequestUrl(WebConfig.HOT_TOTAL_RANKING_URL),
                    DeviceUtils.getPhoneModel())
        }
    }
}