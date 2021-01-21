package com.redartedgames.ball.dialog;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.myobjects.EasterEgg;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.consts.Consts;

public class DialogConversationWindow extends GameObject{

	public boolean isOn;
	private DialogWindow dialogWindow;
	private Conversation conversation;
	private DialogBackground bg, bgIntro, bgName, bgStory;
	private ConversationIntro conversationIntro, conversationName;
	private StoryWindow storyWindow;

	public DialogConversationWindow(float x, float y, int id, GameObject parent) {
		super(x, y, id, parent);
		dialogWindow = new DialogWindow();
		int i = 0;
		if (EasterEggsBase.easterEggs != null)
		for(EasterEgg egg: EasterEggsBase.easterEggs) {
			if (egg.isTrue) i++;
		}	
		
		DialogOption.gap = 30 + i;
		if (DialogOption.gap < 15) DialogOption.gap = 15;
		
		bg = new DialogBackground(x, y, id, this, 1000, 80 + (int)(i*DialogOption.gap));
		
		if (EasterEggsBase.easterEggs != null)
		for(EasterEgg egg: EasterEggsBase.easterEggs) {
			//Gdx.app.log("GameWorld", "id: " + egg.id + ", name: " + egg.name + ", isTrue: " + egg.isTrue);
			if (egg.isTrue)
				dialogWindow.addOption(egg.name, egg.id);
		}	
		
		conversation = new Conversation(0, 0, (int)(bg.width*1.618f/2.618f), (int)bg.height, 0, this);
		conversationIntro = new ConversationIntro(500, 950, 0, this, 
				"Cze��, mam w sobie pare zakodowanych informacji o graczu, kt�ry by� tu przed tob�. \n" + 
				"W, S + SPACJA aby wybra� opcj�. ENTER aby sprawdzi� moj� odpowied�.");
		conversationName = new ConversationIntro(500, 900, 0, this, "Mr Don't Loose It");
		
		storyWindow = new StoryWindow(500, 800, 0, this);
		
		bgStory = new DialogBackground(x, y, id, this, 600, 800);
		bgName = new DialogBackground(x, y + 100, id, this, 200, 50);
		bgIntro = new DialogBackground(x, y, id, this, 600, 180 + getTextHeight(conversationIntro.text));
	}
	
	public void show() {
		dialogWindow.options.clear();
		if(EasterEggsBase.easterEggs!=null)
		for(EasterEgg egg: EasterEggsBase.easterEggs) {
			//Gdx.app.log("GameWorld", "id: " + egg.id + ", name: " + egg.name + ", isTrue: " + egg.isTrue);
			if (egg.isTrue)
				dialogWindow.addOption(egg.name, egg.id);
		}
		
		isOn = true;
		dialogWindow.show();
	}
	
	public void hide() {
		isOn = false;
		dialogWindow.hide();
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		dialogWindow.updateBefore(delta, vx, vy);
		dialogWindow.getPosition().set(bg.getPosition().x - bg.width/2 + 40 + 40, bg.getPosition().y +5);
		conversation.getPosition().set(bg.getPosition().x - bg.width/2 + (int)(bg.width*1f/2.618f), bg.getPosition().y + bg.height/2);
		bg.getPosition().set(position.x, position.y + bg.height/2 + 100);
		bg.updateBefore(delta, vx, vy);
		conversationIntro.getPosition().set(bg.getPosition().x - bg.width/2 + 40, bg.getPosition().y + bg.height/2 + bgIntro.height - 40 + 5);
		bgIntro.getPosition().set(bg.getPosition().x - bg.width/2 + bgIntro.width/2, bg.getPosition().y + bg.height/2 + bgIntro.height/2);
		bgIntro.updateBefore(delta, vx, vy);
		conversationName.getPosition().set(bgIntro.getPosition().x - bgIntro.width/2 + 40, bgIntro.getPosition().y + bgIntro.height/2 + bgName.height/2 +5);
		bgName.getPosition().set(bgIntro.getPosition().x - bgIntro.width/2 + bgName.width/2, bgIntro.getPosition().y + bgIntro.height/2 + bgName.height/2);
		bgName.updateBefore(delta, vx, vy);
		bgStory.getPosition().set(Consts.gameWidth - 340, Consts.gameHeight - 440);
		bgStory.updateBefore(delta, vx, vy);
		storyWindow.getPosition().set(bgStory.getPosition().x - bgStory.width/2 + 20, bgStory.getPosition().y + bgStory.height/2 - 20);
	}
	
	public void render(SpriteBatch batch, int priority) {
		if (isOn) {
			bgIntro.render(batch, priority);
			conversationIntro.render(batch, priority);
			bgName.render(batch, priority);
			conversationName.render(batch, priority);
			bg.render(batch, priority);
			dialogWindow.render(batch, priority);
			conversation.render(batch, priority);
			bgStory.render(batch, priority);
			storyWindow.render(batch, priority);
			storyWindow.storyText.renderText(batch);
		}
	}
	
	public void acceptDialogOptions() {
		conversation.showCombination(dialogWindow.getCombination());
		storyWindow.updateText(dialogWindow.getCombination());
	}
	
	public void markChosenOption() {
		if (isOn) {
			dialogWindow.markChosenOption();
		}
	}
	
	public void moveChosenUp() {
		if (isOn) {
			dialogWindow.moveChosenUp();
		}
	}
	
	public void moveChosenDown() {
		if (isOn) {
			dialogWindow.moveChosenDown();
		}
	}
	
	private int getTextHeight(String s) {
		String[] lines;
		lines = s.split("\n");
		return lines.length*14;
	}
	
	

}
