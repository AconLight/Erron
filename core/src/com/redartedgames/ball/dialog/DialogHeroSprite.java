package com.redartedgames.ball.dialog;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class DialogHeroSprite extends SpriteObject{

	public DialogHeroSprite(float x, float y, GameObject parent, int id, int dialogHeroType) {
		super(x, y, parent, id);
		loadType(dialogHeroType);
	}

	public void activate() {
		frameNum = 1;
	}

	public void deactivate() {
		frameNum = 0;
	}
	
	private void loadType(int dialogHeroType) {
		//TODO
		switch (dialogHeroType) {
			case 0 : {
				addTexture("graphic/mrgamecreator.png");
				addTexture("graphic/mrgamecreator2.png");
				break;
			}
			case 1 : {
				addTexture("graphic/mridunno.png");
				addTexture("graphic/mridunno2.png");
				break;
			}
			case 2 : {
				addTexture("graphic/mrchinaman.png");
				addTexture("graphic/mrchinaman2.png");
				break;
			}
			case 3 : {
				addTexture("graphic/mraku.png");
				addTexture("graphic/mraku2.png");
				break;
			}
		}
	}

}
