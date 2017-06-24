package com.murainy.safeexam.activitis;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.beans.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 绑定手机号
 *
 * @author smile
 * @class UserBindPhoneActivity
 * @date 2015-6-5 下午3:08:53
 */
public class UserBindPhoneActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.et_input)
    EditText et_input;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_send)
    TextView tv_send;

    @BindView(R.id.tv_bind)
    TextView tv_bind;

    MyCountTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        ButterKnife.bind(this);
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("绑定手机号");
    }

    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_send.setText((millisUntilFinished / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            tv_send.setText("重新发送验证码");
        }
    }


    @OnClick(R.id.iv_left)
    public void back() {
        finish();
    }

    @OnClick(R.id.tv_send)
    public void send() {
        requestSMSCode();
    }

    @OnClick(R.id.tv_bind)
    public void bind() {
        verifyOrBind();
    }

    private void requestSMSCode() {
        String number = et_number.getText().toString();
        if (!TextUtils.isEmpty(number)) {
            timer = new MyCountTimer(60000, 1000);
            timer.start();
            BmobSMS.requestSMSCode(number, "Safe2016", new QueryListener<Integer>() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    // TODO Auto-generated method stub
                    if (ex == null) {// 验证码发送成功
                        // 用于查询本次短信发送详情
                        Toast.makeText(getApplicationContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
                    } else {//如果验证码发送错误，可停止计时
                        timer.cancel();
                    }
                }
            });
        } else {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        }
    }

    private void verifyOrBind() {
        final String phone = et_number.getText().toString();
        String code = et_input.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();

            return;
        }

        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();

            return;
        }
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("正在验证短信验证码...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        // V3.3.9提供的一键注册或登录方式，可传手机号码和验证码
        BmobSMS.verifySmsCode(phone, code, new UpdateListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                progress.dismiss();
                if (ex == null) {
                    Toast.makeText(getApplicationContext(), "手机号码已验证", Toast.LENGTH_SHORT).show();

                    bindMobilePhone(phone);
                } else {
                    Toast.makeText(getApplicationContext(), "验证失败：code=" + ex.getErrorCode() + "，错误描述：" + ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void bindMobilePhone(String phone) {
        //开发者在给用户绑定手机号码的时候需要提交两个字段的值：mobilePhoneNumber、mobilePhoneNumberVerified
        Student user = new Student();
        user.setMobilePhoneNumber(phone);
        user.setMobilePhoneNumberVerified(true);
        Student cur = BmobUser.getCurrentUser(Student.class);
        user.update(cur.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "手机号码绑定成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "手机号码绑定失败：" + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
