package com.murainy.safeexam.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Grade;

import java.util.List;

/**
 * Created by murainy on 2015/12/29.
 */
public class GradeListAdapter extends BaseAdapter {

    private List<Grade> gradeData;
    private LayoutInflater mInflater;
    private Context context;

    public GradeListAdapter(Context context, List<Grade> data) {
        this.gradeData = data;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gradeData.size();
    }

    @Override
    public Object getItem(int position) {
        return gradeData.get(position);
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
            view = mInflater.inflate(R.layout.grade_list_item, null);
            holder.paperName = (TextView) view.findViewById(R.id.paper_name);
            holder.joinTime = (TextView) view.findViewById(R.id.paper_date);
            holder.grade = (TextView) view.findViewById(R.id.paper_grade);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.paperName.setText(gradeData.get(position).getPaperName());
        holder.joinTime.setText(gradeData.get(position).getJoinTime());
        holder.grade.setText(gradeData.get(position).getGrade() + "");
        if (gradeData.get(position).getGrade() < 60) {
            holder.grade.setTextColor(Color.RED);
        }
        return view;
    }

    public final class ViewHolder {
        public TextView paperName;
        public TextView joinTime;
        public TextView grade;
    }
}
