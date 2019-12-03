package com.victor.clips.flutter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.victor.clips.util.Loger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * @author: archko 2019/11/28 :10:25 AM
 */
public class FlutterMainPlugin {
    public static final String HTTP_CHANNEL = "http_channel";
    public static final String ROUTE_CHANNEL = "route_channel";
    public static final String URL_CHANNEL = "url_channel";
    private static FlutterMainPlugin sInstance = null;
    private static Handler mHandler = null;

    public static FlutterMainPlugin getInstance() {
        if (sInstance == null) {
            throw new RuntimeException("Flutter not register yet!");
        }
        return sInstance;
    }

    public static void registerWith(BinaryMessenger binaryMessenger) {
        sInstance = new FlutterMainPlugin(binaryMessenger);
        mHandler = new Handler(Looper.getMainLooper());
    }

    private final Set<MethodChannel.MethodCallHandler> mMethodCallHandlers = new HashSet<>();
    private final Map<String, INativeHandler> mINativeHandlers = new HashMap<>();
    private MethodChannel mMethodChannel;

    private FlutterMainPlugin(BinaryMessenger binaryMessenger) {
        mMethodChannel = new MethodChannel(binaryMessenger, "aeyepetizer_channel");

        mMethodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                Object[] handlers;
                synchronized (mMethodCallHandlers) {
                    handlers = mMethodCallHandlers.toArray();
                }

                for (Object o : handlers) {
                    ((MethodChannel.MethodCallHandler) o).onMethodCall(methodCall, result);
                }
            }
        });

        addMethodCallHandler(new NativeMethodCallHandler());
        registDefaultHandler();
    }

    public void registDefaultHandler() {
        addNativeHandler(HTTP_CHANNEL, new NetworkHandler());
        addNativeHandler(ROUTE_CHANNEL, new RouteHandler());
        addNativeHandler(URL_CHANNEL, new UrlHandler());
    }

    public void addMethodCallHandler(MethodChannel.MethodCallHandler handler) {
        synchronized (mMethodCallHandlers) {
            mMethodCallHandlers.add(handler);
        }
    }

    public void removeMethodCallHandler(MethodChannel.MethodCallHandler handler) {
        synchronized (mMethodCallHandlers) {
            mMethodCallHandlers.remove(handler);
        }
    }

    public void addNativeHandler(String key, INativeHandler handler) {
        synchronized (mMethodCallHandlers) {
            mINativeHandlers.put(key, handler);
        }
    }

    public void removeNativeHandler(INativeHandler handler) {
        synchronized (mINativeHandlers) {
            mINativeHandlers.remove(handler);
        }
    }

    class NativeMethodCallHandler implements MethodChannel.MethodCallHandler {

        @Override
        public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
            Activity activity = FlutterBridge.getInstance().getCurrentActiveActivity();
            Loger.Companion.d("method", methodCall.method);
            switch (methodCall.method) {
                case HTTP_CHANNEL:
                case ROUTE_CHANNEL:
                case URL_CHANNEL: {
                    INativeHandler nativeHandler = mINativeHandlers.get(methodCall.method);
                    nativeHandler.onCallMethod(methodCall, result, mHandler, activity);
                    break;
                }

                default: {
                    result.notImplemented();
                }
            }
        }
    }
}
