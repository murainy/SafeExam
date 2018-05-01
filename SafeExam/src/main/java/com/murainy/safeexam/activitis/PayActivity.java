package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PayActivity extends Activity {
	@BindView(R.id.ll)
	LinearLayout ll;
	@BindView(R.id.name)
	TextView name;
	@BindView(R.id.ci_view6)
	ImageView ciView6;
	@BindView(R.id.tv)
	TextView tv;
	@BindView(R.id.go)
	Button go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		ButterKnife.bind(this);

	}


	@SuppressLint("SetTextI18n")
	@OnClick(R.id.go)
	void onGoClick() {
		sharePhotoToWX();
		}

	public void sharePhotoToWX() {
		if (!uninstallSoftware("com.tencent.mm")) {
			Toast.makeText(this, "微信没有安装！", Toast.LENGTH_SHORT).show();
			return;
		}
		viewSaveToImage(ciView6);
		Intent intent = new Intent();
		try {
			File file = new File(getFilesDir(), "code.png");
			Uri uri = FileProvider.getUriForFile(PayActivity.this, "com.murainy.safeexam.fileprovider", file);

			ComponentName componentName = new ComponentName("com.tencent.mm",
					"com.tencent.mm.ui.tools.ShareToTimeLineUI");
			intent.setComponent(componentName);
			intent.setAction("android.intent.action.SEND");
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "支持!支持!支持!");
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_STREAM, uri);
			startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(this, "分享微信失败！", Toast.LENGTH_SHORT).show();
		}


	}

	private boolean uninstallSoftware(String packageName) {
		PackageManager packageManager = this.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
			if (packageInfo != null) {
				return true;
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void viewSaveToImage(View view) {
		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		view.setDrawingCacheBackgroundColor(Color.WHITE);

		// 把一个View转换成图片
		Bitmap cachebmp = loadBitmapFromView(view);

		FileOutputStream fos;
		String imagePath = "";
		try {
			// 判断手机设备是否有SD卡
			boolean isHasSDCard = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED);
			if (isHasSDCard) {
				// SD卡根目录
				//File sdRoot = Environment.getExternalStorageDirectory();
				//File file = new File(sdRoot, Calendar.getInstance().getTimeInMillis()+".png");
				File sdRoot = getFilesDir();
				File file = new File(sdRoot, "code.png");
				fos = new FileOutputStream(file);
				imagePath = file.getAbsolutePath();
			} else
				throw new Exception("创建文件失败!");

			cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos);

			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.e("imagePath=" + imagePath);

		view.destroyDrawingCache();
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
