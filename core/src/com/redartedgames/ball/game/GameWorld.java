package com.redartedgames.ball.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.dialog.BreakWindow;
import com.redartedgames.ball.dialog.DialogHero;
import com.redartedgames.ball.graphicgenerators.BlackScreenAnimation;
import com.redartedgames.ball.myobjects.*;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.World;
import com.redartedgames.ball.sound.SoundHandler;

import static com.redartedgames.ball.LevelLoader.getComicCtr;

public class GameWorld extends World{

	public static Player staticPlayer;
	public ButtonRect but;
	public Ball ball, ball1, ball2;
	public ShiftedRect rect;
	private float time;
	private float timeNum, timeNumNormal = 0.15f;
	public int t, i;
	public boolean isPaused;
	public Player player;
	public ArrayList <ReversableObject> reversableObjects;
	public ArrayList <GameObject> collidableObjects;
	boolean isForward;
	public static  boolean isForwardStatic;
	public ImpsCollection impsCollection;
	private float timeNextLvl = 0;
	public boolean isNextLvl = true, isBreak = false, breakContinue = false;
	float cloudT = 0;
	public BlackScreenAnimation blackScreenAnimation;
	
	public BreakWindow breakWindow;
	
	public DialogHero dialogHero;
	
	public float conversationShade;
	public boolean isConversation;
	
	public SpriteObject nextLvlRect;
	 
	public float timeTime, timeBar, timeVel, timeAcc;
	
	int levelId = 1;
	
	public void restart() {
		restart(levelId);
	}

	float hintTime = 0;

