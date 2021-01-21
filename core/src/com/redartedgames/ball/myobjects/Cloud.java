package com.redartedgames.ball.myobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;

public class Cloud extends GameObject{
	Random rand = new Random();
	Color color = new Color(1, 1, 1, 0.01f);
	Color colorBackward = new Color(1, 1, 1, 0.03f);
	int k = 8;
	int kk = 2;
	float a1 = 1, a2 = 1;
	float div = 3;
	public CloudRect cloudRect;

	boolean isForward = true;
	public void setIsForward(boolean isForward) {
		this.isForward = isForward;
	}
	
	public class CloudRect {
		CloudRect c1, c2, c3, c4;
		int widthd2, heightd2;
		int x, y;
		public CloudRect(int x, int y, int widthd2, int heightd2, int depth, int dir) {
			this.widthd2 = widthd2;
			this.heightd2 = heightd2;
			this.x = x;
			this.y = y;
			if (depth > 0) {
				div = rand.nextInt(100)/100f*a1 + a2;
				if (rand.nextInt(k*kk) != 0 && dir != 2)
				c1 = new CloudRect((int) (x + widthd2 + widthd2/div), y, (int) (widthd2/div), (int) (heightd2/div), depth-1, 1);
				div = rand.nextInt(100)/100f*a1 + a2;
				if (rand.nextInt(k*kk) != 0 && dir != 1)
				c2 = new CloudRect((int) (x - widthd2 - widthd2/div), y, (int) (widthd2/div), (int) (heightd2/div), depth-1, 2);
				div = rand.nextInt(100)/100f*a1 + a2;
				if (rand.nextInt(k) != 0 && dir != 4)
				c3 = new CloudRect(x, (int) (y + heightd2 + heightd2/div), (int) (widthd2/div), (int) (heightd2/div), depth-1, 3);
				div = rand.nextInt(100)/100f*a1 + a2;
				if (rand.nextInt(k) != 0 && dir != 3)
				c4 = new CloudRect(x, (int) (y - heightd2 - heightd2/div), (int) (widthd2/div), (int) (heightd2/div), depth-1, 4);
			}
		}

		float colTime = 0f;
		public void render(SpriteBatch sr, float posx, float posy) {
			if (isForward) {
				colTime += 0.04f;
				if (colTime >= 1) {
					colTime = 1;
				}

			} else {
				colTime -= 0.01f;
				if (colTime <= 0) {
					colTime = 0;
				}
			}
			float a = color.a * colTime + (1-colTime)*colorBackward.a;
			if (rand.nextInt(50) == 0) {
				a = color.a;
			}
			sr.setColor(1, 1, 1, a);

			sr.draw(GameObject.dotTex, posx + x-widthd2, posy + y - heightd2, 2*widthd2, 2*heightd2);
			if (c1 != null) c1.render(sr, posx, posy);
			if (c2 != null) c2.render(sr, posx, posy);
			if (c3 != null) c3.render(sr, posx, posy);
			if (c4 != null) c4.render(sr, posx, posy);
		}
	}
	
	public Cloud(float x, float y, int id, GameObject parent) {
		super(x, y, id, parent);
		cloudRect = new CloudRect(0, 0, 3*120, 2*80, 6, 0);
	}
	
	public void render(SpriteBatch sr) {
		cloudRect.render(sr, position.x, position.y);
	}
	
	

}
