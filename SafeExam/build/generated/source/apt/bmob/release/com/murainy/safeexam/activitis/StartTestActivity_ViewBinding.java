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

  private View view2131296330;

  private View view2131296336;

  private View view2131296325;

  private View view2131296321;

  private View view2131296675;

  private View view2131296523;

  private View view2131296524;

  private View view2131296685;

  private View view2131296686;

  private View view2131296687;

  private View view2131296688;

  private View view2131296573;

  private View view2131296574;

  private View view2131296575;

  private View view2131296576;

  private View view2131296577;

  private View view2131296578;

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
    view2131296330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnMaskTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_skip_test, "field 'btnSkipTest' and method 'onBtnSkipTestClicked'");
    target.btnSkipTest = Utils.castView(view, R.id.btn_skip_test, "field 'btnSkipTest'", Button.class);
    view2131296336 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnSkipTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_finish_test, "field 'btnFinishTest' and method 'onBtnFinishTestClicked'");
    target.btnFinishTest = Utils.castView(view, R.id.btn_finish_test, "field 'btnFinishTest'", Button.class);
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnFinishTestClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "field 'btnBack' and method 'onBtnBackClicked'");
    target.btnBack = Utils.castView(view, R.id.btn_back, "field 'btnBack'", ImageButton.class);
    view2131296321 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnBackClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.seekBar, "field 'seekBar' and method 'onSeekBarClicked'");
    target.seekBar = Utils.castView(view, R.id.seekBar, "field 'seekBar'", SeekBar.class);
    view2131296675 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSeekBarClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.judgeA, "field 'judgeA' and method 'onJudgeAClicked'");
    target.judgeA = Utils.castView(view, R.id.judgeA, "field 'judgeA'", RadioButton.class);
    view2131296523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onJudgeAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.judgeB, "field 'judgeB' and method 'onJudgeBClicked'");
    target.judgeB = Utils.castView(view, R.id.judgeB, "field 'judgeB'", RadioButton.class);
    view2131296524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onJudgeBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_A, "field 'singleOptionA' and method 'onSingleOptionAClicked'");
    target.singleOptionA = Utils.castView(view, R.id.single_option_A, "field 'singleOptionA'", RadioButton.class);
    view2131296685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_B, "field 'singleOptionB' and method 'onSingleOptionBClicked'");
    target.singleOptionB = Utils.castView(view, R.id.single_option_B, "field 'singleOptionB'", RadioButton.class);
    view2131296686 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_C, "field 'singleOptionC' and method 'onSingleOptionCClicked'");
    target.singleOptionC = Utils.castView(view, R.id.single_option_C, "field 'singleOptionC'", RadioButton.class);
    view2131296687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionCClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.single_option_D, "field 'singleOptionD' and method 'onSingleOptionDClicked'");
    target.singleOptionD = Utils.castView(view, R.id.single_option_D, "field 'singleOptionD'", RadioButton.class);
    view2131296688 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSingleOptionDClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionA, "field 'multiOptionA' and method 'onMultiOptionAClicked'");
    target.multiOptionA = Utils.castView(view, R.id.multiOptionA, "field 'multiOptionA'", CheckBox.class);
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionAClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionB, "field 'multiOptionB' and method 'onMultiOptionBClicked'");
    target.multiOptionB = Utils.castView(view, R.id.multiOptionB, "field 'multiOptionB'", CheckBox.class);
    view2131296574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionBClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionC, "field 'multiOptionC' and method 'onMultiOptionCClicked'");
    target.multiOptionC = Utils.castView(view, R.id.multiOptionC, "field 'multiOptionC'", CheckBox.class);
    view2131296575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionCClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionD, "field 'multiOptionD' and method 'onMultiOptionDClicked'");
    target.multiOptionD = Utils.castView(view, R.id.multiOptionD, "field 'multiOptionD'", CheckBox.class);
    view2131296576 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionDClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionE, "field 'multiOptionE' and method 'onMultiOptionEClicked'");
    target.multiOptionE = Utils.castView(view, R.id.multiOptionE, "field 'multiOptionE'", CheckBox.class);
    view2131296577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMultiOptionEClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.multiOptionF, "field 'multiOptionF' and method 'onMultiOptionFClicked'");
    target.multiOptionF = Utils.castView(view, R.id.multiOptionF, "field 'multiOptionF'", CheckBox.class);
    view2131296578 = view;
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

    view2131296330.setOnClickListener(null);
    view2131296330 = null;
    view2131296336.setOnClickListener(null);
    view2131296336 = null;
    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296321.setOnClickListener(null);
    view2131296321 = null;
    view2131296675.setOnClickListener(null);
    view2131296675 = null;
    view2131296523.setOnClickListener(null);
    view2131296523 = null;
    view2131296524.setOnClickListener(null);
    view2131296524 = null;
    view2131296685.setOnClickListener(null);
    view2131296685 = null;
    view2131296686.setOnClickListener(null);
    view2131296686 = null;
    view2131296687.setOnClickListener(null);
    view2131296687 = null;
    view2131296688.setOnClickListener(null);
    view2131296688 = null;
    view2131296573.setOnClickListener(null);
    view2131296573 = null;
    view2131296574.setOnClickListener(null);
    view2131296574 = null;
    view2131296575.setOnClickListener(null);
    view2131296575 = null;
    view2131296576.setOnClickListener(null);
    view2131296576 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296578.setOnClickListener(null);
    view2131296578 = null;
  }
}
