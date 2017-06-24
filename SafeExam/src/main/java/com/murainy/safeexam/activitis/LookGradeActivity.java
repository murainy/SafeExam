package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.OperateSQLite;
import com.murainy.safeexam.adapter.GradeListAdapter;
import com.murainy.safeexam.beans.Grade;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by murainy on 2015/12/16.
 */
public class LookGradeActivity extends Activity {

    private List<Grade> gradeList = new ArrayList<Grade>();
    private ListView listView;
    private OperateSQLite operateSQLite;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_grade);
        EventBus.getDefault().register(this);
        // 数据库操作
        operateSQLite = new OperateSQLite(this);
        gradeList = operateSQLite.getGradeData(SafeExam.getStudent().getUsername());
        listView = (ListView) findViewById(R.id.lv_paper_grade);
        BmobUtils.downloadGradeList(LookGradeActivity.this, SafeExam.getStudent().getUsername());
        ButterKnife.bind(this);
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("查看成绩");
        GradeListAdapter adapter = new GradeListAdapter(this, gradeList);
        listView.setAdapter(adapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onReceiveActionEvent(Action action) {
        switch (action) {
            case DOWNLOAD_GRADE_LIST:
                Logger.i("获取成绩列表成功");
                gradeList = BmobUtils.gradeList;
                GradeListAdapter adapter = new GradeListAdapter(this, gradeList);
                listView.setAdapter(adapter);
                break;
            case QUERY_ERROR:
                Logger.i("获取成绩列表失败");
                break;
        }
    }
    @OnClick(R.id.iv_left)
    public void back(View view) {
        finish();
    }
}
