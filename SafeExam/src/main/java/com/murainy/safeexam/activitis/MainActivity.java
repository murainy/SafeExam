package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.ToastUtils;
import com.murainy.safeexam.adapter.TestqebaAdapter;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Question;
import com.murainy.safeexam.beans.Testqeba;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.murainy.safeexam.Utils.BmobUtils.TestList;
import static com.murainy.safeexam.Utils.BmobUtils.downloadGradeList;
import static com.murainy.safeexam.Utils.BmobUtils.downloadQuestionList;
import static com.murainy.safeexam.Utils.BmobUtils.downloadTestList;
import static com.murainy.safeexam.Utils.BmobUtils.examLists;
import static com.murainy.safeexam.Utils.BmobUtils.gradeList;
import static com.murainy.safeexam.Utils.BmobUtils.paperSet;
import static com.murainy.safeexam.Utils.BmobUtils.updateTestpaper;

/**
 * Created by murainy on 2015/12/14.
 */
public class MainActivity extends Activity {

	@BindView(R.id.iv_left)
	public ImageView iv_left;
	@BindView(R.id.iv_right)
	public ImageView iv_right;
	@BindView(R.id.tv_title)
	public TextView tv_title;
	@BindView(R.id.tv_exam_time)
	public TextView tvExamTime;
	@BindView(R.id.lv_paper)
	public ListView lvPaper;
	private ArrayList<Question> questions = new ArrayList<>();
	private List<Testqeba> papers = new ArrayList<>();
	private TestqebaAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		//隐藏状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		iv_left.setVisibility(View.VISIBLE);
		iv_right.setVisibility(View.VISIBLE);
		tv_title.setText("考试项目");
		tvExamTime.setText(btime());
		downloadGradeList(MainActivity.this, SafeExam.getStudent().getUsername());
		downloadTestList(MainActivity.this);
		papers = TestList;
		adapter = new TestqebaAdapter(this, papers);
		lvPaper.setAdapter(adapter);
		lvPaper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				examLists(papers.get(i).getName());
				Intent intent = new Intent(MainActivity.this, StartTestActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("paperName", papers.get(i).getName());
				bundle.putString("examMode", "正式考试");
				bundle.putParcelableArrayList("questions", questions);
				intent.putExtras(bundle);
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
				TestqebaAdapter adapter = new TestqebaAdapter(this, papers);
				lvPaper.setAdapter(adapter);
			}
	}


	@Subscribe
	public void onReceiveActionEvent(Action action) {
		switch (action) {
			case DOWNLOAD_QUESTION_SET:
				if (BmobUtils.paperSet.size() <50) {
					finish();
				} else {
					Logger.i("获取试题列表成功");
					ToastUtils.showShort(this, paperSet.toString());
				}
				break;
			case DOWNLOAD_QUESTION_EXAM:
				Logger.i("获取试题列表成功");
				questions = BmobUtils.questionsList;
				ToastUtils.showShort(this, "下载成功");
				break;
			case DOWNLOAD_QUESTION_LISTINI:
				Logger.i("获取试题列表成功");
				questions = BmobUtils.questionsList;
				break;
			case DOWNLOAD_Test_LIST:
				Logger.i("获取试卷列表成功");
				//BmobUtils.updateAllPaperList(this);
				papers = TestList;
				adapter = new TestqebaAdapter(this, papers);
				lvPaper.setAdapter(adapter);
				break;
			case DOWNLOAD_GRADE_LIST:
				Logger.i("获取成绩成功");
				downloadGradeList(MainActivity.this, SafeExam.getStudent().getUsername());
				break;
			case QUERY_ERROR:
				Logger.i("获取试卷列表失败");
				finish();
				break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		papers = TestqebaAdapter.paperData;
		for (int i = 0; i < papers.size(); i++) {
			updateTestpaper(papers);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);

	}


	@OnClick({R.id.tv_left, R.id.tv_exam_time})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_left:
				finish();
				break;
			case R.id.tv_exam_time:
				tvExamTime.setText(btime());
				break;
		}
	}

	private String btime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		return formatter.format(curDate);
	}
}
