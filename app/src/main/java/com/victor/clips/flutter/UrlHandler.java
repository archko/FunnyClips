package com.victor.clips.flutter;

import android.app.Activity;
import android.os.Handler;

import com.victor.clips.util.Loger;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * @author: archko 2019/11/28 :11:00 AM
 */
public class UrlHandler implements INativeHandler {
    private final static String URL_CATEGORY = "category";
    private final static String URL_CATEGORY_BY_ID = "category_by_id";
    private final static String URL_VIDEO_BY_ID = "video_by_id";
    private final static String URL_HOT_WEEKLY = "hot_weekly";
    private final static String URL_HOT_MONTHLY = "hot_monthly";
    private final static String URL_TOTAL_RANKING = "total_ranking";

    @Override
    public void onCallMethod(MethodCall call, MethodChannel.Result result,
                             Handler handler, Activity activity) {
        String action = call.argument("action");
        Loger.Companion.d("onCallMethod ", String.format("%s,%s", action, call.arguments));
        Map<String, Object> resultMap = new HashMap<>();
        if (action.equals(URL_CATEGORY)) {
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getCategoryUrl());
            result.success(resultMap);
            return;
        } else if (URL_CATEGORY_BY_ID.equals(action)) {
            int categoryId = call.argument("categoryId");
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getCategoryByIdUrl(categoryId));
            result.success(resultMap);
            return;
        } else if (URL_VIDEO_BY_ID.equals(action)) {
            int videoId = call.argument("videoId");
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getVideoByIdUrl(videoId));
            result.success(resultMap);
            return;
        } else if (URL_HOT_WEEKLY.equals(action)) {
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getHotWeeklyUrl());
            result.success(resultMap);
            return;
        } else if (URL_HOT_MONTHLY.equals(action)) {
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getHotMonthlyUrl());
            result.success(resultMap);
            return;
        } else if (URL_TOTAL_RANKING.equals(action)) {
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("url", FlutterUrl.Companion.getHotTotalRankingUrl());
            result.success(resultMap);
            return;
        }
    }

}
