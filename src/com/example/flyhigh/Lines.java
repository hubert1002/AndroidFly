package com.example.flyhigh;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import android.R.integer;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;

public class Lines implements Rectable  {

	public int startX ;
	public int speed = 5;
	
	public boolean alive = true;
	public int width = 50;
	public int height ;
	public int deltaHeight ;
	public int minDeltaY = 100;
	public int minHeight = 50;
	public ArrayList<Rect> mRect = new ArrayList<Rect>(); 
    public static int screenHeight ;
    public static  int screenWidth;
	public Lines(Context context,int width,int height){
		
		this.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, context.getResources().getDisplayMetrics());
		speed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics());
		minDeltaY =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, context.getResources().getDisplayMetrics());
		minHeight =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, context.getResources().getDisplayMetrics());
		
		
		
		screenWidth = width;
		screenHeight =height;
		minDeltaY =height/4;
		startX = width + this.width;
		deltaHeight = (int) (Math.random()*screenHeight/4+minDeltaY);
		this.height = (int) (Math.random()*(screenHeight-deltaHeight));
		mRect.add(new Rect());
		mRect.add(new Rect());
		
	}
	public void move() {
		// TODO Auto-generated method stub
		Log.e("liuwei", "move "+startX);
		startX =startX -speed;
		
		mRect.get(0).set(startX, 0, startX+width, height);
		mRect.get(1).set(startX, height+deltaHeight, startX+width, screenHeight);
	}
	@Override
	public ArrayList<Rect> getRect() {
		// TODO Auto-generated method stub
		return mRect;
	}
	
	
	
	
	
	
	
}
