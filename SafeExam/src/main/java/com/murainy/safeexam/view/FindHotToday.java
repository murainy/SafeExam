package com.murainy.safeexam.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murainy.safeexam.R;

/**
 * Created by Tenerify on 2016/6/19.
 */
public class FindHotToday extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hottoday_fragment, container, false);

        return v;
    }
}
