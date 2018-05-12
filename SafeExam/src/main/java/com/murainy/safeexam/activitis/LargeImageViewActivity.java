package com.murainy.safeexam.activitis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.BitmapUtil;
import com.shizhefei.view.largeimage.LargeImageView;

import java.io.File;


public class LargeImageViewActivity extends AppCompatActivity {
	LargeImageView mLargeImageView;
	String url;
	Bitmap bm;
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == mLargeImageView) {
				showDialog();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_large_image_view);
		mLargeImageView = findViewById(R.id.id_largetImageview);
		mLargeImageView.setOnClickListener(onClickListener);
		try {
			Intent intent = getIntent();
			url = intent.getStringExtra("fromMain");
			RequestQueue mQueue = Volley.newRequestQueue(this);
			ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
				@Override
				public void onResponse(Bitmap response) {
					mLargeImageView.setImage(response);

				}
			}, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					mLargeImageView.setImage(R.drawable.fish);
				}
			});

			mQueue.add(imageRequest);
			/*InputStream inputStream = getAssets().open("me.jpg");
			mLargeImageView.setImage(new InputStreamBitmapDecoderFactory(inputStream));*/
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.NoBackGroundDialog);
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.list_item_longclicked_img, null);//获取自定义布局
		builder.setView(layout);
		builder.setIcon(R.mipmap.ic_launcher);//设置标题图标
		builder.setTitle(R.string.app_name);//设置标题内容
		builder.setMessage("默认保存到存储卡SafeEXam目录下：");//显示自定义布局内容

		//确认按钮
		builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				try {
					bm = loadBitmapFromView(mLargeImageView);
					File sdCard = Environment.getExternalStorageDirectory();
					File file = new File(sdCard + "Nba.jpg");
					BitmapUtil.saveBmpToSd(bm, file.getPath(), 100);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//取消
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		});
		final AlertDialog dlg = builder.create();
		dlg.show();
	}

	private Bitmap loadBitmapFromView(View v) {
		int w = v.getWidth();
		int h = v.getHeight();

		Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bmp);

		c.drawColor(Color.WHITE);
		/** 如果不设置canvas画布为白色，则生成透明 */
		//v.layout(0, 0, w, h);
		v.draw(c);

		return bmp;
	}
}