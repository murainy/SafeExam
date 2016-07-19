package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.adapter.TPaperListAdapter;
import com.murainy.safeexam.beans.Paper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murainy on 2016/6/17.
 */
public class AdminActivity extends Activity implements View.OnClickListener {

    private ListView paperList;
    private List<Paper> papers = new ArrayList<Paper>();
    private TPaperListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        EventBus.getDefault().register(this);
        BmobUtils.downloadTPaperList(AdminActivity.this);
        paperList = (ListView) findViewById(R.id.lv_paper_teacher);
        adapter = new TPaperListAdapter(this, papers);
        paperList.setAdapter(adapter);
        paperList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminActivity.this, StartTestActivity.class);
                intent.putExtra("paperName", papers.get(i).getPaperName());
                startActivity(intent);
            }
        });

        Button addPaper = (Button) findViewById(R.id.add_paper);
        addPaper.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_paper:
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
}
