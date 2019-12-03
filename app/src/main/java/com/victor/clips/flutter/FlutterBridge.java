package com.victor.clips.flutter;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author: archko 2019/11/28 :10:25 AM
 */
public class FlutterBridge {
    private static FlutterBridge sInstance = null;

    private Activity mCurrentActiveActivity;

    public static FlutterBridge getInstance() {
        if (sInstance == null) {
            sInstance = new FlutterBridge();
        }
        return sInstance;
    }

    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mCurrentActiveActivity = activity;

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (mCurrentActiveActivity == null) {
                }
                mCurrentActiveActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mCurrentActiveActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (mCurrentActiveActivity == activity) {

                    mCurrentActiveActivity = null;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (mCurrentActiveActivity == activity) {
                    mCurrentActiveActivity = null;
                }
            }
        });
        io.flutter.view.FlutterMain.startInitialization(application);
    }

    public Activity getCurrentActiveActivity() {
        return mCurrentActiveActivity;
    }
}
