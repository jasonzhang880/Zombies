package com.lab.jason.vszombie.Layer;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class FirstLayer extends CCLayer {
    public static final int TAG=10;
    public FirstLayer() {

        setIsTouchEnabled(true);//打开点击事件,才能相应触摸事件

        //创建精灵
        //所有图片放到assets根目录下
        //必须有一张fps_images.png默认图片
        Log.d("rect","创建精灵");


        CCSprite sprite=CCSprite.sprite("z_1_01.png");

        sprite.setFlipX(true);//水平翻转
//        sprite.setFlipY(true);//水平翻转
//        sprite.setOpacity(100);//设置不透明度 0-255 0代表完全透明
        sprite.setAnchorPoint(ccp(0,0));//设置锚点

        CCSprite sprite2=CCSprite.sprite("z_1_01.png");

        sprite2.setAnchorPoint(ccp(0,0));//设置锚点
        sprite2.setPosition(300,0);    //设置精灵位置

        this.addChild(sprite,1,TAG);      //默认情况下后添加的精灵在最上层，精灵默认优先级为0,优先级高的显示在上层
        //添加精灵
        this.addChild(sprite2);  //添加僵尸的时候，第二个参数为优先级，第三个是给精灵添加一个标签
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {

        CCSprite ccSprite=(CCSprite)this.getChildByTag(TAG);

//获取精灵图片的矩形坐标
        CGRect boundingBox=ccSprite.getBoundingBox();
        //将android坐标系中的点装换成cocos2d坐标系中的点
        CGPoint point=convertTouchToNodeSpace(event);
        if(CGRect.containsPoint(boundingBox,point)) {
            Log.d("rect","触摸到僵尸");
            ccSprite.setVisible(false);//设置精灵不可见
            //ccSprite.setOpacity(0) 或者 设置精灵不透明度为0，即完全透明
        }
        return true;
    }
}
