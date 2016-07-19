package com.murainy.safeexam.Utils;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.murainy.safeexam.R;

public class SnackbarUtil {

    private static final int red = 0xfff44336;
    private static final int green = 0xff4caf50;
    private static final int blue = 0xff2195f3;
    private static final int orange = 0xffffc107;
    private static final int white = 0xFFFFFFFF;

    /**
     * 提示消息（蓝色背景，白色字体）
     */
    public static void showShortInfo(CoordinatorLayout coordinatorLayout, String message) {
        ShortSnackbar(coordinatorLayout, message, white, blue).show();
    }

    public static void showLongInfo(CoordinatorLayout coordinatorLayout, String message) {
        LongSnackbar(coordinatorLayout, message, white, blue).show();
    }

    /**
     * 提示确认消息（绿色背景，白色字体）
     */
    public static void showShortConfirm(CoordinatorLayout coordinatorLayout, String message) {
        ShortSnackbar(coordinatorLayout, message, white, green).show();
    }

    public static void showLongConfirm(CoordinatorLayout coordinatorLayout, String message) {
        LongSnackbar(coordinatorLayout, message, white, green).show();
    }

    /**
     * 提示错误（黄色背景，白色字体）
     */
    public static void showShortWarning(CoordinatorLayout coordinatorLayout, String message) {
        ShortSnackbar(coordinatorLayout, message, white, orange).show();
    }

    public static void showLongWarning(CoordinatorLayout coordinatorLayout, String message) {
        LongSnackbar(coordinatorLayout, message, white, orange).show();
    }

    /**
     * 提示严重错误（红色背景，白色字体）
     */
    public static void showShortAlert(CoordinatorLayout coordinatorLayout, String message) {
        ShortSnackbar(coordinatorLayout, message, white, red).show();
    }

    public static void showLongAlert(CoordinatorLayout coordinatorLayout, String message) {
        LongSnackbar(coordinatorLayout, message, white, red).show();
    }


    /**
     * 常规提示（原生）
     */
    public static Snackbar ShortSnackbar(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        return snackbar;
    }

    public static Snackbar LongSnackbar(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        return snackbar;
    }

    public static Snackbar ShortSnackbar(CoordinatorLayout coordinatorLayout, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    public static Snackbar LongSnackbar(CoordinatorLayout coordinatorLayout, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    public static Snackbar ShortSnackbarAlert(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, Color.WHITE, orange);
        return snackbar;
    }

    public static Snackbar LongSnackbarAlert(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, Color.WHITE, orange);
        return snackbar;
    }

    public static Snackbar ShortSnackbarWarning(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, Color.WHITE, red);
        return snackbar;
    }

    public static Snackbar LongSnackbarWarning(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, Color.WHITE, red);
        return snackbar;
    }

    /**
     * 设置背景和字体颜色
     */
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }

    }
}