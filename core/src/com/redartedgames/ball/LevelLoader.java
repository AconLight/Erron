package com.redartedgames.ball;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.consts.PhysicConsts;
import com.redartedgames.ball.dialog.DialogHero;
import com.redartedgames.ball.dialog.Hint;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.myobjects.*;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.consts.Consts;

public class LevelLoader {

	public static int getComicCtr(int levelId) {
		switch (levelId) {
			case 0:
				return 0;
			case 1:
				return 3;
			case 2:
				return 0;
			case 3:
				return 0;
			default:
				return 0;
		}
	}
	public static ArrayList<ReversableObject> getLevel(int levelId, Player player, ImpsCollection impsCollection, GameWorld gameWorld) {
		ArrayList<ReversableObject> objects = new ArrayList<ReversableObject>();		
		switch (levelId) {
		case 0: {
//			for (int i = 0; i < 9; i++)
//				for (int j = 0; j < 4; j++) {
//					objects.add(new Ball(1200+ i*45, 800-j*50, 30, 1, BehaviorMode.dynamic, null, 0));
//				}


			//objects.add(new Rect(1920, 650, 200, 1300, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(975, 30, 2060, 160, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(350, 130, 770, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(175, 180, 580, 140, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(-75, 280, 580, 240, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1675, 755, 60, 560, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1100, 755, 60, 560, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(2002, 333, 310, 200, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1125, 460, 80, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1650, 460, 80, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1625, 410, 90, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1154, 410, 90, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1204, 335, 120, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1576, 335, 120, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1801, 106, 310, 130, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1901, 182, 310, 200, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1575, 380, 50, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1657, 480, 50, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1200, 380, 50, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1121, 480, 50, 50, BehaviorMode.kinematic, null, 0));
			
			break;
		}
		case 1: {
			objects.add(new Rect(1920/2, 100, 1920, 200, BehaviorMode.kinematic, null, 0));
			HangingGuy h = new HangingGuy(170, 400, 0, null);
			TimeBackItem e = new TimeBackItem(700, 300, null, 0);
			//e.setItem("graphic/sznur.png", 700, 300, 2);
			player.easterEggs.add(e);
			objects.add(e);
//			Gdx.app.log("lvlLoader", "lvl1 load");
			player.setPosition(h.getHeadPos());
			h.setPlayer(player);
			objects.add(h);
			break;
		}
//		case 2: {
//			objects.add(new Rect(1920/2, 100, 1920, 200, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1920/2, 100, 1920/4, 400, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1920/2, 100, 1920/8, 500, BehaviorMode.kinematic, null, 0));
//			TimeBackItem e = new TimeBackItem(200, 200, null, 0);
//			e.setItem("graphic/bottle.png", 200, 200, 1);
//			player.easterEggs.add(e);
//			objects.add(e);
//			TimeBackItem e2 = new TimeBackItem(1200, 300, null, 0);
//			e2.setItem("graphic/szafa.png", 1200, 300, 3);
//			player.easterEggs.add(e2);
//			objects.add(e2);
//			StoryBase.StoryId = 0;
//			DialogHero dh = new DialogHero(600, 290, 0, null, 0);
//			dh.setCollision(player);
//			objects.add(dh);
//			gameWorld.dialogHero = dh;
//
//			break;
//		}
		case 2: {
			LavaRect lava = new LavaRect(1920/2, 50, 1920, 100, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(1920/16, 100, 1920/8, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920-1920/16, 100, 1920/8, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/2, 200, 400, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4, 150, 100, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920-1920/4, 150, 100, 50, BehaviorMode.kinematic, null, 0));

			break;
		}
		case 3: { // guziki - wprowadzenie
			LavaRect lava = new LavaRect(925, 5, 1390, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1776, 80, 410, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1348, 130, 200, 60, BehaviorMode.kinematic, null, 0));
			MovingRect m1 = new MovingRect(1054, 130, 574, 130, 270, 60, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(400, 130, m1, null, 0));
			DialogHero dh = new DialogHero(200, 280, 0, null, 2, "Wait on this lamp for the platform", 280, 50);
			dh.setCollision(player);
			objects.add(dh);
			break;
		}
		case 4: {
			
			LavaRect lava = new LavaRect(925, 55, 1630, 120, null, 0);
			lava.setPlayer(player);
			MovingRect m1, m2, m3;
			objects.add(m1 = new MovingRect(575, 48, 575, 200, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(975, 48, 975, 500, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(1375, 48, 1375, 800, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 155, m1, null, 0));
			objects.add(new StaticButton(350, 155, m2, null, 0));
			objects.add(new StaticButton(350, 155, m3, null, 0));
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 55, 290, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-125, 80, 640, 180, BehaviorMode.kinematic, null, 0));
			
			
			break;
		}

		// here goes double jump and slide off jump
		case -5: {
			LavaRect lava = new LavaRect(925, 55, 1630, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1135, 205, 200, 190, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(260, 55, 980, 190, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1510, 55, 950, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(891, 505, 50, 410, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(649, 205, 200, 190, BehaviorMode.kinematic, null, 0));



			break;
		}
		case -6: {

			LavaRect lava = new LavaRect(925, 25, 1630, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);

			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(260, 55, 980, 190, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1610, 55, 1030, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(901, 805, 770, 1150, BehaviorMode.kinematic, null, 0));


			break;
		}

		case -7: {
			
			LavaRect lava = new LavaRect(925, 55, 1630, 120, null, 0);
			lava.setPlayer(player);
			MovingRect m1, m2, m3, m4;
			objects.add(m1 = new MovingRect(575, 270, 575, 500, 120, 230, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(875, 270, 875, 500, 120, 230, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(725, 120, 725, 680, 120, 70, BehaviorMode.kinematic, null, 0));
			objects.add(m4 = new MovingRect(350, 720, 300, 720, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(350, 750, 20, 20, BehaviorMode.dynamic, null, 0));
			objects.add(new Rect(1025, 120, 120, 70, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 155, m1, null, 0));
			objects.add(new StaticButton(350, 155, m2, null, 0));

			objects.add(new StaticButton(1110, 270, m4, null, 0));

			objects.add(new Rect(1000, 820, 830, 50, BehaviorMode.kinematic, null, 0));
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 255, 290, 630, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-125, 80, 640, 180, BehaviorMode.kinematic, null, 0));
			
			
			break;
		}
		
		// wi�cej platformowych leveli jak w powy�szych
		
		case 5: { // statyczny imp - wprowadzenie
			LavaRect lava = new LavaRect(1920/2, 50, 1920, 100, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(1920/8 + 225, 100, 1920/4 + 450, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4, 200, 100, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+100, 250, 100, 300, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+200, 300, 100, 450, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+400, 300, 100, 450, BehaviorMode.kinematic, null, 0));
			
			MovingRect m1 = new MovingRect(1920, 500, 1920-400, 500, 800, 100, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(1920/4+300 , 250, m1, null, 0));
			
			TimeBackItem e = new TimeBackItem(1200, 200, null, 0);
			//e.setItem("graphic/gra.png", 1200, 200, 4);
			player.easterEggs.add(e);
			objects.add(e);
			objects.add(new Hint(780, 30, 200, 50, "hold SPACE on the lamp", null, 0));
			objects.add(new Hint(1600, 900, 200, 50, "you have one BLUE IMP", null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		
		//wi�cej leveli ze statycznym impem jak w powy�szym
		
		case 6: { //cofanie z platformy
			LavaRect lava = new LavaRect(925, 5, 1390, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1776, 80, 410, 204, BehaviorMode.kinematic, null, 0));
			MovingRect m1 = new MovingRect(800, 130, 1254, 130, 120, 60, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(400, 130, m1, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 7: { //platforma + cofanie z platformy
			LavaRect lava = new LavaRect(1022, 30, 1290, 70, null, 0);
			lava.setPlayer(player);
			MovingRect m1, m2, m3;
			objects.add(m3 = new MovingRect(1000, 140, 1000, -2400, 100, 350, BehaviorMode.kinematic, null, 0));
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(183, 81, 430, 247, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1800, 230, 320, 700, BehaviorMode.kinematic, null, 0));

			objects.add(m1 = new MovingRect(1450, 140, 625, 140, 160, 60, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(800, 430, 1575, 430, 70, 60, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(525, 120, m1, null, 0));
			objects.add(new StaticButton(525, 120, m2, null, 0));
			objects.add(new StaticButton(525, 120, m3, null, 0));

			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		
		case 8: { //cofanie z platformy x2
			LavaRect lava =new LavaRect(925, 5, 1790, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1876, 80, 210, 1404, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0)); 
			MovingRect m1 = new MovingRect(901, 111, 901, 516, 180, 57, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			MovingRect m2 = new MovingRect(1176, 655, 1526, 655, 159, 56, BehaviorMode.kinematic, null, 0);
			objects.add(m2);
			
			objects.add(new StaticButton(502, 130, m2, null, 0));
			objects.add(new StaticButton(400, 130, m1, null, 0));

			objects.add(new Hint(1520, 900, 230, 50, "you have two BLUE IMPS", null, 0));

			impsCollection.addStaticImp();
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
			case 9: { // boss
				objects.add(new Boss1(Consts.gameWidth*0.8f, 200, null, 0));
				break;
			}
		case 10: { //zamra�anie
			LavaRect lava;
			objects.add(lava = new LavaRect(936, 30, 1520, 70, null, 0));
			lava.setPlayer(player);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));

			objects.add(new Rect(100, 66, 260, 290, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 80, 460, 510, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(583, 270, 100, 70, BehaviorMode.kinematic, null, 0));
			
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(2699, 165, 474, 165, 400, 90, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(583, 541, 583, 250 + 90, 100, 90, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(422, 283, m1, null, 0));
			objects.add(new StaticButton(422, 283, m2, null, 0));
			
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 11: { //zamra�anie cd
			
			MovingRect m1, m2;
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920, 300, 200, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 80, 2030, 240, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1526, 405, 850, 120, BehaviorMode.kinematic, null, 0));
			objects.add(m1 = new MovingRect(1041, 475, 1041, 334, 120, 260, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(941, 405, 941, 253, 120, 120, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 241, m1, null, 0));
			objects.add(new StaticButton(200, 241, m2, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		case 12: { //zamra�anie cd 2
			
			MovingRect m1, m2, m3;
			player.setPosition(new Vector2(10, 550));
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, -40, 2030, 120, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(50, 170, 180, 440, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1847, 170, 180, 440, BehaviorMode.kinematic, null, 0));
			
			objects.add(m1 = new MovingRect(-193, 335, 697, 335, 480, 80, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(2100, 335, 1185, 335, 480, 80, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(951, 963, 951, 465, 130, 150, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(950, 130, m1, null, 0));
			objects.add(new StaticButton(950, 130, m2, null, 0));
			objects.add(new StaticButton(350, 230, m3, null, 0));
			
			TimeBackItem e = new TimeBackItem(900, 650, null, 0);
			//e.setItem("graphic/gracz.png", 900, 650, 6);
			player.easterEggs.add(e);
			objects.add(e);
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		case 13: {
			player.setPosition(new Vector2(10, 550));
			LavaRect lava;
			objects.add(lava = new LavaRect(950, 55, 1720, 150, null, 0));
			lava.setPlayer(player);
			objects.add(new Rect(1825, 155, 270, 410, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(125, 155, 270, 410, BehaviorMode.kinematic, null, 0));
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(-50, 265, 625, 265, 790, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(1997, 265, 1325, 265, 790, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(974, 280, m2, null, 0, true));
			objects.add(new StaticButton(100, 430, m1, null, 0));

			DialogHero dh = new DialogHero(250, 405, 0, null, 3, "The blue lamp freezes everything, also you", 330, 100);
			dh.setCollision(player);
			objects.add(dh);
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 14: {
			player.setPosition(new Vector2(10, 250));
			objects.add(new Rect(950, 55, 1960, 150, BehaviorMode.kinematic, null, 0));

			Ball ball1 = new Ball(1175, 280, 50, 50, BehaviorMode.dynamic, null, 0);
			((ReversableMovement) ball1.getMovement()).setDrag(PhysicConsts.DRAG_X*0.3f, PhysicConsts.DRAG_Y*1f);
			objects.add(ball1);
			Ball ball2 = new Ball(1263-10, 555, 50, 50, BehaviorMode.dynamic, null, 0);
			((ReversableMovement) ball2.getMovement()).setDrag(PhysicConsts.DRAG_X*0.3f, PhysicConsts.DRAG_Y*1f);
			objects.add(ball2);

			objects.add(new Rect(816, 255, 277, 50, BehaviorMode.kinematic, null, 0));

			objects.add(new Rect(1175, 200, 15, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1148, 170, 75, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1130, 140, 140, 50, BehaviorMode.kinematic, null, 0));

			MovingRect m1;
			objects.add(m1 = new MovingRect(622, 305, 622, 205, 110, 151, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(816, 365, m1, null, 0, true));
			
			TimeBackItem e = new TimeBackItem(800, 400, null, 0);
			//e.setItem("graphic/czas.png", 800, 400, 5);
			player.easterEggs.add(e);
			objects.add(e);
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
//		case 16: {
//			objects.add(new Rect(75, 55, 240, 220, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(200, 55, 50, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(275, 67, 110, 100, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(424, 72, 190, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(600, 70, 200, 50, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(800, 80, 230, 80, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(975, 80, 240, 130, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1200, 80, 240, 130, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1375, 154, 206, 179, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1700, 154, 616, 109, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(275, -21, 46, 189, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1700, 54, 66, 189, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(1075, -21, 66, 189, BehaviorMode.kinematic, null, 0));
//			objects.add(new Rect(575, -21, 66, 189, BehaviorMode.kinematic, null, 0));
//			
//
//			
//			StoryBase.StoryId = 1;
//			EasterEggsBase.load(1, 5);
//			
//			DialogHero dh = new DialogHero(660, 200, 0, null, 1);
//			dh.setCollision(player);
//			objects.add(dh);
//			gameWorld.dialogHero = dh;
//			
//			
//			break;
//		}
		case 15: {
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 55, 1980, 150, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1525, 330, 820, 100, BehaviorMode.kinematic, null, 0));
			MovingRect m1, m2;

			objects.add(m1 = new MovingRect(1075, -230, 1075, 205, 100, 330, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(975, -230, 975, 85, 100, 330, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1875, 205, 110, 180, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(1700, 200, m1, null, 0, false));
			objects.add(new StaticButton(1700, 200, m2, null, 0, false));
			
			impsCollection.addActiveImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);

			DialogHero dh = new DialogHero(300, 205, 0, null, 1, "Look! the IMP is red now...", 230, 50);
			dh.setCollision(player);
			objects.add(dh);
			break;
		}
		case 16: {
			MovingRect m1, m2, m3, m4;
			objects.add(m1 = new MovingRect(825, -35, 825, 115, 390, 120, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(1350, -35, 1350, 215, 190, 230, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(1850, 215, 1850, -10, 160, 130, BehaviorMode.kinematic, null, 0));
			objects.add(m4 = new MovingRect(1000, 455, 1570, 455, 590, 50, BehaviorMode.kinematic, null, 0));
			LavaRect lava;
			objects.add(lava = new LavaRect(925, 30, 1090, 150, null, 0));
			lava.setPlayer(player);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1675, 55, 510, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(225, 55, 450, 200, BehaviorMode.kinematic, null, 0));
			

			objects.add(new Rect(1700, 305, 580, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1000, 455, 590, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(900, 545, m3, null, 0));
			objects.add(new StaticButton(900, 545, m4, null, 0));
			objects.add(new StaticButton(250, 220, m1, null, 0));
			objects.add(new StaticButton(375, 220, m2, null, 0));
			objects.add(new Rect(1875, 705, 210, 790, BehaviorMode.kinematic, null, 0));
			
			impsCollection.addActiveImp();
			impsCollection.addActiveImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		
		case 17: {
			MovingRect m1, m2, m3;
			objects.add(m1 = new MovingRect(825, 115, 825, 400, 160, 160, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(200, 715, -100, 715, 160, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(1825, 615, 1825, 905, 280, 1000, BehaviorMode.kinematic, null, 0));
			
			LavaRect lava;
			objects.add(lava = new LavaRect(834, 30, 927, 121, null, 0));
			lava.setPlayer(player);
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(2250, 305, 250, 380, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(200, 55, 570, 160, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1625, 55, 760, 160, BehaviorMode.kinematic, null, 0));

			objects.add(new StaticButton(175, 605, m1, null, 0, true));
			objects.add(new Rect(175, 767, 50, 50, BehaviorMode.dynamic, null, 0));
			objects.add(new StaticButton(1550, 180, m2, null, 0));
			objects.add(new Rect(1400, 430, 590, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(1552, 499, m3, null, 0));
			
			impsCollection.addActiveImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		
		case 18: {
			MovingRect m1, m2, m3, m4, m5;
			objects.add(m1 = new MovingRect(825, 115, 810, 400, 160, 160, BehaviorMode.kinematic, null, 0));
			objects.add(m5 = new MovingRect(825, 115-285, 810, 115, 160, 160, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(200, 715, -100, 715, 160, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(1825, 615, 1825, 905, 280, 1000, BehaviorMode.kinematic, null, 0));
			
			
			LavaRect lava;
			objects.add(lava = new LavaRect(834, 30, 927, 121, null, 0));
			lava.setPlayer(player);
			
			objects.add(m4 = new MovingRect(175, -210, 175, 465, 620, 660, BehaviorMode.kinematic, null, 0));
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(2250, 305, 250, 380, BehaviorMode.kinematic, null, 0));
			//objects.add(new Rect(200, 55, 470, 160, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1625, 55, 900, 160, BehaviorMode.kinematic, null, 0));

			objects.add(new StaticButton(175, 455, m1, null, 0, true));
			objects.add(new StaticButton(175, 455, m5, null, 0, true));
			objects.add(new Rect(175, 767, 50, 50, BehaviorMode.dynamic, null, 0));
			objects.add(new StaticButton(1550, 180, m2, null, 0));
			objects.add(new Rect(1400, 430, 590, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(1552, 499, m4, null, 0));
			objects.add(new StaticButton(175+100, 500, m3, null, 0, true));
			//objects.add(new StaticButton(825 - 80-26-10, 400, null, null, 0, true));
			
			impsCollection.addActiveImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			
			
			break;
		}
		
		case 19: {
			
			MovingRect m1, m2, m3, m4, m5, m6;
			
			objects.add(m1 = new MovingRect(925, 340, 925, 565, 170, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(400, 240, 400, 465, 170, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(225, 90, 875, 90, 640, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m4 = new MovingRect(2050, 90, 1425, 90, 460, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m5 = new MovingRect(1350, 1000, 1350, 190, 50, 160, BehaviorMode.kinematic, null, 0));
			objects.add(m6 = new MovingRect(700, 244, 700, 165, 50, 110, BehaviorMode.kinematic, null, 0));
			
			LavaRect lava;
			objects.add(lava = new LavaRect(1100, 5, 1139, 80, null, 0));
			lava.setPlayer(player);
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1900, 55, 630, 180, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(275, 55, 630, 180, BehaviorMode.kinematic, null, 0));

			objects.add(new StaticButton(1101, 476, m2, null, 0));
			objects.add(new StaticButton(226, 376, m5, null, 0));
			objects.add(new StaticButton(1101, 701, m3, null, 0));
			objects.add(new StaticButton(226, 601, m1, null, 0));
			objects.add(new StaticButton(226, 376, m4, null, 0));
			objects.add(new StaticButton(226, 376, m6, null, 0));

			objects.add(new Rect(1350, 730, 10, 890, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1350, 630, 250, 820, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1075, 205, 800, 30, BehaviorMode.kinematic, null, 0));

			impsCollection.addActiveImp();
			impsCollection.addActiveImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		
		case 20: {
			LavaRect lava;
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(892, 390, 692, 390, 331, 80, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(500, 90, 500, 388, 200, 79, BehaviorMode.kinematic, null, 0));
			objects.add(lava = new LavaRect(1126, 29, 860, 120, null, 0));
			lava.setPlayer(player);
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1200, 655, 331, 650, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(376, 79, 801, 180, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1702, 55, 830, 240, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1200, 1110, 10, 310, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(752, 450, m1, null, 0));
			objects.add(new StaticButton(752, 210, m2, null, 0));
			
			objects.add(new Rect(965, 465, 90, 79, BehaviorMode.dynamic, null, 0));
			
			
			
			impsCollection.addActiveImp();
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			
			break;
		}
		case -10: { 
			objects.add(new Rect(138, 81, 288, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(367, 51, 180, 135, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(500, 89, 90, 192, BehaviorMode.kinematic, null, 0));
			
			objects.add(new Rect(909, 320, 90, 55, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1005, 48, 285, 142, BehaviorMode.kinematic, null, 0));
			LavaRect lava = new LavaRect(1010, 125, 290, 71, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(705, 152, 320, 392, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1500, 84, 720, 202, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1870, 305, 160, 642, BehaviorMode.kinematic, null, 0));

			MovingRect m1 = new MovingRect(1476, 78, 1476, 310, 100, 100, BehaviorMode.kinematic, null, 0);
			MovingRect m2 = new MovingRect(1672, 51, 1672, 450, 100, 100, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			objects.add(m2);
			
		//	MovingRect m3 = new MovingRect(100, 600, 400, 600, 50, 50, BehaviorMode.kinematic, null, 0);
		//	MovingRect m4 = new MovingRect(200, 500, 500, 500, 50, 50, BehaviorMode.kinematic, null, 0);

		//	objects.add(m3);
		//	objects.add(m4);
			objects.add(new StaticButton(920, 240, m1, null, 0));
			objects.add(new StaticButton(920, 240, m2, null, 0));
		//	objects.add(new StaticButton(420, 340, m4, null, 0));
		//	objects.add(new StaticButton(420, 340, m3, null, 0));
			
			
			impsCollection.addStaticImp();
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			
			
			break;	
		}
		
		}
		
		objects.add(new Rect(-100-20, Consts.gameHeight/2, 200, Consts.gameHeight, BehaviorMode.kinematic, null, 0));
		for (ReversableObject obj: objects) {
			if (obj instanceof Rect) {
				if (((Rect)obj).bush != null)
				((Rect)obj).bush.setPlayer(player);
			}
		}

		if (gameWorld != null) {
			gameWorld.blackScreenAnimation.comicCounter = getComicCtr(levelId);
		}

		return objects;
	}
}
