package com.murainy.safeexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Paper;

import java.util.List;

/**
 * Created by murainy on 2015/12/14.
 */
public class TPaperListAdapter extends BaseAdapter {

    private List<Paper> paperData;
    private LayoutInflater mInflater;
    private Context context;

    public TPaperListAdapter(Context context, List<Paper> data) {
        this.paperData = data;
        this.context = context;
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
            view = mInflater.inflate(R.layout.paper_list_item_teacher, null);
            holder.paperName = (TextView) view.findViewById(R.id.paper_name);
            holder.joinTime = (TextView) view.findViewById(R.id.paper_date);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.paperName.setText(paperData.get(position).getPaperName());
        holder.joinTime.setText(paperData.get(position).getJoinTime());
        return view;
    }

    public final class ViewHolder {
        public TextView paperName;
        public TextView joinTime;
    }
}
