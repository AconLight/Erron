package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

import java.math.BigDecimal;

public class LoadingSprite extends ReversableObject{

	SpriteObject sprite;
	Player player;
	float k = 100;
	float dx = 0, dy = 110;
	boolean isAttached;
	Vector2 tmp;

	public LoadingSprite(float x, float y, int id, GameObject parent) {
		super(x, y, parent, id);
		isAttached = true;
		sprite = new SpriteObject(x, y, this, 0);
		sprite.addTexture("graphic/load.png");
		gameObjects.add(sprite);
		sprite.sclX = 1.4f;
		sprite.sclY = 1.4f;
	}
	
	public void render(SpriteBatch batch, int priority) {
		batch.setColor(250/256f, 250/256f, 250/256f, 1);
		sprite.render(batch, priority);
	}
	

}
