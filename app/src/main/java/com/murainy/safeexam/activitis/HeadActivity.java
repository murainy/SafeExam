package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;
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
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;

@SuppressLint("SdCardPath")//忽略警告
public class HeadActivity extends Activity implements OnClickListener {
    private Context mcontext = HeadActivity.this;//上下文
    private ImageView ivHead;//头像显示
    private Button btnTakephoto;//拍照
    private Button btnPhotos;//相册
    private Button btnPhotosok;//相册
    private Bitmap head;//头像Bitmap
    private EditText et_nick;
    private static String path = Environment.getExternalStorageDirectory().getPath();//sd路径
    private static String pathbak = Environment.getDataDirectory() + "tmp";//sd路径
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("修改头像");
        initView();
    }

    private void initView() {
        //初始化控件
        btnPhotos = (Button) findViewById(R.id.btn_photos);
        btnPhotosok = (Button) findViewById(R.id.btn_okphoto);
        btnTakephoto = (Button) findViewById(R.id.btn_takephoto);
        et_nick = (EditText) findViewById(R.id.et_nick);
        btnPhotos.setOnClickListener(this);
        btnTakephoto.setOnClickListener(this);
        btnPhotosok.setOnClickListener(this);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        Bitmap bt = BitmapFactory.decodeFile(path + "/head.jpg");
        // Bitmap bt = createImageThumbnail(path );//从Sd中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            ivHead.setImageDrawable(drawable);
        } else {
          /*  *
             *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
*/
            Bmobfiledown();
        }

    }

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
            // TODO: handle exception
        }
        return bitmap;
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photos://从相册里面取照片
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            case R.id.btn_takephoto://调用相机拍照
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path,
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
                break;
            case R.id.btn_okphoto:
                Bmobfiledown();
                break;
            default:
                break;
        }
    }

    /**
     * 上传文件到bmob后台
     */
    public void uploadbmobfile() {

        String nick = et_nick.getText().toString();
        BmobFile bmobFile = new BmobFile("head", null, new File(path).toString());
        String fileurl = bmobFile.getFileUrl();
        BmobUser student = BmobUser.getCurrentUser();
        Student stu = new Student();
        stu.setNick(nick);
        stu.setHeadurl(fileurl);
        stu.setHeadpng(bmobFile);
        stu.update(student.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    // TODO Auto-generated method stub
                    Log.i("TAG", "更新用户头像信息成功。");
                } else {
                    Log.i("TAG", "更新用户头像信息失败:");
                }
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
            ivHead.setImageBitmap(result);
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 下载Bomb文件
     */
    public void Bmobfiledown() {

        BmobUser userinfo = BmobUser.getCurrentUser(Student.class);
        Student student = new Student();
        BmobFile bmobfile = student.getHeadpng();
        if (bmobfile != null) {
            //调用bmobfile.download方法
            downloadFile(bmobfile);
        }
    }

    private void downloadFile(BmobFile file) {
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        File saveFile = new File(pathbak, file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
                toast("开始下载...");
            }

            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){
                    toast("下载成功,保存路径:"+savePath);
                }else{
                    toast("下载失败："+e.getErrorCode()+","+e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
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
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        uploadbmobfile();
                        setPicToView(head);//保存在SD卡中
                        ivHead.setImageBitmap(head);//用ImageView显示出来
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
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 168);
        intent.putExtra("outputY", 168);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
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
}
