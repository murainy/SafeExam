package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import c.b.BP;
import c.b.PListener;
import c.b.QListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class PayActivity extends Activity {

	@BindView(R.id.ll)
	LinearLayout ll;
	@BindView(R.id.name)
	EditText name;
	@BindView(R.id.ci_view6)
	CircleImageView ciView6;
	@BindView(R.id.price)
	EditText price;
	@BindView(R.id.body)
	EditText body;
	@BindView(R.id.order)
	EditText order;
	@BindView(R.id.type)
	RadioGroup type;
	@BindView(R.id.alipay)
	RadioButton alipay;
	@BindView(R.id.wxpay)
	RadioButton wxpay;
	@BindView(R.id.qq)
	RadioButton qq;
	@BindView(R.id.query)
	RadioButton query;
	@BindView(R.id.tv)
	TextView tv;
	@BindView(R.id.go)
	Button go;

	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.button7)
	void onButton7Click() {
		price.setText("2");
	}

	@OnClick(R.id.button6)
	void onButton6Click() {
		price.setText("5");
	}

	@SuppressLint("SetTextI18n")
	@OnClick(R.id.button5)
	void onButton5Click() {
		price.setText("10");
	}

	@SuppressLint("SetTextI18n")
	@OnClick(R.id.button4)
	void onButton4Click() {
		price.setText("20");
	}

	@SuppressLint("SetTextI18n")
	@OnClick(R.id.button3)
	void onButton3Click() {
		price.setText("50");
	}

	@SuppressLint("SetTextI18n")
	@OnClick(R.id.button2)
	void onButton2Click() {
		price.setText("100");
	}


	@SuppressLint("SetTextI18n")
	@OnClick(R.id.go)
	void onGoClick() {
		switch (type.getCheckedRadioButtonId()) {
			case R.id.alipay:// 支付宝支付
				go.setText("支付宝支付");
				pay(BP.PayType_Alipay);
				break;
			case R.id.wxpay:// 微信支付
				go.setText("微信支付");
				pay(BP.PayType_Wechat);
				break;
			case R.id.qq: // QQ支付
				go.setText("QQ支付");
				pay(BP.PayType_QQ);
				break;
			case R.id.query: // 查询订单
				go.setText("订单查询");
				query();
				break;
		}
	}




	/**
	 * 调用支付
	 *
	 * @param payType 支付类型，BP.PayType_Alipay、BP.PayType_Wechat、BP.PayType_QQ
	 */
	void pay(final int payType) {
		showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
		final String name = getName();

		// 仍然可以通过这种方式支付，其中true为支付宝，false为微信
		// BP.pay(name, getBody(), getPrice(), false, new PListener());

		BP.pay(name, getBody(), getPrice(), payType, new PListener() {

			// 支付成功,如果金额较大请手动查询确认
			@Override
			public void succeed() {
				Toast.makeText(PayActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
				tv.append(name + "'s pay status is success\n\n");
				hideDialog();
			}

			// 无论成功与否,返回订单号
			@Override
			public void orderId(String orderId) {
				// 此处应该保存订单号,比如保存进数据库等,以便以后查询
				order.setText(orderId);
				tv.append(name + "'s orderid is " + orderId + "\n\n");
				showDialog("获取订单成功!请等待跳转到支付页面~");
			}

			// 支付失败,原因可能是用户中断支付操作,也可能是网络原因
			@Override
			public void fail(int code, String reason) {
				Toast.makeText(PayActivity.this, "支付中断!", Toast.LENGTH_SHORT).show();

				tv.append(name + "'s pay status is fail, error code is \n"
						+ code + " ,reason is " + reason + "\n\n");
				hideDialog();
			}
		});
	}

	// 执行订单查询
	void query() {
		showDialog("正在查询订单...");
		final String orderId = getOrder();

		BP.query(orderId, new QListener() {

			@Override
			public void succeed(String status) {
				Toast.makeText(PayActivity.this, "查询成功!该订单状态为 : " + status,
						Toast.LENGTH_SHORT).show();
				tv.append("pay status of" + orderId + " is " + status + "\n\n");
				hideDialog();
			}

			@Override
			public void fail(int code, String reason) {
				Toast.makeText(PayActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
				tv.append(String.format("查询订单失败, 错误代码 %d ,原因 \n%s\n\n", code, reason));
				hideDialog();
			}
		});
	}



	// 默认为0.02
	double getPrice() {
		double price = 0.02;
		try {
			price = Double.parseDouble(this.price.getText().toString());
		} catch (NumberFormatException e) {
		}
		return price;
	}

	// 商品详情(可不填)
	String getName() {
		return this.name.getText().toString();
	}

	// 商品详情(可不填)
	String getBody() {
		return this.body.getText().toString();
	}

	// 支付订单号(查询时必填)
	String getOrder() {
		return this.order.getText().toString();
	}

	void showDialog(String message) {
		try {
			if (dialog == null) {
				dialog = new ProgressDialog(this);
				dialog.setCancelable(true);
			}
			dialog.setMessage(message);
			dialog.show();
		} catch (Exception e) {
			// 在其他线程调用dialog会报错
		}
	}

	void hideDialog() {
		if (dialog != null && dialog.isShowing())
			try {
				dialog.dismiss();
			} catch (Exception e) {
			}
	}


}
