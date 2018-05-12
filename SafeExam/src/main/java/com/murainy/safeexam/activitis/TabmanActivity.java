package com.murainy.safeexam.activitis;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.ToastUtils;

/**
 * 应用主界面
 *
 * @author Stone
 * @date 2014-4-24
 */
@SuppressWarnings("deprecation")
public class TabmanActivity extends TabActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "TabmanActivity";

    private TabHost tabHost;
    private LayoutInflater layoutInflater;


    String[] mTitle = new String[]{"模拟练习", "安全考试", "个人中心"};
    int[] mIcon = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
            R.drawable.home_mbank_5_normal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabman);
        initTabView();
    }

    public View getTabItemView(int i) {
        // TODO Auto-generated method stub  
        View view = layoutInflater.inflate(R.layout.tab_widget_item, null);
	    ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageResource(mIcon[i]);
	    TextView textView = view.findViewById(R.id.textview);
        textView.setText(mTitle[i]);
        return view;
    }

    public void initTabView() {

        tabHost = getTabHost();
        layoutInflater = LayoutInflater.from(this);
        TabHost.TabSpec spec;

        //模拟练习
        Intent intent1 = new Intent(this,CircleActivity.class );
        spec = tabHost.newTabSpec(mTitle[0]).setIndicator(getTabItemView(0)).setContent(intent1);
        tabHost.addTab(spec);

        //安全考试
        Intent intent2 = new Intent(this, MainActivity.class);
        spec = tabHost.newTabSpec(mTitle[1]).setIndicator(getTabItemView(1)).setContent(intent2);
        tabHost.addTab(spec);

        //个人信息
        Intent intent3 = new Intent(this, InformationActivity.class);
        spec = tabHost.newTabSpec(mTitle[2]).setIndicator(getTabItemView(2)).setContent(intent3);
	    tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }


    //准备退出
    private long firstTime = 0;//第一次返回按钮计时
    Boolean isExitByDoubleClick = true;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isExitByDoubleClick) {
                    long secondTime = System.currentTimeMillis();
                    if (secondTime - firstTime > 2000) {
	                    ToastUtils.showShort(this, "再按一次退出");
                        firstTime = secondTime;
                    } else {//完全退出
                        moveTaskToBack(false);//应用退到后台
                        System.exit(0);
                    }
                    return true;
                }
        }

        return super.onKeyUp(keyCode, event);
    }

	/*隐藏状态栏*/
   /* @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/
}
