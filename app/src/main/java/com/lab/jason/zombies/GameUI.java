package com.lab.jason.zombies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class GameUI extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RenderThread thread;
    private Boolean flag;
    private Man man;
    private MyButton button;
    private CopyOnWriteArrayList<Face> faces=new CopyOnWriteArrayList<>();

    public GameUI(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);//令SurfaceHolder.Callback接口的三个方法生效
    }
    //保证线程有效在SurfaceHolder.Callback.surfaceCreate()和SurfaceHolder.Callback.surfaceDestroy()之间调用
    private class RenderThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (flag) {
                long startTime = System.currentTimeMillis();
                try{
                    drawUI();
                }catch(Exception e){
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                long dTime = endTime - startTime;
            }
        }
    }
    //获取画布绘制界面
    public void drawUI() {
        Log.d("rect","drawUI被调用");
        Canvas lockCanvas = holder.lockCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
       //清空画布
        lockCanvas.drawRect(0, 0, getWidth(), getHeight(), paint);


        Log.d("rect","绘制小猫");
        man.drawSelf(lockCanvas);

        for (Face face : faces) {
            Log.d("rect","绘制笑脸");
            face.drawSelf(lockCanvas);
            face.move();
            if (face.mPoint.x < 0 || face.mPoint.x > getWidth()
                    || face.mPoint.y < 0 || face.mPoint.y > getHeight()) {
                faces.remove(face);
            }
        }
        Log.d("rect","绘制按钮");
        button.drawSelf(lockCanvas);
        holder.unlockCanvasAndPost(lockCanvas);
    }
    public void handleTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                Log.d("rect","监听到触摸");
                int  rawX=(int) event.getRawX();
                int  rawY=(int) event.getRawY();
                Point point=new Point(rawX,rawY);

                if (button.isPressed(point)) {
                    button.setPressed(true);
                    button.Click();
                }else {
                    Face face = man.createFace(getContext(), point);
                    faces.add(face);
                }
                break;

            case MotionEvent.ACTION_UP:
                button.setPressed(false);
                break;


        }
    }

    //下面三个方法必须在SurfaceHolder.addCallback()调用才能生效
    //Surface创建时调用
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        Bitmap manBitMap=BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        man=new Man(manBitMap,new Point(400,0));

        Bitmap defaultBitMap=BitmapFactory.decodeResource(getResources(),R.drawable.btn_default_green);
        Bitmap buttonPress=BitmapFactory.decodeResource(getResources(),R.drawable.btn_pressed_yellow);
        button=new MyButton(defaultBitMap,new Point(400,getHeight()-600),buttonPress);
        button.setListener(new MyButton.OnClickListener() {
            @Override
            public void onClick() {
                man.move();
            }
        });

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
