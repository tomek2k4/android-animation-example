package com.example.tomasz.animationexample;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MainActivity extends Activity implements SurfaceHolder.Callback{

    private AnimThread animThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SurfaceView surface = (SurfaceView) findViewById(R.id.mySurface);
        surface.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        animThread = new AnimThread(holder);
        animThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        animThread.requestStop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {



    }

}
