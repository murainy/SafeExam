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
import android.widget.SeekBar;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Utils.Action;
import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.Utils.DefaultGestureListener;
import com.murainy.safeexam.Utils.ToastUtils;
import com.murainy.safeexam.beans.Grade;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by murainy on 2015/12/16.
 */
public class StartTestActivity extends Activity {
	//implements View.OnClickListener //abstract
	@BindView(R.id.btn_mask_test)
	public Button btnMaskTest;
	@BindView(R.id.btn_skip_test)
	public Button btnSkipTest;
	@BindView(R.id.btn_finish_test)
	public Button btnFinishTest;
	@BindView(R.id.btn_back)
	public ImageButton btnBack;
	@BindView(R.id.seekBar)
	public SeekBar seekBar;

	@BindView(R.id.judgeA)
	public RadioButton judgeA;
	@BindView(R.id.judgeB)
	public RadioButton judgeB;
	@BindView(R.id.single_option_A)
	public RadioButton singleOptionA;
	@BindView(R.id.single_option_B)
	public RadioButton singleOptionB;
	@BindView(R.id.single_option_C)
	public RadioButton singleOptionC;
	@BindView(R.id.single_option_D)
	public RadioButton singleOptionD;
	@BindView(R.id.multiOptionA)
	public CheckBox multiOptionA;
	@BindView(R.id.multiOptionB)
	public CheckBox multiOptionB;
	@BindView(R.id.multiOptionC)
	public CheckBox multiOptionC;
	@BindView(R.id.multiOptionD)
	public CheckBox multiOptionD;
	@BindView(R.id.multiOptionE)
	public CheckBox multiOptionE;
	@BindView(R.id.multiOptionF)
	public CheckBox multiOptionF;
	@BindView(R.id.judge)
	public RadioGroup judge;
	@BindView(R.id.single_option_group)
	public RadioGroup singleOptionGroup;
	@BindView(R.id.multiOptionGroup)
	public LinearLayout multiOptionGroup;
	@BindView(R.id.tv_ksxm)
	TextView tvKsxm;

	private List<Question> questions = new ArrayList<Question>();
	private List<Question> qthLists = new ArrayList<Question>();
	private List<String> answers = new ArrayList<>();
	private List<String> muti_answers = new ArrayList<>();
	private TextView question, id, timeTV, answer, note;
	private String answerx = "未答";
	private int tag = 0;
	private String paperName;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Grade grade;
	private String examMode;

