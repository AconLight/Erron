package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.redartedgames.ball.database.Consts2;
import com.redartedgames.ball.database.ConversationsBase;
import com.redartedgames.ball.database.StoryBase;
import com.redartedgames.ball.screen.Consts;

public class StoryText {
	private ArrayList<Combination> elements;
	private ArrayList<Boolean> isOns;
	private ArrayList<Integer> blank;
	public BitmapFont font;
	GlyphLayout layout = new GlyphLayout();
	
	public boolean isComb = false;
	public boolean isOn;
	int width;
	public int X = 0, Y = 0;;
	
	public StoryText() {
		blank = new ArrayList<>();
		load();
		initText();
		font = Consts2.Font(20);
		isOn = true;
		width = 500;
	}
	

	
	public StoryText(int width) {
		blank = new ArrayList<>();
		load();
		initText();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("dupa.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 28;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.WHITE;
		parameter.characters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM�󳜹�����ӣ������1234567890.,?!':_-()/";
		//System.out.println("czcionka");
		System.out.println();
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		font.setColor(0.8f, 0.8f, 0.8f, 1f);
		isOn = true;
		this.width = width;
	}
	
	public void load() {
		//StoryBase.tryFirstLoad();
		StoryBase.load();
		elements = StoryBase.combinations;
	}
	
	public void setAsBreak(int levelId) {
		StoryBase.loadBreak(levelId);
		elements = StoryBase.breakCombinations;
		isOn = false;
	}
	
	public void setText(String text) {
		elements = new ArrayList<>();
		elements.add(new Combination(text, text));
		isOn = false;
	}
	
	private void initText() {
		isOns = new ArrayList<>();
		for (Combination comb: elements) {
			isOns.add(comb.compare(blank));
		}
	}
	
	public void updateText(ArrayList<Integer> combination) {
		for (int i = 0; i < elements.size(); i++) {
			if (!isOns.get(i))
			isOns.set(i, elements.get(i).compare(combination));
		}
	}
	boolean isF = true;
	public void renderText(SpriteBatch batch) {
		renderText(batch, Consts.gameWidth - 620, Consts.gameHeight - 200);
	}
	public void renderText(SpriteBatch batch, float dx, float dy) {
		if (isComb) {
			String[] words;
			String text = "";
			float length = 0, oldLength = 0;
			int line = 0;
			if (elements != null)
			for (int i = 0; i < elements.size(); i++) {
				words = elements.get(i).text.replace("\n", "").split(" ");
				for (String word : words) {
					if (length + word.length() + " ".length() < width) {
						layout.setText(font, word + " ");
						length += layout.width*1f;
						text += word + " ";
					}
					else {
						if (isOns.get(i))
							render(batch, X + dx + oldLength, Y + dy - 30*line, text);
						oldLength = 0;
						layout.setText(font, word + " ");
						length = layout.width;
						text = "";
						line++;
						text += word + " ";
					}
				}
				if (isOns.get(i))
					render(batch, X + dx + oldLength, Y + dy - 30*line, text);
				oldLength = length;
				text = "";
			}
		} else {
			
			String text = "";
			for (Combination element : elements) {
				text += element.text;
			}
			
			render(batch, X, Y, text);

		}
	}
	
	public void render(SpriteBatch batch, float x, float y, String text) {
		if (isOn)
		if (isComb) font.draw(batch, text, x, y);
		else font.draw(batch, text, x, y, width, -1, true);
	}
}
