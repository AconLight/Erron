package com.redartedgames.ball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redartedgames.ball.BallGame;
import com.redartedgames.ball.LvlIconGame;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.consts.LauncherSettings;

public class LvlIconLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) (Consts.screenWidth);
		config.height = (int) (Consts.screenHeight);
		config.fullscreen = LauncherSettings.FullScreen;
		new LwjglApplication(new LvlIconGame(), config);
	}
}
