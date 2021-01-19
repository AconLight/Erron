package com.redartedgames.ball.comic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;

import java.util.ArrayList;
import java.util.Random;

public class Comic {

	SpriteObject animation;
	SpriteObject overlay;

	public Comic() {
	}

	int[] sizes = {14, 50};
	float[] scales = {1.8f, 1.2f};

	public void load(int lvl) {
		animation = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 1);
		animation.setFrameTime(4f);
		animation.sclX = scales[lvl-1];
		animation.sclY = scales[lvl-1];
		for (int i = 0; i < sizes[lvl-1]; i++) {
			animation.addTexture("graphic/comic/" + lvl + "/" + i + ".png");
		}
		overlay = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 1);
		overlay.addTexture("graphic/comic/" + lvl + "/over.png");
		overlay.sclX = scales[lvl-1];
		overlay.sclY = scales[lvl-1];
	}
	
	public void update(float delta) {
		animation.updateFrames(delta);
	}
	
	public void start() {

	}

	
	public void render(SpriteBatch batch) {
//		batch.setColor(0.07f, 0.07f, 0.07f, 1);
		animation.render(batch, 1);
		overlay.render(batch, 1);
	}
}