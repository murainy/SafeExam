package com.murainy.safeexam.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.murainy.safeexam.R;

public class ToastUtils {
	private ToastUtils() {
	/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)

        {
	        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
	        View view = toast.getView();
	        view.setBackgroundResource(R.color.base_bg);
	        view.setMinimumWidth(88);
	        toast.setView(view);
	        toast.show();
        }

    }


    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
	        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
	        View view = toast.getView();
	        view.setBackgroundResource(R.color.base_bg);
	        view.setMinimumWidth(88);
	        toast.setView(view);
	        toast.show();
        }
    }


    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)

        {
	        Toast toast = Toast.makeText(context, message, duration);
	        View view = toast.getView();
	        view.setBackgroundResource(R.color.base_bg);
	        view.setMinimumWidth(88);
	        toast.setView(view);
	        toast.show();
        }
    }

	/**
	 * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
	 */
	public static void ToastMessage(Context context, String titles, String messages) {
		//LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
		LayoutInflater inflater = LayoutInflater.from(context);//调用Activity的getLayoutInflater()
		View view = inflater.inflate(R.layout.toast_style, null); //加載layout下的布局
		ImageView iv = view.findViewById(R.id.tvImageToast);
		iv.setImageResource(R.mipmap.ic_launcher);//显示的图片
		TextView title = view.findViewById(R.id.tvTitleToast);
		title.setText(titles); //toast的标题
		TextView text = view.findViewById(R.id.tvTextToast);
		text.setText(messages); //toast内容
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
		toast.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
		toast.setView(view); //添加视图文件
		toast.show();
	}

}