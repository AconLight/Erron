package com.redartedgames.ball.menu;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.World;

public class MenuWorld extends World{
	int time;
	boolean isForward;
	ArrayList<ReversableObject> reversableObjects;
	SpriteObject menu, menu_play, menu_map, menu_reset, menu_enter_play, menu_enter_map, menu_enter_reset;
	Color selectedColor = new Color(0.2f, 0.85f, 0.68f, 0.6f);
	public Color notSelectedColor = new Color(0.6f, 0.6f, 0.6f, 0.6f);
	Color playColor, mapColor, resetColor;
	int selectedId;
	float selectedTime, selectedTimeMax = 0.6f;

	public void restart() {
		gameObjects.clear();
		restart(0);
		time = -600;
		myTime = 0;
	}
	float menu_right_dx = 150;
	public void restart(int levelId) {
		menu = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu.addTexture("graphic/menu/menu.png");
		menu_play = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_play.addTexture("graphic/menu/menu_play.png");
		menu_map = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_map.addTexture("graphic/menu/menu_map.png");
		menu_reset = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_reset.addTexture("graphic/menu/menu_reset.png");
		gameObjects.add(menu);
		gameObjects.add(menu_play);
		gameObjects.add(menu_map);
		gameObjects.add(menu_reset);

		menu_enter_play = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_enter_play.addTexture("graphic/menu/menuenterplay.png");
		menu_enter_map = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_enter_map.addTexture("graphic/menu/menuentermap.png");
		menu_enter_reset = new SpriteObject(Consts.gameWidth+menu_right_dx, Consts.gameHeight/2, null, 0); menu_enter_reset.addTexture("graphic/menu/menuenterreset.png");
		gameObjects.add(menu_enter_play);
		gameObjects.add(menu_enter_map);
		gameObjects.add(menu_enter_reset);

		selectedTime = 0;
		selectedId = 1;
		setSelected(selectedId);
		playColor = new Color(selectedColor);
		mapColor = new Color(notSelectedColor);
		resetColor = new Color(notSelectedColor);
		reversableObjects = new ArrayList<>();
		reversableObjects.addAll(LevelLoader.getLevel(0, null, null, null));
		gameObjects.addAll(reversableObjects);
		for (GameObject obj : reversableObjects) {
			for (GameObject obj2 : reversableObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		isForward = true;
	}

	public void moveSelectedUp() {
		if (selectedId > 1) {
			setSelected(selectedId -1);
			selectedTime = 0;
		}

	}

	public void moveSelectedDown() {
		if (selectedId < 3) {
			setSelected(selectedId +1);
			selectedTime = 0;
		}
	}

	void setSelected(int id) {
		selectedId = id;
	}

	public MenuWorld() {
		super();
		restart();
	}

	float colTime = 0f;
	float myTime = 1f;
	@Override
	public void update(float delta) {
		colTime += delta;
		Vector3 newselcol = ColorGenerator.hsvToRgb((180+colTime)%360, 0.5f, 0.5f);
		//Gdx.app.log("siema", newselcol.x + "");
		selectedColor = new Color(newselcol.x, newselcol.y, newselcol.z, 1);
		myTime += 0.3f;
		float menu_dx = -200/(10+myTime*myTime)*0.9f;
//		float menu1_dx = -2/time;
//		float menu2_dx = -2/time;
//		float menu3_dx = -2/time;

		if (menu_dx > -0.5) {
			menu_dx = 0;
		}

		menu.transform(menu_dx, 0);
		menu_play.transform(menu_dx, 0);
		menu_enter_play.transform(menu_dx, 0);
		menu_map.transform(menu_dx, 0);
		menu_enter_map.transform(menu_dx, 0);
		menu_reset.transform(menu_dx, 0);
		menu_enter_reset.transform(menu_dx, 0);

		if (selectedTime < selectedTimeMax) {
			selectedTime += 0.01f;
		} else selectedTime = selectedTimeMax;

		float t = (selectedTime/selectedTimeMax)*(selectedTime/selectedTimeMax);

		if (selectedId == 1) playColor.lerp(selectedColor, t);
		else playColor.lerp(notSelectedColor, t);

		if (selectedId == 2) mapColor.lerp(selectedColor, t);
		else mapColor.lerp(notSelectedColor, t);

		if (selectedId == 3) resetColor.lerp(selectedColor, t);
		else resetColor = resetColor.lerp(notSelectedColor, t);

		menu_play.setColor(playColor);
		menu_map.setColor(mapColor);
		menu_reset.setColor(resetColor);

		menu_enter_play.visibility = (playColor.g - notSelectedColor.g) / (selectedColor.g - notSelectedColor.g);
		menu_enter_map.visibility = (mapColor.g - notSelectedColor.g) / (selectedColor.g - notSelectedColor.g);
		menu_enter_reset.visibility = (resetColor.g - notSelectedColor.g) / (selectedColor.g - notSelectedColor.g);



//		for(ReversableObject r : reversableObjects) {
//			r.setIsForward(isForward);
//		}
//		if ((time >= 0 || isForward) && (time < 1500 || !isForward)){
//			if (time >= 0) {
//				super.update(0.01f);
//			}
//			time++;
//			if (!isForward) time -= 2;
//		}
//		else {
//			isForward = !isForward;
//			if (time < 0) time++;
//			else time--;
//		}
	}

	public void pressEnter() {
		if (selectedId == 1)
			this.state.setGame();
		else if (selectedId == 2) {
			this.state.setMap();
		} else {
			Preferences prefs = Gdx.app.getPreferences("maxLavel");
			prefs.putInteger("value", 1);
			prefs.flush();
			LauncherSettings.maxLevel = 1;
		}
	}

}
