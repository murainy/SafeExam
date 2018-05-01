package com.murainy.safeexam.activitis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.PhotoUtils;
import com.murainy.safeexam.Utils.ToastUtils;
import com.murainy.safeexam.beans.Student;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.annotations.NonNull;

public class SetHeadActivity extends AppCompatActivity implements OnClickListener {
	private static final int CODE_GALLERY_REQUEST = 0xa0;
	private static final int CODE_CAMERA_REQUEST = 0xa1;
	private static final int CODE_RESULT_REQUEST = 0xa2;
	private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
	private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
	private static final int OUTPUT_X = 480;
	private static final int OUTPUT_Y = 480;
	private static Boolean diy;
	private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
	private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/head.jpg");
	private Uri imageUri;
	private Uri cropImageUri;
	private String path;
	private File file;
	@BindView(R.id.btn_crop)
	Button btnTest;
	@BindView(R.id.circleImageView2)
	CircleImageView ci;//头像显示
	@BindView(R.id.iv_left)
	ImageView iv_left;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.btn_upload)
	Button upload;
	@BindView(R.id.btn_photos)
	Button btnPhotos;
	@BindView(R.id.btn_takephoto)
	Button btnTakephoto;
	@BindView(R.id.btn_okphoto)
	Button btnPhotosok;
	@BindView(R.id.et_nick)
	EditText et_nick;

	//位图操作
	public static Bitmap createImageThumbnail(String filePath) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);
		opts.inSampleSize = computeSampleSize(opts, -1, 256 * 256);
		opts.inJustDecodeBounds = false;
		try {
			bitmap = BitmapFactory.decodeFile(filePath, opts);
		} catch (Exception e) {
			System.out.print("找不到文件！");
		}
		return bitmap;
	}

	/**
	 * 检查设备是否存在SDCard的工具方法
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_head);
		ButterKnife.bind(this);
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("修改头像");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
			StrictMode.setVmPolicy(builder.build());
		}
		//初始化控件
		upload.setOnClickListener(this);
		btnPhotos.setOnClickListener(this);
		btnTakephoto.setOnClickListener(this);
		btnPhotosok.setOnClickListener(this);
		btnTest.setOnClickListener(this);
		btnTest.setText("裁剪");
		initView();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		switch (requestCode) {
			//调用系统相机申请拍照权限回调
			case CAMERA_PERMISSIONS_REQUEST_CODE: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					if (hasSdcard()) {
						imageUri = Uri.fromFile(fileUri);
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
							imageUri = FileProvider.getUriForFile(SetHeadActivity.this, "com.murainy.safeexam.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
						PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
					} else {
						ToastUtils.showShort(this, "设备没有SD卡！");
					}
				} else {

					ToastUtils.showShort(this, "请允许打开相机！！");
				}
				break;


			}
			//调用系统相册申请Sdcard权限回调
			case STORAGE_PERMISSIONS_REQUEST_CODE:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
				} else {

					ToastUtils.showShort(this, "请允许打操作SDCard！！");
				}
				break;
			default:
		}
	}

	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	private void initView() {
		try {

			file = new File(getFilesDir(), "head.jpg");
			path = file.getAbsolutePath();
			Bitmap bt = BitmapFactory.decodeFile(path);
			// Bitmap bt = createImageThumbnail(path );//从Sd中找头像，转换成Bitmap
			if (bt != null) {
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(bt);//转换成drawable
				ci.setImageDrawable(drawable);
			} else {
				/**
				 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
				 */
				diy = readImage(ci);//显示临时图像然后下载
				//Bmobfiledown();
				System.out.print("找不到头像!");
			}
		} catch (Exception e) {
			System.out.print("找不到路径！");
		}


		upload.setEnabled(readImage(ci));
	}

	//如果本地有,就不需要再去联网去请求
	public boolean readImage(ImageView iv) {
		Boolean yes_no = false;
		if (file.exists()) {
			//存储--->内存
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bitmap);
			yes_no = true;
		}
		return yes_no;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_takephoto://调用相机拍照
				autoObtainCameraPermission();
				break;
			case R.id.btn_photos://从相册里面取照片
				autoObtainStoragePermission();
				break;
			case R.id.btn_okphoto:
				Bmobfiledown();
				break;
			case R.id.btn_upload:
				uploadbmobfile();
				break;
			case R.id.btn_crop:
				Intent intent3 = new Intent(this, com.murainy.safeexam.activitis.RxActivity.class);
				startActivity(intent3);
				break;
			default:
				break;
		}
	}

	/**
	 * 根据URL获取Bitmap
	 */
	public Bitmap getHttpBitmap(String url) {
		Bitmap bitmap = null;
		URL myUrl;

		try {
			myUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			//把bitmap转成圆形
			bitmap = toRoundBitmap(bitmap);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回圆形bitmap
		return bitmap;
	}

	/**
	 * 把bitmap转成圆形
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int r = 0;
		//取最短边做边长
		if (width < height) {
			r = width;
		} else {
			r = height;
		}
		//构建一个bitmap
		Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		//new一个Canvas，在backgroundBmp上画图
		Canvas canvas = new Canvas(backgroundBm);
		Paint p = new Paint();
		//设置边缘光滑，去掉锯齿
		p.setAntiAlias(true);
		RectF rect = new RectF(0, 0, r, r);
		//通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
		//且都等于r/2时，画出来的圆角矩形就是圆形
		canvas.drawRoundRect(rect, r / 2, r / 2, p);
		//设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		//canvas将bitmap画在backgroundBmp上
		canvas.drawBitmap(bitmap, null, rect, p);
		return backgroundBm;
	}

	/*分片上传单个文件 、到bmob库*/
	public void uploadbmobfile() {

		String picPath = cropImageUri.getPath();
		final BmobFile bmobFile = new BmobFile(new File(picPath));
		bmobFile.uploadblock(new UploadFileListener() {

			@Override
			public void done(BmobException e) {
				if (e == null) {
					//bmobFile.getFileUrl()--返回的上传文件的完整地址
					String url = bmobFile.getFileUrl();
					//et_nick.setText(url);
					BmobUser student = BmobUser.getCurrentUser();
					Student stu = new Student();
					stu.setNick(et_nick.getText().toString());
					stu.setHeadurl(url);
					stu.setHeadpng(bmobFile);
					stu.setmClass("2016");
					stu.update(student.getObjectId(), new UpdateListener() {
						@Override
						public void done(BmobException e) {
							if (e == null) {
								Log.i("TAG", "更新用户头像信息成功。");
							} else {
								Log.i("TAG", "更新用户头像信息失败:");
							}
						}
					});
					toast("上传文件成功:" + url);
				} else {
					toast("上传文件失败：" + e.getMessage());
				}

			}

			@Override
			public void onProgress(Integer value) {
				// 返回的上传进度（百分比）
			}
		});
	}

	public void toast(String msg) {
		ToastUtils.showShort(this, msg);
	}

	/**
	 * 下载Bomb文件
	 */
	public void Bmobfiledown() {
		// 查询用
		BmobUser userinfo = BmobUser.getCurrentUser(Student.class);
		BmobQuery<Student> query = new BmobQuery<Student>();
		query.getObject(userinfo.getObjectId(), new QueryListener<Student>() {

			@Override
			public void done(Student object, BmobException e) {
				if (e == null) {
					BmobFile bmobfile = object.getHeadpng();
					if (bmobfile != null) {
						//调用bmobfile.download方法
						downloadFile(bmobfile);
					} else {
						toast("用户对像为空!");
					}

				} else {
					Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
				}
			}

		});

	}

	private void downloadFile(BmobFile file) {

		File saveFile = new File(path);
		file.download(saveFile, new DownloadFileListener() {

			@Override
			public void onStart() {
				toast("开始下载...");
			}

			@Override
			public void done(String savePath, BmobException e) {
				if (e == null) {
					toast("下载成功,保存路径:" + savePath);
				} else {
					toast("下载失败：" + e.getErrorCode() + "," + e.getMessage());

				}
			}

			@Override
			public void onProgress(Integer value, long newworkSpeed) {

				//toast("Bmob下载进度：" + value + "%，" + newworkSpeed / 1000 + "Kb/s");
			}

		});
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			//拍照完成回调
			case CODE_CAMERA_REQUEST:
				cropImageUri = Uri.fromFile(fileCropUri);
				PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
				break;
			//访问相册完成回调
			case CODE_GALLERY_REQUEST:
				if (hasSdcard()) {
					cropImageUri = Uri.fromFile(fileCropUri);
					Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
						newUri = FileProvider.getUriForFile(this, "com.murainy.safeexam.fileprovider", new File(newUri.getPath()));
					}
					PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
				} else {
					ToastUtils.showShort(this, "设备没有SD卡！");
				}
				break;
			case CODE_RESULT_REQUEST:
				Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
				if (bitmap != null) {
					ci.setImageBitmap(bitmap);
				}
				break;
			default:
				break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	@OnClick(R.id.iv_left)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.btn_upload)
	public void upload(View view) {
		uploadbmobfile();
	}

	/**
	 * 自动获取相机权限
	 */
	private void autoObtainCameraPermission() {

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
				ToastUtils.showShort(this, "您已经拒绝过一次");
			}
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
		} else {//有权限直接调用系统相机拍照
			if (hasSdcard()) {
				imageUri = Uri.fromFile(fileUri);
				//通过FileProvider创建一个content类型的Uri
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
					imageUri = FileProvider.getUriForFile(SetHeadActivity.this, "com.murainy.safeexam.fileprovider", fileUri);
				}
				PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
			} else {
				ToastUtils.showShort(this, "设备没有SD卡！");
			}
		}
	}

	/**
	 * 自动获取sdk权限
	 */

	private void autoObtainStoragePermission() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
		} else {
			PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
		}

	}

	private class MyTask extends AsyncTask<String, String, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... arg0) {

			String url = arg0[0];
			Bitmap bm = getHttpBitmap(url);
			return bm;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			ci.setImageBitmap(result);
		}
	}
}
