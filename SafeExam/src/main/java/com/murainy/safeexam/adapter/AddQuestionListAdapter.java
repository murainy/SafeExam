package com.murainy.safeexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Question;

import java.util.List;

/**
 * Created by murainy on 2016/1/8.
 */
public class AddQuestionListAdapter extends BaseAdapter {

    private List<Question> questionData;
    private LayoutInflater mInflater;
    private Context context;

    public AddQuestionListAdapter(Context context, List<Question> data) {
        this.questionData = data;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questionData.size();
    }

    @Override
    public Object getItem(int position) {
        return questionData.get(position);
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
            view = mInflater.inflate(R.layout.add_question_list_item, null);
            holder.id = (TextView) view.findViewById(R.id.tv_id);
            holder.question = (EditText) view.findViewById(R.id.et_question);
            holder.optionAET = (EditText) view.findViewById(R.id.et_optionA);
            holder.optionBET = (EditText) view.findViewById(R.id.et_optionB);
            holder.optionCET = (EditText) view.findViewById(R.id.et_optionC);
            holder.optionDET = (EditText) view.findViewById(R.id.et_optionD);
            holder.multiOptionA = (CheckBox) view.findViewById(R.id.cb_option_A);
            holder.multiOptionB = (CheckBox) view.findViewById(R.id.cb_option_B);
            holder.multiOptionC = (CheckBox) view.findViewById(R.id.cb_option_C);
            holder.multiOptionD = (CheckBox) view.findViewById(R.id.cb_option_D);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.id.setText((position + 1) + ".");
        holder.question.setText(questionData.get(position).getQuestion());
        holder.optionAET.setText(questionData.get(position).getOptionA());
        holder.optionBET.setText(questionData.get(position).getOptionB());
        holder.optionCET.setText(questionData.get(position).getOptionC());
        holder.optionDET.setText(questionData.get(position).getOptionD());
        if (questionData.get(position).getAnswer().equals("A")) {
            holder.multiOptionA.setChecked(true);
        }
        if (questionData.get(position).getAnswer().equals("B")) {
            holder.multiOptionB.setChecked(true);
        }
        if (questionData.get(position).getAnswer().equals("C")) {
            holder.multiOptionC.setChecked(true);
        }
        if (questionData.get(position).getAnswer().equals("D")) {
            holder.multiOptionD.setChecked(true);
        }
        return view;
    }

    public final class ViewHolder {
        public TextView id;
        public EditText question;
        public EditText optionAET;
        public EditText optionBET;
        public EditText optionCET;
        public EditText optionDET;
        public CheckBox multiOptionA;
        public CheckBox multiOptionB;
        public CheckBox multiOptionC;
        public CheckBox multiOptionD;
    }

}
