package com.redartedgames.ball.myobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class RectParticles {
	public ArrayList<Vector3> rects;
	public float width = 8, height = 4;
	public float time = 1;
	Random rand;
	public String framesToSave = "";
	Preferences prefs;
	ArrayList<ArrayList<Vector3>> loaded;
	ArrayList<ArrayList<Vector3>> remembered;
	ArrayList<Vector3> newLoaded;
	public int ctr = 0;

	public RectParticles(int numb) {
		ctr = 0;
		remembered = new ArrayList<>();
		prefs = Gdx.app.getPreferences("maxLavel");
		rand = new Random();
		rects = new ArrayList<>();
		for (int i = 0; i < numb; i++) {
			rects.add(new Vector3(0, i*8, rand.nextInt((int)time*10)/10f));
		}
	}
	
	public void update(float delta) {
		ctr++;
		if (newLoaded != null) {
			if (newLoaded.size() > 0) {
				if (ctr >= newLoaded.size()) {
					ctr = 0;
				}
				for (Vector3 rect : rects) {
					rect.z -= delta;
					if (rect.z <= 0) {
						rect.x = newLoaded.get(ctr).x + rand.nextInt(40) - 20;
						rect.y = newLoaded.get(ctr).y + rand.nextInt(40) - 20;
						rect.z = rand.nextInt((int) time * 10) / 10f;
					}
				}
			}
		} else {
			for (Vector3 v: rects) {
				v.add(0, 0, delta);
			}
		}
	}

	public void saveFrame() {
		remembered.add(new ArrayList<Vector3>());
		for (Vector3 rect: rects) {
			remembered.get(remembered.size()-1).add(new Vector3(rect.x, rect.y, rect.z));
		}
	}

	public void saveAllFrames(int lvl) {
		for (ArrayList<Vector3> myRects: remembered) {
			framesToSave += "asd";
			for (Vector3 rect: myRects) {
				framesToSave += "abc";
				framesToSave += (int)(rect.x);
				framesToSave += "dsa";
				framesToSave += (int)rect.y;
				framesToSave += "dsa";
				framesToSave += (int)rect.z;
			}
		}
		prefs.putString("lvl_hint" + lvl, framesToSave);
		prefs.flush();
	}

	public void loadFrames(int lvl) {
		//Gdx.app.log("hints", "loaded");
		String s = prefs.getString("lvl_hint" + lvl);
		loaded = new ArrayList<>();
		if (prefs.getString("lvl_hint_elo" + lvl).length() < 20) {
			for (String s2 : s.split("asd")) {
				if (s2.length() > 0) {
					ArrayList<Vector3> temp = new ArrayList<>();
					for (String s3 : s2.split("abc")) {
						if (s3.length() > 0) {
							Vector3 rect = new Vector3();
							String[] s4 = s3.split("dsa");
							rect.x = Float.parseFloat(s4[0]);
							rect.y = Float.parseFloat(s4[1]);
							rect.z = Float.parseFloat(s4[2]);
							temp.add(rect);
						}
					}
					loaded.add(temp);
				}
			}
		}
		newLoaded = new ArrayList<>();
		String ss = "";
		//Gdx.app.log("honts loaded size", loaded.size()+"");
		if (loaded.size() > 10) {
			for (ArrayList<Vector3> myRects : loaded) {
				float x = 0, y = 0;
				for (Vector3 rect : myRects) {
					x += rect.x;
					y += rect.y;
				}
				x /= myRects.size();
				y /= myRects.size();
				if (x > 50) {
					newLoaded.add(new Vector3(x, y, 0));
					ss += (int) x + "," + (int) y + "asd";
				}
			}
			for (Vector3 rect : rects) {
				rect.x = newLoaded.get(0).x + rand.nextInt(40) - 20;
				rect.y = newLoaded.get(0).y + rand.nextInt(40) - 20;
				rect.z = rand.nextInt((int) time * 10) / 10f;
			}
			//Gdx.app.log("honts ss", ss);
			prefs.putString("lvl_hint_elo" + lvl, ss);
			// prefs.putString("lvl_hint" + lvl, "");
			prefs.flush();
		} else {
			//Gdx.app.log("honts", lvl+"");
			String sss = prefs.getString("lvl_hint_elo" + lvl);
			//Gdx.app.log("honts", sss);
			for (String sss2: sss.split("asd")) {
				//Gdx.app.log("honts", sss2);
				if (sss2.length() > 0) {
					Vector3 rect = new Vector3();
					String[] sss3 = sss2.split(",");
					rect.x = Float.parseFloat(sss3[0]) + rand.nextInt(40) - 20;
					rect.y = Float.parseFloat(sss3[1]) + rand.nextInt(40) - 20;
					rect.z = rand.nextInt((int) time * 10) / 10f;
					newLoaded.add(rect);
				}
			}
			if (newLoaded.size() > 0)
			for (Vector3 rect : rects) {
				rect.x = newLoaded.get(0).x + rand.nextInt(40) - 20;
				rect.y = newLoaded.get(0).y + rand.nextInt(40) - 20;
				rect.z = rand.nextInt((int) time * 10) / 10f;
			}
		}
	}
}
