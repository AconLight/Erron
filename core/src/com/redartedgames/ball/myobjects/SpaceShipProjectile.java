package com.redartedgames.ball.myobjects;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class SpaceShipProjectile extends Ball{

	public boolean isReady;
	
	public boolean isPlayer;
	
	public int side;
	
	public SpaceShipProjectile(float x, float y) {
		super(x, y, 50, 1, BehaviorMode.kinematic, null, 0);
		isReady = true;
	}
	
	public void shoot(int side, boolean isPlayer) {
		this.side = side;
		this.isPlayer = isPlayer;
	}
	
	public void destroy() {
		
	}

}
