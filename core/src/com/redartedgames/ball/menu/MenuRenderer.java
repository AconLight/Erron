package com.redartedgames.ball.menu;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.graphicgenerators.Mountain;
import com.redartedgames.ball.myobjects.Cloud;
import com.redartedgames.ball.myobjects.Planet;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class MenuRenderer extends ScreenRenderer{

	Color c1 = new Color(120/255f, 180/255f, 160/255f, 1);
	Color c2 = new Color(210/255f, 60/255f, 60/255f, 1);
	int h1[], h2[];
	int h1l, h2l;
	Random rand;
	
	SpriteObject tloswiatlo, desen, bgshade;
	GameObject bg, desenObj;
	Mountain mountain, m2;
	Cloud cloud1, cloud2, cloud3;
	Planet planet, planet2, planet3;
	SpriteObject nextLvlRect;
	SpriteObject monster;

	public MenuRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		monster = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		monster.addTexture("graphic/monster.png");
		monster.sclX = 8;
		monster.sclY = 8;
		nextLvlRect = new SpriteObject(0, 0, null, 0);
		Texture t = GameObject.dotTex;
		nextLvlRect.addTexture(t);
		nextLvlRect.sclX = Consts.gameWidth;
		nextLvlRect.sclY = Consts.gameHeight;
		nextLvlRect.setColor(0, 0, 0, 1);
		nextLvlRect.visibility = 1f;
		bgshade = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		bgshade.addTexture("graphic/bg/bgshade.png").visibility = 1f;
		tloswiatlo = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		tloswiatlo.addTexture("graphic/bg/tloswiatlo.png").visibility = 0.2f;
		desen = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		desen.addTexture("graphic/bg/desen.png").visibility = 1f;
		desen.setColor(120/255f, 180/255f, 160/255f, 1f);
		desen.sclX = 4; desen.sclY = 2.2f;
		desenObj = new GameObject(0, 0, 0, null);
		desenObj.addSprite(desen);
		bgshade.setColor(1, 1, 1, 0f);

		h1l = Consts.gameWidth;
		h2l = Consts.gameWidth;
		h1 = new int[h1l];
		h2 = new int[h2l];
		rand = new Random();
		int z = 0;
		int v = 2;
		for(int i2 = 0; i2 < h1l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h1[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/150f*v)*50 + Math.sin((i2+rand.nextInt(2)-1)/15f*v)*23) + (int)(Math.sqrt(i2))*5 + z*2 + 73-100;
		}
		z = 0;
		for(int i2 = 0; i2 < h2l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h2[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/90f*v)*70 + Math.sin((i2+rand.nextInt(2)-1)/15f*v)*40)  + (int)(Math.sqrt(i2))*8 + z*2 + 110-100;
		}
		cloud1 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2, 0, null);
		cloud2 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 200, 0, null);
		cloud3 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 400, 0, null);
		planet = new Planet((int)(Consts.gameWidth*1.4f), (int)(Consts.gameHeight*1.07f), new Vector3(120/255f, 240/255f, 200/255f), 10);
		planet2 = new Planet((int)(Consts.gameWidth*1.33f), (int)(Consts.gameHeight*1.1f), new Vector3(120/255f, 230/255f, 210/255f), 30);
		planet3 = new Planet((int)(Consts.gameWidth*(0.4f)), (int)(Consts.gameHeight*1.03f), new Vector3(80/255f, 120/255f, 90/255f), 80);
	}
	float position = 0;


	float colorFact = 1.1f;
	public void render() {
		Gdx.app.log("color", c1.toString());
		c1 = new Color(((MenuWorld)world).selectedColor);
		c1 = new Color(c1.r*colorFact, c1.g*colorFact, c1.b*colorFact, c1.a);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		float s1 = 400;
		float s2 = 200;
		float dxf = 0;
		float dxf2 = 0;
		int dx = 0;
		int dx2 = 0;
		
		
		batch.begin();
		desen.setColor(c1);
		desenObj.render(batch, 0);
		batch.setColor(1, 1, 1, 1);

		
		batch.end();
		renderAdvancedGraphic();
		
		sr.begin(ShapeType.Filled);
		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
		for(int i = 1; i < h2l/2 - dx2-1; i++) {
			drawShadyRect(sr, -20+i*10, 300, 10, (int)(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, (int) (60-(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1])/5));
		}
		
		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
		for(int i = 1; i < h1l-dx-1; i++) {
			drawShadyRect(sr, -20+i*12, 200, 12, (int)(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, (int) (70-(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1])/5));
		}
		sr.end();

		super.render();
		batch.begin();
		monster.setColor(((MenuWorld)world).selectedColor);
		monster.render(batch, 1);
		nextLvlRect.render(batch, 1);
		batch.end();
		
	}

	float time = 0;
	public void renderAdvancedGraphic() {
		nextLvlRect.visibility -= 0.03f;
		if (nextLvlRect.visibility <= 0) {
			nextLvlRect.visibility = 0;
		}
		Gdx.gl.glEnable(GL20.GL_BLEND);
		cloud1.getPosition().x = (float) ( + Consts.gameWidth/2 + 10*Math.sin(time/100));
		cloud2.getPosition().x = (float) (+ Consts.gameWidth/2 + 20*Math.sin(time/100 + 2));
		cloud3.getPosition().x = (float) (Consts.gameWidth/2 + 30*Math.sin(time/100 + 1));
		time += Gdx.graphics.getDeltaTime()*2;
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		planet.updateLast(0.005f, 0, 0);
		planet2.updateLast(0.007f, 0, 0);
		planet3.updateLast(0.01f, 0, 0);

		batch.begin();

		planet.updateColor(c1);
		planet2.updateColor(c1);
		planet3.updateColor(c1);


		planet.render(batch, 0);
		planet2.render(batch, 0);
		planet3.render(batch, 0);


		cloud1.render(batch);

		batch.end();

		sr.begin(ShapeType.Filled);

		planet.render(sr, 0);
		planet2.render(sr, 0);
		planet3.render(sr, 0);
		//Gdx.app.log("gr", planets.size() + "");


//		float s1 = 400;
//		float s2 = 200;
//		float dxf = (((GameWorld)world).player.getPosition().x%s2/s2);
//		float dxf2 = (((GameWorld)world).player.getPosition().x%s1/s1);
//		int dx = (int)(((GameWorld)world).player.getPosition().x/s2);
//		int dx2 = (int)(((GameWorld)world).player.getPosition().x/s1);
//		float dxfl = (((GameWorld)world).player.getPosition().x/s2);
//		float dxfl2 = (((GameWorld)world).player.getPosition().x/s1);

//		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
//		for(int i = 1; i < h2l/2 - dx2-1; i++) {
//			drawShadyRect(sr, -20+i*10, 300, 10, (int)(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, (int) (60-(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1])/5));
//		}
		sr.end();

		batch.begin();
		cloud2.render(batch);
//		batch.end();


//		sr.begin(ShapeType.Filled);
//
//		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
//		for(int i = 1; i < h1l-dx-1; i++) {
//			drawShadyRect(sr, -20+i*12, 200, 12, (int)(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, (int) (70-(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1])/5));
//		}
//		sr.end();

//		batch.begin();
		cloud3.render(batch);


		batch.end();




	}



	
	private void drawShadyRect(ShapeRenderer sr, int x, int y, int width, int height, int r, int g, int b, int alfa, int shadeUp, int shadeDown) {
		sr.setColor(r/256f, g/256f, b/256f, alfa/100f);
		sr.rect(x, y, x + width/2, y+height/2, width, height, 1, 1, 0,
				new Color(r/256f*shadeUp/100f, g/256f*shadeUp/100f, b/256f*shadeUp/100f, 1),
				new Color(r/256f*shadeUp/100f, g/256f*shadeUp/100f, b/256f*shadeUp/100f, 1),
				new Color(r/256f*shadeDown/100f, g/256f*shadeDown/100f, b/256f*shadeDown/100f, 1),
				new Color(r/256f*shadeDown/100f, g/256f*shadeDown/100f, b/256f*shadeDown/100f, 1));
		
		//(x, y, Consts.gameWidth, Consts.gameHeight);
	}

}
