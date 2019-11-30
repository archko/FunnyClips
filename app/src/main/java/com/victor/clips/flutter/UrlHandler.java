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
        }
    }

}
