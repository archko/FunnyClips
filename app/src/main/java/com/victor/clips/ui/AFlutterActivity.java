package com.victor.clips.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.victor.clips.flutter.FlutterMainPlugin;

import io.flutter.app.FlutterActivity;
import io.flutter.view.FlutterView;

/**
 * Created by archko on 2019-11-30.
 */
public class AFlutterActivity extends FlutterActivity {

    public static void startActivity(Activity activity, String routeName) {
        Intent intent = new Intent(activity, AFlutterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("routeName", routeName);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, int requestCode, String routeName) {
        Intent intent = new Intent(activity, AFlutterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("routeName", routeName);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取由上一个页面传过来的routeName
        String routeName = "";
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            routeName = intent.getExtras().getString("routeName");
        }
        //GeneratedPluginRegistrant.registerWith(this);

        // 根据指定routeName创建FlutterView用来展示对应dart中的Widget
        FlutterView flutterView = getFlutterView();//Flutter.createView(this, this.getLifecycle(), routeName);
        //setContentView(flutterView);
        flutterView.setInitialRoute(routeName);

        //MethodChannel 通信方式
        FlutterMainPlugin.registerWith(flutterView);
    }
}
