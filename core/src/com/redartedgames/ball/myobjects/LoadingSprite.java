package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;

import java.math.BigDecimal;

public class LoadingSprite extends ReversableObject{

	public SpriteObject sprite, red;
	Player player;
	float k = 100;
	float dx = 0, dy = 110;
	boolean isAttached;
	Vector2 tmp;

	public LoadingSprite(float x, float y, int id, GameObject parent) {
		super(x, y, parent, id);
		isAttached = true;
		sprite = new SpriteObject(x, y, this, 0);
		red = new SpriteObject(x, y + Consts.gameHeight*0.4f, this, 0);
		red.addTexture("graphic/red.png");
		sprite.addTexture("graphic/load1.png");
		sprite.addTexture("graphic/load2.png");
		sprite.addTexture("graphic/load3.png");
		sprite.addTexture("graphic/load4.png");
		gameObjects.add(sprite);
		gameObjects.add(red);
		sprite.sclX = 1f;
		sprite.sclY = 1f;
		sprite.R = 0;
		sprite.G = 0;
		sprite.B = 0;
	}
	
	public void render(SpriteBatch batch, int priority) {

		batch.setColor(0/256f, 0/256f, 0/256f, 1);
		sprite.render(batch, priority);
		batch.setColor(1, 1, 1, 1);
		red.render(batch, priority);
	}
	

}
