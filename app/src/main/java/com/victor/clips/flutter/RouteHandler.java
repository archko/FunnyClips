package com.victor.clips.flutter;

import android.app.Activity;
import android.os.Handler;

import com.victor.clips.ui.AFlutterActivity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * @author: archko 2019/11/28 :11:00 AM
 */
public class RouteHandler implements INativeHandler {

    @Override
    public void onCallMethod(MethodCall call, MethodChannel.Result result,
                             Handler handler, Activity activity) {
        ///home/my/
        String path = call.argument("path");

        AFlutterActivity.startActivity(activity, path);
        return;
    }


}
