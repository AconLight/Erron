package com.redartedgames.ball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
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
import com.redartedgames.ball.map.MapInputHandler;
import com.redartedgames.ball.map.MapScreen;
import com.redartedgames.ball.map.MapWorld;
import com.redartedgames.ball.menu.MenuInputHandler;
import com.redartedgames.ball.menu.MenuScreen;
import com.redartedgames.ball.menu.MenuWorld;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.sound.SoundHandler;
import com.redartedgames.ball.splash.SplashScreen;
import com.redartedgames.ball.splash.SplashWorld;


public class BallGame extends Game{
	
	private int screenId;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private MapScreen mapScreen;
	private GameWorld gameWorld;
	private MenuWorld menuWorld;
	private MapWorld mapWorld;
	private InputHandler gameHandler;
	private MenuInputHandler menuHandler;
	private MapInputHandler mapHandler;
	private StateMachine state;
	private ColorGenerator cg;
	private Color bg;
	private Vector3 v;
	private float time;

	private SplashScreen splashScreen;
	private SplashWorld splashWorld;

	@Override
	public void create () {
		screenId = 0;
		splashScreen = new SplashScreen(Consts.gameWidth, Consts.gameHeight);
		splashWorld = (SplashWorld)splashScreen.getWorld();
		Gdx.gl.glClearColor(240f/256, 240f/256, 240f/256, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.input.setCursorCatched(true);
	}

	public void load() {
		SoundHandler.load();
		EasterEggsBase.tryFirstLoad();
		state = new StateMachine() {
			@Override public void setMenu() { setMenuMy(); }
			@Override public void setMap() { setMapMy(); }
			@Override public void setGame() { setGameMy(); }
		};

		gameScreen = new GameScreen(Consts.gameWidth, Consts.gameHeight);
		menuScreen = new MenuScreen(Consts.gameWidth, Consts.gameHeight);
		mapScreen = new MapScreen(Consts.gameWidth, Consts.gameHeight);
		gameWorld = (GameWorld)gameScreen.getWorld();
		menuWorld = (MenuWorld)menuScreen.getWorld();
		mapWorld = (MapWorld)mapScreen.getWorld();
		gameWorld.setStateMachine(state);
		menuWorld.setStateMachine(state);
		mapWorld.setStateMachine(state);
		gameHandler = new InputHandler(gameWorld);
		menuHandler = new MenuInputHandler(menuWorld);
		mapHandler = new MapInputHandler(mapWorld);
		Gdx.input.setInputProcessor(menuHandler);
		cg = new ColorGenerator(4.5f);
		bg = new Color(cg.generateNextColor(0.3f, 0.7f, 0));
		time = 0;
		setMenuMy();
		Preferences prefs = Gdx.app.getPreferences("maxLavel");
		if (prefs.contains("value")) {
			LauncherSettings.maxLevel = prefs.getInteger("value");
		} else {
			prefs.putInteger("value", 1);
		}
	}

	int frames = 2400;
	int updateFrames = 100;
	float frameTime = 1f/frames;

	float splashTime = 0;
	@Override
	public void render () {
		switch(screenId) {
			case 0: {
				splashTime += 0.01f;
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl.glClearColor(0/255f, 0/255f, 0/255f, 1);
				for(int i = 0; i < 5; i++)
					splashScreen.update(0.01f);
				splashScreen.render();
				if (splashTime > 5) {
					load();
					screenId = 1;
				}
				break;
			}
		case 1: {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(62/255f, 136/255f, 133/255f, 1);
			for(int i = 0; i < 5; i++)
			menuScreen.update(0.01f);
			menuScreen.render();
			break;
		}
		case 2: {
			time += Gdx.graphics.getDeltaTime()*2;
			v = ColorGenerator.hsvToRgb((90 + time)%360, 0.8f, 0.1f);
			if (LauncherSettings.AdvancedGrapghic) Gdx.gl.glClearColor(v.x, v.y, v.z, 1);
			else Gdx.gl.glClearColor(1, 1, 1, 1);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			for(int i = 0; i < updateFrames; i++)
			gameScreen.update(0.01f);
			
			gameScreen.render();
			if (Gdx.graphics.getDeltaTime()/updateFrames > frameTime) {
				if (updateFrames < 4000)
				updateFrames++;
			} else {
				if (updateFrames > 5)
				updateFrames--;
			}
			break;
		}
		case 3: {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(62/255f, 136/255f, 133/255f, 1);
			for(int i = 0; i < 5; i++)
			mapScreen.update(0.01f);
			mapScreen.render();
			break;
		}
		}
		
		
		//if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
			//gameScreen.screenShaker.shake(50);
		//}
	}
	
	public void setMenuMy() {
		screenId = 1;
		menuScreen.restart();
		Gdx.input.setInputProcessor(menuHandler);
		SoundHandler.playMenuSd();
	}
	
	public void setMapMy() {
		screenId = 3;
		mapScreen.restart();
		Gdx.input.setInputProcessor(mapHandler);
	}
	
	public void setGameMy() {
		screenId = 2;
		gameWorld.restartLvl();
		gameWorld.nextLvlRect.visibility = 1f;
		Gdx.input.setInputProcessor(gameHandler);
		time = 0;
		SoundHandler.playGameSdNostalgic();
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
