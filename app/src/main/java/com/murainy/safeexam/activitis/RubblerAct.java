package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.view.Text_Rubbler;

/**
 * FileName: RubblerAct.java
 *
 * @author HTP
 * @version 1.00
 * @Desc 该类通过调用Text_Rubbler这个类将在Activity上显示一片刮一刮的区域，可以出发触摸事件
 * @Date 20140312
 */


public class RubblerAct extends Activity {
	// 刮开后文字显示
	private TextView tv_rubbler;
	// 得到刮一刮的内容
	private Sentence mSentence;
	// 下一张
	private TextView tv_next;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setContentView(new Rubble(this,"谢谢惠顾",new Rect(100, 200,
		// 300,250),2,1f,14));

		// /////////////////////////////////////////
		setContentView(R.layout.rubbler);
		// 设置的颜色必须要有透明度。
		((Text_Rubbler) findViewById(R.id.rubbler)).beginRubbler(0xFFFFFFFF, 20,
				1f);// 设置橡皮擦的宽度等
		mSentence = new Sentence();
		// 随机初始化文字
		tv_rubbler = (TextView) findViewById(R.id.rubbler);
		tv_rubbler.setTextSize(24);
		String str = mSentence.getSentence();
		tv_rubbler.setText(str);

		tv_next = (TextView) findViewById(R.id.tv_next);

		// 点击下一步
		tv_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = mSentence.getSentence();
				tv_rubbler.setText(str);
				((Text_Rubbler) findViewById(R.id.rubbler))// 初始化状态
						.beginRubbler(0xFFFFFFFF, 20, 1f);

			}
		});

	}


	/**
	 * 键盘事件，当按下back键的时候询问是否再按一次退出程序
	 */
	// 退出时间
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);

			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

} 
