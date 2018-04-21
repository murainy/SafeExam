// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LauncherActivity_ViewBinding implements Unbinder {
  private LauncherActivity target;

  private View view2131296721;

  private View view2131296754;

  private View view2131296727;

  private View view2131296736;

  private View view2131296676;

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
    view2131296721 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.next(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_set, "field 'ts' and method 'man'");
    target.ts = Utils.castView(view, R.id.tv_set, "field 'ts'", TextView.class);
    view2131296754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.man(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_git, "field 'tg' and method 'setup'");
    target.tg = Utils.castView(view, R.id.tv_git, "field 'tg'", TextView.class);
    view2131296727 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setup(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_let, "field 'tl' and method 'know'");
    target.tl = Utils.castView(view, R.id.tv_let, "field 'tl'", TextView.class);
    view2131296736 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.know(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.support_me, "field 'supportMe' and method 'onViewClicked'");
    target.supportMe = Utils.castView(view, R.id.support_me, "field 'supportMe'", TextView.class);
    view2131296676 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
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

    view2131296721.setOnClickListener(null);
    view2131296721 = null;
    view2131296754.setOnClickListener(null);
    view2131296754 = null;
    view2131296727.setOnClickListener(null);
    view2131296727 = null;
    view2131296736.setOnClickListener(null);
    view2131296736 = null;
    view2131296676.setOnClickListener(null);
    view2131296676 = null;
  }
}
