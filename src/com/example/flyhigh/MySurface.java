package com.example.flyhigh;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback; 
import android.view.View;
import android.view.View.OnTouchListener;

public class MySurface extends SurfaceView implements Callback,OnTouchListener {
	MyRender mRender ;
	Context mContext;
	Thread mThread;
	//SoundPlayer mPlayer;
	public MySurface(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}
	
	public MySurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		// TODO Auto-generated constructor stub
	}
	  private void init(Context context){
		  mContext =context;
	        SurfaceHolder holder = getHolder();  
	        holder.addCallback(this); //设置Surface生命周期回调  
	        setOnTouchListener(this);
	       // mPlayer = new SoundPlayer(context);
	    }  
	
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.e("liuwei", "surfaceCreated");
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		Log.e("liuwei", "surfaceChanged");
		 mRender = new MyRender(width, height ,holder ,mContext);
		 mRender.doDrawState(true);
//	     mThread = new Thread(mRender);
//	     mThread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.e("liuwei", "surfaceDestroyed");
		mRender.isRunning =false;
		//mPlayer.destroy();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		
		// TODO Auto-generated method stub
		if(!mRender.isRunning){
			mRender.isRunning =true;
			mRender.reset();
			mThread = new Thread(mRender);
			mThread.start();
		}else {
			//mPlayer.play();
			mRender.mBird.fly();
		}
		return false;
	}
	
	
}
