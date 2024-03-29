package com.redartedgames.ball.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.consts.PlayerConsts;
import com.redartedgames.ball.utils.LvlPrinter;

public class InputHandler implements InputProcessor{

	private GameWorld world;
	public InputHandler(GameWorld world) {
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {

		switch(keycode) {
			case Keys.ESCAPE: {
				break;
			}
			default: {
				if (world.isBreak && world.breakTime > 40f) world.breakContinue = true;
			}

		}

		switch(keycode) {
		
		case Keys.A: {
			if (!world.player.hasAPressed) {
				world.player.addXAxis(-PlayerConsts.MOVE_X);
				world.player.hasAPressed = true;
			}
			break;
		}
		case Keys.D: {
			if (!world.player.hasDPressed) {
				world.player.addXAxis(PlayerConsts.MOVE_X);
				world.player.hasDPressed = true;
			}
			break;
		}
		case Keys.S: {
			if (world.dialogHero != null) world.dialogHero.dialogUp();
			break;
		}
		case Keys.W: {
			world.player.setIsJumping(true);
			if (world.dialogHero != null) world.dialogHero.dialogDown();
			break;
		}
		case Keys.LEFT: {
			if (!world.player.hasAPressed) {
				world.player.addXAxis(-PlayerConsts.MOVE_X);
				world.player.hasAPressed = true;
			}
			break;
		}
		case Keys.RIGHT: {
			if (!world.player.hasDPressed) {
				world.player.addXAxis(PlayerConsts.MOVE_X);
				world.player.hasDPressed = true;
			}
			break;
		}
		case Keys.UP: {
			world.player.setIsJumping(true);
			if (world.dialogHero != null) world.dialogHero.dialogDown();
			break;
		}
		case Keys.O: {
			world.getGameObjects().clear();
			world.restart(world.levelId-1);
			break;
		}
		case Keys.P: {
			world.getGameObjects().clear();
			world.restart(world.levelId+1);
			break;
		}
		case Keys.I: {
			LvlPrinter.print(world.levelId);
			world.goNext();
			//world.getGameObjects().clear();
			//world.getGameObjects().addAll(LevelLoader.getLevel(++world.levelId, world.player, world.impsCollection, world));
			break;
		}
		case Keys.K: {
			LauncherSettings.MakeGIF = !LauncherSettings.MakeGIF;
			break;
		}
		case Keys.SPACE: {
			if (!world.isConversation) {
				world.setIsForward(false);
				world.impsCollection.spawnNextImpPressDown(world.player);
			}
			else {
				if (world.dialogHero != null) world.dialogHero.dialogMark();
			}
			break;
		}
		case Keys.F: {
//			world.isConversation = world.dialogHero.showOrHideWindow();
//			world.player.isConversation = world.isConversation;
			break;
		}
		case Keys.R: {
			world.restartLvl();
			break;
		}
		case Keys.Q: {
			if (world.isPause) {
				world.unpause();
				world.state.setMenu();
			}
			break;
		}
		case Keys.ENTER: {
			if (world.isPause) {
				world.unpause();
			}
			if (world.dialogHero != null) world.dialogHero.acceptDialogOptions();
			if (world.isBreak && world.breakTime > 2f) world.breakContinue = true;
			break;
		}
		case Keys.ESCAPE: {
			if (world.isPause) {
				world.unpause();
				//world.state.setMenu();
			} else {
				world.pause();
			}
			break;
		}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		switch(keycode) {
		case Keys.A: {
			if (world.player.hasAPressed) {
				world.player.addXAxis(PlayerConsts.MOVE_X);
				world.player.hasAPressed = false;
			}
			//Gdx.app.log("InputHandler", "A");
			break;
		}
		case Keys.D: {
			if (world.player.hasDPressed) {
				world.player.addXAxis(-PlayerConsts.MOVE_X);
				world.player.hasDPressed = false;
			}
			break;
		}
		case Keys.W: {
			world.player.setIsJumping(false);
			break;
		}
		case Keys.LEFT: {
			if (world.player.hasAPressed) {
				world.player.addXAxis(PlayerConsts.MOVE_X);
				world.player.hasAPressed = false;
			}
			//Gdx.app.log("InputHandler", "A");
			break;
		}
		case Keys.RIGHT: {
			if (world.player.hasDPressed) {
				world.player.addXAxis(-PlayerConsts.MOVE_X);
				world.player.hasDPressed = false;
			}
			break;
		}
		case Keys.UP: {
			world.player.setIsJumping(false);
			break;
		}
		
		case Keys.SPACE: {
			if (!world.isForward) {
				world.setIsForward(true);
				world.impsCollection.spawnNextImpPressUp((world.player));
			}
			break;
		}

		} 
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
