package com.murainy.safeexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Testqeba;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Tenerify on 2017/6/6.
 */

public class TestqebaAdapter extends BaseAdapter {

	public static List<Testqeba> paperData;
	private List<Grade> gradeList;
	private LayoutInflater mInflater;
	private Context context;

	public TestqebaAdapter(Context context, List<Testqeba> data) {
		paperData = data;
		this.context = context;
		gradeList = BmobUtils.gradeList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return paperData.size();
	}

	@Override
	public Object getItem(int position) {
		return paperData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		TestqebaAdapter.ViewHolder holder = null;
		if (view == null) {
			holder = new TestqebaAdapter.ViewHolder();
			view = mInflater.inflate(R.layout.testqeba_list_item, null);
			holder.paperName = (TextView) view.findViewById(R.id.paper_name);
			holder.joinTime = (TextView) view.findViewById(R.id.paper_date);
			holder.finishState = (TextView) view.findViewById(R.id.paper_state);
			view.setTag(holder);
		} else {
			holder = (TestqebaAdapter.ViewHolder) view.getTag();
		}
		holder.paperName.setText(paperData.get(position).getName());
		holder.joinTime.setText(paperData.get(position).getNote());
		holder.finishState.setText("未完成");
		Logger.i(paperData.size() + "adapter");
		for (int i = 0; i < gradeList.size(); i++) {
			if (paperData.get(position).getName().equals(gradeList.get(i).getPaperName())) {
				paperData.get(position).setFinishState(true);
				holder.finishState.setText("已完成");
			}
		}
		return view;
	}

	public final class ViewHolder {
		public TextView paperName;
		public TextView joinTime;
		public TextView finishState;
	}

}
