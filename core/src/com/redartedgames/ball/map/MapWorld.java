package com.redartedgames.ball.map;

import java.util.ArrayList;

import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.myobjects.LvlIcon;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.World;

public class MapWorld extends World{
	
	ArrayList<LvlIcon> lvls;
	int jj = 22;
	int selectedIdX, selectedIdY;
	public float selectedIdXf, selectedIdYf;

	private void restartMap() {
		lvls = new ArrayList<>();

		int width = 100, height = 100;
		for (int j = 0; j < jj; j++) {
			lvls.add(new LvlIcon(Consts.gameWidth - Consts.gameWidth/1.68f + j*480, Consts.gameHeight/2, j+1));
		}
		selectedIdX = LauncherSettings.startLvl;
		selectedIdY = 0;
		selectedIdXf = LauncherSettings.startLvl;
		selectedIdYf = 0;
		lvls.get(LauncherSettings.startLvl).select();
		gameObjects.clear();
		gameObjects.addAll(lvls);
		SpriteObject map_esc = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); map_esc.addTexture("graphic/menu/mapesc.png");
		gameObjects.add(map_esc);
	}
	public MapWorld() {
		super(); 
		restartMap();
	}
	
	@Override
	public void update(float delta) {
		selectedIdXf += (selectedIdX - selectedIdXf + Math.signum((selectedIdX - selectedIdXf)))*delta;
		selectedIdYf += (selectedIdY - selectedIdYf + Math.signum((selectedIdY - selectedIdYf)))*delta;
		if (Math.abs(selectedIdX - selectedIdXf) <= 0.01) {
			selectedIdXf = selectedIdX;
		}
		if (Math.abs(selectedIdY - selectedIdYf) <= 0.01) {
			selectedIdYf = selectedIdY;
		}
		for (LvlIcon lvl: lvls) {
			lvl.updatePos(selectedIdXf*480, selectedIdYf*480);
		}
	}
	
	@Override
	public void restart() {
		restartMap();
	}
	
	public void pressEnter() {
		if (LauncherSettings.maxLevel >= selectedIdX + 1) {
			LauncherSettings.startLvl = selectedIdX + 1;
			this.state.setGame();
		}
	}
	
	public void moveSelectedLeft() {
		if (selectedIdX > 0) {
			lvls.get(selectedIdX-1).select();
			lvls.get(selectedIdX).deselect();
			selectedIdX--;
		}
	}
	
	public void moveSelectedRight() {
		if (selectedIdX < jj-1) {
			lvls.get(selectedIdX+1).select();
			lvls.get(selectedIdX).deselect();
			selectedIdX++;
		}
	}
	
	public void moveSelectedUp() {

	}
	
	public void moveSelectedDown() {

	}

}
