package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class LvlIcon extends GameObject{
	SpriteObject lvl, lvl_selected, lvl_selected_down, ground, bridge;
	SpriteObject selectedShade1, selectedShade2;
	int numb;
	float originX, originY;

	float lvl_select_delta = 0f;
	
	public LvlIcon(float x, float y, int numb) {
		super(x, y, 0, null);
		this.originX = x;
		this.originY = y;
		this.numb = numb;

		selectedShade1 = new SpriteObject(x, y, null, 0);
		selectedShade1.addTexture("graphic/shape/dot.png");
		selectedShade1.sclX = 240-16;
		selectedShade1.sclY = 400-48;

		lvl_selected = new SpriteObject(x, y, null, 0);
		lvl_selected.addTexture("graphic/lvl_select.png");
		lvl_selected.sclX = 2;
		lvl_selected.sclY = 2;

		lvl_selected_down = new SpriteObject(x, y, null, 0);
		lvl_selected_down.addTexture("graphic/lvl_select_down.png");
		lvl_selected_down.sclX = 2;
		lvl_selected_down.sclY = 2;

		lvl = new SpriteObject(x, y, null, 0);
		lvl.addTexture("graphic/lvlicons/lvlicon" + numb + ".png");
		lvl.sclX = 0.093f;
		lvl.sclY = 0.093f;

		ground = new SpriteObject(x, y, null, 0);
		ground.addTexture("graphic/lvl_ground.png");
		ground.sclX = 2;
		ground.sclY = 2;

		bridge = new SpriteObject(x-1, y+20, null, 0);
		bridge.addTexture("graphic/lvl_bridge.png");
		bridge.sclX = 2;
		bridge.sclY = 2;

		deselect();
	}
	
	public void select() {
		lvl_selected.visibility = 1f;
		lvl_selected_down.visibility = 1f;
	}
	
	public void deselect() {
		lvl_selected.visibility = 0f;
		lvl_selected_down.visibility = 0f;
	}
	
	public void updateLast(float delta, float vx, float vy) {
		
	}

	float time = 0f;

	public void updatePos(float x, float y) {
		time += 0.015f;
		lvl_select_delta = (float) Math.sin(time)*2;
		lvl.setPosition(new Vector2(originX - x, originY - y));
		lvl_selected.setPosition(new Vector2(originX - x, originY - y + lvl_select_delta));
		lvl_selected_down.setPosition(new Vector2(originX - x, originY - y - lvl_select_delta));
		ground.setPosition(new Vector2(originX - x, originY - y));
		bridge.setPosition(new Vector2(originX - x, originY - y));
		selectedShade1.setPosition(new Vector2(originX - x-112, originY - y - lvl_select_delta-176-6));
		selectedShade1.sclY = 400-48 + lvl_select_delta*2;
		selectedShade1.setColor(75/255f, 20/255f, 55/255f, ((-lvl_select_delta+2) / 4f*0.10f + 0.05f)*lvl_selected.visibility);
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		selectedShade1.render(batch, priority);
		ground.render(batch, priority);
		lvl.render(batch, priority);
		lvl_selected.render(batch, priority);
		lvl_selected_down.render(batch, priority);
		bridge.render(batch, priority);
	}
	


}