package com.huangjifeng.androidutils;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /**
    * Handler  的正确使用
    * http://blog.csdn.net/u012221046/article/details/52387024
    *
    * 在Java中，非静态的内部类和匿名内部类都会隐式地持有其外部类的引用。静态的内部类不会持有外部类的引用。
    *
    * 正常Activitiy finish后，已经没有必要对消息处理，那需要怎么做呢？
    * 解决方案也很简单，在Activity onStop或者onDestroy的时候，取消掉该Handler对象的Message和Runnable。
    * 通过查看Handler的API，它有几个方法：removeCallbacks(Runnable r)和removeMessages(int what)等。
    *
    * */
    private MyHandler myHandler = new MyHandler(MainActivity.this);

    private static class MyHandler extends Handler {

        private final WeakReference<Activity> weakReference;

        public MyHandler(Activity activity) {
            weakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MainActivity mainActivity = (MainActivity) weakReference.get();
                    Toast.makeText(mainActivity, "现在是 " + mainActivity.count, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    /**
    * Timer就是一个线程，使用schedule方法完成对TimerTask的调度，多个TimerTask可以共用一个Timer，
    * 也就是说Timer对象调用一次schedule方法就是创建了一个线程，并且调用一次schedule后TimerTask是
    * 无限制的循环下去的，使用Timer的cancel()停止操作。当然同一个Timer执行一次cancel()方法后，所有Timer线程都被终止。
    * */
    private Timer mTimer;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_timer:
                startTimer();
                break;
            case R.id.stop_timer:
                stopTimer();
                break;
        }
    }

    private void stopTimer() {
        mTimer.cancel();
    }

    private void startTimer() {
        //true 说明这个timer以daemon方式运行（优先级低，程序结束timer也自动结束）,,最好不要设置为true，
        // 因为cancel()方法后也不会自动停止
        mTimer = new Timer();
        count = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //注意，这里是异步的，所以不要再这里进行UI方面的操作
                ++count;
                Message message = myHandler.obtainMessage();
                message.what = 1;
                myHandler.sendMessage(message);
            }
        };

        /**
        * schedule方法有三个参数
        * 第一个参数就是TimerTask类型的对象，我们实现TimerTask的run()方法就是要周期执行的一个任务；
        * 第二个参数有两种类型，第一种是long类型，表示多长时间后开始执行，另一种是Date类型，表示从那个时间后开始执行；
        * 第三个参数就是执行的周期，为long类型。
        * 下面参数的意思是1秒之后每隔1秒运行一次timerTask里面的run方法
        * */
        mTimer.schedule(timerTask, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);   //一切都是为了不要让mHandler拖泥带水
    }
}

