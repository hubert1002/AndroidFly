package com.example.flyhigh;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundPlayer {
	private SoundPool mPool ;
	private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>(); 
	private boolean isLoadComplete =false;
	public SoundPlayer(Context context){
	    mPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
	    mPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				// TODO Auto-generated method stub
				isLoadComplete =true;
			}
		});
	    soundPoolMap.put(0,  mPool.load(context, R.raw.beep, 0));
	}
	public void play(){
		if(mPool!=null&&isLoadComplete){
			mPool.play((int) soundPoolMap.get(0),  1, 1, 0, 0, 2);
		}
	}
	
	public void destroy(){
		mPool.release();
		mPool = null;
	}
}
