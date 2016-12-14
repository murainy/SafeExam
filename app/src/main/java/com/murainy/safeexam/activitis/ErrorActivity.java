package com.murainy.safeexam.activitis;

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
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BmobUtils;

import mehdi.sakout.fancybuttons.FancyButton;

public class ErrorActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//隐藏状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		assert toolbar != null;
		toolbar.inflateMenu(R.menu.menu_error);//设置右上角的填充菜单
		// App Logo
		toolbar.setLogo(R.drawable.ic_launcher);
		// Title
		toolbar.setTitle("SafeExam2016");
		// Sub Title
		toolbar.setSubtitle("安全管理人员考试");
		setSupportActionBar(toolbar);
		// Navigation Icon 要設定在 setSupoortActionBar 才有作用， 否則會出現 back button
		toolbar.setNavigationIcon(R.drawable.back_icon_selector);
		// Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
		toolbar.setOnMenuItemClickListener(onMenuItemClick);

		FancyButton facebookLoginBtn = new FancyButton(this);
		facebookLoginBtn.setText("检查");
		facebookLoginBtn.setBackgroundColor(Color.parseColor("#3b5998"));
		facebookLoginBtn.setFocusBackgroundColor(Color.parseColor("#5577bd"));
		facebookLoginBtn.setTextSize(15);
		facebookLoginBtn.setRadius(20);
		facebookLoginBtn.setIconResource("\uf082");
		facebookLoginBtn.setIconPosition(FancyButton.POSITION_LEFT);
		facebookLoginBtn.setFontIconSize(20);
		facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(ErrorActivity.this,"facebookLoginBtn",Toast.LENGTH_SHORT).show();
			}
		});

		FancyButton signupBtn = new FancyButton(this);
		signupBtn.setText("报告");
		signupBtn.setIconResource("\uf016");
		signupBtn.setBackgroundColor(Color.parseColor("#3b5998"));
		signupBtn.setFocusBackgroundColor(Color.parseColor("#5577bd"));
		signupBtn.setTextSize(15);
		signupBtn.setRadius(20);
		signupBtn.setFontIconSize(20);
		signupBtn.setIconPosition(FancyButton.POSITION_LEFT);
		signupBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(ErrorActivity.this,"signupBtn",Toast.LENGTH_SHORT).show();
			}
		});
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 0, 0, 10);
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
				Toast.makeText(ErrorActivity.this, msg, Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.menu_error, menu);
		return true;
	}

	public void select(View view) {
		RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this)
				.inflate(R.layout.error_list_rv, null);
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
				Toast.makeText(ErrorActivity.this, text, Toast.LENGTH_SHORT).show();
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
			View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.error_list_item, parent, false);
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
