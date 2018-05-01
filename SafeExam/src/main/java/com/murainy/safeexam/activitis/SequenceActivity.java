package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.ToastUtils;
import com.murainy.safeexam.adapter.QuickAdapter;
import com.murainy.safeexam.beans.Kemu;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SequenceActivity extends Activity {

    private RecyclerView mRecyclerView;
	private List<Kemu> papers = new ArrayList<>();
	private QuickAdapter adapter = new QuickAdapter();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		BmobUtils.downloadKemuList(SequenceActivity.this);
		adapter.openLoadAnimation();
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
	            BmobUtils.sequLists(papers.get(position).getSubject());
	            Intent intent = new Intent(SequenceActivity.this, StartTestActivity.class);
	            intent.putExtra("paperName", papers.get(position).getSubject());
	            intent.putExtra("examMode", "顺序练习");
	            startActivityForResult(intent, 0);

            }
        });
		EventBus.getDefault().register(this);
    }


	@Subscribe
	public void onReceiveActionEvent(Action action) {
		switch (action) {
			case DOWNLOAD_KEMU_LIST:
				Logger.i("获取试卷列表成功");
				papers = BmobUtils.kmList;
				adapter.replaceData(papers);
				mRecyclerView.setAdapter(adapter);
				ToastUtils.ToastMessage(this, "安全考试", "获取试卷列表成功");
				break;

			case DOWNLOAD_QUESTION_SEQU:
				ToastUtils.ToastMessage(this, "安全考试", "获取试题列表成功");

				break;
			case QUERY_ERROR:
				Logger.i("获取列表试卷失败");
				break;

		}
	}

	@Override
	protected void onDestroy() {
		// 不要忘记注销！！！！
		EventBus.getDefault().unregister(this);
		super.onDestroy();
    }
}
