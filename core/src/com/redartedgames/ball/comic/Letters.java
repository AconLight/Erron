package com.redartedgames.ball.comic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.objects.SpriteObject;

import java.util.ArrayList;

public class Letters {

	ArrayList<SpriteObject> text;

	public Letters(int x, int y, String letters) {
		text = new ArrayList<>();
		int dx = 32;
		int ctr = 0;
		for (char c: letters.toCharArray()) {
			SpriteObject s = new SpriteObject(x + ctr, y, null, 0);
			s.addTexture("graphic/literki/" + c + ".png");
			text.add(s);
			ctr += dx;
		}
	}

	public void render(SpriteBatch batch) {
		for (SpriteObject l: text) {
			l.render(batch, 1);
		}
	}
}
