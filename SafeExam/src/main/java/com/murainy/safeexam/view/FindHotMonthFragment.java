package com.murainy.safeexam.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murainy.safeexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Tenerify on 2016/6/19.
 */
public class FindHotMonthFragment extends Fragment {

	@BindView(R.id.rv2)
	RecyclerView rv2;
	Unbinder unbinder;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.hotmonth_fragment, container, false);
		unbinder = ButterKnife.bind(this, v);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@OnClick({R.id.rv2})
	public void onViewClicked(View view) {
		switch (view.getId()) {

			case R.id.rv2:
				break;
		}
	}
}
