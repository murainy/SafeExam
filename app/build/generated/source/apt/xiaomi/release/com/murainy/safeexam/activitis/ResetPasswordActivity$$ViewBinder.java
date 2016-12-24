// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ResetPasswordActivity$$ViewBinder<T extends ResetPasswordActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624257, "field 'iv_left' and method 'back'");
    target.iv_left = finder.castView(view, 2131624257, "field 'iv_left'");
    unbinder.view2131624257 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624259, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131624259, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131624154, "field 'et_phone'");
    target.et_phone = finder.castView(view, 2131624154, "field 'et_phone'");
    view = finder.findRequiredView(source, 2131624155, "field 'et_code'");
    target.et_code = finder.castView(view, 2131624155, "field 'et_code'");
    view = finder.findRequiredView(source, 2131624156, "field 'btn_send' and method 'sendCode'");
    target.btn_send = finder.castView(view, 2131624156, "field 'btn_send'");
    unbinder.view2131624156 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendCode(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624157, "field 'et_pwd'");
    target.et_pwd = finder.castView(view, 2131624157, "field 'et_pwd'");
    view = finder.findRequiredView(source, 2131624158, "field 'btn_reset' and method 'reset'");
    target.btn_reset = finder.castView(view, 2131624158, "field 'btn_reset'");
    unbinder.view2131624158 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.reset(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ResetPasswordActivity> implements Unbinder {
    private T target;

    View view2131624257;

    View view2131624156;

    View view2131624158;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      view2131624257.setOnClickListener(null);
      target.iv_left = null;
      target.tv_title = null;
      target.et_phone = null;
      target.et_code = null;
      view2131624156.setOnClickListener(null);
      target.btn_send = null;
      target.et_pwd = null;
      view2131624158.setOnClickListener(null);
      target.btn_reset = null;
    }
  }
}
