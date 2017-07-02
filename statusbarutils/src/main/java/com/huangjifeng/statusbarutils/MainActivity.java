package com.huangjifeng.statusbarutils;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_01:
                StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
                break;
            case R.id.button_02:
                StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent), 0);
                break;
            case R.id.button_03:
                Scree
                break;
            case R.id.button_04:
                break;
            case R.id.button_05:
                break;
            case R.id.button_06:
                break;
            case R.id.button_07:
                break;
            case R.id.button_08:
                break;
        }
    }
}
