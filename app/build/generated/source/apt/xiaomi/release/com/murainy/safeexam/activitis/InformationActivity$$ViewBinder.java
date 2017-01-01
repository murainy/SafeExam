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

public class InformationActivity$$ViewBinder<T extends InformationActivity> implements ViewBinder<T> {
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
        target.back(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689799, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131689799, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131689645, "field 'bnt_exit' and method 'exit'");
    target.bnt_exit = finder.castView(view, 2131689645, "field 'bnt_exit'");
    unbinder.view2131689645 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exit(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689646, "field 'bnt_end' and method 'exitsys'");
    target.bnt_end = finder.castView(view, 2131689646, "field 'bnt_end'");
    unbinder.view2131689646 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exitsys(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689634, "field 'ci100' and method 'largepic'");
    target.ci100 = finder.castView(view, 2131689634, "field 'ci100'");
    unbinder.view2131689634 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.largepic(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends InformationActivity> implements Unbinder {
    private T target;

    View view2131689797;

    View view2131689645;

    View view2131689646;

    View view2131689634;

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
      target.tv_title = null;
      view2131689645.setOnClickListener(null);
      target.bnt_exit = null;
      view2131689646.setOnClickListener(null);
      target.bnt_end = null;
      view2131689634.setOnClickListener(null);
      target.ci100 = null;
    }
  }
}
