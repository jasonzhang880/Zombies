package com.lab.jason.vszombie.Layer;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseIn;
import org.cocos2d.actions.ease.CCEaseOut;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.ccColor3B;

public class ActionLayer extends CCLayer {
	public ActionLayer() {
		init();
	}

	private void init() {
		// moveTo();
		// moveBy();
		// jump();
		// bezier();
		// ease();
		// scale();
		// rotateTo();
		// rotateBy();
		// tint();
		// ��˸(Blink)
		// blink();
		// fadeTo();

		// complex();
		walk();
	}

	private void walk() {
		ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
		// ��7֡ȫ����ӵ�������
		String format="z_1_%02d.png";//%2d ��ʾ��λ����      ������λǰ�油0  
		for (int i = 1; i <= 7; i++) {
			CCSpriteFrame frame = CCSprite.sprite(String.format(format, i)).displayedFrame();
			frames.add(frame);
		}
		// ����1 name ����2 ÿһ֡�ļ��ʱ�� ����3 ���ŵ�����֡
		CCAnimation anim = CCAnimation.animation("walk", .2f, frames);
		CCAnimate animate = CCAnimate.action(anim);
		//  ����֡ Ĭ������� ��������ֹͣ��ѭ��  
		CCRepeatForever forever=CCRepeatForever.action(animate);
		CCSprite sprite = getSprite();
		sprite.setFlipX(true);
		sprite.runAction(forever);
		CCMoveTo moveTo = CCMoveTo.action(5, ccp(300, 0));
		sprite.runAction(moveTo);
	}

	private void complex() {
		// ��շ� ��Ծ����,��������ת
		CCJumpBy jumpBy = CCJumpBy.action(4, ccp(300, 200), 100, 2);
		CCRotateBy rotateBy = CCRotateBy.action(2, 360);
		// ����ִ�еĶ���
		CCSpawn spawn = CCSpawn.actions(jumpBy, rotateBy);
		CCIntervalAction reverse = spawn.reverse();
		// ����
		CCSequence sequence = CCSequence.actions(spawn, reverse);
		// ����ֹͣ��ѭ��
		CCRepeatForever forever = CCRepeatForever.action(sequence);
		CCSprite sprite = getSprite();
		sprite.setAnchorPoint(0.5f, 0.5f);
		sprite.setPosition(50, 50);
		sprite.runAction(forever);
	}

	private void fadeTo() {
		CCFadeOut fadeIn = CCFadeOut.action(3);
		getSprite().runAction(fadeIn);
	}

	private void blink() {
		// 1 ʱ�� 2 ����
		CCBlink blink = CCBlink.action(2, 3);
		getSprite().runAction(blink);
	}

	private void tint() {
		// ר����ʾ���� ���������
		// 1 ���ֵ����� 2 �������ʽ ��������,����... 3 ����Ĵ�С
		CCLabel label = CCLabel.labelWithString("��Щ��,�����ں���Ŀ������", "hkbd.ttf",
				20);
		label.setColor(ccc3(0, 100, 255));
		label.setPosition(200, 200);
		this.addChild(label);
		// ����1 ʱ�� RGB
		ccColor3B color3b = CCNode.ccc3(100, 100, 0);

		CCTintBy ccTintBy = CCTintBy.action(1, color3b);
		CCTintBy reverse = ccTintBy.reverse();
		CCSequence sequence = CCSequence.actions(ccTintBy, reverse);
		CCRepeatForever forever = CCRepeatForever.action(sequence);

		label.runAction(forever);
	}

	private void rotateBy() {
		// ��͵��
		// ����1 ����ʱ�� ����2 ��ת�ĽǶ� 0-360
		CCRotateBy rotateBy = CCRotateBy.action(3, 300);
		CCSprite heart = getHeart();
		heart.setPosition(200, 100);
		heart.runAction(rotateBy);
	}

	private void rotateTo() {
		// ͵��
		// ����1 ����ʱ�� ����2 ��ת�ĽǶ� 0-360
		CCRotateTo rotateTo = CCRotateTo.action(3, 300);
		getHeart().runAction(rotateTo);

	}

	private void scale() {
		// 1 ʱ�� 2���ŵı��� ����ê���������
		CCScaleBy ccScaleBy = CCScaleBy.action(0.5f, 1.5f);
		CCScaleBy ccScaleBy2 = ccScaleBy.reverse();
		CCSequence sequence = CCSequence.actions(ccScaleBy, ccScaleBy2);
		CCRepeatForever forever = CCRepeatForever.action(sequence);
		getHeart().runAction(forever);
	}

	private CCSprite getHeart() {
		CCSprite sprite = CCSprite.sprite("heart.png");
		sprite.setAnchorPoint(0.5f, 0.5f);// �޸�ê��
		sprite.setPosition(100, 100);
		this.addChild(sprite);

		return sprite;

	}

	private void ease() {
		CCMoveTo moveTo = CCMoveTo.action(5, ccp(200, 0));
		// 1 ���� 2 ���ٶ�
		CCEaseOut easeOut = CCEaseOut.action(moveTo, 5);

		getSprite().runAction(easeOut);
	}

	private void bezier() {
		// ���ߵĻ�������
		CCBezierConfig c = new CCBezierConfig();
		c.controlPoint_1 = ccp(0, 0);
		c.controlPoint_2 = ccp(100, 100);
		c.endPosition = ccp(200, 0);
		// 1 ʱ�� 2
		CCBezierBy by = CCBezierBy.action(2, c);
		getSprite().runAction(by);
	}

	private void jump() {
		// 1 ʱ�� 2 ��Ծ��Ŀ�ĵ� 3 ��Ծ����ĸ߶� 4 ��Ծ�Ĵ���
		CCJumpBy jumpBy = CCJumpBy.action(2, ccp(200, 200), 100, 2);
		CCJumpBy reverse = jumpBy.reverse();// ��ȡ���෴�Ķ���
		// ���ж��� ���԰��������� ������
		CCSequence sequence = CCSequence.actions(jumpBy, reverse);
		CCRepeatForever forever = CCRepeatForever.action(sequence);// ����ֹͣ��ѭ��
		getSprite().runAction(forever);
	}

	private void moveBy() {
		// ����1 �ƶ��ĳ���ʱ�� ����2 ����ı仯 (x��������200 ,y���겻���޸�)
		CCMoveBy moveBy = CCMoveBy.action(2, ccp(200, 0));
		CCSprite sprite = getSprite();
		sprite.setPosition(0, 100);
		sprite.runAction(moveBy);
	}

	private void moveTo() {
		// 1 �ƶ���ʱ�� ��λ�� 2 �ƶ�����Ŀ�ĵ�
		CCMoveTo moveTo = CCMoveTo.action(2, ccp(200, 0));
		getSprite().runAction(moveTo);
	}

	private CCSprite getSprite() {
		CCSprite sprite = CCSprite.sprite("z_1_attack_01.png");
		sprite.setAnchorPoint(0, 0);// �޸�ê��
		this.addChild(sprite);

		return sprite;

	}
}
