package ru.kirillisachenko.awesomebird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class PipesModel {
    public Bitmap topPipe;
    public Bitmap bottomPipe;
    public float x;
    private final float xSpeed = 20;
    private int xMax;
    private int yMax;
    private final int spacerSize = 300;
    private int ySpacer;
    private int score = 0;
    public boolean scoreable = true;
    public PipesModel(Context context, int xMax, int yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        topPipe = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe_rotated);
        bottomPipe = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe);
        x  = xMax ;
        generatePipes();
        x = xMax;
    }


    private void generatePipes(){
        ySpacer = random(yMax/4, yMax * 3 /4 );
        Rect topRect = new Rect(0 ,0, topPipe.getWidth(), ySpacer-spacerSize );
        Rect bottomRect = new Rect (0, 0, bottomPipe.getWidth(), yMax - ySpacer - spacerSize);
        Bitmap topPlaceholder = Bitmap.createBitmap(
                topPipe.getWidth(),
                ySpacer- spacerSize,
                Bitmap.Config.ARGB_8888);
        Bitmap bottomPlaceHolder = Bitmap.createBitmap(
                bottomPipe.getWidth(),
                yMax - ySpacer- spacerSize,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(topPlaceholder);
        canvas.drawBitmap(topPipe, null, topRect, null);
        canvas = new Canvas(bottomPlaceHolder);
        canvas.drawBitmap(bottomPipe, null, bottomRect, null);
        topPipe = topPlaceholder;
        bottomPipe = bottomPlaceHolder;
    }

    public void updatePosition(){
        x-= xSpeed;
        if(x < -bottomPipe.getWidth()){
            x =xMax;
            generatePipes();
            scoreable = true;
        }
    }

    public boolean isCollision(BirdModel bird){
        if (x-100< bird.x && x + bottomPipe.getWidth() > bird.x ){
            if(bird.y - topPipe.getHeight() < 0) return true;
            if(bird.y > yMax - bottomPipe.getWidth()) return true;
        }
        return false;
    }

    public int Score(BirdModel bird){
        if (scoreable) {if (x< bird.x && x + bottomPipe.getWidth() > bird.x ){ score+=1; scoreable = false;}}
        return score;
    }

    public int getscore() {
        return score;
    }

    private int random(int min, int max){
        return min + (int) (Math.random() * (max - min));
    }


}
