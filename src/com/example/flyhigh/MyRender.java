package com.example.flyhigh;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.AudioManager;
import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.media.SoundPool;
import android.util.Log;
import android.view.SurfaceHolder;


public class MyRender implements Runnable {
	
	public int width;
	public int height;
    SurfaceHolder surfaceHolder;  
    Context context;  
    Paint paint;  
    boolean isRunning =false;  
	ArrayList<Lines> mLines;
	Bird mBird;
	
	private int count = 0;

	public MyRender(int w,int h,SurfaceHolder surfaceHolder,Context context){
		this.context =context;
		width =w;
		height =h;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(2);
		paint.setTextSize(30);
		paint.setTextAlign(Align.CENTER);
		this.surfaceHolder =surfaceHolder;
		reset();
	}
	
	public void reset(){
		count=0;
		mLines = new ArrayList<Lines>();
		mBird =new Bird(context,width, height);
	}
	

	public boolean isNeedAddLine(){
		boolean res =false;
		if(mLines.size()==0){
			res= true;
		}
		else {
			//得到最后一个line的坐标
			Lines lastLine = mLines.get(mLines.size()-1);
			if(lastLine.startX<lastLine.screenWidth/5*3){
				res= true;
			}
		}
		if(res)
			count++;
		Log.e("liuwei", "isNeedAddLine "+res);
		return res;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	    Canvas c = null;  
        while(isRunning){  
			for (Lines line : mLines) {
				
				isRunning=!(Rect.intersects(line.getRect().get(0), mBird.getRect().get(0))||Rect.intersects(line.getRect().get(1), mBird.getRect().get(0)));
				//isRunning=!(line.getRect().get(0).contains(mBird.getRect().get(0))||line.getRect().get(1).contains(mBird.getRect().get(0)));
				if(!isRunning)
				{
					doDrawState(false);
					return;
				}
					
			}
        	
			if(isNeedAddLine()){
				mLines.add(new Lines(context,width, height));
				if(mLines.size()>3){
					mLines.remove(0);
				}
			}
			for (Lines line : mLines) {
				line.move();
			}
			mBird.move();
			
            try{  
                    c = surfaceHolder.lockCanvas(null);  
                    doDraw(c);  
                    //通过它来控制帧数执行一次绘制后休息50ms  
                    Thread.sleep(20);  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {
            	try {
            		 surfaceHolder.unlockCanvasAndPost(c);  
				} catch (Exception e2) {
					// TODO: handle exception
				}
            }  
        }  
	}
	
	public void doDraw(Canvas c){
		Log.e("liuwei", "doDraw"+mLines.size());
		c.drawARGB(255, 0, 0, 0);
		paint.setColor(Color.BLUE);
		for (Lines line : mLines) {
			if(line.startX>=-line.width&&line.startX<=line.screenHeight){
				c.drawRect(line.getRect().get(1), paint);
				c.drawRect(line.getRect().get(0), paint);
			}
		}
		
		paint.setColor(Color.RED);
		if(mBird.currenSpeed>=0){
			c.drawBitmap(mBird.birdBitmap[0], new Rect(0,0,mBird.birdBitmap[0].getWidth(),mBird.birdBitmap[0].getHeight()),mBird.getRect().get(0) , paint);
		}else{
			c.drawBitmap(mBird.birdBitmap[1], new Rect(0,0,mBird.birdBitmap[1].getWidth(),mBird.birdBitmap[1].getHeight()),mBird.getRect().get(0) , paint);
		}
		
		//c.drawRect(mBird.getRect().get(0), paint);
		paint.setColor(Color.GREEN);
		c.drawText(String.valueOf(count), width/2, height/4, paint);
		
//		int x = (int) mBird.x;
//		int y = (int) mBird.y;
//		int size = mBird.size;
//		c.drawRect((int)x-size, (int)y-size, (int)x+size, (int)y+size, paint);
		
	}
	
	public void doDrawState(boolean start){
		Canvas c = null;  
        try{  
        	paint.setColor(Color.RED);
            c = surfaceHolder.lockCanvas(null);  
            c.drawARGB(255, 0, 0, 0);
            if(start){
            	c.drawText("点击开始", width/2, height/2, paint);
            }else {
            	c.drawText("你嗝屁了,还没到"+(count/10+1)*10+",菜的抠脚~", width/2, height/2, paint);
			}
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {
    	try {
    		 surfaceHolder.unlockCanvasAndPost(c);  
		} catch (Exception e2) {
			// TODO: handle exception
		}
    }  
	}
	
	
	
	
	
}
