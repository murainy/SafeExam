package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.adapter.MyGridAdapter;
import com.murainy.safeexam.view.Mygridview;

public class GridActivity extends Activity {

    private Mygridview gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_grid_layout);
        initView();
    }

    private void initView() {
        gridview=(Mygridview) findViewById(R.id.my_gridview);
        gridview.setAdapter(new MyGridAdapter(this));
        //注册监听事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Log.e("TAG", "点击了"+Integer.toString(position) );
            }
        });
    }

}
