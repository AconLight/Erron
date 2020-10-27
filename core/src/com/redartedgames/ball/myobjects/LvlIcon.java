package com.redartedgames.ball.myobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.database.Consts2;
import com.redartedgames.ball.dialog.StoryText;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class LvlIcon extends GameObject{
	public SpriteObject rectFrameSelected, rectFrame;
	ColorGenerator cg;
	int rectNum;
	SpriteObject rect;
	float r;
	Random rand;
	BitmapFont font;
	public boolean isSelected = false;
	public String label;
	Color allowed = new Color(0, 0, 0, 1);
	Color notAllowed = new Color(0.20f, 0.1f, 0.1f, 1);
	int numb;
	
	public LvlIcon(float x, float y, int numb) {
		super(x, y, 0, null);
		this.numb = numb;
		this.numb = 20;
		font = Consts2.Font(60);
		label = "" + numb;
		rectFrameSelected = new SpriteObject(x+50, y+50, null, 0);
		rectFrame = new SpriteObject(x + 50, y+50, null, 0);
		rect = new SpriteObject(x, y, null, 0);
		rectFrameSelected.addTexture("graphic/lvlicon1.png");
		rectFrame.addTexture("graphic/lvlicon2.png");
		rect.addTexture("graphic/shape/dot.png").visibility = 0.3f;
		rect.sclX = 120;
		rect.sclY = 120;
		rect.position.x -= 10;
		rect.position.y -= 10;
		if (numb > LauncherSettings.maxLevel) {
			rect.setColor(notAllowed.r, notAllowed.g, notAllowed.b, 0.6f);
		} else {
			rect.setColor(allowed.r, allowed.g, allowed.b, 0.3f);
		}
		
	}
	
	public void select() {
		if (numb > LauncherSettings.maxLevel || true) {
			Gdx.app.log("lvlIcon", LauncherSettings.maxLevel + "");
			rect.setColor(notAllowed.r, notAllowed.g, notAllowed.b, 0.7f);
		} else {
			rect.setColor(allowed.r, allowed.g, allowed.b, 0.5f);
		}
		isSelected = true;
	}
	
	public void deselect() {
		if (numb > LauncherSettings.maxLevel || true) {
			rect.setColor(notAllowed.r, notAllowed.g, notAllowed.b, 0.6f);
		} else {
			rect.setColor(allowed.r, allowed.g, allowed.b, 0.3f);
		}
		isSelected = false;
	}
	
	public void updateLast(float delta, float vx, float vy) {
		
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		
		if (isSelected)
			rectFrameSelected.render(batch, priority);
		else
			rectFrame.render(batch, priority);
		
		rect.render(batch, priority);
		
		font.draw(batch, label, position.x + 50 - label.length()*14, position.y + 70);
	}
	


}