package com.huangjifeng.screen.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ScreenUtils {

    /**
     * 通过Configuration这个类来识别屏幕的方向
     */
    public static boolean isPortrait(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return true;
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        }
        return true;
    }

    /**
     * 通过Display将屏幕的宽高赋值给point
     * Point holds two integer coordinates
     */
    public static Point getScreenPixels(Activity activity, Point point) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        display.getSize(point);
        return point;
    }

    /**
     * 通过DisplayMetrics来获取屏幕信息
     * A structure describing general information about a display, such as its size, density, and font scaling.
     * */
    public static int[] getScreenPixels(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        return new int[]{screenWidth, screenHeight};
    }


}