	private GestureDetector mGestureDetector;
	private RelativeLayout test;


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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_test);
		EventBus.getDefault().register(this);
		ButterKnife.bind(this);
		paperName = getIntent().getStringExtra("paperName");
		examMode = getIntent().getStringExtra("examMode");
		Logger.i(paperName + "：收到了" + examMode);
		test = (RelativeLayout) findViewById(R.id.test_item);
		tvKsxm.setText(examMode);
		timeTV = (TextView) findViewById(R.id.tv_time);
		question = (TextView) findViewById(R.id.question);
		id = (TextView) findViewById(R.id.id);
		//judge = (RadioGroup) findViewById(R.id.judge);
		judgeA = (RadioButton) findViewById(R.id.judgeA);
		judgeB = (RadioButton) findViewById(R.id.judgeB);
		question = (TextView) findViewById(R.id.question);
		//singleRadioGroup = (RadioGroup) findViewById(R.id.single_option_group);
		singleOptionA = (RadioButton) findViewById(R.id.single_option_A);
		singleOptionB = (RadioButton) findViewById(R.id.single_option_B);
		singleOptionC = (RadioButton) findViewById(R.id.single_option_C);
		singleOptionD = (RadioButton) findViewById(R.id.single_option_D);
		//multiOptionGroup = (LinearLayout) findViewById(R.id.multiOptionGroup);
		multiOptionA = (CheckBox) findViewById(R.id.multiOptionA);
		multiOptionB = (CheckBox) findViewById(R.id.multiOptionB);
		multiOptionC = (CheckBox) findViewById(R.id.multiOptionC);
		multiOptionD = (CheckBox) findViewById(R.id.multiOptionD);
		multiOptionE = (CheckBox) findViewById(R.id.multiOptionE);
		multiOptionF = (CheckBox) findViewById(R.id.multiOptionF);
		answer = (TextView) findViewById(R.id.answerd);
		note = (TextView) findViewById(R.id.answers);

		testTimeThread(40);

		mGestureDetector = new GestureDetector(this, new DefaultGestureListener() {
			// 重写onFling方法
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			                       float velocityY) {
				if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
						&& Math.abs(velocityX) > FLING_MIN_VELOCITX) {
					// Fling left
					if (tag < 100) {
						if (saveAnswer()) {
							tag++;
							eaxmlist();
							seekBar.setProgress(tag);
						}
					}

					//Toast.makeText(StartTestActivity.this, "向左手势", Toast.LENGTH_SHORT).show();
				} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
						&& Math.abs(velocityX) > FLING_MIN_VELOCITX) {
					// Fling right

					if (tag > 0) {
						if (saveAnswer()) {
							tag--;
							eaxmlist();
							seekBar.setProgress(tag);
						}
					}


					//Toast.makeText(StartTestActivity.this, "向右手势", Toast.LENGTH_SHORT).show();

				} else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE
						&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
					// Fling up
					Log.e("向上手势", "向上手势");
					// 下一题
					//Toast.makeText(StartTestActivity.this, "向上手势", Toast.LENGTH_SHORT).show();
				} else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
						&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
					// Fling D
					Log.e("向下手势", "向下手势");
					// 下一题
					//Toast.makeText(StartTestActivity.this, "向下手势", Toast.LENGTH_SHORT).show();
				}

				return super.onFling(e1, e2, velocityX, velocityY);

			}

		});
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				Logger.e("ACTION_DOWN" + action);
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
			case MotionEvent.ACTION_SCROLL:
				if (saveAnswer()) {
					tag++;
					eaxmlist();
				}
				break;

		}

		return mGestureDetector.onTouchEvent(event);
	}


	@Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
	public void onEventMainThread(Action action) {
		switch (action) {
			case DOWNLOAD_QUESTION_LIST:
				Logger.i("获取试题列表成功");
				questions = BmobUtils.questionsList;
				break;
			case DOWNLOAD_QUESTION_LISTMy:
				Logger.i("获取试题列表成功");
				questions = BmobUtils.questionsList;
				break;
			case DOWNLOAD_QUESTION_LIST100:
				Logger.i("获取试题列表成功");
				questions = BmobUtils.questionsList;
				break;
			case QUESTION_COUNT:
				qthLists = BmobUtils.qthList;
				break;
			case QUERY_ERROR:
				finish();
				break;
			case DOWNLOAD_QUESTION_LISTID:

				break;
		}
	}

	private void eaxmlist() {
		id.setText((tag + 1) + ". ");
		Logger.i(questions.get(tag).getType());
		question.setText(questions.get(tag).getQuestion());
		if (examMode.equals("正式考试")) {
			answer.setVisibility(View.GONE);
			note.setVisibility(View.GONE);
		} else {
			answer.setText("答案：" + questions.get(tag).getAnswer());
			note.setText(questions.get(tag).getNote());
		}

		switch (questions.get(tag).getType()) {
			case "判断题":
				answerx = "未答";
				Log.d("判断：", "this is a cat1");
				judge.setVisibility(View.VISIBLE);
				judge.clearCheck();

				singleOptionGroup.setVisibility(View.GONE);
				multiOptionGroup.setVisibility(View.GONE);
				judgeA.setText("A、正确");
				judgeB.setText("B、错误");
				break;
			case "单选题":
				answerx = "未答";
				Log.d("单选：", "this is a cat2");
				singleOptionGroup.setVisibility(View.VISIBLE);
				singleOptionGroup.clearCheck();
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
					singleOptionD.setVisibility(View.GONE);
				} else {
					singleOptionD.setVisibility(View.VISIBLE);
				}
				break;
			case "多选题":
				muti_answers.clear();
				Log.d("多选：", "this is a cat3");
				multiOptionGroup.setVisibility(View.VISIBLE);
				judge.setVisibility(View.GONE);
				singleOptionGroup.setVisibility(View.GONE);
				String ma = "A、" + questions.get(tag).getOptionA();
				multiOptionA.setText(ma);
				multiOptionA.setChecked(false);
				String mb = "B、" + questions.get(tag).getOptionB();
				multiOptionB.setText(mb);
				multiOptionB.setChecked(false);
				String mc = "C、" + questions.get(tag).getOptionC();
				multiOptionC.setText(mc);
				multiOptionC.setChecked(false);
				String md = "D、" + questions.get(tag).getOptionD();
				multiOptionD.setText(md);
				multiOptionD.setChecked(false);
				String me = "E、" + questions.get(tag).getOptionE();
				multiOptionE.setText(me);
				multiOptionE.setChecked(false);
				String mf = "F、" + questions.get(tag).getOptionF();
				multiOptionF.setText(mf);
				multiOptionF.setChecked(false);
				if (md.equals("D、")) {
					multiOptionD.setVisibility(View.GONE);
				} else {
					multiOptionD.setVisibility(View.VISIBLE);
				}
				if (me.equals("E、")) {
					multiOptionE.setVisibility(View.GONE);
				} else {
					multiOptionE.setVisibility(View.VISIBLE);
				}
				if (mf.equals("F、")) {
					multiOptionF.setVisibility(View.GONE);
				} else {
					multiOptionF.setVisibility(View.VISIBLE);
				}
				break;
		}

	}

	private boolean saveAnswer() {
		boolean return_value = false;
		try {
			if (!"未答".equals(answerx)) {
				Logger.e("我的答案：" + answerx);
				answers.add(answerx);
				return_value = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (muti_answers.size() > 0) {
				Logger.e("我的答案：" + answerx);
				//StringUtils.removeDuplicateWithOrder(muti_answers);
				answers.add(muti_answers.toString());
				return_value = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return return_value;
	}


	private void finishTest() {
		List<Question> errorLists = new ArrayList<Question>();
		int questionNum = questions.size();
		int correctNum = 0;
		try {
			for (int i = 0; i < answers.size(); i++) {
				if (answers.get(i).equals(questions.get(i).getAnswer())) {
					correctNum++;
				} else {
					//错误记录
					errorLists.add(questions.get(i));
				}
			}
			BmobUtils.updatemark(errorLists);
			Logger.i(questionNum + "/" + correctNum);
			grade = new Grade();
			grade.setPaperName(paperName);
			grade.setUsername(SafeExam.getStudent().getUsername());
			grade.setJoinTime(df.format(new Date()));
			grade.setGrade((correctNum * 100) / questionNum);
			BmobUtils.saveGrade(StartTestActivity.this, grade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Logger.i(answers.toString());
	}

	private void showDialog(final Grade grade) {
		AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);
		String msg = "本次成绩" + grade.getGrade() + "分";
		try {
			builder.setMessage(msg);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int whichButton) {
					Intent i = new Intent();
					i.putExtra("data", grade);
					setResult(1, i);
					//finish();
				}
			});
			builder.create().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showWarnDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);
		try {
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
		} catch (Exception e) {
			Log.e("错误", "对话框");
		}

	}

	private void showTimeOverDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(StartTestActivity.this);

		builder.setMessage("考试时间结束！");

		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	/*@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		paperName = getIntent().getStringExtra("paperName");
		examMode = getIntent().getStringExtra("examMode");
	}*/

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);

	}


	@OnClick(R.id.btn_back)
	public void onBtnBackClicked() {
		showWarnDialog();
	}

	@OnClick(R.id.judgeA)
	public void onJudgeAClicked() {
		answerx = "y";
	}

	@OnClick(R.id.judgeB)
	public void onJudgeBClicked() {
		answerx = "n";
	}

	@OnClick(R.id.single_option_A)
	public void onSingleOptionAClicked() {
		answerx = "A";
	}

	@OnClick(R.id.single_option_B)
	public void onSingleOptionBClicked() {
		answerx = "B";
	}

	@OnClick(R.id.single_option_C)
	public void onSingleOptionCClicked() {
		answerx = "C";
	}

	@OnClick(R.id.single_option_D)
	public void onSingleOptionDClicked() {
		answerx = "D";
	}

	@OnClick(R.id.multiOptionA)
	public void onMultiOptionAClicked() {
		if (multiOptionA.isChecked()) {
			muti_answers.add("A");
		}
	}

	@OnClick(R.id.multiOptionB)
	public void onMultiOptionBClicked() {
		if (multiOptionB.isChecked()) {
			muti_answers.add("B");
		}
	}

	@OnClick(R.id.multiOptionC)
	public void onMultiOptionCClicked() {
		if (multiOptionC.isChecked()) {
			muti_answers.add("C");
		}
	}

	@OnClick(R.id.multiOptionD)
	public void onMultiOptionDClicked() {
		if (multiOptionD.isChecked()) {
			muti_answers.add("D");
		}
	}

	@OnClick(R.id.multiOptionE)
	public void onMultiOptionEClicked() {
		if (multiOptionE.isChecked()) {
			muti_answers.add("E");
		}
	}

	@OnClick(R.id.multiOptionF)
	public void onMultiOptionFClicked() {
		if (multiOptionF.isChecked()) {
			muti_answers.add("F");
		}
	}

	@OnClick(R.id.seekBar)
	public void onSeekBarClicked() {
		tag = seekBar.getProgress();
		eaxmlist();
	}

	@OnClick(R.id.btn_mask_test)
	public void onBtnMaskTestClicked() {
		btnMaskTest.setText("标记");
	}

	@OnClick(R.id.btn_skip_test)
	public void onBtnSkipTestClicked() {
		try {
			if (tag == 0) {
				ToastUtils.showShort(this, "点击开始练习。");
				eaxmlist();
				seekBar.setMax(BmobUtils.ALL);
				test.setVisibility(View.VISIBLE);
				btnSkipTest.setText("交卷");

			} else {
				saveAnswer();
				finishTest();
				showDialog(grade);
				Intent i = new Intent();
				i.putExtra("data", grade);
				setResult(1, i);
				finish();
			}
		} catch (Exception e) {
			Log.e("错误", "");
		}
	}

	@OnClick(R.id.btn_finish_test)
	public void onBtnFinishTestClicked() {
		try {
			if (tag < 100) {
				if (saveAnswer()) {
					tag++;
					eaxmlist();
					seekBar.setProgress(tag);
				}
			}
		} catch (Exception e) {
			Log.e("错误", "向上手势");
		}

	}
}