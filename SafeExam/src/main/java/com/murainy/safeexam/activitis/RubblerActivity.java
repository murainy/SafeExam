package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.view.Text_Rubbler;

import java.util.Random;

/**
 * FileName: RubblerActivity.java
 *
 * @author HTP
 * @version 1.00
 * @Desc 该类通过调用Text_Rubbler这个类将在Activity上显示一片刮一刮的区域，可以出发触摸事件
 * @Date 20140312
 */


public class RubblerActivity extends Activity {
	// 刮开后文字显示
	private TextView tv_rubbler;
	// 得到刮一刮的内容
	private int times = 3;
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

		// 随机初始化文字
		tv_rubbler = (TextView) findViewById(R.id.rubbler);
		tv_rubbler.setTextSize(24);
		tv_rubbler.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		String str = getSentence();
		tv_rubbler.setText(str);

		tv_next = (TextView) findViewById(R.id.tv_next);

		// 点击下一步

		tv_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (times > 0) {
					times = times - 1;
					String str = getSentence();
					tv_rubbler.setText(str);
					((Text_Rubbler) findViewById(R.id.rubbler))// 初始化状态
							.beginRubbler(0xFFFFFFFF, 20, 1f);
				} else {
					tv_next.setText("明天再来");
				}
			}
		});


	}




	public String getSentence() {
		Random rand = new Random(System.currentTimeMillis());
		int s = 0;
		for (int i = 0; i < 10; i++) {
			s = s + rand.nextInt(1000);
		}
		//Toast.makeText(RubblerActivity.this,"Random: \n" + s, Toast.LENGTH_LONG).show();

		String jiang = String.valueOf(s);
		//Toast.makeText(RubblerActivity.this,"Random: " + jiang, Toast.LENGTH_LONG).show();
		String jj;
		switch (jiang.substring(1, 2)) {
			case "7":
				jj = "特等奖";
				break;
			case "1":
				jj = "一等奖";
				break;
			case "0":
				jj = "二等奖";
				break;
			case "5":
				jj = "三等奖";
				break;
			case "2":
				jj = "鼓励奖";
				break;
			default:
				jj = "谢谢惠顾";
				break;
		}
		return jj;
	}

} 
