package com.murainy.safeexam.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.murainy.safeexam.R;

/**
 * Created by Tenerify on 2016/6/19.
 */
public class FindHotRecommendFragment extends Fragment{
    private FragmentActivity activity;
    /**
     * 把activity造型为FragmentActivity
     */
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.hotrecommend_fragment, container,
                false);

        //查找控件并设置点击事件
        final TextView tv1 = (TextView) view.findViewById(R.id.textView1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //改变背景值
               tv1.setText("Fragment");
            }

        });

        //查找控件并设置点击事件
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //改变整个Fragment
                changeFragment();
            }

        });
        return view;
    }

    //改变整个Fragment
    private void changeFragment() {
        // 1.获取FragmentManager对象
        FragmentManager manager = getActivity()
                .getSupportFragmentManager();

        // 2.获取fragment的事务操作 代表：activity对fragment执行的多个改变的操作
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加替换或删除Fragment这时候就需要FragmentTransaction的布局动态文件
        // 执行替换
        //参数1:父元素的id值，参数2：替换新fragment对象
        transaction.replace(R.id.rdtj, new FindHotMonthFragment());

        // 3.提交事务
        transaction.commit();
    }



}
