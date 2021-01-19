package com.redartedgames.ball.graphicgenerators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.comic.Comic;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.screen.Consts;

public class BlackScreenAnimation {
	int width = 20;
	public int direction = 0;
	float speed = 10;
	Random rand;
	ArrayList<Float> rects, speeds;
	public boolean hasEnded;
	public boolean isOn;
	public Comic comic;
	
	public BlackScreenAnimation() {
		comic = new Comic();
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
		comic.update(delta);
		boolean flag = true;
		float k = 0f;
		if (direction > 0) {
			k = 1f;
		}
		for(int i = 0; i < rects.size(); i++) {
			if (rects.get(i) < Consts.gameHeight && direction > 0 || rects.get(i) > 0 && direction < 0) {
				flag = false;
				float ff = (float) (1f - Math.sin(0.67f*Math.PI*(speeds.size()-i*0.5f)/speeds.size()));
				ff = ff;
				rects.set(i, (float) (rects.get(i) + (direction*(rand.nextInt((int) speed*4)))*delta));
				rects.set(i, (float) (rects.get(i) + speed*3*ff*delta));
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

		if (isOn) {
			comic.render(batch);
			float f = 0f;//(Consts.gameHeight-rects.get(0)*1f) / Consts.gameHeight;
			batch.setColor(0.05f*f, 0.03f*f, 0.03f*f, 1);
			for (int i = 0; i < rects.size(); i++) {
//			if (direction < 0) {
				batch.draw(GameObject.dotTex, i * width, 0, width, rects.get(i));
//			}
//			else
				batch.draw(GameObject.dotTex, i * width, Consts.gameHeight - rects.get(i), width, rects.get(i));
			}
		}
		
	}
}
