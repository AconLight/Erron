package com.redartedgames.ball.graphicgenerators;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.database.ConversationsBase;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.game.GameScreen;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.game.InputHandler;
import com.redartedgames.ball.menu.MenuInputHandler;
import com.redartedgames.ball.menu.MenuScreen;
import com.redartedgames.ball.menu.MenuWorld;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.sound.SoundHandler;


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
