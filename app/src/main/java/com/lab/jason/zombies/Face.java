package com.lab.jason.zombies;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Face extends Spirit {
    private Point targetPoint;
    public Face(Bitmap defaultBitMap, Point mPoint ,Point targetPoint) {
        super(defaultBitMap, mPoint);
        this.targetPoint=targetPoint;
    }
}
