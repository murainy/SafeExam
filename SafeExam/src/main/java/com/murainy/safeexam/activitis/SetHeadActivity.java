package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.LogUtil;
import com.murainy.safeexam.beans.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

import static android.os.Environment.MEDIA_MOUNTED;

public class SetHeadActivity extends Activity implements OnClickListener {

	private static final int REQUEST_ORIGINAL = 3;// 请求原图信号标识
	private static final int REQUEST_CAPTURE = 2;
	private static final int REQUEST_PICTURE = 1;
	private Bitmap head;//头像Bitmap
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
	private String path = "";
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
	 * 正常开发中获取存储路径的方法
	 *
	 * @param context 上下文对象
	 * @param dir     存储目录
	 * @return
	 */
	public static String getFilePath(Context context, String dir) {
		String directoryPath = "";
		//判断SD卡是否可用
		try {
			if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				directoryPath = context.getExternalFilesDir(dir).getPath();
				//directoryPath =context.getExternalCacheDir().getAbsolutePath() ;
			} else {
				//没内存卡就存机身内存
				directoryPath = context.getFilesDir() + File.separator + dir;
				// directoryPath=context.getCacheDir()+File.separator+dir;
			}
		} catch
				(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			File file = new File(directoryPath);
			if (!file.exists()) {//判断文件目录是否存在
				file.mkdirs();
			}
			LogUtil.i("filePath====>" + directoryPath);

		}
		return directoryPath;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_head);
		ButterKnife.bind(this);
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("修改头像");
		//初始化控件
		upload.setOnClickListener(this);
		btnPhotos.setOnClickListener(this);
		btnTakephoto.setOnClickListener(this);
		btnPhotosok.setOnClickListener(this);
		btnTest.setOnClickListener(this);
		btnTest.setText("裁剪");
		try {
			path = getFilesDir().getPath();
		} catch (Exception e) {
			System.out.print("找不到路径！");
		}
		initView();
	}

	private void initView() {
		Bitmap bt = BitmapFactory.decodeFile(path + "/head.jpg");
		// Bitmap bt = createImageThumbnail(path );//从Sd中找头像，转换成Bitmap
		if (bt != null) {
			@SuppressWarnings("deprecation")
			Drawable drawable = new BitmapDrawable(bt);//转换成drawable
			ci.setImageDrawable(drawable);
		} else {
			/**
			 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
			 */
			//readImage();//显示临时图像然后下载
			//Bmobfiledown();
			System.out.print("找不到头像!");
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

	//如果本地有,就不需要再去联网去请求
	private boolean readImage() {
		File filesDir;
		if (Environment.getExternalStorageState().equals(MEDIA_MOUNTED)) {//判断sd卡是否挂载
			//路径1：storage/sdcard/Android/data/包名/files
			filesDir = getExternalFilesDir("");
		} else {//手机内部存储
			//路径：data/data/包名/files
			filesDir = getFilesDir();
		}
		File file = new File(filesDir, "/head.jpg");
		if (file.exists()) {
			//存储--->内存
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			ci.setImageBitmap(bitmap);
			return true;
		}
		return false;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_photos://从相册里面取照片
				Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent1, REQUEST_PICTURE);
				break;
			case R.id.btn_takephoto://调用相机拍照
				File file = new File(getFilesDir().getAbsolutePath()+  "/head.jpg");
				Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
					//添加权限
					intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					intent2.putExtra(MediaStore.EXTRA_OUTPUT,
							FileProvider.getUriForFile(this, "com.murainy.safeexam.fileprovider", file));
				} else {
					intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				}
				//startActivityForResult(intent2, REQUEST_ORIGINAL);//获取原图
				startActivityForResult(intent2, REQUEST_CAPTURE);//启动拍照
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


	/*分片上传单个文件 、到bmob库*/
	public void uploadbmobfile() {
		String picPath = path + "/head.jpg";
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	private class MyTask extends AsyncTask<String, String, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... arg0) {
			// TODO Auto-generated method stub
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

	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
		//允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
		File saveFile = new File(path, file.getFilename());
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
			case 1:
				if (resultCode == RESULT_OK) {
					cropPhoto(data.getData());//裁剪图片
				}

				break;
			case 2:
				if (resultCode == RESULT_OK) {
					File temp = new File(path + "/head.jpg");
					cropPhoto(FileProvider.getUriForFile(this, "com.murainy.safeexam.fileprovider", temp));//裁剪图片
				}

				break;
			case 3:
				if (data != null) {
					Bundle extras = data.getExtras();
					assert extras != null;
					head = extras.getParcelable("data");
					if (head != null) {
						/**
						 * 上传服务器代码
						 */
						uploadbmobfile();
						savePic(head);//保存在SD卡中
						ci.setImageBitmap(head);//用ImageView显示出来
					}
				}
				break;
			default:
				break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 调用系统的裁剪
	 *
	 * @param uri
	 */
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 168);
		intent.putExtra("outputY", 168);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, REQUEST_ORIGINAL);
	}

	private void savePic(Bitmap mBitmap) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(MEDIA_MOUNTED)) { // 检测sd是否可用
			return;
		}
		FileOutputStream b = null;
		File file = new File(path);
		file.mkdirs();// 创建文件夹
		String fileName = path + "/head.jpg";//图片名字
		Log.e("文件名：", fileName);
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭流
				assert b != null;
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}



	@OnClick(R.id.iv_left)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.btn_upload)
	public void upload(View view) {
		uploadbmobfile();
	}
}
