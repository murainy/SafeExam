// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayActivity_ViewBinding implements Unbinder {
  private PayActivity target;

  private View view2131296457;

  private View view2131296344;

  private View view2131296343;

  private View view2131296342;

  private View view2131296340;

  private View view2131296338;

  private View view2131296337;

  @UiThread
  public PayActivity_ViewBinding(PayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayActivity_ViewBinding(final PayActivity target, View source) {
    this.target = target;

    View view;
    target.ll = Utils.findRequiredViewAsType(source, R.id.ll, "field 'll'", LinearLayout.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", EditText.class);
    target.ciView6 = Utils.findRequiredViewAsType(source, R.id.ci_view6, "field 'ciView6'", CircleImageView.class);
    target.price = Utils.findRequiredViewAsType(source, R.id.price, "field 'price'", EditText.class);
    target.body = Utils.findRequiredViewAsType(source, R.id.body, "field 'body'", EditText.class);
    target.order = Utils.findRequiredViewAsType(source, R.id.order, "field 'order'", EditText.class);
    target.type = Utils.findRequiredViewAsType(source, R.id.type, "field 'type'", RadioGroup.class);
    target.alipay = Utils.findRequiredViewAsType(source, R.id.alipay, "field 'alipay'", RadioButton.class);
    target.wxpay = Utils.findRequiredViewAsType(source, R.id.wxpay, "field 'wxpay'", RadioButton.class);
    target.qq = Utils.findRequiredViewAsType(source, R.id.qq, "field 'qq'", RadioButton.class);
    target.query = Utils.findRequiredViewAsType(source, R.id.query, "field 'query'", RadioButton.class);
    target.tv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.go, "field 'go' and method 'onGoClick'");
    target.go = Utils.castView(view, R.id.go, "field 'go'", Button.class);
    view2131296457 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGoClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.button7, "method 'onButton7Click'");
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton7Click();
      }
    });
    view = Utils.findRequiredView(source, R.id.button6, "method 'onButton6Click'");
    view2131296343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton6Click();
      }
    });
    view = Utils.findRequiredView(source, R.id.button5, "method 'onButton5Click'");
    view2131296342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton5Click();
      }
    });
    view = Utils.findRequiredView(source, R.id.button4, "method 'onButton4Click'");
    view2131296340 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton4Click();
      }
    });
    view = Utils.findRequiredView(source, R.id.button3, "method 'onButton3Click'");
    view2131296338 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton3Click();
      }
    });
    view = Utils.findRequiredView(source, R.id.button2, "method 'onButton2Click'");
    view2131296337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton2Click();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll = null;
    target.name = null;
    target.ciView6 = null;
    target.price = null;
    target.body = null;
    target.order = null;
    target.type = null;
    target.alipay = null;
    target.wxpay = null;
    target.qq = null;
    target.query = null;
    target.tv = null;
    target.go = null;

    view2131296457.setOnClickListener(null);
    view2131296457 = null;
    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296343.setOnClickListener(null);
    view2131296343 = null;
    view2131296342.setOnClickListener(null);
    view2131296342 = null;
    view2131296340.setOnClickListener(null);
    view2131296340 = null;
    view2131296338.setOnClickListener(null);
    view2131296338 = null;
    view2131296337.setOnClickListener(null);
    view2131296337 = null;
  }
}
