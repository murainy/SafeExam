package com.murainy.safeexam.activitis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.ToastUtils;
import com.stephentuso.welcome.WelcomeHelper;

import mehdi.sakout.fancybuttons.FancyButton;

import static com.murainy.safeexam.Utils.DeviceUtil.getDeviceId;
import static com.murainy.safeexam.activitis.AboutActivity.getVersionName;

public class KnowledgeActivity extends AppCompatActivity {
	private WelcomeHelper welcomeScreen;
	private NestedScrollView mNestedScrollView;
	private AppBarLayout mAppBarLayout;
	private TextView mTvTitle;
	private TextView device_id;
	private final Context context = this;
	private int density;
	private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			String msg = "";
			switch (menuItem.getItemId()) {
				case R.id.empty_table:
					BmobUtils.deletPaper();
					break;
				case R.id.update_paper:
					BmobUtils.papers();
					break;
				case R.id.update_tiku:
					BmobUtils.testBanks();
					break;
				case R.id.update_subject:
					BmobUtils.kemu();
					break;
			}

			if (!msg.equals("")) {
				Toast.makeText(KnowledgeActivity.this, msg, Toast.LENGTH_SHORT).show();
			}
			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knowledge);
		Toolbar toolbar = findViewById(R.id.toolbar);
		mNestedScrollView = findViewById(R.id.myscroll);
		//设置 NestedScrollView 的内容是否拉伸填充整个视图，
		//这个设置是必须的，否者我们在里面设置的ViewPager会不可见
		mNestedScrollView.setFillViewport(true);
		device_id = findViewById(R.id.device_id);
		mTvTitle = findViewById(R.id.tv_title);
		mAppBarLayout = findViewById(R.id.apl);
		mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
				int scrollRangle = appBarLayout.getTotalScrollRange();
				//初始verticalOffset为0，不能参与计算。
				if (verticalOffset == 0) {
					mTvTitle.setAlpha(0.0f);
				} else {
					//保留一位小数
					float alpha = Math.abs(Math.round(1.0f * verticalOffset / scrollRangle) * 10) / 10;
					mTvTitle.setAlpha(alpha);
				}
			}
		});

		//隐藏状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		welcomeScreen = new WelcomeHelper(this, SplashActivity.class);
		assert toolbar != null;
		toolbar.inflateMenu(R.menu.menu_knowledge);//设置右上角的填充菜单

		// Title
		mTvTitle.setText("安全考试" + getVersionName(this));
		// Sub Title
		toolbar.setSubtitle("安全管理人员考试");
		setSupportActionBar(toolbar);
		// App Logo 位置偏移太大
		toolbar.setLogo(R.drawable.launcher);
		// Navigation Icon 要設定在 setSupoortActionBar 才有作用， 否則會出現 back button
		toolbar.setNavigationIcon(R.drawable.back_icon_selector);
		// Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
		toolbar.setOnMenuItemClickListener(onMenuItemClick);
		toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
		FancyButton updateBtn = findViewById(R.id.btn_like);
		updateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
				assert wm != null;
				Display display = wm.getDefaultDisplay();
				DisplayMetrics metric = new DisplayMetrics();
				display.getRealMetrics(metric);
				int width = metric.widthPixels;  // 宽度（像素）
				int height = metric.heightPixels;  // 高度（像素）
				density = (int) metric.density;  // dp缩放因子
				int densityDpi = metric.densityDpi;  // 广义密度
				float xdpi = metric.xdpi;//x轴方向的真实密度
				float ydpi = metric.ydpi;//y轴方向的真实密度
				ToastUtils.ToastMessage(context, "屏幕密度：" + Integer.toString(densityDpi), getAppInfo());
				intro();
			}
		});
		FancyButton LoginBtn = findViewById(R.id.btn_face);
		LoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				welcomeScreen.forceShow();
			}
		});

		FancyButton signupBtn = findViewById(R.id.btn_select);
		signupBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(KnowledgeActivity.this, MomentListActivity.class);
				startActivity(intent);
			}
		});

		device_id.append(getDeviceId(this));
	}

	public void intro() {
		BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.myscroll));
		if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
			behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		} else {
			behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
		}

		behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {

			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_knowledge, menu);
		return true;
	}

	public void select(View view) {
		RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this)
				.inflate(R.layout.knowledge_list_rv, null);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		Adapter adapter = new Adapter();
		recyclerView.setAdapter(adapter);

		final BottomSheetDialog dialog = new BottomSheetDialog(this);
		dialog.setContentView(recyclerView);
		dialog.show();
		adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position, String text) {
				Toast.makeText(KnowledgeActivity.this, text, Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
	}

	static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

		private OnItemClickListener mItemClickListener;

		public void setOnItemClickListener(OnItemClickListener li) {
			mItemClickListener = li;
		}

		@Override
		public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
			View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.knowledge_list_item, parent, false);
			return new Holder(item);
		}

		@Override
		public void onBindViewHolder(final Adapter.Holder holder, int position) {
			String nr = "Hippophae" + String.valueOf(position);
			holder.tv.setText(nr);
			if (mItemClickListener != null) {
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mItemClickListener.onItemClick(holder.getLayoutPosition(),
								holder.tv.getText().toString());
					}
				});
			}
		}

		@Override
		public int getItemCount() {
			return 10;
		}

		class Holder extends RecyclerView.ViewHolder {
			TextView tv;

			public Holder(View itemView) {
				super(itemView);
				tv = itemView.findViewById(R.id.textView6);
			}
		}

		interface OnItemClickListener {
			void onItemClick(int position, String text);
		}
	}

	private String getAppInfo() {
		try {
			String pkName = this.getPackageName();
			String versionName = this.getPackageManager().getPackageInfo(
					pkName, 0).versionName;
			int versionCode = this.getPackageManager()
					.getPackageInfo(pkName, 0).versionCode;
			return pkName + "   " + versionName + "  " + versionCode;
		} catch (Exception e) {
		}
		return null;
	}
}
