package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.murainy.safeexam.R;
import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

/**
 * <pre>
 * @author zhy
 * http://blog.csdn.net/lmj623565791/article/details/43131133
 * </pre>
 */
public class CircleActivity extends Activity {

    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[]{"安全资讯 ", "安全知识", "顺序练习",
            "模拟考试", "错题练习", "设置", "关于"};
    private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal,
            R.drawable.home_mbank_6_normal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自已切换布局文件看效果
        setContentView(R.layout.activity_circle);
//		setContentView(R.layout.activity_main);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {
                switch (pos){
                    case 0:
                        startActivity(new Intent(CircleActivity.this, InformationActivity.class));
                        break;
                    case 1:
                startActivity(new Intent(CircleActivity.this, ErrorActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(CircleActivity.this, HeaderAndFooterUseActivity.class));
                        break;
                    case 3:
                    startActivity(new Intent(CircleActivity.this, AdminActivity.class));
                    break;
                    case 4:
                        startActivity(new Intent(CircleActivity.this, ExamActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(CircleActivity.this, ContentExamActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(CircleActivity.this, AboutActivity.class));
                        break;
            }
            }

            @Override
            public void itemCenterClick(View view) {
                startActivity(new Intent(CircleActivity.this, GridActivity.class));

            }
        });

    }

}
