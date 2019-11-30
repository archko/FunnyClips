package com.victor.clips.flutter;

import android.app.Activity;
import android.os.Handler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * @author: archko 2019/11/28 :10:59 AM
 */
public interface INativeHandler {

    public void onCallMethod(MethodCall call, MethodChannel.Result result,
                             Handler handler, Activity activity);
}
