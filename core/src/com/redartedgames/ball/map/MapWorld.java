package com.redartedgames.ball.map;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.myobjects.LvlIcon;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

public class MapWorld extends World{
	
	ArrayList<LvlIcon> lvls;
	int ii = 4, jj = 5;
	int selectedId;
	
	private void restartMap() {
		lvls = new ArrayList<>();

		int width = 100, height = 100;
		for (int i = 0; i < ii; i++) {
			for (int j = 0; j < jj; j++) {
				lvls.add(new LvlIcon((j+1)*Consts.gameWidth/(jj+1f) - width/2, (ii-i)*Consts.gameHeight/(ii+1f) + height/2, i*5 + j + 1));
			}
		}
		float j = 1.5f, i = 5;
		lvls.add(new LvlIcon((int)((j+1)*Consts.gameWidth/(jj+1f) - width/2), (int)((ii-i+1)*Consts.gameHeight/(ii+1f) + height/2), 21));
		j = 2.5f; i = 5;
		lvls.add(new LvlIcon((int)((j+1)*Consts.gameWidth/(jj+1f) - width/2), (int)((ii-i+1)*Consts.gameHeight/(ii+1f) + height/2), 22));

		lvls.get(0).select();
		selectedId = 0;
		gameObjects.clear();
		gameObjects.addAll(lvls);
	}
	public MapWorld() {
		super(); 
		restartMap();
	}
	
	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public void restart() {
		restartMap();
	}
	
	public void pressEnter() {
		if (LauncherSettings.maxLevel >= selectedId + 1 || true) {
			LauncherSettings.startLvl = selectedId + 1;
			this.state.setGame();
		}
	}
	
	public void moveSelectedLeft() {
		if (selectedId > 0) {
			lvls.get(selectedId).deselect();
			selectedId--;
			lvls.get(selectedId).select();
		}
	}
	
	public void moveSelectedRight() {
		if (selectedId < ii*jj + 1) {
			// lvls.get(0).deselect();
			lvls.get(selectedId).deselect();
			selectedId++;
			lvls.get(selectedId).select();
		}
		for (LvlIcon lvl: lvls) {
			Gdx.app.log("mapWorld", lvl.label + ", " + lvl.isSelected);
		}
	}
	
	public void moveSelectedUp() {
		if (selectedId >= jj && selectedId < 20) {
			lvls.get(selectedId).deselect();
			selectedId -= jj;
			lvls.get(selectedId).select();
		}
		else if (selectedId == 20) {
			lvls.get(selectedId).deselect();
			selectedId = 16;
			lvls.get(selectedId).select();
		}
		else if (selectedId == 21) {
			lvls.get(selectedId).deselect();
			selectedId = 18;
			lvls.get(selectedId).select();
		}
	}
	
	public void moveSelectedDown() {
		if (selectedId < (ii-1)*jj) {
			lvls.get(selectedId).deselect();
			selectedId+=jj;
			lvls.get(selectedId).select();
		}
		else if (selectedId >= 17 && selectedId <= 19) {
			lvls.get(selectedId).deselect();
			selectedId = 21;
			lvls.get(selectedId).select();
		}
		else if (selectedId >= 15 && selectedId <= 16) {
			lvls.get(selectedId).deselect();
			selectedId = 20;
			lvls.get(selectedId).select();
		}
	}

}
