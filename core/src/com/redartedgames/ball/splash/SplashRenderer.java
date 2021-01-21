package com.redartedgames.ball.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class SplashRenderer extends ScreenRenderer{
	public SpriteObject nextLvlRect;
	public SplashRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		nextLvlRect = new SpriteObject(0, 0, null, 0);
		Texture t = GameObject.dotTex;
		nextLvlRect.addTexture(t);
		nextLvlRect.sclX = Consts.gameWidth;
		nextLvlRect.sclY = Consts.gameHeight;
		nextLvlRect.setColor(0, 0, 0, 1);
		nextLvlRect.visibility = 0f;
		time = 0;
	}
	float time = 0;
	public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		time += 0.01f;
		if (time > 3) {
			if (time >= 4) {
				time = 4;
			}
			nextLvlRect.visibility = time - 3;
		}
		super.render();
		batch.begin();
		nextLvlRect.render(batch, 0);
		batch.end();
	}

}
