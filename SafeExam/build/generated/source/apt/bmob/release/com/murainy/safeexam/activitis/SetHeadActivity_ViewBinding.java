// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetHeadActivity_ViewBinding implements Unbinder {
  private SetHeadActivity target;

  private View view2131296499;

  private View view2131296334;

  @UiThread
  public SetHeadActivity_ViewBinding(SetHeadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetHeadActivity_ViewBinding(final SetHeadActivity target, View source) {
    this.target = target;

    View view;
    target.btnTest = Utils.findRequiredViewAsType(source, R.id.btn_crop, "field 'btnTest'", Button.class);
    target.ci = Utils.findRequiredViewAsType(source, R.id.circleImageView2, "field 'ci'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_left, "field 'iv_left' and method 'back'");
    target.iv_left = Utils.castView(view, R.id.iv_left, "field 'iv_left'", ImageView.class);
    view2131296499 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back(p0);
      }
    });
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_upload, "field 'upload' and method 'upload'");
    target.upload = Utils.castView(view, R.id.btn_upload, "field 'upload'", Button.class);
    view2131296334 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.upload(p0);
      }
    });
    target.btnPhotos = Utils.findRequiredViewAsType(source, R.id.btn_photos, "field 'btnPhotos'", Button.class);
    target.btnTakephoto = Utils.findRequiredViewAsType(source, R.id.btn_takephoto, "field 'btnTakephoto'", Button.class);
    target.btnPhotosok = Utils.findRequiredViewAsType(source, R.id.btn_okphoto, "field 'btnPhotosok'", Button.class);
    target.et_nick = Utils.findRequiredViewAsType(source, R.id.et_nick, "field 'et_nick'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SetHeadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnTest = null;
    target.ci = null;
    target.iv_left = null;
    target.tv_title = null;
    target.upload = null;
    target.btnPhotos = null;
    target.btnTakephoto = null;
    target.btnPhotosok = null;
    target.et_nick = null;

    view2131296499.setOnClickListener(null);
    view2131296499 = null;
    view2131296334.setOnClickListener(null);
    view2131296334 = null;
  }
}
