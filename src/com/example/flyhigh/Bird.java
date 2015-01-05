package com.example.flyhigh;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

public class Bird implements Rectable {
	
	public float y ;
	public float x;
	
	public int size =20;
	
	public float flySpeed = 40;
	public float currenSpeed ;
    Paint paint;  
	public long startTime ;
	public int screenh ;
	
	public int gravity =10;
	
	public ArrayList<Rect> mRect = new ArrayList<Rect>(); 
	
	public Bitmap birdBitmap[];
	
	public int flyY = 25;
	
   public Bird(Context context, int width, int height){
	   
	   size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
	   flyY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
	   flySpeed = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
	   
	   
	   screenh =height;
	   x = width/2;
	   y = height/2;
	   startTime =System.currentTimeMillis();
	   mRect.add(new Rect());
	   birdBitmap = new Bitmap[2];
	   birdBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	   birdBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher2);
	   
	   
   }
  public void fly(){
	  currenSpeed =flySpeed;
	  y -=flyY;
	  startTime =System.currentTimeMillis();
	  move();
  }
	public void move() {
		// TODO Auto-generated method stub
		long pasttime =System.currentTimeMillis() - startTime;
		currenSpeed = currenSpeed - gravity*pasttime/1000;
		y= y- currenSpeed*pasttime/1000 +gravity/2*(pasttime/1000)*(pasttime/1000);
		if(y<0)
			y = 0;
		if(y>screenh){
			y =screenh;
		}
		
		mRect.get(0).set((int)x-size, (int)y-size, (int)x+size, (int)y+size);
		
	}
	@Override
	public ArrayList<Rect> getRect() {
		// TODO Auto-generated method stub
		return mRect;
	}

}
