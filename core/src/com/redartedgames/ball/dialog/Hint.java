package com.redartedgames.ball.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;

public class Hint extends ReversableObject {
    public Hint(float x, float y, int width, int height, String text, GameObject parent, int id) {
        super(x, y, parent, id);
        DialogBackground dBg = new DialogBackground(x-25 + 25, y + 50 + height, 0, this, width + 50, height);
        dBg.myColor = new Color(0.25f, 0.3f, 0.25f, 1f);
        dBg.myColorBg = new Color(0.1f, 0.15f, 0.1f, 0.5f);
        gameObjects.add(dBg);
        gameObjects.add(new ConversationIntro(x + 15-width/2, y + 100 + 7 + height - 50, 0, this, text));
    }

    public void render(SpriteBatch batch, int priority) {
        super.render(batch, priority);
        for (GameObject obj: gameObjects) {
            obj.render(batch, priority);
            Gdx.app.log("asd", "render");
        }
    }
}
