// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296725;

  private View view2131296735;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.iv_left = Utils.findRequiredViewAsType(source, R.id.iv_left, "field 'iv_left'", ImageView.class);
    target.iv_right = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'iv_right'", ImageView.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_exam_time, "field 'tvExamTime' and method 'onViewClicked'");
    target.tvExamTime = Utils.castView(view, R.id.tv_exam_time, "field 'tvExamTime'", TextView.class);
    view2131296725 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.lvPaper = Utils.findRequiredViewAsType(source, R.id.lv_paper, "field 'lvPaper'", ListView.class);
    view = Utils.findRequiredView(source, R.id.tv_left, "method 'onViewClicked'");
    view2131296735 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_left = null;
    target.iv_right = null;
    target.tv_title = null;
    target.tvExamTime = null;
    target.lvPaper = null;

    view2131296725.setOnClickListener(null);
    view2131296725 = null;
    view2131296735.setOnClickListener(null);
    view2131296735 = null;
  }
}
