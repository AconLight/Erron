package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.redartedgames.ball.database.Consts2;
import com.redartedgames.ball.objects.GameObject;

public class ConversationIntro extends GameObject{

	public String text;
	private BitmapFont font;
	public boolean isOn = true;
	
	public ConversationIntro(float x, float y, int id, GameObject parent, String intro) {
		super(x, y, id, parent);
		text = intro;
		font = Consts2.Font(18);
	}
	
	public void render(SpriteBatch batch, int priority) {
		if (isOn) {
		super.render(batch, priority);
		
		// font.draw(batch, text, position.x, position.y);
		font.draw(batch, text, position.x, position.y, 500, -1, true);
		}
	}

}
