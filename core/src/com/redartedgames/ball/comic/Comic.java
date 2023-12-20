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

	public int myLvl;

	int[] sizes = {14, 50, 16};
	float[] scales = {1.8f, 1.2f, 5f};
	boolean[] isOvers = {true, true, true};
	boolean isOver = false;
	Letters[][] texts = {
			{new Letters(400, 900, "somewhere in the abyss of darkness..."), new Letters(350, 100, "...an entity was awaiting for its rebirth")},
			{new Letters(370, 900, "at last, the entity broke the shackles..."), new Letters(250, 100, "...and ventured into the dirt driven by hunger")},
			{new Letters(350, 900, "the entity crawled tirelessly to the surface"), new Letters(400, 100, "looking forward to seeing the sky")}
	};

	public void loadNext() {
		load(myLvl+1);
	}

	public void load(int lvl) {
		myLvl = lvl;
		if (lvl > 3) {
			//return;
			lvl = 1;
		}
		letters = texts[lvl-1];
		isOver = isOvers[lvl-1];
		animation = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 1);
		animation.setFrameTime(4f);
		animation.sclX = scales[lvl-1];
		animation.sclY = scales[lvl-1];
		for (int i = 0; i < sizes[lvl-1]; i++) {
			animation.addTexture("graphic/comic/" + lvl + "/" + i + ".png");
		}
		if (isOver) {
			overlay = new SpriteObject(Consts.gameWidth / 2, Consts.gameHeight / 2, null, 1);
			overlay.addTexture("graphic/comic/" + lvl + "/over.png");
			overlay.sclX = scales[lvl - 1];
			overlay.sclY = scales[lvl - 1];
		}
	}
	
	public void update(float delta) {
		animation.updateFrames(delta);
	}
	
	public void start() {

	}

	
	public void render(SpriteBatch batch) {
//		batch.setColor(0.07f, 0.07f, 0.07f, 1);
		animation.render(batch, 1);
		if (isOver) {
			overlay.render(batch, 1);
		}
		for (Letters l: letters) {
			l.render(batch);
		}
	}
}
