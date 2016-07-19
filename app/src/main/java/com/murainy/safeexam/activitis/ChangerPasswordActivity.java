package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Shareprefrence.OperateShareprefrence;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.beans.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;

public class ChangerPasswordActivity extends Activity implements View.OnClickListener {

    private Student user;
    private EditText oldPasswordEt;
    private EditText newPasswordEt;
    private EditText newPasswordAgainEt;
    private ProgressDialog waitDialog;
    private String oldPassword;
    private String newPassword;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sureChangePassowrd();
        }
    };
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_left)
    TextView tv_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        user = SafeExam.getStudent();

        oldPasswordEt = (EditText) findViewById(R.id.et_old_password);
        newPasswordEt = (EditText) findViewById(R.id.et_new_password);
        newPasswordAgainEt = (EditText) findViewById(R.id.et_new_password_again);
        Button sureChangeBtn = (Button) findViewById(R.id.btn_sure_change);
        sureChangeBtn.setOnClickListener(this);
        iv_left.setVisibility(View.VISIBLE);
        tv_left.setVisibility(View.VISIBLE);
        tv_title.setText("修改密码");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sure_change:
                isPrepare();
                break;
            case R.id.iv_left:
                back();
                break;
        }
    }
    @OnClick(R.id.iv_left)
    public void back() {
        finish();
    }
    public void isPrepare() {
        oldPassword = oldPasswordEt.getText().toString();
        newPassword = newPasswordEt.getText().toString();
        String newPasswordAgain = newPasswordAgainEt.getText().toString();
        if (newPassword.equals(newPasswordAgain)) {
            if (((ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null) {
                Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
            } else {
                checkOldPassword(oldPassword);
            }
        } else {
            Toast.makeText(this, "两次输入的密码不同ͬ", Toast.LENGTH_SHORT).show();
            newPasswordEt.setText("");
            newPasswordAgainEt.setText("");
        }
    }

    public void sureChangePassowrd() {
        BmobUser.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                Toast.makeText(ChangerPasswordActivity.this, "修改成功,请重新登录", Toast.LENGTH_SHORT).show();
                OperateShareprefrence.storeShareprefrence(ChangerPasswordActivity.this, user.getUsername(), newPasswordEt.getText().toString(),true);
                waitDialog.dismiss();
                Intent i = new Intent(ChangerPasswordActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }else {
                    Toast.makeText(ChangerPasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    waitDialog.dismiss();
                }
                }
        });
    }

    public void checkOldPassword(String oldPassword) {
        waitDialog = new ProgressDialog(this);
        waitDialog.setMessage("请稍等");
        waitDialog.setCancelable(false);
        waitDialog.show();
        BmobUser.loginByAccount(user.getUsername(), oldPassword, new LogInListener<Student>() {
            @Override
            public void done(Student student, BmobException e) {
                // TODO Auto-generated method stub
                if (student != null) {
                    Message msg = new Message();
                    handler.sendMessage(msg);
                } else {
                    Toast.makeText(ChangerPasswordActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    waitDialog.dismiss();
                }
            }
        });

    }
}
