package com.murainy.safeexam.activitis;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import c.b.BP;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.update.BmobUpdateAgent;

public class LauncherActivity extends AppCompatActivity {
	private static final int REQUEST_WELCOME_SCREEN_RESULT = 13;

	private WelcomeHelper welcomeScreen;

	private ShimmerFrameLayout mShimmerViewContainer;
	private ViewFlipper mFlipper;
	private GestureDetectorCompat mDetector; //手势检测
	//背景图片int[] id
	private static int[] resID = {R.drawable.jjzk, R.drawable.jjz2013, R.drawable.xm_logo1, R.drawable.redocn, R.drawable.cloud};
	BmobPushManager<BmobInstallation> bmobPushManager;
	@BindView(R.id.tv_apple)
	TextView ta;
	@BindView(R.id.tv_set)
	TextView ts;
	@BindView(R.id.tv_git)
	TextView tg;
	@BindView(R.id.tv_let)
	TextView tl;
	@BindView(R.id.support_me)
	TextView supportMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		ButterKnife.bind(this);
		welcomeScreen = new WelcomeHelper(this, SplashActivity.class);
		// 初始化BmobPay对象,可以在支付时再初始化
		BP.init(SafeExam.APPID);
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation().save();
		// 启动推送服务
		BmobPush.startWork(this);
		// 创建推送消息的对象
		bmobPushManager = new BmobPushManager<>();
		BmobUpdateAgent.update(this);

		welcomeScreen.show(savedInstanceState);
		mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
		assert mShimmerViewContainer != null;
		mShimmerViewContainer.setDuration(1000);
		mShimmerViewContainer.startShimmerAnimation();
		mShimmerViewContainer.setRepeatMode(ObjectAnimator.RESTART);
		Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
		FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);
		mFlipper = (ViewFlipper) findViewById(R.id.flipper);
		//动态导入的方式为ViewFlipper加入子View
		for (int aResID : resID) {
			mFlipper.addView(getImageView(aResID));
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
						 Toast.makeText(LauncherActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
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

	/**
	 * 键盘事件，当按下back键的时候询问是否再按一次退出程序
	 */
	// 退出时间
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);

			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
		startActivity(new Intent(LauncherActivity.this, ContentExamActivity.class));
	}

	@OnClick(R.id.tv_set)
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
		} else {
			startActivity(new Intent(LauncherActivity.this, RegisterActivity.class));
		}
	}

	@OnClick(R.id.tv_git)
	public void setup(View view) {
        // 创建Installation表的BmobQuery对象
		BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
		// 并添加条件为设备类型属于android
		query.addWhereEqualTo("deviceType", "android");
		// 设置推送条件给bmobPushManager对象。
		bmobPushManager.setQuery(query);
		// 设置推送消息，服务端会根据上面的查询条件，来进行推送这条消息
		bmobPushManager.pushMessage("这是一条推送给所有Android设备的消息。");
		//CrashReport.testJavaCrash();
		startActivity(new Intent(LauncherActivity.this, ExamActivity.class));
	}

	@OnClick(R.id.tv_let)
	public void know(View view) {

		//CrashReport.testJavaCrash();
		startActivity(new Intent(LauncherActivity.this, KnowledgeActivity.class));
	}

	@OnClick(R.id.support_me)
	public void onViewClicked() {
		startActivity(new Intent(LauncherActivity.this, PayActivity.class));
	}
}
