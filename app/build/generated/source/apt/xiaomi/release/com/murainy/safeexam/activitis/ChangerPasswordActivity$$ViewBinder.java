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

public class ChangerPasswordActivity$$ViewBinder<T extends ChangerPasswordActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624218, "field 'iv_left' and method 'back'");
    target.iv_left = finder.castView(view, 2131624218, "field 'iv_left'");
    unbinder.view2131624218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
    view = finder.findRequiredView(source, 2131624220, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131624220, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131624219, "field 'tv_left'");
    target.tv_left = finder.castView(view, 2131624219, "field 'tv_left'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ChangerPasswordActivity> implements Unbinder {
    private T target;

    View view2131624218;

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
      view2131624218.setOnClickListener(null);
      target.iv_left = null;
      target.tv_title = null;
      target.tv_left = null;
    }
  }
}