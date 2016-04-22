package com.lab.jason.zombies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Man extends Spirit{

    public Man(Bitmap defaultBitMap, Point mPoint) {
        super(defaultBitMap, mPoint);
    }

    public Face createFace(Context context, Point targetPoint) {
        Bitmap faceBitMap= BitmapFactory.decodeResource(context.getResources(),R.drawable.picasa);
        Face face=new Face(faceBitMap,new Point(this.mPoint.x+160,this.mPoint.y+160),targetPoint);
        return face;
    }
}
