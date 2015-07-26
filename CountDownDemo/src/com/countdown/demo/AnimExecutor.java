
package com.countdown.demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * 倒计时动画执行者
 * 
 * @author Administrator
 * 
 */
public class AnimExecutor implements Callback
{
	/**
	 * log
	 */
	private final static String LOG_TAG = AnimExecutor.class.getSimpleName();

	/**
	 * 倒计时的数字透明度动画时间
	 */
	private final static int ALPHA_DURATION = 70;

	/**
	 * 1秒
	 */
	private final static int SECOND = 1000;

	/**
	 * 图片进入和退出的偏移时间
	 */
	private final static long OFFSET_IN = 0;
	private final static long OFFSET_OUT = 930;

	/**
	 * 要倒计时15秒
	 */
	private final static int COUNT_DOWN = 15;

	/**
	 * 倒计时的图片资源提前加载
	 */
	private Drawable drawable_15;
	private Drawable drawable_14;
	private Drawable drawable_13;
	private Drawable drawable_12;
	private Drawable drawable_11;
	private Drawable drawable_10;
	private Drawable drawable_09;
	private Drawable drawable_08;
	private Drawable drawable_07;
	private Drawable drawable_06;
	private Drawable drawable_05;
	private Drawable drawable_04;
	private Drawable drawable_03;
	private Drawable drawable_02;
	private Drawable drawable_01;
	private Drawable drawable_00;

	/**
	 * 上下文
	 */
	private Context context;

	/**
	 * 异步处理
	 */
	private Handler handler;

	private ImageView countdownAnimImageView;

	private ImageView rotationAnimImageView;

	public AnimExecutor(Context context)
	{
		this.context = context;
		initDrawable();
		handler = new Handler(this);
	}

	public void startAnim(ImageView rotationAnimImageView,
			ImageView countdownAnimImageView)
	{
		this.rotationAnimImageView = rotationAnimImageView;
		this.countdownAnimImageView = countdownAnimImageView;
		rotationAnimImageView.setVisibility(View.VISIBLE);
		start(COUNT_DOWN);
	}

	private void start(int index)
	{
		if (0 != index)
		{
			startRotationAnim();
		}
		startCountDownAnim(index);
	}

