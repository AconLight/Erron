package com.redartedgames.ball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redartedgames.ball.BallGame;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.graphicgenerators.TreeGeneration;
import com.redartedgames.ball.screen.Consts;

public class TreeGenerationLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TreeGeneration(), config);
	}
}
