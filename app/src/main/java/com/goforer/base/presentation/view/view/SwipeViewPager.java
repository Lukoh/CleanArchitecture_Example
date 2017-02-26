package com.goforer.base.presentation.view.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * SwipeViewPager detect when user is trying to swipe out of bounds
 */
public class SwipeViewPager extends ViewPager {
    private static final String TAG = "SwipeViewPager";

    private static final float SWIPE_MIN_DISTANCE = 25;
    private static final float SWIPE_THRESHOLD_VELOCITY = 150;

    private float mStartDragX;
    private float mStartDragY;

    private OnSwipeOutListener mListener;

    private boolean mIsPageScrolled;

    public SwipeViewPager(Context context) {
        super(context);
    }

    public SwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;

        float x = ev.getX();
        float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = ev.getX();
                mStartDragY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = y - mStartDragY;
                float distanceX = x - mStartDragX;
                if (mStartDragX < x && getCurrentItem() == 0) {
                    mListener.onSwipeOutAtStart();
                } else if (mStartDragX > x && getCurrentItem() == getAdapter().getCount() - 1) {
                    mListener.onSwipeOutAtEnd();
                } else if (Math.abs(distanceY) > Math.abs(distanceX)) {
                    Log.d(TAG, "onInterceptTouchEvent : ACTION_MOVE = "
                            + Float.toString(y - mStartDragY) + "Y : " + Math.abs(y));
                    if (distanceY > SWIPE_MIN_DISTANCE && Math.abs(y) > SWIPE_THRESHOLD_VELOCITY) {
                        mListener.onSwipeDown(x, y);
                    } else {
                        mListener.onSwipeUp(x, y);
                    }
                } else {
                    if (distanceX > SWIPE_MIN_DISTANCE && Math.abs(x) > SWIPE_THRESHOLD_VELOCITY) {
                        mListener.onSwipeRight(x, y);
                    } else{
                        mListener.onSwipeLeft(x, y);
                    }
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    public void setPageScrolled(boolean isPageScrolled) {
        mIsPageScrolled  = isPageScrolled;
    }

    public interface OnSwipeOutListener {
        void onSwipeOutAtStart();

        void onSwipeOutAtEnd();

        void onSwipeLeft(float x, float y);

        void onSwipeRight(float x, float y);

        void onSwipeDown(float x, float y);

        void onSwipeUp(float x, float y);
    }
}
