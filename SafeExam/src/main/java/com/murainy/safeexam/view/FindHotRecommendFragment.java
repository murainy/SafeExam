package com.murainy.safeexam.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.murainy.safeexam.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Tenerify on 2016/6/19.
 */
public class FindHotRecommendFragment extends Fragment {
	@BindView(R.id.rv3)
	RecyclerView rv3;
	@BindView(R.id.ll_recommend)
	LinearLayout llRecommend;
	private Unbinder unbinder;
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
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		//设置布局
		View view = inflater.inflate(R.layout.hotrecommend_fragment, container,
				false);


		unbinder = ButterKnife.bind(this, view);
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

		// 3.提交事务
		transaction.commit();
	}


	/**
	 * @param httpUrl :请求接口
	 * @param httpArg :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", "33833c1368f7c8e82f124aef86df2a0b");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@OnClick({R.id.rv3, R.id.ll_recommend})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.rv3:
				break;
			case R.id.ll_recommend:
				break;
		}
	}
}
