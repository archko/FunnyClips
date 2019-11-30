package com.victor.clips.flutter;

import android.app.Activity;
import android.os.Handler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * @author: archko 2019/11/28 :11:00 AM
 */
public class NetworkHandler implements INativeHandler {
    private final static String HTTP_GET_DATA = "xxaction";
    @Override
    public void onCallMethod(MethodCall call, MethodChannel.Result result,
                             Handler handler, Activity activity) {
        String action = call.argument("action");
        if (action.equals(HTTP_GET_DATA)) {
        }
    }

}
