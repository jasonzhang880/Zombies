package com.lab.jason.vszombie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lab.jason.vszombie.Layer.ActionLayer;
import com.lab.jason.vszombie.Layer.DemoLayer;
import com.lab.jason.vszombie.Layer.FirstLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends Activity {
    private CCDirector ccDirector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CCGLSurfaceView surfaceView=new CCGLSurfaceView(this);
        setContentView(surfaceView);
//采用单例设计模式
        ccDirector=CCDirector.sharedDirector();//创建导演对象
        ccDirector.attachInView(surfaceView);//开启一个线程

        ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        ccDirector.setScreenSize(480,320);//设置屏幕的分辨率 自动适配
        ccDirector.setDisplayFPS(true);   //显示帧率
        ccDirector.setAnimationInterval(1.0f/30); //设置帧率1秒30帧

        CCScene ccScene=CCScene.node();//创建一个场景，作为需要展示内容的根节点

        Log.d("rect","创建图层");
//        FirstLayer layer=new FirstLayer();

//        ActionLayer layer=new ActionLayer();

        DemoLayer layer=new DemoLayer();
        ccScene.addChild(layer);  //添加图层
        ccDirector.runWithScene(ccScene); //设置场景


    }
    //导演的生命周期和Activity的生命周期一致onResume()/pause()/end()
    @Override
    protected void onResume() {
        super.onResume();
        ccDirector.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ccDirector.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ccDirector.end();
    }
}
