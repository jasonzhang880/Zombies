package com.lab.jason.zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Spirit {

    protected Bitmap defaultBitMap;
    protected Point mPoint;

    public Spirit(Bitmap defaultBitMap,Point mPoint) {
        super();
        this.defaultBitMap=defaultBitMap;
        if (mPoint!=null) {
            this.mPoint=mPoint;
        } else {
            this.mPoint=new Point(0,0);
        }
    }

    public void drawSelf(Canvas canvas) {
        if (defaultBitMap!=null) {
            canvas.drawBitmap(defaultBitMap,mPoint.x,mPoint.y,new Paint());
        }
    }

}