	/**
	 * 转圈动画
	 * 
	 * @param view
	 */
	private void startRotationAnim()
	{
		AnimationSet animationSet = new AnimationSet(true);
		final RotateAnimation animation = new RotateAnimation(0f, 360f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(SECOND);
		animation.setStartOffset(0);
		animationSet.addAnimation(animation);

		AlphaAnimation alphaIn = new AlphaAnimation(
				rotationAnimImageView.getAlpha(), 1.0f);
		alphaIn.setDuration(330);
		alphaIn.setStartOffset(0);
		animationSet.addAnimation(alphaIn);

		AlphaAnimation alphaOut = new AlphaAnimation(1.0f, 0.0f);
		alphaOut.setDuration(340);
		alphaOut.setStartOffset(660);
		animationSet.addAnimation(alphaOut);

		rotationAnimImageView.startAnimation(animationSet);
	}

	/**
	 * @param view
	 * @param index
	 */
	private void startCountDownAnim(int index)
	{
		AnimationSet animationSet = new AnimationSet(true);

		if (COUNT_DOWN == index)
		{
			countdownAnimImageView.setImageDrawable(getDrawable(COUNT_DOWN));
		}

		if (0 == index)
		{
			// 最后一个抢字只有图片进入动画
			animationSet.addAnimation(createInAlphaAnimation(index));
		}
		else
		{
			// 15到1的图片先有进入动画
			animationSet.addAnimation(createInAlphaAnimation(index));

			// 15到1的图片再有退出动画
			animationSet.addAnimation(createOutAlphaAnimation(index));
		}

		countdownAnimImageView.startAnimation(animationSet);
	}

	private void initDrawable()
	{
		drawable_15 = context.getResources().getDrawable(
				R.drawable.count_down_15);
		drawable_14 = context.getResources().getDrawable(
				R.drawable.count_down_14);
		drawable_13 = context.getResources().getDrawable(
				R.drawable.count_down_13);
		drawable_12 = context.getResources().getDrawable(
				R.drawable.count_down_12);
		drawable_11 = context.getResources().getDrawable(
				R.drawable.count_down_11);
		drawable_10 = context.getResources().getDrawable(
				R.drawable.count_down_10);
		drawable_09 = context.getResources().getDrawable(
				R.drawable.count_down_09);
		drawable_08 = context.getResources().getDrawable(
				R.drawable.count_down_08);
		drawable_07 = context.getResources().getDrawable(
				R.drawable.count_down_07);
		drawable_06 = context.getResources().getDrawable(
				R.drawable.count_down_06);
		drawable_05 = context.getResources().getDrawable(
				R.drawable.count_down_05);
		drawable_04 = context.getResources().getDrawable(
				R.drawable.count_down_04);
		drawable_03 = context.getResources().getDrawable(
				R.drawable.count_down_03);
		drawable_02 = context.getResources().getDrawable(
				R.drawable.count_down_02);
		drawable_01 = context.getResources().getDrawable(
				R.drawable.count_down_01);
		drawable_00 = context.getResources().getDrawable(R.drawable.icon_grab);
	}

	@Override
	public boolean handleMessage(Message msg)
	{
		start(msg.arg1);
		return true;
	}

	/**
	 * 图片淡入
	 * 
	 * @return
	 */
	private AlphaAnimation createInAlphaAnimation(int index)
	{
		AlphaAnimation alphaIn = new AlphaAnimation(0.0f, 1.0f);
		alphaIn.setDuration(ALPHA_DURATION);
		alphaIn.setStartOffset(OFFSET_IN);
		alphaIn.setAnimationListener(getInListener(index));
		return alphaIn;
	}

	/**
	 * 图片淡出
	 * 
	 * @param view
	 * @param index
	 * @return
	 */
	private AlphaAnimation createOutAlphaAnimation(final int index)
	{
		AlphaAnimation alphaOut = new AlphaAnimation(1.0f, 0.0f);
		alphaOut.setDuration(ALPHA_DURATION);
		alphaOut.setStartOffset(OFFSET_OUT);
		alphaOut.setAnimationListener(getOutListener(index));
		return alphaOut;
	}

	private AnimationListener getOutListener(final int index)
	{
		AnimationListener listener = new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation arg0)
			{
			}

			@Override
			public void onAnimationRepeat(Animation arg0)
			{
			}

			@Override
			public void onAnimationEnd(Animation arg0)
			{
				Log.d(LOG_TAG, "getListener.onAnimationEnd. index==" + index);

				countdownAnimImageView.setImageDrawable(getDrawable(index - 1));
				// handler.sendMessage(handler.obtainMessage(0, index - 1, 0));

				handler.sendMessage(handler.obtainMessage(0, index - 1, 0));
			}
		};

		return listener;
	}

	private AnimationListener getInListener(final int index)
	{
		AnimationListener listener = new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation arg0)
			{
			}

			@Override
			public void onAnimationRepeat(Animation arg0)
			{
			}

			@Override
			public void onAnimationEnd(Animation arg0)
			{
				if (1 == index)
				{
					rotationAnimImageView.setVisibility(View.GONE);
				}
			}
		};

		return listener;
	}

	/**
	 * 根据动画的次序获取图片资源
	 * 
	 * @param index
	 * @return
	 */
	private Drawable getDrawable(int index)
	{
		switch (index)
		{
		case 15:
			return drawable_15;
		case 14:
			return drawable_14;
		case 13:
			return drawable_13;
		case 12:
			return drawable_12;
		case 11:
			return drawable_11;
		case 10:
			return drawable_10;
		case 9:
			return drawable_09;
		case 8:
			return drawable_08;
		case 7:
			return drawable_07;
		case 6:
			return drawable_06;
		case 5:
			return drawable_05;
		case 4:
			return drawable_04;
		case 3:
			return drawable_03;
		case 2:
			return drawable_02;
		case 1:
			return drawable_01;
		case 0:
			return drawable_00;

		default:
			break;
		}

		return null;
	}
}
