// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangerPasswordActivity_ViewBinding implements Unbinder {
  private ChangerPasswordActivity target;

  private View view2131296515;

  @UiThread
  public ChangerPasswordActivity_ViewBinding(ChangerPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangerPasswordActivity_ViewBinding(final ChangerPasswordActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_left, "field 'iv_left' and method 'back'");
    target.iv_left = Utils.castView(view, R.id.iv_left, "field 'iv_left'", ImageView.class);
    view2131296515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_left = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tv_left'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangerPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_left = null;
    target.tv_title = null;
    target.tv_left = null;

    view2131296515.setOnClickListener(null);
    view2131296515 = null;
  }
}
