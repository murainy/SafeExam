package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.beans.Paper;
import com.murainy.safeexam.beans.Question;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by murainy on 2016/1/8.
 */
public class AddPaperActivity extends Activity implements View.OnClickListener {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Paper paper = new Paper();
    private LinearLayout addPaperDialog;
    private EditText inputPaperId;
    private EditText inputPaperName;
    private Button addQuestionBtn;
    private Button finishBtn;
    private List<Question> questions = new ArrayList<Question>();

    private TextView id;
    private EditText questionET, optionAET, optionBET, optionCET, optionDET;
    private CheckBox optionA, optionB, optionC, optionD;
    private int tag = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper);
        EventBus.getDefault().register(this);
        addPaperDialog = (LinearLayout) findViewById(R.id.add_paper_dialog);
        inputPaperId = (EditText) findViewById(R.id.et_paper_id);
        inputPaperName = (EditText) findViewById(R.id.et_paper_name);
        addQuestionBtn = (Button) findViewById(R.id.add_question);
        finishBtn = (Button) findViewById(R.id.finish);
        Button dialogSureBtn = (Button) findViewById(R.id.dialog_sure);
        ImageButton backBtn = (ImageButton) findViewById(R.id.btn_back);
        TextView paperDate = (TextView) findViewById(R.id.tv_paper_date);
        id = (TextView) findViewById(R.id.tv_id);
        questionET = (EditText) findViewById(R.id.et_question);
        optionAET = (EditText) findViewById(R.id.et_optionA);
        optionBET = (EditText) findViewById(R.id.et_optionB);
        optionCET = (EditText) findViewById(R.id.et_optionC);
        optionDET = (EditText) findViewById(R.id.et_optionD);
        optionA = (CheckBox) findViewById(R.id.cb_option_A);
        optionB = (CheckBox) findViewById(R.id.cb_option_B);
        optionC = (CheckBox) findViewById(R.id.cb_option_C);
        optionD = (CheckBox) findViewById(R.id.cb_option_D);

        id.setText(tag + ". ");
        paperDate.setText(df.format(new Date()));
        dialogSureBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                if (!inputPaperId.getText().toString().equals("")
                        && !inputPaperName.getText().toString().equals("")) {
                    paper.setPaperId(inputPaperId.getText().toString());
                    paper.setPaperName(inputPaperName.getText().toString());
                    paper.setJoinTime(df.format(new Date()));
                    addPaperDialog.setVisibility(View.GONE);
                    addQuestionBtn.setOnClickListener(this);
                    finishBtn.setOnClickListener(this);
                } else {
                    Toast.makeText(AddPaperActivity.this, "请补全信息", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.finish:
                finishBtn.setOnClickListener(null);
                savePaper();
                break;
            case R.id.add_question:

                addQuestion();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void addQuestion() {
        int j = checkInformation();
        switch (j) {
            case -1:
                Toast.makeText(AddPaperActivity.this, "请补全信息", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(AddPaperActivity.this, "请选择答案", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                optionA.setChecked(false);
                saveQuestion("A");
                break;
            case 2:
                optionB.setChecked(false);
                saveQuestion("B");
                break;
            case 3:
                optionC.setChecked(false);
                saveQuestion("C");
                break;
            case 4:
                optionD.setChecked(false);
                saveQuestion("D");
                break;
        }
    }

    private int checkInformation() {
        if (!questionET.getText().toString().equals("") && !optionAET.getText().toString().equals("")
                && !optionBET.getText().toString().equals("") && !optionCET.getText().toString().equals("")
                && !optionDET.getText().toString().equals("")) {
            if (optionA.isChecked()) {
                return 1;
            }
            if (optionB.isChecked()) {
                return 2;
            }
            if (optionC.isChecked()) {
                return 3;
            }
            if (optionD.isChecked()) {
                return 4;
            }
            return 0;
        } else {
            return -1;
        }
    }

    private void saveQuestion(String answer) {
        Question q = new Question();
        q.setQuestionid(paper.getPaperName() + tag);
        q.setPaperName(paper.getPaperName());
        q.setQuestion(questionET.getText().toString());
        q.setOptionA(optionAET.getText().toString());
        q.setOptionB(optionBET.getText().toString());
        q.setOptionC(optionCET.getText().toString());
        q.setOptionD(optionDET.getText().toString());
        q.setAnswer(answer);
        questions.add(q);
        tag++;
        id.setText(tag + ". ");
        questionET.setText("");
        optionAET.setText("");
        optionBET.setText("");
        optionCET.setText("");
        optionDET.setText("");
        Logger.i(questions.size() + "");
    }

    private boolean savePaper() {
        addQuestion();
        BmobUtils.saveNewPaper(AddPaperActivity.this, paper, questions);
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onReceiveActionEvent(Action action) {
        switch (action) {
            case SAVE_PAPER_SUCCESS:
                Toast.makeText(AddPaperActivity.this, "保存试卷成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case SAVE_PAPER_ERROR:
                Toast.makeText(this, "保存试卷失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
