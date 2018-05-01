package com.murainy.safeexam.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Kemu;
import com.murainy.safeexam.data.DataServer;

public class QuickAdapter extends BaseQuickAdapter<Kemu, BaseViewHolder> {
	public QuickAdapter() {
		super(R.layout.tweet, DataServer.getSampleData());
	}

	@Override
	protected void convert(BaseViewHolder helper, Kemu item) {
		helper.setText(R.id.tweetName, item.getSubject())
				.setText(R.id.tweetText, item.getCreatedAt())
				.setText(R.id.tweetDate, item.getYear())
				.setVisible(R.id.tweetRT, item.isFinishState())
				.linkify(R.id.tweetText);


	}
}