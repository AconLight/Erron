package com.redartedgames.ball.splash;

import com.redartedgames.ball.myobjects.LoadingSprite;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

public class SplashWorld extends World{

	LoadingSprite sprite;

	public SplashWorld() {
		super();
		sprite = new LoadingSprite(Consts.gameWidth/2, Consts.gameHeight/4, 0, null);
		gameObjects.add(sprite);
	}
	float time = 0;
	@Override
	public void update(float delta) {
		time += delta;
		sprite.sprite.frameNum = ((int)(time*0.5)) % 4;
	}
	
	@Override
	public void restart() {

	}
	


}
