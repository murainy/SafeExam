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

public class UserBindPhoneActivity$$ViewBinder<T extends UserBindPhoneActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689797, "field 'iv_left' and method 'back'");
    target.iv_left = finder.castView(view, 2131689797, "field 'iv_left'");
    unbinder.view2131689797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
    view = finder.findRequiredView(source, 2131689612, "field 'et_number'");
    target.et_number = finder.castView(view, 2131689612, "field 'et_number'");
    view = finder.findRequiredView(source, 2131689613, "field 'et_input'");
    target.et_input = finder.castView(view, 2131689613, "field 'et_input'");
    view = finder.findRequiredView(source, 2131689799, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131689799, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131689614, "field 'tv_send' and method 'send'");
    target.tv_send = finder.castView(view, 2131689614, "field 'tv_send'");
    unbinder.view2131689614 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.send();
      }
    });
    view = finder.findRequiredView(source, 2131689615, "field 'tv_bind' and method 'bind'");
    target.tv_bind = finder.castView(view, 2131689615, "field 'tv_bind'");
    unbinder.view2131689615 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.bind();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends UserBindPhoneActivity> implements Unbinder {
    private T target;

    View view2131689797;

    View view2131689614;

    View view2131689615;

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
      view2131689797.setOnClickListener(null);
      target.iv_left = null;
      target.et_number = null;
      target.et_input = null;
      target.tv_title = null;
      view2131689614.setOnClickListener(null);
      target.tv_send = null;
      view2131689615.setOnClickListener(null);
      target.tv_bind = null;
    }
  }
}
