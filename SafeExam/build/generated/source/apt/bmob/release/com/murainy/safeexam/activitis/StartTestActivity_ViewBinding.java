// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.murainy.safeexam.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StartTestActivity_ViewBinding implements Unbinder {
  private StartTestActivity target;

  private View view2131296325;

  private View view2131296331;

  private View view2131296321;

  private View view2131296317;

  private View view2131296642;

  private View view2131296506;

  private View view2131296507;

  private View view2131296652;

  private View view2131296653;

  private View view2131296654;

  private View view2131296655;

  private View view2131296551;

  private View view2131296552;

  private View view2131296553;

  private View view2131296554;

  private View view2131296555;

  private View view2131296556;

  @UiThread
  public StartTestActivity_ViewBinding(StartTestActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StartTestActivity_ViewBinding(final StartTestActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_mask_test, "field 'btnMaskTest' and method 'onBtnMaskTestClicked'");
    target.btnMaskTest = Utils.castView(view, R.id.btn_mask_test, "field 'btnMaskTest'", Button.class);
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnMaskTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_skip_test, "field 'btnSkipTest' and method 'onBtnSkipTestClicked'");
    target.btnSkipTest = Utils.castView(view, R.id.btn_skip_test, "field 'btnSkipTest'", Button.class);
    view2131296331 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnSkipTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_finish_test, "field 'btnFinishTest' and method 'onBtnFinishTestClicked'");
    target.btnFinishTest = Utils.castView(view, R.id.btn_finish_test, "field 'btnFinishTest'", Button.class);
    view2131296321 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnFinishTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "field 'btnBack' and method 'onBtnBackClicked'");
    target.btnBack = Utils.castView(view, R.id.btn_back, "field 'btnBack'", ImageButton.class);
    view2131296317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnBackClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.seekBar, "field 'seekBar' and method 'onSeekBarClicked'");
    target.seekBar = Utils.castView(view, R.id.seekBar, "field 'seekBar'", SeekBar.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSeekBarClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.judgeA, "field 'judgeA' and method 'onJudgeAClicked'");
    target.judgeA = Utils.castView(view, R.id.judgeA, "field 'judgeA'", RadioButton.class);
    view2131296506 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onJudgeAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.judgeB, "field 'judgeB' and method 'onJudgeBClicked'");
    target.judgeB = Utils.castView(view, R.id.judgeB, "field 'judgeB'", RadioButton.class);
    view2131296507 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onJudgeBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_A, "field 'singleOptionA' and method 'onSingleOptionAClicked'");
    target.singleOptionA = Utils.castView(view, R.id.single_option_A, "field 'singleOptionA'", RadioButton.class);
    view2131296652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_B, "field 'singleOptionB' and method 'onSingleOptionBClicked'");
    target.singleOptionB = Utils.castView(view, R.id.single_option_B, "field 'singleOptionB'", RadioButton.class);
    view2131296653 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_C, "field 'singleOptionC' and method 'onSingleOptionCClicked'");
    target.singleOptionC = Utils.castView(view, R.id.single_option_C, "field 'singleOptionC'", RadioButton.class);
    view2131296654 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionCClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_D, "field 'singleOptionD' and method 'onSingleOptionDClicked'");
    target.singleOptionD = Utils.castView(view, R.id.single_option_D, "field 'singleOptionD'", RadioButton.class);
    view2131296655 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionDClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionA, "field 'multiOptionA' and method 'onMultiOptionAClicked'");
    target.multiOptionA = Utils.castView(view, R.id.multiOptionA, "field 'multiOptionA'", CheckBox.class);
    view2131296551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionB, "field 'multiOptionB' and method 'onMultiOptionBClicked'");
    target.multiOptionB = Utils.castView(view, R.id.multiOptionB, "field 'multiOptionB'", CheckBox.class);
    view2131296552 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionC, "field 'multiOptionC' and method 'onMultiOptionCClicked'");
    target.multiOptionC = Utils.castView(view, R.id.multiOptionC, "field 'multiOptionC'", CheckBox.class);
    view2131296553 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionCClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionD, "field 'multiOptionD' and method 'onMultiOptionDClicked'");
    target.multiOptionD = Utils.castView(view, R.id.multiOptionD, "field 'multiOptionD'", CheckBox.class);
    view2131296554 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionDClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionE, "field 'multiOptionE' and method 'onMultiOptionEClicked'");
    target.multiOptionE = Utils.castView(view, R.id.multiOptionE, "field 'multiOptionE'", CheckBox.class);
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionEClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionF, "field 'multiOptionF' and method 'onMultiOptionFClicked'");
    target.multiOptionF = Utils.castView(view, R.id.multiOptionF, "field 'multiOptionF'", CheckBox.class);
    view2131296556 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionFClicked();
      }
    });
    target.judge = Utils.findRequiredViewAsType(source, R.id.judge, "field 'judge'", RadioGroup.class);
    target.singleOptionGroup = Utils.findRequiredViewAsType(source, R.id.single_option_group, "field 'singleOptionGroup'", RadioGroup.class);
    target.multiOptionGroup = Utils.findRequiredViewAsType(source, R.id.multiOptionGroup, "field 'multiOptionGroup'", LinearLayout.class);
    target.tvKsxm = Utils.findRequiredViewAsType(source, R.id.tv_ksxm, "field 'tvKsxm'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StartTestActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnMaskTest = null;
    target.btnSkipTest = null;
    target.btnFinishTest = null;
    target.btnBack = null;
    target.seekBar = null;
    target.judgeA = null;
    target.judgeB = null;
    target.singleOptionA = null;
    target.singleOptionB = null;
    target.singleOptionC = null;
    target.singleOptionD = null;
    target.multiOptionA = null;
    target.multiOptionB = null;
    target.multiOptionC = null;
    target.multiOptionD = null;
    target.multiOptionE = null;
    target.multiOptionF = null;
    target.judge = null;
    target.singleOptionGroup = null;
    target.multiOptionGroup = null;
    target.tvKsxm = null;

    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296331.setOnClickListener(null);
    view2131296331 = null;
    view2131296321.setOnClickListener(null);
    view2131296321 = null;
    view2131296317.setOnClickListener(null);
    view2131296317 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131296506.setOnClickListener(null);
    view2131296506 = null;
    view2131296507.setOnClickListener(null);
    view2131296507 = null;
    view2131296652.setOnClickListener(null);
    view2131296652 = null;
    view2131296653.setOnClickListener(null);
    view2131296653 = null;
    view2131296654.setOnClickListener(null);
    view2131296654 = null;
    view2131296655.setOnClickListener(null);
    view2131296655 = null;
    view2131296551.setOnClickListener(null);
    view2131296551 = null;
    view2131296552.setOnClickListener(null);
    view2131296552 = null;
    view2131296553.setOnClickListener(null);
    view2131296553 = null;
    view2131296554.setOnClickListener(null);
    view2131296554 = null;
    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296556.setOnClickListener(null);
    view2131296556 = null;
  }
}
