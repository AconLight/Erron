package com.redartedgames.ball.myobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class LavaRect extends Rect{
	
	Player player;
	boolean isFrozen;
	Float[] freeze;
	float freezeStart;
	Random rand;
	ArrayList<StaticImp> imps;
	ArrayList<LavaRect> rects;
	int rectsWidth = 20;
	
	public LavaRect(float x, float y, float width, float height, GameObject parent, int id, boolean isRects) {
		super(x, y, width, height, BehaviorMode.none, parent, id);
		lavaRect(x, y, width, height, parent, id, isRects);
	}
	
	public LavaRect(float x, float y, float width, float height, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.none, parent, id);
		lavaRect(x, y, width, height, parent, id, true);
	}

	public void lavaRect(float x, float y, float width, float height, GameObject parent, int id, boolean isRects) {
		
		// TODO Auto-generated constructor stub
		rects = new ArrayList<>();
		if (isRects)
		for (int i = 0; i < width / rectsWidth; i++) {
			rects.add(new LavaRect(x -width/2 + rectsWidth/2 + i*rectsWidth, y + height/2, rectsWidth + 0f, 20f, parent, id, false));
		}
		priority = 0;
		isFrozen = false;
		freeze = new Float[(int) (width/4)];
		int idx;
		for (int i = 0; i < freeze.length; i++) {
			idx = i*4;
			freeze[i] = -5f;
		}
		rand = new Random();
		imps = new ArrayList<>();
	}
	float time = 0;
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		
		if (isFrozen) {
			int idx = 0;
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				freeze[i] +=(float) ( 10*(20 + rand.nextInt(1000))*delta/  (  (    Math.abs(freezeStart - idx) + 40)*(Math.sin((freezeStart - idx+rand.nextInt(1000)/10f)/20)+2f)  ) );
			}
		}
		time += 0.01f;
		int i = 0;
		for (LavaRect rect: rects) {
			rect.height = (float) (20 + 5f*Math.sin((2+Math.sin(time/20))*i/3f + time/10));
			i++;
		}
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setStaticImps(ImpsCollection imps) {
		for (Imp imp: imps.getImps()) {
			if (imp.type == Imp.STATIC_TYPE) {
				this.imps.add((StaticImp) imp);
			}
		}
	}
	
	public void render(SpriteBatch sr, int priority) {
		//Gdx.app.log("LavaRect", priority + ", " + this.priority);
		sr.setColor(114/256f, 19/256f, 0/256f, 1f);
		sr.draw(dotTex, (position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		// sr.setColor(256f/256f, 256f/256f, 0/256f, 1f);
		if (!isFrozen) {
			for (LavaRect rect: rects) {
//				Gdx.app.log("lavaRect", "lava: " + rect.width + ", " + rect.height);
				sr.draw(dotTex, (rect.position.x - rect.width/2+0.5f), rect.position.y - rect.height/2+0.5f, rect.width+0.5f, rect.height+0.5f);
			}
		}

		sr.setColor(114/256f, 19/256f, 0/256f, 1f);
		if (isFrozen) {
			int idx;
			sr.setColor(40/256f, 0/256f, 20/256f, 1f);
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				if (freeze[i] > 0)
					sr.draw(dotTex, (position.x - width/2+0.5f + idx), position.y + height/2+0.5f - freeze[i]*3, 4, freeze[i]*3);
				idx += 4;
			}
			
			sr.setColor(40/256f, 0/256f, 60/256f, 1f);
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				if (freeze[i] > 0)
					sr.draw(dotTex, (position.x - width/2+0.5f + idx), position.y + height/2+0.5f - freeze[i]*2, 4, freeze[i]*2);
				idx += 4;
			}

			sr.setColor(40/256f, 60/256f, 160/256f, 1f);
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				if (freeze[i] > 0)
					sr.draw(dotTex, (position.x - width/2+0.5f + idx), position.y + height/2+0.5f - (int)(freeze[i]*1.5f), 4, (int)(freeze[i]*1.5f));
				idx += 4;
			}
			
			sr.setColor(40/256f, 150/256f, 160/256f, 1f);
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				if (freeze[i] > 0)
					sr.draw(dotTex, (position.x - width/2+0.5f + idx), position.y + height/2+0.5f - freeze[i], 4, freeze[i]);
				idx += 4;
			}
		}
	}
	
	public void collide(GameObject obj) {
		super.collide(obj);
		if(obj == player && c.isTrue) {
			player.isAlive = false;
		}
		for (Imp imp: imps) {
			if (obj == imp && c.isTrue && imp.isSpawned) {
				isFrozen = true;
				freezeStart = imp.getPosition().x - position.x + width/2;
			}
		}
	}
	
	@Override
	public GameObject createCopy() {
		return new LavaRect(position.x, position.y, width, height, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "LavaRect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new LavaRect(" +(int)position.x + ", " + (int)position.y + ", " + (int)width + ", " + (int)height + ", " + parent + ", " + 0 + ")";
	}
}
