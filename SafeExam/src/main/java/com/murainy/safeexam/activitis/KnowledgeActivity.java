package com.murainy.safeexam.activitis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BmobUtils;
import com.stephentuso.welcome.WelcomeHelper;

import mehdi.sakout.fancybuttons.FancyButton;

import static com.murainy.safeexam.activitis.AboutActivity.getVersionName;

public class KnowledgeActivity extends AppCompatActivity {
	private WelcomeHelper welcomeScreen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knowledge);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//隐藏状态栏
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		welcomeScreen = new WelcomeHelper(this, SplashActivity.class);
		assert toolbar != null;
		toolbar.inflateMenu(R.menu.menu_knowledge);//设置右上角的填充菜单

		// Title
		toolbar.setTitle("SafeExam" + getVersionName(this));
		// Sub Title
		toolbar.setSubtitle("安全管理人员考试");
		setSupportActionBar(toolbar);
		// App Logo 位置偏移太大
		toolbar.setLogo(R.drawable.launcher);
		// Navigation Icon 要設定在 setSupoortActionBar 才有作用， 否則會出現 back button
		toolbar.setNavigationIcon(R.drawable.back_icon_selector);
		// Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
		toolbar.setOnMenuItemClickListener(onMenuItemClick);
		toolbar.setBackgroundColor(getResources().getColor(R.color.air_speed_label));
		FancyButton facebookLoginBtn = new FancyButton(this);
		facebookLoginBtn.setText("检查");
		facebookLoginBtn.setBackgroundColor(Color.parseColor("#3b5998"));
		facebookLoginBtn.setFocusBackgroundColor(Color.parseColor("#5577bd"));
		facebookLoginBtn.setTextSize(15);
		facebookLoginBtn.setPadding(20, 20, 20, 20);
		facebookLoginBtn.setIconPadding(10, 0, 10, 0);
		facebookLoginBtn.setBorderColor(Color.parseColor("#ffffff"));
		facebookLoginBtn.setBorderWidth(2);
		facebookLoginBtn.setRadius(32);
		facebookLoginBtn.setMinimumWidth(192);
		facebookLoginBtn.setIconResource("\uf00d");
		facebookLoginBtn.setIconPosition(FancyButton.POSITION_RIGHT);
		facebookLoginBtn.setFontIconSize(15);
		facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(KnowledgeActivity.this, "facebookLoginBtn", Toast.LENGTH_SHORT).show();
				welcomeScreen.forceShow();
			}
		});

		FancyButton signupBtn = new FancyButton(this);
		signupBtn.setText("报告");
		signupBtn.setIconResource("\uf016");
		signupBtn.setBackgroundColor(Color.parseColor("#3b5998"));
		signupBtn.setFocusBackgroundColor(Color.parseColor("#5577bd"));
		signupBtn.setTextSize(15);
		signupBtn.setRadius(32);
		signupBtn.setBorderColor(Color.parseColor("#ffffff"));
		signupBtn.setBorderWidth(2);
		signupBtn.setFontIconSize(15);
		signupBtn.setPadding(20, 20, 20, 20);
		signupBtn.setIconPadding(10, 0, 10, 0);
		//signupBtn.setGravity(Gravity.FILL_HORIZONTAL);
		signupBtn.setMinimumWidth(192);
		signupBtn.setIconPosition(FancyButton.POSITION_RIGHT);
		signupBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(KnowledgeActivity.this, "signupBtn", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(KnowledgeActivity.this, MomentListActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 10, 0, 10);
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(facebookLoginBtn, layoutParams);
		container.addView(signupBtn, layoutParams);
	}

	private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			String msg = "";
			switch (menuItem.getItemId()) {
				case R.id.check_grade:
					BmobUtils.deletPaper();
					break;
				case R.id.change_password:
					BmobUtils.papers();
					break;
				case R.id.action_testings:
					BmobUtils.testBanks();
					break;
			}

			if (!msg.equals("")) {
				Toast.makeText(KnowledgeActivity.this, msg, Toast.LENGTH_SHORT).show();
			}
			return true;
		}
	};

	public void intro(View view) {
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
				tv = (TextView) itemView.findViewById(R.id.textView6);
			}
		}

		interface OnItemClickListener {
			void onItemClick(int position, String text);
		}
	}

}
