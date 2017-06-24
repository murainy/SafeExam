package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.adapter.TPaperListAdapter;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.murainy.safeexam.Utils.BmobUtils.downloadTPaperList;
import static com.murainy.safeexam.Utils.BmobUtils.gradeList;
import static com.murainy.safeexam.Utils.BmobUtils.testLists;

/**
 * Created by murainy on 2016/6/17.
 */
public class AdminActivity extends Activity implements View.OnClickListener {

	private ListView paperList;
	private List<Paper> papers = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TPaperListAdapter adapter;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		downloadTPaperList(AdminActivity.this);
		paperList = (ListView) findViewById(R.id.lv_paper_teacher);
		papers = BmobUtils.paperList;
		adapter = new TPaperListAdapter(this, papers);
		paperList.setAdapter(adapter);
		paperList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				testLists(papers.get(i).getPaperName());
				Intent intent = new Intent(AdminActivity.this, StartTestActivity.class);
				intent.putExtra("paperName", papers.get(i).getPaperName());
				intent.putExtra("examMode", "模拟考试");
				startActivityForResult(intent, 0);
			}
		});

		EventBus.getDefault().register(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0)
			if (resultCode == 1) {
				Grade grade = (Grade) data.getSerializableExtra("data");
				gradeList.add(grade);
				TPaperListAdapter adapter = new TPaperListAdapter(this, papers);
				paperList.setAdapter(adapter);
			}
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_left:
				Intent intent = new Intent(AdminActivity.this, AddPaperActivity.class);
				startActivity(intent);
				break;
		}
	}

	@Subscribe
	public void onReceiveActionEvent(Action action) {
		switch (action) {
			case DOWNLOAD_PAPER_LIST:
				Logger.i("获取试卷列表成功");
				papers = BmobUtils.paperList;
				TPaperListAdapter adapter = new TPaperListAdapter(this, papers);
				paperList.setAdapter(adapter);
				break;
			case QUERY_ERROR:
				Logger.i("获取列表试卷失败");
				Toast.makeText(this, "获取试卷列表失败", Toast.LENGTH_SHORT).show();
				break;

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);

	}
}
