package com.redartedgames.ball.map;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.redartedgames.ball.graphicgenerators.Biom;
import com.redartedgames.ball.graphicgenerators.Mountain;
import com.redartedgames.ball.myobjects.Cloud;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class MapRenderer extends ScreenRenderer{

	Color c1 = new Color(55/255f, 20/255f, 64/255f, 1);
	int h1[], h2[];
	int h1l, h2l;
	Random rand;
	
	SpriteObject tloswiatlo, desen, bgshade, map_bg;

	Cloud cloud1, cloud2, cloud3;
	GameObject bg, desenObj;
	Mountain mountain, m2;
	
	public MapRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		cloud1 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2, 0, null);
		cloud2 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 200, 0, null);
		cloud3 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 400, 0, null);
		bgshade = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		bgshade.addTexture("graphic/bg/bgshade.png").visibility = 1f;
		map_bg = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		map_bg.addTexture("graphic/map_bg.png").visibility = 0.9f;
		map_bg.sclX = 5;
		map_bg.sclY = 5;
		tloswiatlo = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		tloswiatlo.addTexture("graphic/bg/tloswiatlo.png").visibility = 0.2f;
		desen = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		desen.addTexture("graphic/bg/desen.png").visibility = 0.7f;
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
		Biom biom = new Biom();
	}
	float position = 0;
	
	public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		float s1 = 400;
		float s2 = 200;
		float dxf = 0;
		float dxf2 = 0;
		int dx = 0;
		int dx2 = 0;
		
		
		batch.begin();
		desenObj.render(batch, 0);

		
		batch.end();
		
		
		sr.begin(ShapeType.Filled);
		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
		for(int i = 1; i < h2l/2 - dx2-1; i++) {
			drawShadyRect(sr, (int)(-20+i*10-((MapWorld)world).selectedIdXf*480/100), 300, 10, (int)(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, (int) (60-(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1])/5));
		}
		
		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
		for(int i = 1; i < h1l-dx-1; i++) {
			drawShadyRect(sr, (int)(-20+i*12-((MapWorld)world).selectedIdXf*480/100), 200, 12, (int)(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, (int) (70-(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1])/5));
		}
		sr.end();

		cloud1.getPosition().x = (float) (-((MapWorld)world).selectedIdXf*480/10 + Consts.gameWidth/2);
		cloud2.getPosition().x = (float) (-((MapWorld)world).selectedIdXf*380/10 + Consts.gameWidth/2);
		cloud3.getPosition().x = (float) (-((MapWorld)world).selectedIdXf*180/10 + Consts.gameWidth/2);

		map_bg.getPosition().x = (float) (-((MapWorld)world).selectedIdXf*480/60 + Consts.gameWidth/2);


		batch.begin();



		cloud1.render(batch);
		cloud2.render(batch);
		cloud3.render(batch);
		cloud1.render(batch);
		cloud2.render(batch);
		cloud3.render(batch);
		cloud1.render(batch);
		cloud2.render(batch);
		cloud3.render(batch);


		map_bg.render(batch, 0);

		batch.end();

		super.render();

		batch.begin();

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
