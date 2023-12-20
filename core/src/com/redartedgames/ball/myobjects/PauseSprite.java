package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.consts.Consts;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class PauseSprite extends SpriteObject {
    public PauseSprite() {
        super(Consts.gameWidth/2, Consts.gameHeight/2+50, null, 0);
        addTexture("graphic/pause.png");
        isOn = false;
        shitfY = 0f;
    }

    float speed = 0.2f;
    public boolean isOn;
    public float shitfY;
    float k = 0;

    public void pause() {
        shitfY = 0;
        isOn = true;
        k = 0;
    }

    public void unpause() {
        isOn = false;
        k = 1;
    }

    @Override
    public void updateLast(float delta, float vx, float vy) {
        myUpdate(delta*4);
    }

    public void myUpdate(float delta) {
        if (isOn) {
            k += delta*speed;
            visibility = k*k*k;
            shitfY = k*k*50;
            if (k > 1) {
                visibility = 1f;
                shitfY = 50f;
            }
        } else {
            k -= delta*speed;
            visibility = k*k;
            shitfY = k*k*50;
            if (k < 0) {
                visibility = 0f;
                shitfY = 0f;
            }
        }
        position = new Vector2(Consts.gameWidth/2, Consts.gameHeight/2+50-shitfY);
    }
}
