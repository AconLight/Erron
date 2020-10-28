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
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;
import com.redartedgames.ball.sound.SoundHandler;

public class GameWorld extends World{

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
	
	private void load() {
		if (LauncherSettings.startLvl > 22) {
			LauncherSettings.startLvl = 1;
			this.state.setMenu();
		}
		reversableObjects.addAll(LevelLoader.getLevel(levelId, player, impsCollection, this));
		reversableObjects.add(player);
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
		player = new Player(0, 250, 1f, null, 10);	
		impsCollection = new ImpsCollection();
		nextLvlRect.visibility = 1f;
	}

	public void pause() {
		isPause = true;
		gameObjects.add(pause);
	}

	public void unpause() {
		isPause = false;
		gameObjects.remove(pause);
	}

	SpriteObject pause;
	public Boolean isPause = false;
	
	public GameWorld() {
		super(); 
		nextLvlRect = new SpriteObject(0, 0, null, 0);
		Texture t = GameObject.dotTex;
		nextLvlRect.addTexture(t);
		nextLvlRect.sclX = Consts.gameWidth;
		nextLvlRect.sclY = Consts.gameHeight;
		nextLvlRect.visibility = 1f;
		nextLvlRect.R = 0.08f;
		nextLvlRect.G = 0.08f;
		nextLvlRect.B = 0.08f;
		gameObjects.add(nextLvlRect);

		restart(LauncherSettings.startLvl);
		blackScreenAnimation = new BlackScreenAnimation();
		pause = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); pause.addTexture("graphic/pause.png");
		pause.priority = 2;
	}
	
	public void restartLvl() {
		levelId = LauncherSettings.startLvl;
		// restart(LauncherSettings.startLvl);
		timeNextLvl = 0;
		isNextLvl  = true;
	}

	float breakTime = 0f;
	
	@Override
	public void update(float delta) {
		if (!isNextLvl) {
			nextLvlRect.visibility-=delta/10;
			if (nextLvlRect.visibility < 0) nextLvlRect.visibility = 0;
			
			timeManagerUpdate(delta);
			if (player.getPosition().x > Consts.gameWidth) {
				LauncherSettings.startLvl++;
				LauncherSettings.maxLevel = LauncherSettings.startLvl;
				Preferences prefs = Gdx.app.getPreferences("maxLavel");
				prefs.putInteger("value", LauncherSettings.startLvl);
				prefs.flush();
				timeNextLvl = 0;
				isNextLvl  = true;
				levelId++;
				SoundHandler.nextLvl();
			}
			
			if (!player.isAlive) {
				restartLvl();
			}
				
		}
		else {
			nextLvlRect.visibility+=delta/10;
			if (nextLvlRect.visibility > 1) nextLvlRect.visibility = 1;
			timeNextLvl += delta;
			if (timeNextLvl > 10 && !isBreak) {
				gameObjects.clear();
				gameObjects.add(nextLvlRect); ///  +1
				restart(levelId);
				gameObjects.remove(nextLvlRect);
				gameObjects.add(nextLvlRect);
				//isNextLvl = false;
				isBreak = true;
				breakTime = 0f;
				blackScreenAnimation.reset();
				breakContinue = false;
				breakWindow.storyText.isOn = true;
			}
			if (isBreak) {
				breakTime += delta;
				blackScreenAnimation.update(delta);
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction < 0 && breakContinue) blackScreenAnimation.animateOn();
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction > 0) {
					blackScreenAnimation.isOn = false;
					isBreak = false;
					isNextLvl = false;
					breakWindow.storyText.isOn = false;
					load();
				}
			}
		}

		if (isConversation) {
			conversationShade += delta/5;
			if (conversationShade > 1) conversationShade = 1;
		}
		else {
			conversationShade -= delta/5;
			if (conversationShade < 0.4f) conversationShade = 0.4f;
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
			timeNum -= timeNumNormal*delta/4;
			if (timeNum < timeNumNormal*2) timeNum = timeNumNormal*2;
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
