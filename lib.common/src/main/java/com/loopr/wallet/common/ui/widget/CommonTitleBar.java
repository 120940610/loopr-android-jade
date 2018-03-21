package com.loopr.wallet.common.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopr.wallet.common.R;

/**
 * Created by snow on 2018/3/12.
 */
public class CommonTitleBar extends FrameLayout {
    private String mTitle;
    private int mLeftIconId, mRightIconId;
    private ImageView mLeftIcon, mRightIcon;
    private int mBackground;
    private TextView mTitleView;
    private ViewGroup titleBar;

    public CommonTitleBar(Context context) {
        this(context, null);
    }

    public CommonTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        mTitle = typedArray.getString(R.styleable.CommonTitleBar_title);
        boolean titleInCenter = typedArray.getBoolean(R.styleable.CommonTitleBar_titleInCenter, false);
        int bgColor = typedArray.getColor(R.styleable.CommonTitleBar_backgroundColor, Color.BLACK);
        int titleColor = typedArray.getColor(R.styleable.CommonTitleBar_titleColor, Color.WHITE);
        mLeftIconId = typedArray.getResourceId(R.styleable.CommonTitleBar_leftDrawable, 0);
        mRightIconId = typedArray.getResourceId(R.styleable.CommonTitleBar_rightDrawable, 0);
        mBackground = typedArray.getResourceId(R.styleable.CommonTitleBar_background, 0);

        typedArray.recycle();

        View rootView = LayoutInflater.from(context).inflate(R.layout.widget_base_title, this);

        titleBar = (ViewGroup) rootView.findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(bgColor);
        if (mBackground > 0) {
            titleBar.setBackgroundResource(mBackground);
        }
        mLeftIcon = (ImageView) rootView.findViewById(R.id.left_icon);
        mRightIcon = (ImageView) rootView.findViewById(R.id.right_icon);
        mTitleView = (TextView) rootView.findViewById(R.id.title_content);

        mTitleView.setText(mTitle);
        setTitleInCenter(titleInCenter);
        mTitleView.setTextColor(titleColor);

        if (mLeftIconId > 0) {
            mLeftIcon.setImageResource(mLeftIconId);
            mLeftIcon.setVisibility(VISIBLE);
        } else {
            mLeftIcon.setVisibility(GONE);
        }

        if (mRightIconId > 0) {
            mRightIcon.setImageResource(mRightIconId);
            mRightIcon.setVisibility(VISIBLE);
        } else {
            mRightIcon.setVisibility(GONE);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
        mTitleView.setText(mTitle);
    }

    /*public void setBackgroundColor(int color) {
        titleBar.setBackgroundColor(color);
    }*/

    public void setLeftIcon(int iconId, OnClickListener leftOnClickListener) {
        mLeftIcon.setVisibility(VISIBLE);
        mLeftIcon.setImageResource(iconId);
        mLeftIcon.setOnClickListener(leftOnClickListener);
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        if (mLeftIcon.getVisibility() == VISIBLE) {
            mLeftIcon.setOnClickListener(leftOnClickListener);
        }
    }

    public void setRightIcon(int iconId, OnClickListener rightOnClickListener) {
        mRightIcon.setVisibility(VISIBLE);
        mRightIcon.setImageResource(iconId);
        mRightIcon.setOnClickListener(rightOnClickListener);
    }


    public void setRightIconVisibility(int visible) {
        mRightIcon.setVisibility(visible);
    }

    public ImageView getRightImageView() {
        return mRightIcon;
    }


    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        if (mRightIcon.getVisibility() == VISIBLE) {
            mRightIcon.setOnClickListener(rightOnClickListener);
        }
    }


    public void setTitleInCenter(boolean center) {
        mTitleView.setGravity((center ? Gravity.CENTER : Gravity.START)
                | Gravity.CENTER_VERTICAL);
    }
}
