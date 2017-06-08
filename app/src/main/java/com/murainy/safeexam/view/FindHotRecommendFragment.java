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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	            String httpUrl = "http://apis.baidu.com/txapi/weixin/wxhot";
	            String httpArg = "num=10&rand=1&word=%E7%9B%97%E5%A2%93%E7%AC%94%E8%AE%B0&page=1&src=%E4%BA%BA%E6%B0%91%E6%97%A5%E6%8A%A5";
	            String jsonResult = request(httpUrl, httpArg);
	            System.out.println(jsonResult);
	            tv1.setText(jsonResult);
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

}
