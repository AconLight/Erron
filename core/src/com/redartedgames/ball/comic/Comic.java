package com.redartedgames.ball.comic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;

public class Comic {

	SpriteObject animation;
	SpriteObject overlay;
	Letters[] letters = {};

	public Comic() {
	}

	int[] sizes = {14, 50};
	float[] scales = {1.8f, 1.2f};
	Letters[][] texts = {
			{new Letters(400, 900, "somewhere in the abyss of the dark..."), new Letters(350, 100, "...an entity was awaiting for its rebirth")}
	};

	public void load(int lvl) {
		if (lvl > 2) {
			return;
		}
		letters = texts[lvl-1];
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
		for (Letters l: letters) {
			l.render(batch);
		}
	}
}