	private void load() {
		if (LauncherSettings.startLvl > 22) {
			LauncherSettings.startLvl = 1;
			this.state.setMenu();
		}
		LauncherSettings.startLvl = levelId;
		reversableObjects.addAll(LevelLoader.getLevel(levelId, player, impsCollection, this));
		reversableObjects.add(player);
		player.setHints(levelId);
		gameObjects.addAll(reversableObjects);
		
		for (GameObject obj : reversableObjects) {
			for (GameObject obj2 : reversableObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		player.collidableObjects.removeAll(impsCollection.getImps());
		for (GameObject obj : impsCollection.getImps()) {
			obj.collidableObjects.remove(player);
		}
	}
	
	public void restart(int levelId) {
		breakWindow = new BreakWindow(0, 0, 0, null, levelId);
		isConversation = false;
		conversationShade = 0.4f;
		this.levelId = levelId;
		time = 0;
		timeNum = timeNumNormal;
		timeBar = 0.1f;
		timeVel = 0f;
		timeTime = 0;
		time = 0;
		t = 1;
		i = 0;
		reversableObjects = new ArrayList<ReversableObject>();
		isForward = true;	
		player = new Player(40, 250, 1f, null, 10);
		staticPlayer = player;
		player.setHints(levelId);
		impsCollection = new ImpsCollection();
		nextLvlRect.visibility = 1f;
		blackScreenAnimation.comic.load(levelId);

	}

	public void pause() {
		isPause = true;
		if (!gameObjects.contains(pause)) {
			gameObjects.add(pause);
		}
		pause.pause();
	}

	public void unpause() {
		isPause = false;
		//gameObjects.remove(pause);
		pause.unpause();
	}

	public PauseSprite pause;
	public Boolean isPause = false;
	
	public GameWorld() {
		super(); 
		nextLvlRect = new SpriteObject(0, 0, null, 0);
		Texture t = GameObject.dotTex;
		nextLvlRect.addTexture(t);
		nextLvlRect.sclX = Consts.gameWidth;
		nextLvlRect.sclY = Consts.gameHeight;
		nextLvlRect.visibility = 1f;
		nextLvlRect.R = 0.00f;
		nextLvlRect.G = 0.00f;
		nextLvlRect.B = 0.00f;
		//gameObjects.add(nextLvlRect);


		blackScreenAnimation = new BlackScreenAnimation();
		restart(LauncherSettings.startLvl);
		pause = new PauseSprite();
		pause.priority = 20;
	}
	
	public void restartLvl() {
		levelId = LauncherSettings.startLvl;
		// restart(LauncherSettings.startLvl);
		timeNextLvl = 0;
		isNextLvl  = true;
		blackScreenAnimation.comicCounter = getComicCtr(levelId);
		blackScreenAnimation.comic.load(levelId);
	}

	float breakTime = 0f;

	public void goNext() {
		if (LauncherSettings.saveHints) {
			player.particles.saveAllFrames(levelId);
		}
		LauncherSettings.startLvl++;
		LauncherSettings.maxLevel = Math.max(LauncherSettings.maxLevel, LauncherSettings.startLvl);
		Preferences prefs = Gdx.app.getPreferences("maxLavel");
		prefs.putInteger("value", LauncherSettings.maxLevel);
		prefs.flush();
		timeNextLvl = 0;
		isNextLvl  = true;
		levelId++;
		blackScreenAnimation.comicCounter = getComicCtr(levelId);
		SoundHandler.nextLvl();
	}
	
	@Override
	public void update(float delta) {
		hintTime += delta;
		player.hintTime = hintTime;
		if (!isNextLvl) {
			nextLvlRect.visibility-=delta/10;
			if (nextLvlRect.visibility < 0) nextLvlRect.visibility = 0;
			
			timeManagerUpdate(delta);
			if (player.getPosition().x > Consts.gameWidth) {
//				if (LauncherSettings.saveHints) {
//					player.particles.saveAllFrames(levelId);
//				}
//				LauncherSettings.startLvl++;
//				LauncherSettings.maxLevel = Math.max(LauncherSettings.maxLevel, LauncherSettings.startLvl);
//				Preferences prefs = Gdx.app.getPreferences("maxLavel");
//				prefs.putInteger("value", LauncherSettings.maxLevel);
//				prefs.flush();
//				timeNextLvl = 0;
//				isNextLvl  = true;
//				levelId++;
//				SoundHandler.nextLvl();
				goNext();
			}
			
			if (!player.isAlive) {
				restartLvl();
			}
				
		}
		else {
			nextLvlRect.visibility+=delta/10;
			if (nextLvlRect.visibility > 1) nextLvlRect.visibility = 1;
			timeNextLvl += delta;
			if (timeNextLvl > 10 && !isBreak && nextLvlRect.visibility >= 1) {
				gameObjects.clear();
				//gameObjects.add(nextLvlRect); ///  +1
				hintTime = 0;
				restart(levelId);
				//gameObjects.remove(nextLvlRect);
				//gameObjects.add(nextLvlRect);
//				isNextLvl = false;
				isBreak = true;
				breakTime = 0f;
				blackScreenAnimation.reset();
				breakContinue = false;
				if (blackScreenAnimation.comicCounter <= 0) {
					breakContinue = true;
				}
				breakWindow.storyText.isOn = true;

			}
			if (isBreak) {
				breakTime += delta;
				blackScreenAnimation.update(delta);
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction < 0 && breakContinue) blackScreenAnimation.animateOn();
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction > 0) {
					if (blackScreenAnimation.comicCounter <= 1) {
						blackScreenAnimation.isOn = false;
						isBreak = false;
						isNextLvl = false;
						breakWindow.storyText.isOn = false;
						load();
					} else {
						blackScreenAnimation.comicCounter--;
						isBreak = true;
						breakTime = 0f;
						blackScreenAnimation.reset();
						breakContinue = false;
						breakWindow.storyText.isOn = true;
						blackScreenAnimation.comic.loadNext();
//						blackScreenAnimation.isOn = false;
//						isBreak = false;
//						isNextLvl = false;
//						breakWindow.storyText.isOn = false;
//						load();
					}

				}
			}
		}

		if (!isForward) {
			conversationShade += delta/30;
			if (conversationShade > 1) conversationShade = 1;
		}
		else {
			conversationShade -= delta/30;
			if (conversationShade < 0.2f) conversationShade = 0.2f;
		}
		
		cloudT += delta;
			
			
	
	}
	
	private void timeManagerUpdate(float delta) {
		for(ReversableObject r : reversableObjects) {
			r.setIsForward(isForward);
		}
		time += delta;
		//Gdx.app.log("gameWorld", time + "");
		
		
		
		if (!isForward)  {
			timeNum -= timeNumNormal*delta/8;
			if (timeNum < timeNumNormal*0.9f) timeNum = timeNumNormal*0.9f;
		}
		else {
			timeNum = timeNumNormal;
		}
		if (time >= timeNum) {
			time -= timeNum;
			super.update(0.01f);
		}
	}
	
	public void setIsForward(boolean isForward) {
		this.isForward = isForward;
		isForwardStatic = isForward;
		if (!isForward) timeNum = timeNumNormal*8;
	}
}
