package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.app.ActivityManager;
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
import com.murainy.safeexam.Shareprefrence.OperateShareprefrence;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;


public class InformationActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.bnt_exit)
    Button bnt_exit;
    @BindView(R.id.info_end)
    Button bnt_end;
    @BindView(R.id.ci_view)
    CircleImageView ci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
	    Button lookGradeBtn = findViewById(R.id.btn_look_grade);
	    Button changePasswordBtn = findViewById(R.id.btn_change_password);
	    TextView classTV = findViewById(R.id.tv_class);
	    TextView numberTV = findViewById(R.id.tv_number);
	    TextView nameTV = findViewById(R.id.tv_name);
	    Button bindpBtn = findViewById(R.id.bnt_bindphone);
	    Button setheadBtn = findViewById(R.id.bnt_sethead);
	    nameTV.setText(SafeExam.getStudent().getName());
	    numberTV.setText(SafeExam.getStudent().getUsername());
	    classTV.setText(SafeExam.getStudent().getNick());
	    lookGradeBtn.setOnClickListener(this);
        changePasswordBtn.setOnClickListener(this);
        bindpBtn.setOnClickListener(this);
        setheadBtn.setOnClickListener(this);
        //String path = getFilesDir()+"/head.jpg";
        //ci.setImageBitmap(BitmapUtil.GetBitmap(path,100));
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("个人信息");
	    readImage(ci);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                Intent i0 = new Intent(InformationActivity.this, TabmanActivity.class);
                startActivity(i0);
                break;
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

	//如果本地有,就不需要再去联网去请求
	public void readImage(ImageView iv) {
		File file = new File(getFilesDir(), "head.jpg");
		if (file.exists()) {
			//存储--->内存
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bitmap);

		}


	}
    @OnClick(R.id.iv_left)
    public void back(View view) {
        finish();
    }
    @OnClick(R.id.bnt_exit)
    public void exit(View view) {
        BmobUser.logOut();
        OperateShareprefrence.deleteShareprefrence(this);
       }
    @OnClick(R.id.info_end)
    public void exitsys(View view) {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.killBackgroundProcesses(getPackageName());
        finish();
        //退出后台线程,以及销毁静态变量
        System.exit(0);
        }

    @OnClick(R.id.ci_view)
    public void largepic(View view) {
        Intent i5 = new Intent(InformationActivity.this, LargeImageViewActivity.class);
        startActivity(i5);
    }
}
