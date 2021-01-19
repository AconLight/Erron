package com.redartedgames.ball.graphicgenerators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.screen.Consts;

public class BlackScreenAnimation {
	int width = 20;
	public int direction = 0;
	float speed = 20;
	Random rand;
	ArrayList<Float> rects, speeds;
	public boolean hasEnded;
	public boolean isOn;
	
	public BlackScreenAnimation() {
		rand = new Random();
		rects = new ArrayList<>();
		speeds = new ArrayList<>();
		for (int i = 0; i < Consts.gameWidth/width + 1; i++) {
			rects.add(0f);
			speeds.add((float) rand.nextInt((int) speed/2));
		}
		isOn = false;
	}
	
	public void reset() {
		for(int i = 0; i < rects.size(); i++) {
			rects.set(i, (float)Consts.gameHeight);
		}
		direction = -1;
		isOn = true;
	}
	
	public void update(float delta) {
		boolean flag = true;
		float k = 0f;
		if (direction > 0) {
			k = 1f;
		}
		for(int i = 0; i < rects.size(); i++) {
			if (rects.get(i) < Consts.gameHeight*0.8f && direction > 0 || rects.get(i) > Consts.gameHeight*0.2f && direction < 0) {
				flag = false;
				rects.set(i, (float) (rects.get(i) + direction*(speed*(k - direction*Math.sin(Math.PI*(speeds.size()-i)/speeds.size())*Math.sin(Math.PI*(speeds.size()-i)/speeds.size())) + speeds.get(i) + rand.nextInt((int) speed))*delta));
			}

		}
		hasEnded = flag;
		//Gdx.app.log("blackScreenAnimation", "flag: " + flag);
	}
	
	public void animateOn() {
		direction = 1;
		hasEnded = false;
		//Gdx.app.log("blackScreenAnimation", "animateOn");
	}
	
	public void animateOff() {
		direction = -1;
	}
	
	public void render(SpriteBatch batch) {
		batch.setColor(0.07f, 0.07f, 0.07f, 1);
		if (isOn)
		for(int i = 0; i < rects.size(); i++) {
//			if (direction < 0) {
				batch.draw(GameObject.dotTex, i*width, 0, width, rects.get(i));
//			}
//			else
				batch.draw(GameObject.dotTex, i*width, Consts.gameHeight - rects.get(i), width, rects.get(i));
		}
		
	}
}
