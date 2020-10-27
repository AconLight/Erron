package com.redartedgames.ball.splash;

import com.redartedgames.ball.myobjects.LoadingSprite;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

public class SplashWorld extends World{

	public SplashWorld() {
		super(); 
		gameObjects.add(new LoadingSprite(Consts.gameWidth/2, Consts.gameHeight/4, 0, null));
	}
	
	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public void restart() {

	}
	


}
