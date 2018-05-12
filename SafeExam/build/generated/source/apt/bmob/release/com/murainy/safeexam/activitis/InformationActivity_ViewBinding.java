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

  private View view2131296384;

  private View view2131296322;

  private View view2131296316;

  private View view2131296319;

  private View view2131296329;

  private View view2131296515;

  private View view2131296317;

  private View view2131296498;

  @UiThread
  public InformationActivity_ViewBinding(InformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InformationActivity_ViewBinding(final InformationActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ci_view, "field 'ci' and method 'onViewClicked2'");
    target.ci = Utils.castView(view, R.id.ci_view, "field 'ci'", CircleImageView.class);
    view2131296384 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked2(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_change_password, "field 'btnChangePassword' and method 'onViewClicked1'");
    target.btnChangePassword = Utils.castView(view, R.id.btn_change_password, "field 'btnChangePassword'", Button.class);
    view2131296322 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked1(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bnt_bindphone, "field 'bntBindphone' and method 'onViewClicked1'");
    target.bntBindphone = Utils.castView(view, R.id.bnt_bindphone, "field 'bntBindphone'", Button.class);
    view2131296316 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked1(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bnt_sethead, "field 'bntSethead' and method 'onViewClicked1'");
    target.bntSethead = Utils.castView(view, R.id.bnt_sethead, "field 'bntSethead'", Button.class);
    view2131296319 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked1(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_look_grade, "field 'btnLookGrade' and method 'onViewClicked1'");
    target.btnLookGrade = Utils.castView(view, R.id.btn_look_grade, "field 'btnLookGrade'", Button.class);
    view2131296329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked1(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_left, "field 'ivLeft' and method 'onViewClicked2'");
    target.ivLeft = Utils.castView(view, R.id.iv_left, "field 'ivLeft'", ImageView.class);
    view2131296515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked2(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bnt_exit, "field 'bntExit' and method 'onViewClicked2'");
    target.bntExit = Utils.castView(view, R.id.bnt_exit, "field 'bntExit'", Button.class);
    view2131296317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked2(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.info_end, "field 'infoEnd' and method 'onViewClicked2'");
    target.infoEnd = Utils.castView(view, R.id.info_end, "field 'infoEnd'", Button.class);
    view2131296498 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked2(p0);
      }
    });
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvClass = Utils.findRequiredViewAsType(source, R.id.tv_class, "field 'tvClass'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.ci = null;
    target.btnChangePassword = null;
    target.bntBindphone = null;
    target.bntSethead = null;
    target.btnLookGrade = null;
    target.ivLeft = null;
    target.bntExit = null;
    target.infoEnd = null;
    target.tvName = null;
    target.tvNumber = null;
    target.tvClass = null;

    view2131296384.setOnClickListener(null);
    view2131296384 = null;
    view2131296322.setOnClickListener(null);
    view2131296322 = null;
    view2131296316.setOnClickListener(null);
    view2131296316 = null;
    view2131296319.setOnClickListener(null);
    view2131296319 = null;
    view2131296329.setOnClickListener(null);
    view2131296329 = null;
    view2131296515.setOnClickListener(null);
    view2131296515 = null;
    view2131296317.setOnClickListener(null);
    view2131296317 = null;
    view2131296498.setOnClickListener(null);
    view2131296498 = null;
  }
}
