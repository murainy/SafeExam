package com.murainy.safeexam.adapter;

/**
 * Created by Tenerify on 2016/6/10.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.murainy.safeexam.R;
import com.murainy.safeexam.view.BaseViewHolder;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class MyGridAdapter extends BaseAdapter {
    private Context mContext;

    public String[] img_text = inittxt();
    public int[] status={R.drawable.unmask,R.drawable.mask,
            R.drawable.cerectb,R.drawable.cerect,
            R.drawable.err,R.drawable.errb};
    public int[] imgs = initimg();

    public MyGridAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    private String[] inittxt() {
        String[] ti = new String[100];
        for (int i = 0; i < 100; i++) {
            ti[i] = Integer.toString(i+1);
        }
        return ti;
    }

    private int[] initimg() {
        int[] ti = new int[100];
        for (int i = 0; i < 100; i++) {
            ti[i] = R.drawable.unmask;
        }
        return ti;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.status_griditem_layout, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_num);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_ok);
        iv.setBackgroundResource(imgs[position]);
        tv.setText(img_text[position]);
        return convertView;
    }

}
