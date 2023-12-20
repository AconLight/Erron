package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

import java.util.Random;

public class Plant extends ReversableObject{

	SpriteObject sprite, spritef;
	Random r = new Random();
	float myScl = (int)(r.nextFloat()*2) + 3;
	boolean myIsOn = false;

	Player player;

	float scl = 1;
	float sclV = 0.97f;

	float myPosX, myPosY;

	public Plant(float x, float y, int id, GameObject parent, float width) {
		super(x, y, parent, id);
		this.player = GameWorld.staticPlayer;
		myScl *= Math.min(width / 300f, 1);
		sprite = new SpriteObject(x-myScl*14, y+myScl*14, null, 0);
		spritef = new SpriteObject(x-myScl*14, y+myScl*14, null, 0);
		if (r.nextInt()%3 == 0) {
			myIsOn = true;
			sprite.addTexture("graphic/plants/plant0.png");
			sprite.addTexture("graphic/plants/plant1.png");
			sprite.addTexture("graphic/plants/plant2.png");
			gameObjects.add(sprite);
			sprite.sclX = myScl;
			sprite.sclY = myScl;


			spritef.addTexture("graphic/plants/plantf0.png");
			spritef.addTexture("graphic/plants/plantf1.png");
			spritef.addTexture("graphic/plants/plantf2.png");
			spritef.setColor(220 / 256f, 40 / 256f, 20 / 256f, 1f);
			gameObjects.add(spritef);
			spritef.sclX = myScl;
			spritef.sclY = myScl;

			sprite.frameNum = Math.abs(r.nextInt()) % 3;
			spritef.frameNum = sprite.frameNum;
		}
		myPosX = /*getPosition().x + */sprite.getPosition().x;
		myPosY = /*getPosition().y + */sprite.getPosition().y;
	}

	public boolean shouldGrow = true;

	@Override
	public void updateLast(float delta, float vx, float vy) {
		super.updateLast(delta, vx, vy);
		this.player = GameWorld.staticPlayer;

		if (player != null) {
			float ry = player.getPosition().y - myPosY;
			float rx = player.getPosition().x - myPosX;
			Gdx.app.log("r", "" + (ry * ry + rx * rx));
			if (ry * ry + rx * rx > 550 * 550) {
				shouldGrow = true;
			} else if (ry * ry + rx * rx > 350 * 350) {
				shouldGrow = true;
			} else {
				shouldGrow = false;

			}
		}
		if (shouldGrow) {
			scl +=  delta * 0.01f;
			scl /= sclV;
			if (scl > 1) scl = 1;
		}
		else {
			scl *= sclV;
		}
		sprite.sclX = myScl*scl;
		sprite.sclY = myScl*scl;
		spritef.sclX = myScl*scl;
		spritef.sclY = myScl*scl;
		spritef.setColor(220 / 256f, 40 / 256f, 20 / 256f, scl*scl);
		sprite.setPosition(new Vector2(myPosX, myPosY - 15*myScl -15 *(1 - sprite.sclY)));
		spritef.setPosition(new Vector2(myPosX, myPosY - 15*myScl - 15*(1 - spritef.sclY)));
	}
	
	public void render(SpriteBatch batch, int priority) {
//		batch.setColor(20/256f, 120/256f, 20/256f, 1);
		if (myIsOn) {
			sprite.render(batch, priority);
			spritef.render(batch, priority);
		}
	}
	

}
