package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class ConversationsBase {

	public static Preferences prefs = Gdx.app.getPreferences("conversation");
	
	public static ArrayList<Combination> combinations;
	
	private static int recordNumber = 7;
	public static void load() {
		combinations = new ArrayList<>();
		convertStory(example);
	}
	
	public static void load2() {
		combinations = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			//Gdx.app.log("ConversationBase", "iteration");
			if(prefs.contains("" + (i+1)) && prefs.contains((i+1) + "v")) {
				//Gdx.app.log("ConversationBase", "contains");
				
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
	
	static String example = "";
}
