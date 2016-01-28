package no.larsla.coptergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by lars on 28.01.16.
 */
public class CopterAnimation {
    private static final String TAG = CopterAnimation.class.getSimpleName();
    private Bitmap bitmap;
    private Rect sourceRect;
    private int frameNr;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;

    private int spriteWidth;
    private int spriteHeight;

    private int x;
    private int y;

    public CopterAnimation(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth() / frameCount;
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect (0, 0, spriteWidth, spriteHeight);
        framePeriod = 1000 / fps;
        frameTicker = 01;
    }

    public void update(long gameTime) {
        gameTime = System.currentTimeMillis();
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;
            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }
        this.sourceRect.left = currentFrame * spriteWidth;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }

    public void draw(Canvas canvas) {
        Rect destRect = new Rect(this.x, this.y, this.x + spriteWidth, this.y + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);

    }
}
