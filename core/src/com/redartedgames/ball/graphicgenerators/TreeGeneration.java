package com.redartedgames.ball.graphicgenerators;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class TreeGeneration extends Game{
	@Override
	public void create () {
		Gdx.gl.glClearColor(240f/256, 240f/256, 240f/256, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void render () {
		int n = 70;
		for (int i = 50; i < n; i++) {
			TreeGenerator.mutate(i, n + 10);
			TreeGenerator.generate(i);

		}
		Gdx.app.exit();

	}

}
