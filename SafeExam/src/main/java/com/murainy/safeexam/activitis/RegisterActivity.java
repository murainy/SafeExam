package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Student;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by murainy on 2015/12/13.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText inputUserName;
    private EditText inputPassword;
    private EditText inputName;
    private EditText inputClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUserName = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        inputName = (EditText) findViewById(R.id.register_name);
        inputClass = (EditText) findViewById(R.id.register_class);
        Button registerBtn = (Button) findViewById(R.id.register);

        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.register:

                if (checkInput()) {
                    String username = inputUserName.getText().toString();
                    String password = inputPassword.getText().toString();
                    String name = inputName.getText().toString();
                    String mClass = inputClass.getText().toString();
                    final ProgressDialog waitDialog = new ProgressDialog(RegisterActivity.this);

                    waitDialog.setMessage("注册中");
                    waitDialog.setCancelable(false);
                    waitDialog.show();

                    Student student = new Student();
                    student.setUsername(username);
                    student.setPassword(password);
                    student.setName(name);
                    student.setmClass(mClass);

                    student.signUp(new SaveListener<Student>() {
                        @Override
                        public void done(Student s, BmobException e) {
                            if(e==null){

                                Toast.makeText(RegisterActivity.this, "注册成功:" +s.toString(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "请补全信息", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private boolean checkInput() {
        return !inputUserName.getText().toString().equals("") && !inputPassword.getText().toString().equals("")
                && !inputName.getText().toString().equals("") && !inputClass.getText().toString().equals("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}