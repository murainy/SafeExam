package com.murainy.safeexam.activitis;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.murainy.safeexam.R;
import com.murainy.safeexam.Utils.Moment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPPToolbarActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 你自己项目里「可以不继承 BGAPPToolbarActivity」，我在这里继承 BGAPPToolbarActivity 只是为了方便写 Demo
 */
public class MomentAddActivity extends BGAPPToolbarActivity implements EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {
	private static final int REQUEST_CODE_PERMISSION_PHOTO_PICKER = 1;

	private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
	private static final int REQUEST_CODE_PHOTO_PREVIEW = 2;

	private static final String EXTRA_MOMENT = "EXTRA_MOMENT";

	// ==================================== 测试图片选择器 START ====================================
	/**
	 * 是否是单选「测试接口用的」
	 */
	private CheckBox mSingleChoiceCb;
	/**
	 * 是否具有拍照功能「测试接口用的」
	 */
	private CheckBox mTakePhotoCb;
	// ==================================== 测试图片选择器 END ====================================

	// ==================================== 测试拖拽排序九宫格图片控件 START ====================================
	/**
	 * 是否可编辑
	 */
	private CheckBox mEditableCb;
	/**
	 * 是否显示九图控件的加号按钮「测试接口用的」
	 */
	private CheckBox mPlusCb;
	/**
	 * 是否开启拖拽排序功能「测试接口用的」
	 */
	private CheckBox mSortableCb;
	/**
	 * 拖拽排序九宫格控件
	 */
	private BGASortableNinePhotoLayout mPhotosSnpl;
	// ==================================== 测试拖拽排序九宫格图片控件 END ====================================

	private EditText mContentEt;

	public static Moment getMoment(Intent intent) {
		return intent.getParcelableExtra(EXTRA_MOMENT);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_moment_add);
		mSingleChoiceCb = getViewById(R.id.cb_moment_add_single_choice);
		mTakePhotoCb = getViewById(R.id.cb_moment_add_take_photo);

		mEditableCb = getViewById(R.id.cb_moment_add_editable);
		mPlusCb = getViewById(R.id.cb_moment_add_plus);
		mSortableCb = getViewById(R.id.cb_moment_add_sortable);

		mContentEt = getViewById(R.id.et_moment_add_content);
		mPhotosSnpl = getViewById(R.id.snpl_moment_add_photos);
	}

	@Override
	protected void setListener() {
		mSingleChoiceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				if (checked) {
					mPhotosSnpl.setData(null);
					mPhotosSnpl.setMaxItemCount(1);
				} else {
					mPhotosSnpl.setMaxItemCount(9);
				}
			}
		});
		mEditableCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				mPhotosSnpl.setEditable(checked);
			}
		});
		mPlusCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				mPhotosSnpl.setPlusEnable(checked);
			}
		});
		mSortableCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				mPhotosSnpl.setSortable(checked);
			}
		});

		// 设置拖拽排序控件的代理
		mPhotosSnpl.setDelegate(this);
	}

	@Override
	protected void processLogic(Bundle savedInstanceState) {
		//setTitle("添加朋友圈");
		Drawable d = getResources().getDrawable(R.drawable.dodgerblue);
		getSupportActionBar().setBackgroundDrawable(d);
		getSupportActionBar().hide();
		mEditableCb.setChecked(mPhotosSnpl.isEditable());
		mPlusCb.setChecked(mPhotosSnpl.isPlusEnable());
		mSortableCb.setChecked(mPhotosSnpl.isSortable());
	}

	public void onClick(View v) {
		if (v.getId() == R.id.tv_moment_add_choice_photo) {
			choicePhotoWrapper();
		} else if (v.getId() == R.id.tv_moment_add_publish) {
			String content = mContentEt.getText().toString().trim();
			if (content.length() == 0 && mPhotosSnpl.getItemCount() == 0) {
				Toast.makeText(this, "必须填写这一刻的想法或选择照片！", Toast.LENGTH_SHORT).show();
				return;
			}

			Intent intent = new Intent();
			intent.putExtra(EXTRA_MOMENT, new Moment(mContentEt.getText().toString().trim(), mPhotosSnpl.getData()));
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	@Override
	public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
		choicePhotoWrapper();
	}

	@Override
	public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
		mPhotosSnpl.removeItem(position);
	}

	@Override
	public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
		startActivityForResult(BGAPhotoPickerPreviewActivity.newIntent(this, mPhotosSnpl.getMaxItemCount(), models, models, position, false), REQUEST_CODE_PHOTO_PREVIEW);
	}

	@AfterPermissionGranted(REQUEST_CODE_PERMISSION_PHOTO_PICKER)
	private void choicePhotoWrapper() {
		String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
		if (EasyPermissions.hasPermissions(this, perms)) {
			// 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
			File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

			startActivityForResult(BGAPhotoPickerActivity.newIntent(this, mTakePhotoCb.isChecked() ? takePhotoDir : null, mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount(), null, false), REQUEST_CODE_CHOOSE_PHOTO);
		} else {
			EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
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
		if (requestCode == REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
			Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
			if (mSingleChoiceCb.isChecked()) {
				mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedImages(data));
			} else {
				mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedImages(data));
			}
		} else if (requestCode == REQUEST_CODE_PHOTO_PREVIEW) {
			mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedImages(data));
		}
	}
}