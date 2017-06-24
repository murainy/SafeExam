package com.murainy.safeexam.Utils;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Tenerify on 2016/6/19.
 */
public  class DefaultGestureListener extends GestureDetector.SimpleOnGestureListener {
	public static final int FLING_MIN_DISTANCE = 50;
	public static final int FLING_MIN_VELOCITX = 5;
	public static final int FLING_MIN_VELOCITY = 10;

	// Touch down时触发
    @Override
    public boolean onDown(MotionEvent e) {
        return super.onDown(e);
    }

    // 在Touch down之后一定时间（115ms）触发
    @Override
    public void onShowPress(MotionEvent e) {
    }

    /**
     * 这个方法不同于onSingleTapUp，他是在GestureDetector确信用户在第一次触摸屏幕后，没有紧跟着第二次触摸屏幕，也就是不是“双击”的时候触发
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return super.onSingleTapUp(e);
    }

    /**
     * @param e1        The first down motion event that started the scrolling.
     * @param e2        The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis(轴) that has been scrolled since the last call to onScroll. This is NOT the distance between e1 and e2.
     * @param distanceY The distance along the Y axis that has been scrolled since the last call to onScroll. This is NOT the distance between e1 and e2.
     *                  无论是用手拖动view，或者是以抛的动作滚动，都会多次触发 ,这个方法在ACTION_MOVE动作发生时就会触发 参看GestureDetector的onTouchEvent方法源码
     */
    // 滑动时触发
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        //   Log.e("滚动机构", "滚动机构");
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    /**
     * @param e1        第1个ACTION_DOWN MotionEvent 并且只有一个
     * @param e2        最后一个ACTION_MOVE MotionEvent
     * @param velocityX X轴上的移动速度，像素/秒
     * @param velocityY Y轴上的移动速度，像素/秒
     *                  这个方法发生在ACTION_UP时才会触发 参看GestureDetector的onTouchEvent方法源码
     */
    // 滑动一段距离，up时触发
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITX) {
            // Fling left
            Log.e("向左手势", "向左手势");
            //Toast.makeText(StartTestActivity.this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITX) {
            // Fling right
            Log.e("向右手势", "向右手势");
            //Toast.makeText(StartTestActivity.this, "向右手势", Toast.LENGTH_SHORT).show();

        } else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE
                && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling up
            Log.e("向上手势", "向上手势");
            // 下一题
            //Toast.makeText(StartTestActivity.this, "向上手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
                && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling D
            Log.e("向下手势", "向下手势");
            // 下一题
            //Toast.makeText(StartTestActivity.this, "向下手势", Toast.LENGTH_SHORT).show();
        }

        return super.onFling(e1, e2, velocityX, velocityY);

    }

    // 长按后触发(Touch down之后一定时间（500ms）)
    @Override
    public void onLongPress(MotionEvent e) {
        Log.e("常按机构", "常按机构");
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // TODO Auto-generated method stub
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // TODO Auto-generated method stub
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // TODO Auto-generated method stub
        return super.onSingleTapConfirmed(e);
    }

}
