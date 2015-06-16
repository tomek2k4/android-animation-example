package com.example.tomasz.animationexample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by tomasz on 14.06.2015.
 */
public class AnimThread extends Thread {


    private final SurfaceHolder holder;
    private boolean stopFlag = false;
    private int frameNumber = 0;
    final Paint circlePaint =  new Paint();
    final Paint circleClearPaint = new Paint();


    public AnimThread(SurfaceHolder holder) {
        super("Anim Thread");
        this.holder = holder;

        //circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setColor(0xFF7777FF);
        //circleClearPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circleClearPaint.setColor(0xFF0000);
    }


    public synchronized void start(){
        stopFlag = false;
        super.start();
    }

    public synchronized void requestStop(){
        stopFlag = true;
    }

    @Override
    public void run() {
        float prevX = 0;
        float prevY = 0;

        while(!stopFlag){
            double position = ((double)frameNumber/119d);

            if( holder!=null){
                Canvas c = holder.lockCanvas();
                c.drawColor(Color.BLACK);
                float x = (float) (position * (double) c.getWidth());
                float y = (float) (c.getHeight() / 2d - Math.sin(position * 4d * Math.PI) * c.getHeight() / 3);
                c.drawCircle(x,y,10,circlePaint);
                holder.unlockCanvasAndPost(c);
                frameNumber = (frameNumber + 1) % 120;
            }

            try{
                sleep(50);
            }catch (InterruptedException e){
                break;
            }

        }

    }


}
