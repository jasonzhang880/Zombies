package com.lab.jason.zombies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewDebug;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class GameUI extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private RenderThread thread;
    private Boolean flag;
    private Man man;
    private Face face;
    private MyButton button;

    public GameUI(Context context) {
        super(context);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
    }

    public void handleTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int  rawX=(int) event.getRawX();
                int  rawY=(int) event.getRawY();
                face=man.createFace(getContext(),new Point(rawX,rawY));

        }
    }


    //保证线程有效在SurfaceHolder.Callback.surfaceCreate()和SurfaceHolder.Callback.surfaceDestroy()之间调用
    private class RenderThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (flag) {
                long start=System.currentTimeMillis();
                drawUI();
                long end=System.currentTimeMillis();
                long dTime=end-start;
                int fps=(int)(1000/dTime);
                Log.d("z",""+fps);
            }
        }
    }
//获取画布绘制界面
    public void drawUI() {
        Canvas lockCanvas=surfaceHolder.lockCanvas();//默认锁定整个屏幕
//        Paint paint=new Paint();
//        paint.setColor(Color.RED);
//        lockCanvas.drawRect(new Rect(0,0,100,100),paint);

        man.drawSelf(lockCanvas);
        if (face!=null) {
            face.drawSelf(lockCanvas);
        }
        button.drawSelf(lockCanvas);

        surfaceHolder.unlockCanvasAndPost(lockCanvas);
    }

    //下面三个方法必须在SurfaceHolder.addCallback()调用才能生效
    //Surface创建时调用
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        Bitmap manBitMap=BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        man=new Man(manBitMap,new Point(0,0));

        Bitmap buttonBitMap=BitmapFactory.decodeResource(getResources(),R.drawable.downloads);
        button=new MyButton(buttonBitMap,new Point(0,getHeight()-600));

        thread=new RenderThread();
        flag=true;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
//Surface 不可见时调用。销毁Surface
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        flag=false;
    }
}
