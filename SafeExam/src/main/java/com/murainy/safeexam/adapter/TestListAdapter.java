package com.murainy.safeexam.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murainy on 2015/12/26.
 */
public class TestListAdapter extends BaseAdapter {

    public List<Integer> isSelected = new ArrayList<Integer>();
    private List<Question> questionData;
    private LayoutInflater mInflater;
    private Context context;

    public TestListAdapter(Context context, List<Question> data) {
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
            Log.d("你好,这是一个", questionData.get(position).getType());
            view = mInflater.inflate(R.layout.test_list_item, null);
            holder.question = (TextView) view.findViewById(R.id.question);
            holder.question.setText((position + 1) + ". " + questionData.get(position).getQuestion());
            holder.answer = (TextView) view.findViewById(R.id.answerd);
            holder.answer.setText((position + 1) + ". " + questionData.get(position).getAnswer());
            holder.note = (TextView) view.findViewById(R.id.answers);
            holder.note.setText((position + 1) + ". " + questionData.get(position).getNote());
            // isSelected.add(new Integer(position));//选中
            // isSelected.remove(new Integer(position));//取消
            switch (questionData.get(position).getType()) {
                case "判断题":
                    Log.d("判断：", "this is a cat1");
                    holder.judge = (RadioGroup) view.findViewById(R.id.judge);
                    holder.judgeA = (RadioButton) view.findViewById(R.id.judgeA);
                    holder.judgeB = (RadioButton) view.findViewById(R.id.judgeB);
                    holder.judgeA.setText("正确");
                    holder.judgeB.setText("错误");
                    holder.singleRadioGroup.setVisibility(View.GONE);
                    holder.multiOptionGroup.setVisibility(View.GONE);
                    view.setTag(holder);
                    break;
                case "单选题 ":
                    Log.d("单选：", "this is a cat2");
                    holder.singleRadioGroup = (RadioGroup) view.findViewById(R.id.single_option_group);
                    holder.singleOptionA = (RadioButton) view.findViewById(R.id.single_option_A);
                    holder.SingleOptionB = (RadioButton) view.findViewById(R.id.single_option_B);
                    holder.singleOptionC = (RadioButton) view.findViewById(R.id.single_option_C);
                    holder.SingleOptionD = (RadioButton) view.findViewById(R.id.single_option_D);
                    holder.singleOptionA.setText(questionData.get(position).getOptionA());
                    holder.SingleOptionB.setText(questionData.get(position).getOptionB());
                    holder.singleOptionC.setText(questionData.get(position).getOptionC());
                    holder.SingleOptionD.setText(questionData.get(position).getOptionD());
                    holder.judge.setVisibility(View.GONE);
                    holder.multiOptionGroup.setVisibility(View.GONE);
                    view.setTag(holder);
                    break;
                case "多选题 ":
                    Log.d("多选：", "this is a cat3");
                    holder.multiOptionGroup = (LinearLayout) view.findViewById(R.id.multiOptionGroup);
                    holder.multiOptionA = (CheckBox) view.findViewById(R.id.multiOptionA);
                    holder.multiOptionB = (CheckBox) view.findViewById(R.id.multiOptionB);
                    holder.multiOptionC = (CheckBox) view.findViewById(R.id.multiOptionC);
                    holder.multiOptionD = (CheckBox) view.findViewById(R.id.multiOptionD);
                    holder.multiOptionE = (CheckBox) view.findViewById(R.id.multiOptionE);
                    holder.multiOptionF = (CheckBox) view.findViewById(R.id.multiOptionF);
                    holder.multiOptionA.setText(questionData.get(position).getOptionA());
                    holder.multiOptionB.setText(questionData.get(position).getOptionB());
                    holder.multiOptionC.setText(questionData.get(position).getOptionC());
                    holder.multiOptionD.setText(questionData.get(position).getOptionD());
                    holder.multiOptionE.setText(questionData.get(position).getOptionE());
                    holder.multiOptionF.setText(questionData.get(position).getOptionF());
                    holder.judge.setVisibility(View.GONE);
                    holder.singleRadioGroup.setVisibility(View.GONE);
                    view.setTag(holder);
                    break;
            }
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }

	private final class ViewHolder {

		private TextView question;
		private RadioGroup judge;
		private RadioButton judgeA;
		private RadioButton judgeB;
		private RadioGroup singleRadioGroup;
		private RadioButton singleOptionA;
		private RadioButton SingleOptionB;
		private RadioButton singleOptionC;
		private RadioButton SingleOptionD;
		private LinearLayout multiOptionGroup;
		private CheckBox multiOptionA;
		private CheckBox multiOptionB;
		private CheckBox multiOptionC;
		private CheckBox multiOptionD;
		private CheckBox multiOptionE;
		private CheckBox multiOptionF;
		private TextView answer;
		private TextView note;
	}
}