package com.murainy.safeexam.activitis;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Shareprefrence.Account;
import com.murainy.safeexam.Shareprefrence.OperateShareprefrence;
import com.murainy.safeexam.beans.Student;
import com.murainy.safeexam.view.FontManager;
import com.stephentuso.welcome.WelcomeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LauncherActivity extends AppCompatActivity {
	private static final int REQUEST_WELCOME_SCREEN_RESULT = 13;
	private WelcomeHelper welcomeScreen;
	private ShimmerFrameLayout mShimmerViewContainer;
	private ViewFlipper mFlipper;
	private GestureDetectorCompat mDetector; //手势检测
	//背景图片int[] id
	private static int[] resID = {R.drawable.flower, R.drawable.rain, R.drawable.hippophaes, R.drawable.sky, R.drawable.cloud};

	@BindView(R.id.tv_apple)
	TextView ta;
	@BindView(R.id.tv_set)
	TextView ts;
	@BindView(R.id.tv_git)
	TextView tg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		ButterKnife.bind(this);
		welcomeScreen = new WelcomeHelper(this, SplashActivity.class);
		welcomeScreen.show(savedInstanceState);
		mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
		assert mShimmerViewContainer != null;
		mShimmerViewContainer.setDuration(5000);
		mShimmerViewContainer.setRepeatMode(ObjectAnimator.REVERSE);
		Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
		FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);
		mFlipper = (ViewFlipper) findViewById(R.id.flipper);
		//动态导入的方式为ViewFlipper加入子View
		for (int i = 0; i < resID.length; i++) {
			mFlipper.addView(getImageView(resID[i]));
		}

		mFlipper.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				return mDetector.onTouchEvent(event);
			}
		});
		//为ViewFlipper去添加动画效果
		mFlipper.setInAnimation(this, R.anim.left_in);
		mFlipper.setOutAnimation(this, R.anim.right_out);
		mFlipper.startFlipping();

		mDetector = new GestureDetectorCompat(this, new simpleGestureListener());
		//自动登陆
		final Account account = OperateShareprefrence.loadShareprefrence(this);
		if (account != null && account.isAutologin()) {
			BmobUser.loginByAccount(account.getAccount(), account.getPassword(), new LogInListener<Student>() {
				@Override
				public void done(Student student, BmobException e) {

					if (student != null) {
						//tg.setVisibility(View.VISIBLE);

						OperateShareprefrence.storeShareprefrence(LauncherActivity.this, account.getAccount(), account.getPassword(), true);
						SafeExam.setStudent(student);
						if (student.getIdentity() == 1) {
							startActivity(new Intent(LauncherActivity.this, TabmanActivity.class));
						}
						if (student.getIdentity() == 2) {
							startActivity(new Intent(LauncherActivity.this, AdminActivity.class));
						}

					} else {
						// Toast.makeText(LauncherActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
						//startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
					}
				}
			});
		}

	}

	private ImageView getImageView(int resId) {
		ImageView image = new ImageView(this);
		image.setBackgroundResource(resId);
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		return image;
	}

	private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
		final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

		//不知道为什么，不加上onDown函数的话，onFling就不会响应，真是奇怪
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			// Toast.makeText(LauncherActivity.this, "ondown", Toast.LENGTH_SHORT).show();
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		                       float velocityY) {
			// Fling left
			if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				mFlipper.showNext();
				// Toast.makeText(LauncherActivity.this, "Fling Left", Toast.LENGTH_SHORT).show();
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				// Fling right
				mFlipper.showPrevious();
				// Toast.makeText(LauncherActivity.this, "Fling Right", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == WelcomeHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
			String welcomeKey = data.getStringExtra(SplashActivity.WELCOME_SCREEN_KEY);

			if (resultCode == RESULT_OK) {
				//startActivity(new Intent(LauncherActivity.this, SplashActivity.class));
				//Toast.makeText(getApplicationContext(), welcomeKey + " completed", Toast.LENGTH_SHORT).show();
				Log.e("TAG", "首次启动");

			} else {
				//Toast.makeText(getApplicationContext(), welcomeKey + " canceled", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
				Log.e("TAG", "正常工作");

			}

		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mShimmerViewContainer.startShimmerAnimation();
	}

	@Override
	public void onPause() {
		mShimmerViewContainer.stopShimmerAnimation();
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		welcomeScreen.onSaveInstanceState(outState);
	}

	@OnClick(R.id.tv_apple)
	public void next(View view) {
		startActivity(new Intent(LauncherActivity.this, RegisterActivity.class));
	}

	@OnClick(R.id.tv_git)
	public void man(View view) {

		//自动登陆
		final Account account = OperateShareprefrence.loadShareprefrence(this);
		if (account != null && account.isAutologin()) {
			BmobUser.loginByAccount(account.getAccount(), account.getPassword(), new LogInListener<Student>() {
				@Override
				public void done(Student student, BmobException e) {

					if (student != null) {
						//tg.setVisibility(View.VISIBLE);

						OperateShareprefrence.storeShareprefrence(LauncherActivity.this, account.getAccount(), account.getPassword(), true);
						SafeExam.setStudent(student);
						if (student.getIdentity() == 1) {
							startActivity(new Intent(LauncherActivity.this, TabmanActivity.class));
						}
						if (student.getIdentity() == 2) {
							startActivity(new Intent(LauncherActivity.this, AdminActivity.class));
						}

					} else {
						//  用户注册
						//  Toast.makeText(LauncherActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
					}
				}
			});
		}
		else
		{
			startActivity(new Intent(LauncherActivity.this, RegisterActivity.class));
		}
	}

	@OnClick(R.id.tv_set)
	public void setup(View view) {
		//welcomeScreen.forceShow();
		//CrashReport.testJavaCrash();
		startActivity(new Intent(LauncherActivity.this, RubblerAct.class));
	}
}
