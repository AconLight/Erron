package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class ActiveImp extends Imp{

	public ActiveImp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, m, parent, id);
		type = ACTIVE_TYPE;
		forwardColor = new Color(70/256f, 40/256f, 40/256f, 1);
		backwardColor = new Color(60/256f, 0/256f, 0/256f, 1);
		stack = 0;
	}
	

	public int stack;
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		if (playerMovesData == null) return;
		if (((ReversableMovement)movement).getIsForward() && stack >= 0 && stack < playerMovesData.accelerationsX.size() && playerMovesData.accelerationsX.size() > 0) {
			((ReversableMovement) movement).addCollisionAcc(playerMovesData.accelerationsX.get(stack), playerMovesData.accelerationsY.get(stack));
			//playerMovesData.accelerationsX.remove(0);
			//playerMovesData.accelerationsY.remove(0);
		}
		if(((ReversableMovement)movement).getIsForward()) {
			stack++;
		}
		if(!((ReversableMovement)movement).getIsForward()) {
			stack--;
		}
		if (!((ReversableMovement)movement).getIsForward() && stack >= 0 && stack < playerMovesData.accelerationsX.size() && playerMovesData.accelerationsX.size() > 0) {
			((ReversableMovement) movement).addCollisionAcc(playerMovesData.accelerationsX.get(stack), playerMovesData.accelerationsY.get(stack));
			//playerMovesData.accelerationsX.remove(0);
			//playerMovesData.accelerationsY.remove(0);
		}
//		Gdx.app.log("size", "" + playerMovesData.accelerationsX.size());
//		Gdx.app.log("stack", "" + stack);
	}

}
