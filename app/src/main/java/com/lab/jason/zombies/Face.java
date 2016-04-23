package com.lab.jason.zombies;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Face extends Spirit {
    private Point targetPoint;
    //每次绘图移动的距离的变量
    private int tX;
    private int tY;
    public Face(Bitmap defaultBitMap, Point mPoint ,Point targetPoint) {
        super(defaultBitMap, mPoint);
        this.targetPoint=targetPoint;

        int speed=6;
        int X=targetPoint.x-this.mPoint.x;
        int Y=targetPoint.y-this.mPoint.y;
        int D=(int)Math.sqrt(X*X+Y*Y);
        //计算每次移动的距离
        tX=speed*X/D;
        tY=speed*Y/D;
    }
    public void move() {
        this.mPoint.x+=tX;
        this.mPoint.y+=tY;
    }
}
