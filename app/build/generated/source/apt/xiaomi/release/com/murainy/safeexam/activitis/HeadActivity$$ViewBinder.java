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

public class HeadActivity$$ViewBinder<T extends HeadActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624219, "field 'iv_left' and method 'back'");
    target.iv_left = finder.castView(view, 2131624219, "field 'iv_left'");
    unbinder.view2131624219 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624221, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131624221, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131624099, "field 'upload' and method 'upload'");
    target.upload = finder.castView(view, 2131624099, "field 'upload'");
    unbinder.view2131624099 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.upload(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends HeadActivity> implements Unbinder {
    private T target;

    View view2131624219;

    View view2131624099;

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
      view2131624219.setOnClickListener(null);
      target.iv_left = null;
      target.tv_title = null;
      view2131624099.setOnClickListener(null);
      target.upload = null;
    }
  }
}
