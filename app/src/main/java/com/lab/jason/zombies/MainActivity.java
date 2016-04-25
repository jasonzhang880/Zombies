package com.lab.jason.zombies;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    private GameUI gameUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameUI=new GameUI(getApplicationContext());
        setContentView(gameUI);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把touch事件传递给GameUI
        gameUI.handleTouch(event);
        return super.onTouchEvent(event);
    }
}
