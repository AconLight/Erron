package com.redartedgames.ball.map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.redartedgames.ball.consts.PlayerConsts;

public class MapInputHandler implements InputProcessor {

	MapWorld menuWorld;
	
	public MapInputHandler(MapWorld menuWorld) {
		this.menuWorld = menuWorld;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		
		case Keys.ESCAPE: {
			menuWorld.state.setMenu();
			break;
		}
		
		case Keys.ENTER: {
			menuWorld.pressEnter();
			break;
		}
		case Keys.W: {
			menuWorld.moveSelectedUp();
			break;
		}
		case Keys.S: {
			menuWorld.moveSelectedDown();
			break;
		}
		case Keys.A: {
			menuWorld.moveSelectedLeft();
			break;
		}
		case Keys.D: {
			menuWorld.moveSelectedRight();
			break;
		}
			case Keys.UP: {
				menuWorld.moveSelectedUp();
				break;
			}
			case Keys.DOWN: {
				menuWorld.moveSelectedDown();
				break;
			}
			case Keys.LEFT: {
				menuWorld.moveSelectedLeft();
				break;
			}
			case Keys.RIGHT: {
				menuWorld.moveSelectedRight();
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
		// TODO Auto-generated method stub
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

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

}
