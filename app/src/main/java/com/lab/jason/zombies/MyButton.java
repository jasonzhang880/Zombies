package com.lab.jason.zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;


/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class MyButton extends Spirit {
    private Boolean isClick=false;
    private Bitmap pressedBitmap;

    public MyButton(Bitmap defaultBitMap, Point mPoint,Bitmap pressedBitmap) {
        super(defaultBitMap, mPoint);
        this.pressedBitmap=pressedBitmap;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (isClick) {
            //绘制按下图片
            Log.d("rect","Button绘制按下时图片");
            canvas.drawBitmap(pressedBitmap,mPoint.x,mPoint.y,new Paint());
        }
        else {
            //绘制默认图片
            Log.d("rect","Button绘制默认图片");
            super.drawSelf(canvas);
        }
    }
    /**根据点的位置 判断当前按钮是否被点击*/
    public Boolean isPressed(Point touchPoint) {
        Rect rect=new Rect(mPoint.x,mPoint.y,
                mPoint.x+defaultBitMap.getWidth(),mPoint.y+defaultBitMap.getHeight());

        isClick= rect.contains(touchPoint.x,touchPoint.y);
        Log.d("rect","button被按下"+isClick);
        return isClick;
    }
    public void setPressed(Boolean b) {
        isClick=b;
    }
}
