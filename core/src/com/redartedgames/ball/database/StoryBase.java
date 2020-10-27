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
		
		Gdx.app.log("StoryBase", "load" + id);
		/*story = story.replace("�", "s");
		story = story.replace("�", "a");
		story = story.replace("�", "z");
		story = story.replace("�", "o");
		story = story.replace("�", "l");
		story = story.replace("�", "n");
		story = story.replace("�", "c");
		story = story.replace("�", "e");*/
		
		
		convertStory(story);
		
	}
	public static void load2() {
		combinations = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			//Gdx.app.log("ConversationBase", "iteration");
			if(prefs.contains("" + (i+1)) && prefs.contains((i+1) + "v")) {
				Gdx.app.log("ConversationBase", "contains: " + prefs.getString("" + (i+1)));
				
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
			"\nA / LEFT - left\n\nD / RIGHT - right\n\nW / UP - jump\n\nSPACE - time travel\n\nR - restart level\n\nENTER - start level\n" +
			"",

			"Hold W / UP to jump" +
			"",
			
			"Yee.. moving objects..",
			
			"Jump, jump, jump",
			
			"You can jump in the air after sliding off",

			"Did you know you can do a double jump?",

			"Let\'s see what you learnt",
			
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
			
			"First time in the history. Blue imp and a red one. Together. ",
			
			"This is the end of the demo\n\nThanks for playing :)\n\nGame developers hate him! \nHe finished all levels using this one weird trick. See how...",
			
	};
	
	static String[] stories2 = {
			"first conv",
			"sec conv"
			
					
	};
	
	static String[] stories = {
			"To wspomnienie dotyczy ostatniego wieczoru. (1)Jak zwykle w pi�tek oko�o godziny osiemnastej pi�e� piwo i gra�e� na komputerze. @"
			+ "Trwa�o to mniej wi�cej cztery godziny. (1 2 3)Po jakim� czasie zacz��e� si� kreci� po pokoju z nud�w. W ko�cu stan��e� przed szaf�. @"
			+ " (2 3)Otworzy�e� szaf� i skierowa�e� sw�j pijany wzrok na sznur. @(2)Zawi�zanie w�z�a by�o zaskakuj�co proste. @(3) Spojrza�e� na szaf�, wydawa�a si� do�c wysoka, ale mi�dzy ni� a sufitem by�o du�o miejsca. @"
			+ "Chcia�e� po sobie co� zostawi�... Niestety nic ci nie przychodzi�o do g�owy, wi�c napisa�e� tylko: 'game over'.\n",
			
			"(4)W�a�nie sko�czy�e� gr�. (5)Nie by�o to nic specjalnego, ale troch� czasu nad tym sp�dzi�e�. (6)Wspomnienia gracza, kt�re pozna�e�, by�y zadziwiaj�co znajome. @Tak jakby by�a to osoba, kt�r� zna�e�. "
			+ "(2 6)Co wi�cej nie by�y to zabawne wspomnienia, a gracz sko�czy� do�� nieciekawie. (4 6)Jednak najbardziej ciekawi� ci� fakt, �e gracz wspomnia�, �e nie jest mo�liwe, aby gra udost�pnia�a czyje� wspomnienia, bo gdyby tak by�o, to i tak pierwszy gracz nie mia�by od kogo tych wspomnie� uzyska� i zosta�o by to w jego wspomnieniach zaznaczone, a kolejni gracze z pewno�ci� by przekazywali ten fakt dalej. @"
			+ "No chyba, �e pierwszy gracz uzyska� by wspomnienia ostatniego gracza.... (5 6)Ale wtedy musia�by przenie�� si� w czasie, co nie jest mo�liwe... Musia�by tak�e wiedzie�, kto zostanie ostatnim graczem.. @"
			+ "W ka�dym razie czu�e� si� mocno zagubiony i nie by�o to wcale przyjemne, raczej do�uj�ce."
					
	};
}
