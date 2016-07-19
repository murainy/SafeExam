package com.murainy.safeexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;

import java.util.List;

/*
*
 * Created by murainy on 2015/12/14.
*/
public class PaperListAdapter extends BaseAdapter {

    public static List<Paper> paperData;
    private List<Grade> gradeList;
    private LayoutInflater mInflater;
    private Context context;

    public PaperListAdapter(Context context, List<Paper> data) {
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
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.paper_list_item, null);
            holder.paperName = (TextView) view.findViewById(R.id.paper_name);
            holder.joinTime = (TextView) view.findViewById(R.id.paper_date);
            holder.finishState = (TextView) view.findViewById(R.id.paper_state);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.paperName.setText(paperData.get(position).getPaperName());
        holder.joinTime.setText(paperData.get(position).getJoinTime());
        holder.finishState.setText("未完成");
        Logger.i(paperData.size() + "adapter");
        for (int i = 0; i < gradeList.size(); i++) {
            if (paperData.get(position).getPaperName().equals(gradeList.get(i).getPaperName())) {
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
