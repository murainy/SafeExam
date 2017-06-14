package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.adapter.TestqebaAdapter;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Testqeba;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by murainy on 2015/12/14.
 */
public class MainActivity extends Activity {

	private ListView paperList;
	private List<Testqeba> papers = new ArrayList<>();
	private TestqebaAdapter adapter;
	@BindView(R.id.iv_left)
	public ImageView iv_left;
	@BindView(R.id.iv_right)
	public ImageView iv_right;
	@BindView(R.id.tv_title)
	public TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//OperateSQLite operateSQLite;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		//隐藏状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		iv_left.setVisibility(View.VISIBLE);
		iv_right.setVisibility(View.VISIBLE);
		tv_title.setText("考试项目");
		EventBus.getDefault().register(this);
		paperList = (ListView) findViewById(R.id.lv_paper);
		//operateSQLite = new OperateSQLite(this);
		BmobUtils.downloadGradeList(MainActivity.this, SafeExam.getStudent().getUsername());
		BmobUtils.downloadTestList(MainActivity.this);
		//papers = operateSQLite.getTestData();
		papers = BmobUtils.TestList;
		adapter = new TestqebaAdapter(this, papers);
		paperList.setAdapter(adapter);
		paperList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (((ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null) {
					Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
				} else {
					if (!papers.get(i).isFinishState()) {
						Intent intent = new Intent(MainActivity.this, StartTestActivity.class);
						intent.putExtra("paperName", papers.get(i).getName());
						intent.putExtra("examMode", "正式考试");
						startActivityForResult(intent, 0);
					}
				}
			}
		});


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0)
			if (resultCode == 1) {
				Grade grade = (Grade) data.getSerializableExtra("data");
				BmobUtils.gradeList.add(grade);
				TestqebaAdapter adapter = new TestqebaAdapter(this, papers);
				paperList.setAdapter(adapter);
			}
	}




	@Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
	public void onEventMainThread(Action action) {
		switch (action) {
			case DOWNLOAD_Test_LIST:
				Logger.i("获取试卷列表成功");
				//BmobUtils.updateAllPaperList(this);
				papers = BmobUtils.TestList;
				adapter = new TestqebaAdapter(this, papers);
				paperList.setAdapter(adapter);
				break;
			case DOWNLOAD_GRADE_LIST:
				Logger.i("获取成绩成功");
				BmobUtils.downloadPaperList(MainActivity.this);
				break;
			case QUERY_ERROR:
				Logger.i("获取试卷列表失败");
				break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		papers = TestqebaAdapter.paperData;
		for (int i = 0; i < papers.size(); i++) {
			BmobUtils.updateTestpaper(papers);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);

	}

	@OnClick(R.id.iv_left)
	public void back(View view) {
		finish();
	}
}
