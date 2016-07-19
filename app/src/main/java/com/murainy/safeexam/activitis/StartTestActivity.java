package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.DefaultGestureListener;
import com.murainy.safeexam.Utils.OperateSQLite;
import com.murainy.safeexam.Utils.ToastUtil;
import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;
import com.murainy.safeexam.beans.Question;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by murainy on 2015/12/16.
 */
public class StartTestActivity extends Activity implements View.OnClickListener {

    private List<Question> questions = new ArrayList<Question>();
    private List<Question> qthLists = new ArrayList<Question>();
    private List<Question> judgethList = new ArrayList<Question>();
    private List<Question> singerthList = new ArrayList<Question>();
    private List<Question> mutithList = new ArrayList<Question>();
    private List<String> answers = new ArrayList<String>();
    private TextView question, id, timeTV, answer, note;
    private Button finishBtn;
    private int tag = 0;
    private String paperName;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Grade grade;
    private Paper paper;
    private OperateSQLite operateSQLite;
    private CheckBox multiOptionA, multiOptionB, multiOptionC, multiOptionD, multiOptionE, multiOptionF;
    private RadioButton judgeA, judgeB, singleOptionA, singleOptionB, singleOptionC, singleOptionD;
    private RadioGroup singleRadioGroup, judge;
    private LinearLayout multiOptionGroup;
    private RelativeLayout test;
    private GestureDetector mGestureDetector;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    timeTV.setText(msg.arg1 + "分钟");
                    break;
                case 1:
                    showTimeOverDialog();
                    break;
            }
        }
    };
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        if (hasFocus)
        {
            ToastUtil.showShort(this,"开始练习。");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        EventBus.getDefault().register(this);
        // 数据库操作
        operateSQLite = new OperateSQLite(this);
        paperName = getIntent().getStringExtra("paperName");
        Logger.i(paperName + "");
        BmobUtils.questionCount(StartTestActivity.this, paperName, "判断题", 40);
        judgethList = qthLists;
        BmobUtils.questionCount(StartTestActivity.this, paperName, "单选题", 40);
        singerthList = qthLists;
        BmobUtils.questionCount(StartTestActivity.this, paperName, "多选题", 20);
        mutithList = qthLists;
        BmobUtils.downloadQuestionList100(StartTestActivity.this, judgethList, singerthList, mutithList);
        //BmobUtils.downloadQuestionList(StartTestActivity.this, paperName);
        test= (RelativeLayout) findViewById(R.id.test_item);
        ImageButton backBtn = (ImageButton) findViewById(R.id.btn_back);
        finishBtn = (Button) findViewById(R.id.btn_finish_test);
        timeTV = (TextView) findViewById(R.id.tv_time);
        question = (TextView) findViewById(R.id.question);
        id = (TextView) findViewById(R.id.id);
        judge = (RadioGroup) findViewById(R.id.judge);
        judgeA = (RadioButton) findViewById(R.id.judgeA);
        judgeB = (RadioButton) findViewById(R.id.judgeB);
        question = (TextView) findViewById(R.id.question);
        singleRadioGroup = (RadioGroup) findViewById(R.id.single_option_group);
        singleOptionA = (RadioButton) findViewById(R.id.single_option_A);
        singleOptionB = (RadioButton) findViewById(R.id.single_option_B);
        singleOptionC = (RadioButton) findViewById(R.id.single_option_C);
        singleOptionD = (RadioButton) findViewById(R.id.single_option_D);
        multiOptionGroup = (LinearLayout) findViewById(R.id.multiOptionGroup);
        multiOptionA = (CheckBox) findViewById(R.id.multiOptionA);
        multiOptionB = (CheckBox) findViewById(R.id.multiOptionB);
        multiOptionC = (CheckBox) findViewById(R.id.multiOptionC);
        multiOptionD = (CheckBox) findViewById(R.id.multiOptionD);
        multiOptionE = (CheckBox) findViewById(R.id.multiOptionE);
        multiOptionF = (CheckBox) findViewById(R.id.multiOptionF);
        answer = (TextView) findViewById(R.id.answerd);
        note = (TextView) findViewById(R.id.answers);
        backBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        testTimeThread(40);
        mGestureDetector = new GestureDetector(this, new DefaultGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Logger.e("ACTION_DOWN" + action);
                if (saveAnswer()) {
                    if (tag >= questions.size() - 1) {
                        finishTest();
                        showDialog(grade);
                    } else {
                        tag++;
                        eaxmlist();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Logger.e("ACTION_UP" + action);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Logger.e("ACTION_POINTER_UP" + action);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Logger.e("ACTION_POINTER_DOWN" + action);
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.e("ACTION_MOVE" + action);
                break;
        }

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                showWarnDialog();
                break;
            case R.id.btn_finish_test:
                if (tag == questions.size() - 1) {
                    finishBtn.setText("交卷");
                }
                if (saveAnswer()) {
                    if (tag >= questions.size() - 1) {
                        finishTest();
                        showDialog(grade);
                    } else {
                        tag++;
                        eaxmlist();
                    }
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMainThread(Action action) {
        switch (action) {
            case DOWNLOAD_QUESTION_LIST:
                Logger.i("获取试题列表成功");
                questions = BmobUtils.questionsList;
                Logger.i(questions.size() + "");
                if (questions.size() == 1) {
                    finishBtn.setText("交卷");
                }
                eaxmlist();
                break;
            case DOWNLOAD_QUESTION_LISTMy:
                Logger.i("获取试题列表成功");
                questions = BmobUtils.questionsList;
                Logger.i(questions.size() + "");
                if (questions.size() == 1) {
                    finishBtn.setText("交卷");
                }
                eaxmlist();
                break;
            case DOWNLOAD_QUESTION_LIST100:
                Logger.i("获取试题列表成功");
                questions = BmobUtils.questionsList;
                Logger.i(questions.size() + "");
                if (questions.size() == 1) {
                    finishBtn.setText("交卷");
                }
                eaxmlist();
                break;
            case QUESTIONCOUNT:
                qthLists = BmobUtils.qthList;
                break;
            case QUERY_ERROR:

                break;

        }
    }

    private void eaxmlist() {
        id.setText((tag + 1) + ". ");
        Logger.i(questions.get(tag).getType());
        question.setText(questions.get(tag).getQuestion());
        answer.setText(questions.get(tag).getAnswer());
        note.setText(questions.get(tag).getNote());
        switch (questions.get(tag).getType()) {
            case "判断题":
                Log.d("判断：", "this is a cat1");
                judge.setVisibility(View.VISIBLE);
                singleRadioGroup.setVisibility(View.GONE);
                multiOptionGroup.setVisibility(View.GONE);
                judgeA.setText("A、正确");
                judgeB.setText("B、错误");
                break;
            case "单选题":
                Log.d("单选：", "this is a cat2");
                singleRadioGroup.setVisibility(View.VISIBLE);
                judge.setVisibility(View.GONE);
                multiOptionGroup.setVisibility(View.GONE);
                String xa = "A、" + questions.get(tag).getOptionA();
                singleOptionA.setText(xa);
                String xb = "B、" + questions.get(tag).getOptionB();
                singleOptionB.setText(xb);
                String xc = "C、" + questions.get(tag).getOptionC();
                singleOptionC.setText(xc);
                String xd = "D、" + questions.get(tag).getOptionD();
                singleOptionD.setText(xd);
                if (xd.equals("D、")) {
                    singleOptionD.setVisibility(View.INVISIBLE);
                } else {
                    singleOptionD.setVisibility(View.VISIBLE);
                }
                break;
            case "多选题":
                Log.d("多选：", "this is a cat3");
                multiOptionGroup.setVisibility(View.VISIBLE);
                judge.setVisibility(View.GONE);
                singleRadioGroup.setVisibility(View.GONE);
                String ma = "A、" + questions.get(tag).getOptionA();
                multiOptionA.setText(ma);
                String mb = "B、" + questions.get(tag).getOptionB();
                multiOptionB.setText(mb);
                String mc = "C、" + questions.get(tag).getOptionC();
                multiOptionC.setText(mc);
                String md = "D、" + questions.get(tag).getOptionD();
                multiOptionD.setText(md);
                String me = "E、" + questions.get(tag).getOptionE();
                multiOptionE.setText(me);
                String mf = "F、" + questions.get(tag).getOptionF();
                multiOptionF.setText(mf);
                if (me.equals("E、")) {
                    multiOptionE.setVisibility(View.INVISIBLE);
                } else {
                    multiOptionE.setVisibility(View.VISIBLE);
                }
                if (mf.equals("F、")) {
                    multiOptionF.setVisibility(View.INVISIBLE);
                } else {
                    multiOptionF.setVisibility(View.VISIBLE);
                }
                break;
        }
        if (test.getVisibility()==View.INVISIBLE){test.setVisibility(View.VISIBLE);

        }
    }

    private boolean saveAnswer() {
        String answerx = "";
        if (judgeA.isChecked()) {
            answerx = "y";
        } else if (judgeB.isChecked()) {
            answerx = "n";
        } else if (singleOptionA.isChecked()) {
            answerx = "A";
        } else if (singleOptionB.isChecked()) {
            answerx = "B";
        } else if (singleOptionC.isChecked()) {
            answerx = "C";
        } else if (singleOptionD.isChecked()) {
            answerx = "D";
        } else if (multiOptionA.isChecked()) {
            answerx = "A";
        } else if (multiOptionB.isChecked()) {
            answerx = answerx + "B";
        } else if (multiOptionC.isChecked()) {
            answerx = answerx + "C";
        } else if (multiOptionD.isChecked()) {
            answerx = answerx + "D";
        } else if (multiOptionE.isChecked()) {
            answerx = answerx + "E";
        } else if (multiOptionA.isChecked()) {
            answerx = answerx + "F";
        } else {
            Toast t3 = Toast.makeText(this, "还没答题呢！", Toast.LENGTH_SHORT);
            t3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
            t3.setMargin(0f, 0.5f);
            t3.show();
            return false;
        }

        Logger.e("我的答案："+answerx);
        answers.add(answerx);
        singleRadioGroup.clearCheck();
        judge.clearCheck();
        multiOptionA.setChecked(false);
        multiOptionB.setChecked(false);
        multiOptionC.setChecked(false);
        multiOptionD.setChecked(false);
        multiOptionE.setChecked(false);
        multiOptionF.setChecked(false);

        return true;
    }

    private void finishTest() {
        qthLists = new ArrayList<Question>();
        int questionNum = questions.size();
        int correctNum = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(questions.get(i).getAnswer())) {
                correctNum++;
            } else {
                //错误记录
                qthLists.add(questions.get(i));
            }
        }
        BmobUtils.updatemark(qthLists);
        Logger.i(questionNum + "/" + correctNum);
        grade = new Grade();
        grade.setPaperName(paperName);
        grade.setUsername(SafeExam.getStudent().getUsername());
        grade.setJoinTime(df.format(new Date()));
        grade.setGrade((correctNum * 100) / questionNum);
        BmobUtils.saveGrade(StartTestActivity.this, grade);
        paper = new Paper();

        Logger.i(answers.toString());
    }

    private void showDialog(final Grade grade) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);
        builder.setMessage("交卷后可前往个人信息查看成绩");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                Intent i = new Intent();
                i.putExtra("data", grade);
                setResult(1, i);
                finish();
            }
        });
        builder.create().show();
    }

    private void showWarnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);

        builder.setMessage("点击确定将自动提交成绩，放弃结束考试。");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                saveAnswer();
                finishTest();
                Intent i = new Intent();
                i.putExtra("data", grade);
                setResult(1, i);
                finish();
            }
        });
        builder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }

        });
        builder.create().show();
    }

    private void showTimeOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);

        builder.setMessage("考试时间结束！");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                saveAnswer();
                finishTest();
                Intent i = new Intent();
                i.putExtra("data", grade);
                setResult(1, i);
                finish();
            }
        });
        builder.create().show();
    }

    private void testTimeThread(final int time) {
        timeTV.setText(time + "分钟");
        new Thread() {
            public void run() {
                int t = time;
                for (int i = 0; i < time; i++) {
                    Logger.i("开始" + i);
                    try {
                        sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t--;
                    Message msg = new Message();
                    msg.what = 0;
                    msg.arg1 = t;
                    handler.sendMessage(msg);
                }
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = t;
                handler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            showWarnDialog();
            return false;
        } else if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            showWarnDialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



}
