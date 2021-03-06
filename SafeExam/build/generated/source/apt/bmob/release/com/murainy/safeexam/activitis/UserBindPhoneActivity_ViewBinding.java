// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserBindPhoneActivity_ViewBinding implements Unbinder {
  private UserBindPhoneActivity target;

  private View view2131296515;

  private View view2131296783;

  private View view2131296752;

  @UiThread
  public UserBindPhoneActivity_ViewBinding(UserBindPhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserBindPhoneActivity_ViewBinding(final UserBindPhoneActivity target, View source) {
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
    target.et_number = Utils.findRequiredViewAsType(source, R.id.et_number, "field 'et_number'", EditText.class);
    target.et_input = Utils.findRequiredViewAsType(source, R.id.et_input, "field 'et_input'", EditText.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_send, "field 'tv_send' and method 'send'");
    target.tv_send = Utils.castView(view, R.id.tv_send, "field 'tv_send'", TextView.class);
    view2131296783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.send();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_bind, "field 'tv_bind' and method 'bind'");
    target.tv_bind = Utils.castView(view, R.id.tv_bind, "field 'tv_bind'", TextView.class);
    view2131296752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.bind();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UserBindPhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_left = null;
    target.et_number = null;
    target.et_input = null;
    target.tv_title = null;
    target.tv_send = null;
    target.tv_bind = null;

    view2131296515.setOnClickListener(null);
    view2131296515 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296752.setOnClickListener(null);
    view2131296752 = null;
  }
}
