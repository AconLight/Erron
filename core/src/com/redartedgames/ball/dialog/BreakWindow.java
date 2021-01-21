package com.redartedgames.ball.dialog;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.consts.Consts;

public class BreakWindow extends ConversationIntro {

	public StoryText storyText;
	
	public BreakWindow(float x, float y, int id, GameObject parent, int levelId) {
		super(x, y, id, parent, "");
		float g = 1/1.62f;
		storyText = new StoryText((int) (Consts.gameWidth*g));
		storyText.setAsBreak(levelId);
		storyText.X = (int)((Consts.gameWidth*(1-g))*(0.5f));
		storyText.Y = Consts.gameHeight - storyText.X;
	}
}