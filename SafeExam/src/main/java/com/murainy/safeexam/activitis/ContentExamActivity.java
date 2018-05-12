package com.murainy.safeexam.activitis;

/**
 * Created by Tenerify on 2016/6/19.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.murainy.safeexam.R;
import com.murainy.safeexam.adapter.FindTabAdapter;
import com.murainy.safeexam.view.FindHotCollectionFragment;
import com.murainy.safeexam.view.FindHotMonthFragment;
import com.murainy.safeexam.view.FindHotRecommendFragment;
import com.murainy.safeexam.view.FindHotToday;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面
 */
public class ContentExamActivity extends FragmentActivity {

	private TabLayout tab_FindFragment_title;                            //定义TabLayout
	private ViewPager vp_FindFragment_pager;                             //定义viewPager
	private FragmentPagerAdapter fAdapter;                               //定义adapter

	private List<Fragment> list_fragment;                                //定义要装fragment的列表
	private List<String> list_title;                                     //tab名称列表

	private FindHotRecommendFragment hotRecommendFragment;              //热门推荐fragment
	private FindHotCollectionFragment hotCollectionFragment;            //热门收藏fragment
	private FindHotMonthFragment hotMonthFragment;                      //本月热榜fragment
	private FindHotToday hotToday;                                      //今日热榜fragment

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_eaxm);
		FragmentManager manager = getSupportFragmentManager();
		FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				// TODO Auto-generated method stub
				Logger.d("qijian", "backstack changed");
			}
		};
		manager.addOnBackStackChangedListener(listener);
		initControls();
	}

	/**
	 * 初始化各控件
	 */
	private void initControls() {

		tab_FindFragment_title = findViewById(R.id.tab_FindFragment_title);
		vp_FindFragment_pager = findViewById(R.id.vp_FindFragment_pager);

		//初始化各fragment
		hotRecommendFragment = new FindHotRecommendFragment();
		hotCollectionFragment = new FindHotCollectionFragment();
		hotMonthFragment = new FindHotMonthFragment();
		hotToday = new FindHotToday();

		//将fragment装进列表中
		list_fragment = new ArrayList<>();
		list_fragment.add(hotCollectionFragment);
		list_fragment.add(hotRecommendFragment);
		list_fragment.add(hotMonthFragment);
		list_fragment.add(hotToday);

		//将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
		list_title = new ArrayList<>();
		list_title.add("热门收藏");
		list_title.add("热门推荐");
		list_title.add("本月热榜");
		list_title.add("今日热榜");

		//设置TabLayout的模式
		tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
		//为TabLayout添加tab名称
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
		tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));

		fAdapter = new FindTabAdapter(this.getSupportFragmentManager(), list_fragment, list_title);

		//viewpager加载adapter
		vp_FindFragment_pager.setAdapter(fAdapter);
		//TabLayout加载viewpager
		tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);

		tab_FindFragment_title.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				vp_FindFragment_pager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		final TabLayout.TabLayoutOnPageChangeListener tablistener =
				new TabLayout.TabLayoutOnPageChangeListener(tab_FindFragment_title);
		vp_FindFragment_pager.addOnPageChangeListener(tablistener);

	}


}