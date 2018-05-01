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
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResetPasswordActivity_ViewBinding implements Unbinder {
  private ResetPasswordActivity target;

  private View view2131296499;

  private View view2131296330;

  private View view2131296328;

  @UiThread
  public ResetPasswordActivity_ViewBinding(ResetPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ResetPasswordActivity_ViewBinding(final ResetPasswordActivity target, View source) {
    this.target = target;

    View view;
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
    target.et_phone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'et_phone'", EditText.class);
    target.et_code = Utils.findRequiredViewAsType(source, R.id.et_verify_code, "field 'et_code'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_send, "field 'btn_send' and method 'sendCode'");
    target.btn_send = Utils.castView(view, R.id.btn_send, "field 'btn_send'", Button.class);
    view2131296330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendCode(p0);
      }
    });
    target.et_pwd = Utils.findRequiredViewAsType(source, R.id.et_pwd, "field 'et_pwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_reset, "field 'btn_reset' and method 'reset'");
    target.btn_reset = Utils.castView(view, R.id.btn_reset, "field 'btn_reset'", Button.class);
    view2131296328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.reset(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ResetPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_left = null;
    target.tv_title = null;
    target.et_phone = null;
    target.et_code = null;
    target.btn_send = null;
    target.et_pwd = null;
    target.btn_reset = null;

    view2131296499.setOnClickListener(null);
    view2131296499 = null;
    view2131296330.setOnClickListener(null);
    view2131296330 = null;
    view2131296328.setOnClickListener(null);
    view2131296328 = null;
  }
}
