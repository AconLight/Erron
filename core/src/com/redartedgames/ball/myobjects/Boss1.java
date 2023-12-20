package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Boss1 extends ReversableObject {

    public SpriteObject main;
    BigDecimal time;
    static BigDecimal deltaTime = new BigDecimal(0.01f);
    ArrayList<SpriteObject> projs;

    public Boss1(float x, float y, GameObject parent, int id) {
        super(x, y, parent, id);
        main = new SpriteObject(x, y, null, 0);
        main.addTexture("graphic/shape/dot.png");
        projs = new ArrayList<>();

        main.sclX = 40;
        main.sclY = 40;
        time = new BigDecimal(0f);
    }

    public void shoot() {
        SpriteObject proj = new SpriteObject(this.position.x, this.position.y, null, 0);
        proj.addTexture("graphic/shape/dot.png");
        proj.sclX = 4;
        proj.sclY = 4;
    }

    public void updateBefore(float delta, float vx, float vy) {
        super.updateBefore(delta, vx, vy);
    }

    public void updateLast(float delta, float vx, float vy) {
        super.updateLast(delta, vx, vy);
    }

    public void applyPhysicsToAcceleration() {
        super.applyPhysicsToAcceleration();
    }

    public void updateAfter(float delta, float vx, float vy) {
        super.updateAfter(delta, vx, vy);
    }

    public void render(SpriteBatch sr, int priority) {
        main.setPosition(movement.getPosition());
        main.render(sr, priority);
    }
}
