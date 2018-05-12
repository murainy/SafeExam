package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.murainy.safeexam.R;
import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

/**
 * <pre>
 * @author zhy
 * http://blog.csdn.net/lmj623565791/article/details/43131133
 * </pre>
 */
public class CircleActivity extends Activity {

	private CircleMenuLayout mCircleMenuLayout;

	private String[] mItemTexts = new String[]{"网页精灵", "安全知识", "顺序练习",
			"模拟考试", "错题练习", "幸运时刻", "设置", "关于"};
	private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal, R.drawable.home_mbank_7_normal,
			R.drawable.home_mbank_8_normal};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//自已切换布局文件看效果
		setContentView(R.layout.activity_circle);
		mCircleMenuLayout = findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void itemClick(View view, int pos) {
				switch (pos) {
					case 0:
						Intent intent0 = new Intent(CircleActivity.this, WebActivity.class);
						intent0.putExtra("url", "http://bmob-cdn-2129.b0.upaiyun.com/2018/05/12/bc151a21400e3f3f80dbeb58380d6f10.html");
						startActivity(intent0);
						break;
					case 1:
						startActivity(new Intent(CircleActivity.this, KnowledgeActivity.class));
						break;
					case 2:
						Intent intent1 = new Intent(CircleActivity.this, SequenceActivity.class);
						intent1.putExtra("examMode", "顺序练习");
						startActivity(intent1);
						break;
					case 3:
						Intent intent2 = new Intent(CircleActivity.this, MuniActivity.class);
						intent2.putExtra("examMode", "模拟考试");
						startActivity(intent2);
						break;
					case 4:
						Intent intent3 = new Intent(CircleActivity.this, CuotiActivity.class);
						intent3.putExtra("examMode", "错题练习");
						startActivity(intent3);
						break;
					case 5:
						startActivity(new Intent(CircleActivity.this, RubblerActivity.class));
						break;
					case 6:
						startActivity(new Intent(CircleActivity.this, SettingsActivity.class));
						break;
					case 7:

						Intent intent = new Intent(CircleActivity.this, AboutActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("url", "file:///android_asset/about.html");
						intent.putExtra("bundle", bundle);
						startActivity(intent);

						break;
				}
			}

			@Override
			public void itemCenterClick(View view) {
				startActivity(new Intent(CircleActivity.this, GridActivity.class));

			}
		});

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
