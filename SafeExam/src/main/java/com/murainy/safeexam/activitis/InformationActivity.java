package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class InformationActivity extends Activity {

	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.ci_view)
	CircleImageView ci;
	@BindView(R.id.btn_change_password)
	Button btnChangePassword;
	@BindView(R.id.bnt_bindphone)
	Button bntBindphone;
	@BindView(R.id.bnt_sethead)
	Button bntSethead;
	@BindView(R.id.btn_look_grade)
	Button btnLookGrade;
	@BindView(R.id.iv_left)
	ImageView ivLeft;
	@BindView(R.id.bnt_exit)
	Button bntExit;
	@BindView(R.id.info_end)
	Button infoEnd;
	@BindView(R.id.tv_name)
	TextView tvName;
	@BindView(R.id.tv_number)
	TextView tvNumber;
	@BindView(R.id.tv_class)
	TextView tvClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		ButterKnife.bind(this);

		tvName.setText(SafeExam.getStudent().getNick());
		tvNumber.setText(SafeExam.getStudent().getUsername());
		tvClass.setText(SafeExam.getStudent().getmClass());

		ivLeft.setVisibility(View.VISIBLE);
		tv_title.setText("个人信息");
		readImage(ci);
	}


	//如果本地有,就不需要再去联网去请求
	public void readImage(ImageView iv) {
		File file = new File(getFilesDir(), "head.jpg");
		if (file.exists()) {
			//存储--->内存
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bitmap);

		}


	}


	@OnClick({R.id.btn_change_password, R.id.bnt_bindphone, R.id.bnt_sethead, R.id.btn_look_grade})
	public void onViewClicked1(View view) {
		switch (view.getId()) {
			case R.id.btn_look_grade:
				Intent i1 = new Intent(InformationActivity.this, LookGradeActivity.class);
				startActivity(i1);
				break;
			case R.id.btn_change_password:
				Intent i2 = new Intent(InformationActivity.this, ChangerPasswordActivity.class);
				startActivity(i2);
				break;
			case R.id.bnt_bindphone:
				Intent i3 = new Intent(InformationActivity.this, UserBindPhoneActivity.class);
				startActivity(i3);
				break;
			case R.id.bnt_sethead:
				Intent i4 = new Intent(InformationActivity.this, SetHeadActivity.class);
				startActivity(i4);
				break;
		}
	}

	@OnClick({R.id.iv_left, R.id.bnt_exit, R.id.info_end, R.id.ci_view})
	public void onViewClicked2(View view) {
		switch (view.getId()) {
			case R.id.iv_left:
				Intent i0 = new Intent(InformationActivity.this, TabmanActivity.class);
				startActivity(i0);
				break;
			case R.id.bnt_exit:
				Intent i5 = new Intent(InformationActivity.this, ScrollingActivity.class);
				startActivity(i5);
				break;
			case R.id.info_end:
				Intent i6 = new Intent(InformationActivity.this, AddPaperActivity.class);
				startActivity(i6);
				break;
			case R.id.ci_view:
				Intent i7 = new Intent(InformationActivity.this, SystemGalleryActivity.class);
				startActivity(i7);
				break;
		}
	}
}
