package com.murainy.safeexam.activitis;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 重置密码
 * @class ResetPasswordActivity
 * @author smile
 * @date 2015-6-5 上午11:23:44
 * 
 */
public class ResetPasswordActivity extends BaseActivity {

	MyCountTimer timer;
	@BindView(R.id.iv_left)
	ImageView iv_left;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.et_phone)
	EditText et_phone;
	@BindView(R.id.et_verify_code)
	EditText et_code;
	@BindView(R.id.btn_send)
	Button btn_send;
	@BindView(R.id.et_pwd)
	EditText et_pwd;
	@BindView(R.id.btn_reset)
	Button btn_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pwd);
		ButterKnife.bind(this);
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("重置密码");
	}

	class MyCountTimer extends CountDownTimer{  
		  
        public MyCountTimer(long millisInFuture, long countDownInterval) {  
            super(millisInFuture, countDownInterval);  
        }  
		@Override  
        public void onTick(long millisUntilFinished) {  
            btn_send.setText((millisUntilFinished / 1000) +"秒后重发");  
        }  
        @Override  
        public void onFinish() {  
        	btn_send.setText("重新发送验证码");  
        }  
    }  
	
	@OnClick(R.id.iv_left)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.btn_reset)
	public void reset(View view) {
		resetPwd();
	}

	@OnClick(R.id.btn_send)
	public void sendCode(View view) {
		requestSMSCode();
	}

	private void requestSMSCode() {
		String number = et_phone.getText().toString();
		if (!TextUtils.isEmpty(number)) {
			timer = new MyCountTimer(60000, 1000);   
			timer.start();   
			BmobSMS.requestSMSCode(number, "Safe2016",new QueryListener<Integer>(){

				@Override
				public void done(Integer smsId, BmobException ex) {
					// TODO Auto-generated method stub
					if (ex == null) {// 验证码发送成功
						// 用于查询本次短信发送详情
						Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
					}else{//如果验证码发送错误，可停止计时
						timer.cancel();
					}
				}
			});
		} else {
			Toast.makeText(getApplicationContext(),"请输入手机号码",Toast.LENGTH_SHORT).show();

		}
	}

	/**
	 */
	private void resetPwd() {
		final String code = et_code.getText().toString();
		final String pwd = et_pwd.getText().toString();
		if (TextUtils.isEmpty(code)) {
			Toast.makeText(getApplicationContext(),"验证码不能为空",Toast.LENGTH_SHORT).show();
						return;
		}
		if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();

			return;
		}
		final ProgressDialog progress = new ProgressDialog(ResetPasswordActivity.this);
		progress.setMessage("正在重置密码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// V3.3.9提供的重置密码功能，只需要输入验证码和新密码即可
		BmobUser.resetPasswordBySMSCode( code, pwd,  new UpdateListener() {
			@Override
			public void done(BmobException ex) {
				if(ex==null){
					toast("smile:"+"密码重置成功");
				}else{
					Log.i(TAG, "重置失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(timer!=null){
			timer.cancel();
		}
	}
}
