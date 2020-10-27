package com.redartedgames.ball.myobjects;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class SpaceShip extends Ball {

	public Player player;
	
	public SpaceShipProjectile proj;
	
	public int side;
	
	public SpaceShip(float x, float y) {
		super(x, y, 50, 1, BehaviorMode.kinematic, null, 0);
		player = null;
		side = 1;
		proj = new SpaceShipProjectile(x, y);
	}
	
	public void shoot() {
		if (proj.isReady) proj.shoot(side, player != null);
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if (obj instanceof SpaceShipProjectile && c.isTrue) {
			if (player == null && ((SpaceShipProjectile)obj).isPlayer) {
				destroy();
				((SpaceShipProjectile)obj).destroy();
			} else if (player != null && !((SpaceShipProjectile)obj).isPlayer) {
				destroy();
				((SpaceShipProjectile)obj).destroy();
			}
		}
	}
	
	public void destroy() {
		
	}
	
	
	
	
	
	

}
