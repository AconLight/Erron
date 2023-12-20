package com.redartedgames.ball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.redartedgames.ball.consts.Consts;

public class LvlPrinter {

    public static void print(int numb) {
        System.out.println("duppa");
        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

        for (int i = 4; i < pixels.length; i += 4) {
            pixels[i - 1] = (byte) 255;
        }

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        Pixmap pixmap2 = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        for (int y = 0; y < Gdx.graphics.getBackBufferHeight(); y++) {
            for (int x = 0; x < Gdx.graphics.getBackBufferWidth(); x++) {
                int c1 = pixmap.getPixel(x, y);
                Color c = new Color(c1);
                if (c.g > 0.7f) {
                    pixmap2.drawPixel(x, y, Color.rgba8888(Color.CLEAR));
                } else {
                    pixmap2.drawPixel(x, y, c1);
                }
            }
        }
        PixmapIO.writePNG(Gdx.files.external("lvlicons/lvlicon" + numb + ".png"), pixmap2);
        System.out.println(Gdx.files.external("lvlicons/lvlicon" + numb + ".png").path());
        //pixmap.dispose();
    }
}
