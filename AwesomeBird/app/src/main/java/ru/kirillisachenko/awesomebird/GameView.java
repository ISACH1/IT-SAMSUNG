package ru.kirillisachenko.awesomebird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private BirdModel bird;
    private PipesModel pipes;
    private final Bitmap background;
    final static private int fps = 60;
    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    public boolean lose = false;
    Paint paint = new Paint();

    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        bird = new BirdModel(getContext(), 200);
        pipes = new PipesModel(getContext(), getWidth(), getHeight());
        this.surfaceHolder = surfaceHolder;
        drawThread = new DrawThread();
        drawThread.start();
    }

    private void DrawFrames(Canvas canvas){
        canvas.drawBitmap(background, null, new Rect(0, 0, getWidth(), getHeight()), null );
        if (bird.getSpeed() < -10) canvas.drawBitmap(bird.bird1, bird.x, bird.y, null);
        if (bird.getSpeed() >= -10 && bird.getSpeed() <= 10) canvas.drawBitmap(bird.bird2, bird.x, bird.y, null);
        if ( bird.getSpeed() > 10) canvas.drawBitmap(bird.bird3, bird.x, bird.y, null);
        canvas.drawBitmap(pipes.topPipe, pipes.x, 0, null);
        canvas.drawBitmap(pipes.bottomPipe, pipes.x, getHeight() - pipes.bottomPipe.getWidth(), null);
    }


    private  void update(){
        bird.updatePosition();
        pipes.updatePosition();
    }

    private class DrawThread extends Thread{
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                Canvas canvas = surfaceHolder.lockCanvas();
                try {
                    Thread.sleep(1000/fps);
                    DrawFrames(canvas);
                    update();
                    pipes.Score(bird);
                    if (pipes.isCollision(bird)){
                        running = false;
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(Color.DKGRAY);
                        paint.setTextSize(160);
                        lose = true;
                        canvas.drawText(String.valueOf(pipes.getscore()), getWidth()/2f, 300, paint);
                    }

                }catch (Exception e){}
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bird.fly();
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.running = false;
        boolean retry = true;
        while (retry){
            try {
                drawThread.join();
                retry = false;
            } catch (Exception e) {}
        }
    }
}
