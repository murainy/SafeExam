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

public class LauncherActivity_ViewBinding implements Unbinder {
  private LauncherActivity target;

  private View view2131296750;

  private View view2131296784;

  private View view2131296756;

  private View view2131296765;

  private View view2131296709;

  private View view2131296592;

  @UiThread
  public LauncherActivity_ViewBinding(LauncherActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LauncherActivity_ViewBinding(final LauncherActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_apple, "field 'ta' and method 'next'");
    target.ta = Utils.castView(view, R.id.tv_apple, "field 'ta'", TextView.class);
    view2131296750 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.next(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_set, "field 'ts' and method 'man'");
    target.ts = Utils.castView(view, R.id.tv_set, "field 'ts'", TextView.class);
    view2131296784 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.man(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_git, "field 'tg' and method 'setup'");
    target.tg = Utils.castView(view, R.id.tv_git, "field 'tg'", TextView.class);
    view2131296756 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setup(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_let, "field 'tl' and method 'know'");
    target.tl = Utils.castView(view, R.id.tv_let, "field 'tl'", TextView.class);
    view2131296765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.know(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.support_me, "field 'supportMe' and method 'onViewClicked'");
    target.supportMe = Utils.castView(view, R.id.support_me, "field 'supportMe'", TextView.class);
    view2131296709 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.news, "field 'NewMe' and method 'newsonClicked'");
    target.NewMe = Utils.castView(view, R.id.news, "field 'NewMe'", ImageView.class);
    view2131296592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.newsonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LauncherActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ta = null;
    target.ts = null;
    target.tg = null;
    target.tl = null;
    target.supportMe = null;
    target.NewMe = null;

    view2131296750.setOnClickListener(null);
    view2131296750 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296756.setOnClickListener(null);
    view2131296756 = null;
    view2131296765.setOnClickListener(null);
    view2131296765 = null;
    view2131296709.setOnClickListener(null);
    view2131296709 = null;
    view2131296592.setOnClickListener(null);
    view2131296592 = null;
  }
}
