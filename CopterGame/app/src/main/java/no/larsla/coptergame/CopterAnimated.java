package no.larsla.coptergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import sheep.game.Sprite;

public class CopterAnimated extends Sprite {
    private Bitmap bitmap;      // the animation sequence
    private Rect sourceRect;    // rectangle to select image frame
    private int frameNr;        // number of frames in animation
    private int currentFrame;   // the current frame
    private long frameTicker;   // the time of the last frame update
    private int framePeriod;    // milliseconds between each frame (1000/fps)
    private int spriteWidth;    // the width of the sprite to calculate the cut out rectangle
    private int spriteHeight;   // the height of the sprite
    private float x;              // the X coordinate of the object (top left of the image)
    private float y;

    public CopterAnimated(Bitmap bitmap, float x, float y, int fps, int frameCount){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth()/frameCount;
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
        framePeriod = 1000/fps;
        frameTicker = 1l;
        flip();
    }

    public void update(long gameTime) {
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;
            // increment the frame
            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }
        // define the rectangle to cut out sprite
        this.sourceRect.left = currentFrame * spriteWidth;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;

    }

    @Override
    public void draw(Canvas canvas) {
        setX(getX() + getSpeed().getX() / 100);
        setY(getY() + getSpeed().getY() / 100);

        Rect destRect = new Rect((int)x, (int)y, (int)x + spriteWidth, (int)y + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    public void flip(){
        Matrix mirrorMatrix = new Matrix();
        mirrorMatrix.preScale(-1, 1);
        Bitmap turnMap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mirrorMatrix, false);
        turnMap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        bitmap = new BitmapDrawable(turnMap).getBitmap();
    }

    //Setters and getters
    public void setX(float x){ this.x = x;}
    public void setY(float y){this.y = y;}

    public float getX(){return x;}
    public float getY(){return y;}

    public int getHeight(){return spriteHeight;}
    public int getWidth(){return spriteWidth;}

    public Rect hitBox(){
        return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
    }
}


