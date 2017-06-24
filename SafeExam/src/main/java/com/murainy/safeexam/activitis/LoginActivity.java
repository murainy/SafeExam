package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.SafeExam;
import com.murainy.safeexam.Shareprefrence.OperateShareprefrence;
import com.murainy.safeexam.beans.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText inputUsername;
    private EditText inputPassword;
    private boolean auto=true;
    private ProgressDialog waitDialog;

    @BindView(R.id.checkBox)
    CheckBox  rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inputUsername = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.login);
        TextView register = (TextView) findViewById(R.id.register);
        TextView forpass = (TextView) findViewById(R.id.tv_forpass);
        loginBtn.setOnClickListener(this);
        register.setOnClickListener(this);
        forpass.setOnClickListener(this);

    }

    private boolean prepareForLogin() {
        if (((ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null) {
            Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (inputUsername.length() == 0) {
            inputUsername.setError("请输入账号");
            inputUsername.requestFocus();
            return true;
        }

        if (inputPassword.length() == 0) {
            inputPassword.setError("请输入密码");
            inputPassword.requestFocus();
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (prepareForLogin()) {
                    return;
                }
                // if the data has ready
                final String username = inputUsername.getText().toString();
                final String password = inputPassword.getText().toString();
                waitDialog = new ProgressDialog(this);
                waitDialog.setMessage("登录中");
                waitDialog.setCancelable(false);
                waitDialog.show();
                BmobUser.loginByAccount( username, password, new LogInListener<Student>() {
                    @Override
                    public void done(Student student, BmobException e) {
                        // TODO Auto-generated method stub
                        if (student != null) {
                            OperateShareprefrence.storeShareprefrence(LoginActivity.this, username, inputPassword.getText().toString(),auto);
                            SafeExam.setStudent(student);
                            if (student.getIdentity() == 1) {
                                startActivity(new Intent(LoginActivity.this, TabmanActivity.class));
                            }
                            if (student.getIdentity() == 2) {
                                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                            }
                            finish();
                            waitDialog.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                            waitDialog.dismiss();
                        }
                    }
                });
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_forpass:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;

        }
    }

    @OnClick(R.id.checkBox)
    //保存设置
    public void remb() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("name", "李明");
        editor.putBoolean("auto_login", rem.isChecked());
        editor.putInt("age", 14);
        editor.apply();//editor.commit();//提交修改
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        //getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 1);
    }
}
