package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class StoryBase {

	public static Preferences prefs = Gdx.app.getPreferences("story");
	
	public static ArrayList<Combination> combinations, breakCombinations;
	
	private static int recordNumber = 8;
	
	public static void loadBreak(int levelId) {
		if (levelId < 0) return;
		breakCombinations = new ArrayList<>();
		
		String breakText;
		if (levelId < breakExample.length)
			breakText = breakExample[levelId];
		else
			breakText = "";
		
		/*breakText = breakText.replace("�", "s");
		breakText = breakText.replace("�", "a");
		breakText = breakText.replace("�", "z");
		breakText = breakText.replace("�", "o");
		breakText = breakText.replace("�", "l");
		breakText = breakText.replace("�", "n");
		breakText = breakText.replace("�", "c");
		breakText = breakText.replace("�", "e");*/
		
		convertStoryBreak(breakText);
		
	}
	
	public static int StoryId = 0;
	
	public static void load() {
		load(StoryId);
	}
	
	public static void load(int id) {
		combinations = new ArrayList<>();
		String story;
		if (id < stories.length) story = stories[id];
		else story = "dddd";
		
//		Gdx.app.log("StoryBase", "load" + id);

		
		
		convertStory(story);
		
	}
	public static void load2() {
		combinations = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			//Gdx.app.log("ConversationBase", "iteration");
			if(prefs.contains("" + (i+1)) && prefs.contains((i+1) + "v")) {
//				Gdx.app.log("ConversationBase", "contains: " + prefs.getString("" + (i+1)));
				
				combinations.add(new Combination(prefs.getString("" + (i+1)), prefs.getString((i+1) + "v")));
			}
			
		}
	}
	
	//TODO
	public static void update(EasterEgg easterEgg) {
		
	}
	
	public static void tryFirstLoad() {
		prefs.clear();
		prefs.flush();
		if (!prefs.contains("firstLoad")) {
			//Gdx.app.log("ConversationBase", "tryFirstLoad");
			prefs.putString("firstLoad", "true");
			prefs.putString("1", "1");
			prefs.putString("1v", "So yeah, you should definetly go right to the end of the screen.");
			prefs.putString("2", "2");
			prefs.putString("2v", "You will be able to turn back the time to overcome obstacles. \n"
					+ "However that will be happening during further levels... \n"
					+ "You aren't ready yet.");
			prefs.putString("3", "3");
			prefs.putString("3v", "Well, I don't know, but I guess you have just\n"
					+ " died or you are close to do it.");
			prefs.putString("4", "1 2");
			prefs.putString("4v", "Your ability of turning back the time will make\n"
					+ " you be able to undo all your moves. Pretty cool.");
			prefs.putString("5", "2 3");
			prefs.putString("5v", "Time travel is more than just a mechanic, \n"
					+ "it's connected with your life, but i can't say anything more.");
			prefs.putString("6", "1 3");
			prefs.putString("6v", "I'm pretty sure you will find out something about\n you by going right to the next levels");
			prefs.putString("7", "1 2 3");
			prefs.putString("7v", "Heh. I'm glad you were so curious as to mark all the options. \n"
					+ "I may award you and tell this curiosity will be \n"
					+ "helpfull for you to find out why you are here and \n"
					+ "what your real goal is.");
			prefs.putString("8", "");
			prefs.putString("8v", "THIS IS THE END");
			prefs.flush();
		}
		
	}
	
	static String END_OF_ELEMENT = "@";
	static String COMBINATION_START = "\\(";
	static String COMBINATION_END = "\\)";
	
	private static void convertStory(String s) {
		s.replace("\n", "");
		String[] elements = s.split(COMBINATION_START);
		String[] elementParts;
		String[] elementParts2;
		for (String element: elements) {
			elementParts = element.split(COMBINATION_END);
			if (elementParts.length > 1) {
				elementParts2 = elementParts[1].split(END_OF_ELEMENT);
				combinations.add(new Combination(elementParts[0], elementParts2[0]));
				if (elementParts2.length > 1) {
					combinations.add(new Combination("", elementParts2[1]));
				}
			}
			else {
				combinations.add(new Combination("", element));
			}

		}
	}
	
	private static void convertStoryBreak(String s) {
		s.replace("\n", "");
		String[] elements = s.split(COMBINATION_START);
		String[] elementParts;
		String[] elementParts2;
		for (String element: elements) {
			elementParts = element.split(COMBINATION_END);
			if (elementParts.length > 1) {
				elementParts2 = elementParts[1].split(END_OF_ELEMENT);
				breakCombinations.add(new Combination(elementParts[0], elementParts2[0]));
				if (elementParts2.length > 1) {
					breakCombinations.add(new Combination("", elementParts2[1]));
				}
			}
			else {
				breakCombinations.add(new Combination("", element));
			}

		}
	}
	
	static String[] breakExample = {
			"",
			
			"Let\'s get it started!\n\n" +
			"Press Esc anytime to pause the game and see the keyboard controls",

			"Hold W / UP to jump" +
			"",
			
			"Yee.. moving objects..",
			
			"Jump, jump, jump",
			
			"You can jump in the air after sliding off",

			"Did you know you can perform a double jump?",

			"Let\'s see what you learned",
			
			"Hold SPACE to travel backward in time",
			
			"The fun has started",
			
			"Are you ready?",
			
			"Seriously, are you ready?",
			
			"You can use a blue IMP to freeze a platform",
			
			"Just give up already",
			
			"Plz don\'t break my game",
			
			"The real fun hasn\'t started yet",
			
			"Time to break the time",
			
			"Somebody follows me",
			
			"Two imps, three lamps",
			
			"One imp, two lamps",
			
			"And again...",
			
			"It isn\'t that complicated, right?",
			
			"First time in the history. A blue imp and a red one. Together. ",
			
			"This is the end of the demo\n\nThanks for playing :)\n\nGame developers hate him! \nHe finished all levels using this one weird trick. See how...",
			
	};
	
	static String[] stories2 = {
			"first conv",
			"sec conv"
			
					
	};
	
	static String[] stories = {
			""
					
	};
}
