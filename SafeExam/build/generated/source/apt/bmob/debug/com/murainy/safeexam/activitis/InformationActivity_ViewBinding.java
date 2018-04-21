// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InformationActivity_ViewBinding implements Unbinder {
  private InformationActivity target;

  private View view2131296500;

  private View view2131296311;

  private View view2131296485;

  private View view2131296378;

  @UiThread
  public InformationActivity_ViewBinding(InformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InformationActivity_ViewBinding(final InformationActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_left, "field 'iv_left' and method 'back'");
    target.iv_left = Utils.castView(view, R.id.iv_left, "field 'iv_left'", ImageView.class);
    view2131296500 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back(p0);
      }
    });
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.bnt_exit, "field 'bnt_exit' and method 'exit'");
    target.bnt_exit = Utils.castView(view, R.id.bnt_exit, "field 'bnt_exit'", Button.class);
    view2131296311 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exit(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.info_end, "field 'bnt_end' and method 'exitsys'");
    target.bnt_end = Utils.castView(view, R.id.info_end, "field 'bnt_end'", Button.class);
    view2131296485 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exitsys(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ci_view, "field 'ci' and method 'largepic'");
    target.ci = Utils.castView(view, R.id.ci_view, "field 'ci'", CircleImageView.class);
    view2131296378 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.largepic(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    InformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_left = null;
    target.tv_title = null;
    target.bnt_exit = null;
    target.bnt_end = null;
    target.ci = null;

    view2131296500.setOnClickListener(null);
    view2131296500 = null;
    view2131296311.setOnClickListener(null);
    view2131296311 = null;
    view2131296485.setOnClickListener(null);
    view2131296485 = null;
    view2131296378.setOnClickListener(null);
    view2131296378 = null;
  }
}
