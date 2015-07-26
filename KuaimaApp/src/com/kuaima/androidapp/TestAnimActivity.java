package com.kuaima.androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

/**
 * ���Ե���ʱ�����Ľ���
 * @author Administrator
 *
 */
public class TestAnimActivity extends Activity
{
	ImageView countdownAnimImageView, rotationAnimImageView;
	AnimExecutor animExecutor ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_test_anim);

		countdownAnimImageView = (ImageView) findViewById(R.id.countdown_anim_view);

		rotationAnimImageView = (ImageView) findViewById(R.id.rotation_anim_view);
		
		animExecutor = new AnimExecutor(this);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		animExecutor.startAnim(rotationAnimImageView, countdownAnimImageView);
	}
}
