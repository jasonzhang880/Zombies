package com.lab.jason.zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class MyButton extends Spirit {
    private Boolean isClick;
    private Bitmap pressedBitmap;

    public MyButton(Bitmap defaultBitMap, Point mPoint,Bitmap pressedBitmap) {
        super(defaultBitMap, mPoint);
        this.pressedBitmap=pressedBitmap;
    }

}
