package com.murainy.safeexam.activitis;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Moment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.photopicker.activity.BGAPPToolbarActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.imageloader.BGARVOnScrollListener;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 你自己项目里「可以不继承 BGAPPToolbarActivity」，我在这里继承 BGAPPToolbarActivity 只是为了方便写 Demo
 * BGAOnRVItemClickListener和BGAOnRVItemLongClickListener这两个接口是为了测试事件传递是否正确，你自己的项目里可以不实现这两个接口
 */
public class MomentListActivity extends BGAPPToolbarActivity implements EasyPermissions.PermissionCallbacks, BGANinePhotoLayout.Delegate, BGAOnRVItemClickListener, BGAOnRVItemLongClickListener {
	private static final int REQUEST_CODE_PERMISSION_PHOTO_PREVIEW = 1;

	private static final int REQUEST_CODE_ADD_MOMENT = 1;

	private RecyclerView mMomentRv;
	private MomentAdapter mMomentAdapter;

	/**
	 * 设置图片预览时是否具有保存图片功能「测试接口用的」
	 */
	private CheckBox mDownLoadableCb;

	private BGANinePhotoLayout mCurrentClickNpl;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_moment_list);
		mDownLoadableCb = getViewById(R.id.cb_moment_list_downloadable);
		mMomentRv = getViewById(R.id.rv_moment_list_moments);
	}

	@Override
	protected void setListener() {
		mMomentAdapter = new MomentAdapter(mMomentRv);
		mMomentAdapter.setOnRVItemClickListener(this);
		mMomentAdapter.setOnRVItemLongClickListener(this);

		mMomentRv.addOnScrollListener(new BGARVOnScrollListener(this));
	}

	@Override
	protected void processLogic(Bundle savedInstanceState) {
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		Drawable d = getResources().getDrawable(R.drawable.dodgerblue);
		getSupportActionBar().setBackgroundDrawable(d);
		getSupportActionBar().hide();
		//setTitle("朋友圈列表");
		mMomentRv.setLayoutManager(new LinearLayoutManager(this));
		mMomentRv.setAdapter(mMomentAdapter);

		addNetImageTestData();
	}

	/**
	 * 添加网络图片测试数据
	 */
	private void addNetImageTestData() {
		List<Moment> moments = new ArrayList<>();

		moments.add(new Moment("1张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/1.jpg", "http://oiqawgwly.bkt.clouddn.com/2.jpg"))));
		moments.add(new Moment("2张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/3.jpg", "http://oiqawgwly.bkt.clouddn.com/4.jpg"))));
		moments.add(new Moment("9张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/hjzgg.jpg", "http://oiqawgwly.bkt.clouddn.com/13.jpg", "http://oiqawgwly.bkt.clouddn.com/Image06.webp", "http://oiqawgwly.bkt.clouddn.com/Image13.webp", "http://oiqawgwly.bkt.clouddn.com/15.jpg", "http://oiqawgwly.bkt.clouddn.com/热浪垦丁.jpg", "http://oiqawgwly.bkt.clouddn.com/日光岩.jpg", "http://oiqawgwly.bkt.clouddn.com/清水断崖.jpg", "http://oiqawgwly.bkt.clouddn.com/白沙滩.jpg"))));
		moments.add(new Moment("5张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/郑州大学.jpg", "http://oiqawgwly.bkt.clouddn.com/14.jpg", "http://oiqawgwly.bkt.clouddn.com/加州.张雨石.jpg", "http://oiqawgwly.bkt.clouddn.com/Image14.webp", "http://oiqawgwly.bkt.clouddn.com/psbCADUM0AR.jpg"))));
		moments.add(new Moment("3张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/7.jpg", "http://oiqawgwly.bkt.clouddn.com/山丘.jpg", "http://oiqawgwly.bkt.clouddn.com/Image08.webp"))));
		moments.add(new Moment("8张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/8.jpg", "http://oiqawgwly.bkt.clouddn.com/48.jpg", "http://oiqawgwly.bkt.clouddn.com/36.jpg", "http://oiqawgwly.bkt.clouddn.com/Image15.webp", "http://oiqawgwly.bkt.clouddn.com/201109.jpg", "http://oiqawgwly.bkt.clouddn.com/152.jpg", "http://oiqawgwly.bkt.clouddn.com/坚强.jpg", "http://oiqawgwly.bkt.clouddn.com/船帆石.jpg"))));
		moments.add(new Moment("4张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/9.jpg", "http://oiqawgwly.bkt.clouddn.com/Image02.webp", "http://oiqawgwly.bkt.clouddn.com/Image10.webp", "http://oiqawgwly.bkt.clouddn.com/6700S.jpg"))));
		moments.add(new Moment("5张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/10.jpg", "http://oiqawgwly.bkt.clouddn.com/Image03.webp", "http://oiqawgwly.bkt.clouddn.com/金门大桥.jpg", "http://oiqawgwly.bkt.clouddn.com/573842281.jpg", "http://oiqawgwly.bkt.clouddn.com/H5Cmr.jpg"))));
		moments.add(new Moment("6张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/11.jpg", "http://oiqawgwly.bkt.clouddn.com/Image04.webp", "http://oiqawgwly.bkt.clouddn.com/Image12.webp", "http://oiqawgwly.bkt.clouddn.com/clusteramaryllis彼岸花.jpg", "http://oiqawgwly.bkt.clouddn.com/褚人伟.jpg", "http://oiqawgwly.bkt.clouddn.com/151.jpg"))));
		moments.add(new Moment("7张网络图片", new ArrayList<>(Arrays.asList("http://oiqawgwly.bkt.clouddn.com/facebook_s.jpg", "http://oiqawgwly.bkt.clouddn.com/25.jpg", "http://oiqawgwly.bkt.clouddn.com/private_aircraft_s.jpg", "http://oiqawgwly.bkt.clouddn.com/timg.jpg", "http://oiqawgwly.bkt.clouddn.com/yngkU.jpg", "http://oiqawgwly.bkt.clouddn.com/砂岛.jpg", "http://oiqawgwly.bkt.clouddn.com/平溪.jpg", "http://oiqawgwly.bkt.clouddn.com/jysperm.jpg"))));

		mMomentAdapter.setData(moments);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.tv_moment_list_add) {
			startActivityForResult(new Intent(this, MomentAddActivity.class), REQUEST_CODE_ADD_MOMENT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_ADD_MOMENT) {
			mMomentAdapter.addFirstItem(MomentAddActivity.getMoment(data));
			mMomentRv.smoothScrollToPosition(0);
		}
	}

	/**
	 * 图片预览，兼容6.0动态权限
	 */
	@AfterPermissionGranted(REQUEST_CODE_PERMISSION_PHOTO_PREVIEW)
	private void photoPreviewWrapper() {
		if (mCurrentClickNpl == null) {
			return;
		}

		// 保存图片的目录，改成你自己要保存图片的目录。如果不传递该参数的话就不会显示右上角的保存按钮
		File downloadDir = new File(Environment.getExternalStorageDirectory(), "MurainyDownload");

		String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
		if (EasyPermissions.hasPermissions(this, perms)) {
			if (mCurrentClickNpl.getItemCount() == 1) {
				// 预览单张图片

				startActivity(BGAPhotoPreviewActivity.newIntent(this, mDownLoadableCb.isChecked() ? downloadDir : null, mCurrentClickNpl.getCurrentClickItem()));
			} else if (mCurrentClickNpl.getItemCount() > 1) {
				// 预览多张图片

				startActivity(BGAPhotoPreviewActivity.newIntent(this, mDownLoadableCb.isChecked() ? downloadDir : null, mCurrentClickNpl.getData(), mCurrentClickNpl.getCurrentClickItemPosition()));
			}
		} else {
			EasyPermissions.requestPermissions(this, "图片预览需要以下权限:\n\n1.访问设备上的照片", REQUEST_CODE_PERMISSION_PHOTO_PREVIEW, perms);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
	}

	@Override
	public void onPermissionsGranted(int requestCode, List<String> perms) {
	}

	@Override
	public void onPermissionsDenied(int requestCode, List<String> perms) {
		if (requestCode == REQUEST_CODE_PERMISSION_PHOTO_PREVIEW) {
			Toast.makeText(this, "您拒绝了「图片预览」所需要的相关权限!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
		mCurrentClickNpl = ninePhotoLayout;
		photoPreviewWrapper();
	}

	@Override
	public void onRVItemClick(ViewGroup viewGroup, View view, int position) {
		Toast.makeText(this, "点击了item " + position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onRVItemLongClick(ViewGroup viewGroup, View view, int position) {
		Toast.makeText(this, "长按了item " + position, Toast.LENGTH_SHORT).show();
		return true;
	}

	private class MomentAdapter extends BGARecyclerViewAdapter<Moment> {

		public MomentAdapter(RecyclerView recyclerView) {
			super(recyclerView, R.layout.item_moment);
		}

		@Override
		protected void fillData(BGAViewHolderHelper helper, int position, Moment moment) {
			if (TextUtils.isEmpty(moment.content)) {
				helper.setVisibility(R.id.tv_item_moment_content, View.GONE);
			} else {
				helper.setVisibility(R.id.tv_item_moment_content, View.VISIBLE);
				helper.setText(R.id.tv_item_moment_content, moment.content);
			}

			BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.npl_item_moment_photos);
			ninePhotoLayout.setDelegate(MomentListActivity.this);
			ninePhotoLayout.setData(moment.photos);
		}
	}
}