package com.murainy.safeexam.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Student;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
	public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<Student> data) {
		super(layoutResId, data);
	}
	@Override
	protected void convert(BaseViewHolder helper, Student item) {
		helper.setText(R.id.tweetText, item.getName());
		//通过helper.getView获取Imageview
		Glide.with(mContext).load(item.getHeadurl()).into((ImageView) helper.getView(R.id.tweetAvatar));
	}
}