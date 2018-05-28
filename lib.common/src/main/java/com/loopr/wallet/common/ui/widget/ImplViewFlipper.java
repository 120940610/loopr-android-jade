package com.loopr.wallet.common.ui.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.loopr.wallet.common.R;

/**
 * Created by snow on 2018/5/28.
 */

public class ImplViewFlipper extends ViewFlipper implements GestureListener.OnFlingListener {

    private GestureDetector mGestureDetector = null;

    private OnViewFlipperListener mOnViewFlipperListener = null;

    public ImplViewFlipper(Context context) {
        super(context);
    }

    public ImplViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnViewFlipperListener(OnViewFlipperListener mOnViewFlipperListener) {
        this.mOnViewFlipperListener = mOnViewFlipperListener;
        GestureListener myGestureListener = new GestureListener();
        myGestureListener.setOnFlingListener(this);
        mGestureDetector = new GestureDetector(myGestureListener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (null != mGestureDetector) {
            return mGestureDetector.onTouchEvent(ev);
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public void flingToNext() {
        if (null != mOnViewFlipperListener) {
            int childCnt = getChildCount();
            if (childCnt == 2) {
                removeViewAt(1);
            }
            addView(mOnViewFlipperListener.getNextView(), 0);
            if (0 != childCnt) {
                setInAnimation(getContext(), R.anim.slide_in_right);
                setOutAnimation(getContext(), R.anim.slide_out_left);
                setDisplayedChild(0);
            }
        }
    }

    @Override
    public void flingToPrevious() {
        if (null != mOnViewFlipperListener) {
            int childCnt = getChildCount();
            if (childCnt == 2) {
                removeViewAt(1);
            }
            addView(mOnViewFlipperListener.getPreviousView(), 0);
            if (0 != childCnt) {
                setInAnimation(getContext(), R.anim.slide_in_right);
                setOutAnimation(getContext(), R.anim.slide_out_left);
                setDisplayedChild(0);
            }
        }
    }

    public interface OnViewFlipperListener {
        View getNextView();

        View getPreviousView();
    }
}  
