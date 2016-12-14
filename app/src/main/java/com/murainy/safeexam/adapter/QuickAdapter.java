package com.murainy.safeexam.adapter;

import android.content.Context;

import com.murainy.safeexam.R;
import com.murainy.safeexam.data.DataServer;
import com.murainy.safeexam.entity.Status;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.murainy.safeexam.transform.GlideCircleTransform;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickAdapter extends BaseQuickAdapter<Status> {
	public QuickAdapter(Context context) {
		super(context, R.layout.tweet, DataServer.getSampleData(100));
	}

	public QuickAdapter(Context context, int dataSize) {
		super(context, R.layout.tweet, DataServer.getSampleData(dataSize));
	}

	@Override
	protected void convert(BaseViewHolder helper, Status item) {
		helper.setText(R.id.tweetName, item.getUserName())
				.setText(R.id.tweetText, item.getText())
				.setText(R.id.tweetDate, item.getCreatedAt())
				.setImageUrl(R.id.tweetAvatar, item.getUserAvatar(), R.mipmap.ic_launcher, new GlideCircleTransform(mContext))
				.setVisible(R.id.tweetRT, item.isRetweet())
				.setOnClickListener(R.id.tweetAvatar, new BaseQuickAdapter.OnItemChildClickListener())
				.setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
				.linkify(R.id.tweetText);
	}


}
