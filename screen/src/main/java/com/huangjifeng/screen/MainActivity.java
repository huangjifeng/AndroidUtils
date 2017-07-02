package com.huangjifeng.screen;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.huangjifeng.screen.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private OrientationEventListener orientationEventListener;
    private TextView text_angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("haha", "----onCreate()");
        setContentView(R.layout.activity_main);
        text_angle = (TextView) findViewById(R.id.text_angle);

        /**
         *
         * */
        orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.v("haha", "current angle = " + orientation);
                text_angle.setText("当前角度是 ： " + orientation);
            }
        };
        orientationEventListener.enable();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("haha", "----onResume()");


        /**
         * 通过Configuration这个类来识别屏幕的方向
         * */
        Log.v("haha", "Screen  direction  isPortrait = " + ScreenUtils.isPortrait(this));


        /**
         * 通过Display将屏幕的宽高赋值给point
         * Point holds two integer coordinates
         * */
        Point point = new Point();
        ScreenUtils.getScreenPixels(this, point);
        Log.v("haha", "Screen pixels   width = " + point.x + " , height = " + point.y);


        /**
         * 通过DisplayMetrics来获取屏幕信息
         * A structure describing general information about a display, such as its size, density, and font scaling.
         * */
        int[] metrics = ScreenUtils.getScreenPixels(this);
        Log.v("haha", "Screen pixels   width = " + metrics[0] + " , height = " + metrics[1]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("haha", "----onDestroy()");
        orientationEventListener.disable();
    }
}
