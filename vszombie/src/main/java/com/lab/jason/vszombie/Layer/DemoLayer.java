package com.lab.jason.vszombie.Layer;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class DemoLayer extends CCLayer {
    private CCTMXTiledMap map;
    private ArrayList<CGPoint> roadPoints;
    private CCSprite sprite;
    public DemoLayer() {
        setIsTouchEnabled(true);//让ccTouchesMoved()方法生效
        init();
    }

    private void init() {
        loadMap();

        loadRoad();

        loadZombie();
    }

    private void loadZombie() {
        sprite=CCSprite.sprite("z_1_01.png");
        sprite.setAnchorPoint(0.5f,0);//设置锚点
        //设置僵尸位置
        CGPoint location =roadPoints.get(0);
        sprite.setPosition(location);
        Log.d("rect","坐标"+location.x+"  "+location.y);
        sprite.setFlipX(true);
        sprite.setScale(0.65);
        map.addChild(sprite);
        walk();

        moveToNext();
    }

    int count=0;
    int speed=50;
    public void moveToNext() {
        count ++;
        if (count<roadPoints.size()) {
            CGPoint point = roadPoints.get(count);
            float time=CGPointUtil.distance(roadPoints.get(count-1),point)/speed; //计算两个点以50速度匀速运动所需的时间
            CCMoveTo move = CCMoveTo.action(time, point);

            CCCallFunc func = CCCallFunc.action(this, "moveToNext");//通过反射调用moveToNext()方法
            CCSequence sequence = CCSequence.actions(move, func);
            sprite.runAction(sequence);
        }
    }

    private void walk() {
        ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
        String format="z_1_%02d.png";
        for (int i = 1; i <= 7; i++) {
            CCSpriteFrame frame = CCSprite.sprite(String.format(format, i)).displayedFrame();
            frames.add(frame);
        }  //创建一个帧数组

        CCAnimation anim = CCAnimation.animation("walk", .2f, frames); //命名、播放时间间隔、List数组
        CCAnimate animate = CCAnimate.action(anim);
        CCRepeatForever forever=CCRepeatForever.action(animate);
        sprite.runAction(forever);

    }


    private void loadMap() {
        map=CCTMXTiledMap.tiledMap("map.tmx");
        // 把地图设置到地图的中心的位置
        map.setAnchorPoint(0.5f,0.5f);
        map.setPosition(map.getContentSize().getWidth()/2,map.getContentSize().getHeight()/2);
        this.addChild(map);
    }

    private void loadRoad() {
        roadPoints=new ArrayList<>();
        //获取road对象
        CCTMXObjectGroup objectGroupNamed=map.objectGroupNamed("road");
        ArrayList<HashMap<String,String>> objects=objectGroupNamed.objects;
        for (HashMap<String,String> map:objects) {
            float x=Float.parseFloat(map.get("x"));
            float y=Float.parseFloat(map.get("y"));
            CGPoint point= CCNode.ccp(x,y);

            roadPoints.add(point);

        }
    }

    @Override
    public boolean ccTouchesMoved(MotionEvent event) {
        map.touchMove(event,map);     //令地图随着手指移动而移动
        return super.ccTouchesMoved(event);
    }
}
