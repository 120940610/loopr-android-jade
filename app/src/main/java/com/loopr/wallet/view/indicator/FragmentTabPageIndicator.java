package com.loopr.wallet.view.indicator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopr.wallet.R;
import com.loopr.wallet.utils.AppGlobal;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * PageIndicator of the home page
 *
 * @snow
 */
public class FragmentTabPageIndicator extends LinearLayout{

    private final String[] CONTENT = new String[] {"Assets", "Market","Transaction","Setting"};
    public static final int TAB_ASSETS = 0;
    public static final int TAB_MARKET = 1;
    public static final int TAB_TRANSACTION = 2;
    public static final int TAB_SETTING = 3;

    private boolean clickable = true;
    private ViewPager viewPager;

    public interface OnTabReselectedListener {
        void onTabReselected(int position);
    }
    private OnTabReselectedListener onTabReselectedListener;
    public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener){
        this.onTabReselectedListener = onTabReselectedListener;
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position);
    }
    private OnTabSelectedListener onTabSelectedListener;
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener){
        this.onTabSelectedListener = onTabSelectedListener;
    }

    private final View.OnClickListener mTabClickListener = new View.OnClickListener() {
        public void onClick(View view) {

            if(!clickable){
                return;
            }
            TabView tabView = (TabView) view;
            final int newSelected = tabView.getIndex();
            if (newSelected == mSelectedTabIndex) {
                if (onTabReselectedListener != null)
                    onTabReselectedListener.onTabReselected(newSelected);
            } else {
            }
            setCurrentItem(newSelected);
        }
    };

    private final LinearLayout mTabLayout;
    private int mSelectedTabIndex;
    private int mTabWidth;


    public FragmentTabPageIndicator(Context context) {
        this(context, null);
    }

    public FragmentTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        mTabLayout = new LinearLayout(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            mTabWidth = MeasureSpec.getSize(widthMeasureSpec) / childCount;
        } else {
            mTabWidth = -1;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @SuppressLint("ResourceAsColor")
	private void addTab(int index, CharSequence text) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setOnClickListener(mTabClickListener);
    	tabView.setText(text);
        tabView.setIcon(index);
        mTabLayout.setGravity(Gravity.BOTTOM);
        mTabLayout.addView(tabView);
    }

    private boolean initViewCalled = false;

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!initViewCalled){
                    initViewCalled = true;
                    try{
                        addView(mTabLayout, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                        mTabLayout.removeAllViews();
                        for (int i = 0; i < CONTENT.length; i++) {
                            addTab(i, CONTENT[i]);
                        }
                        setCurrentItem(mSelectedTabIndex);
                        requestLayout();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        },200);
    }

    public void setCurrentItem(int item) {
        if (mSelectedTabIndex != item && onTabSelectedListener != null) {
            onTabSelectedListener.onTabSelected(item);
        }
        mSelectedTabIndex = item;
        for (int i = 0; i < CONTENT.length; i++) {
            final View child = mTabLayout.getChildAt(i);
            if (child == null) {
                return;
            }
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
            }
        }
        if (viewPager != null) {
          viewPager.setCurrentItem(item, false);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class TabView extends RelativeLayout {
		private int mIndex;
        public ImageView mImageView;
        public TextView mTextView;
        public TabView(Context context) {
            super(context);
            inflate(context, R.layout.tab_view, this);
            mImageView = (ImageView) findViewById(R.id.tab_image);
            mTextView = (TextView) findViewById(R.id.tab_text);

        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (mTabWidth > 0) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }

        public void setText(CharSequence text) {
        	if(TextUtils.isEmpty(text)){
        		mTextView.setVisibility(View.GONE);
        	}else {
          		mTextView.setVisibility(View.VISIBLE);
        		mTextView.setText(text);
			}

        }

        public void setIcon(int index) {
            int resId = 0;
            mIndex = index;
            switch (index) {
                case TAB_ASSETS:
                    resId = R.drawable.tab_assets_selector;
                    break;
                case TAB_MARKET:
                    resId = R.drawable.tab_transaction_selector;
                    break;
                case TAB_TRANSACTION:
                    resId = R.drawable.tab_market_selector;
                    break;
                case TAB_SETTING:
                    resId = R.drawable.tab_setting_selector;
                    break;
                default:
                    break;
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mImageView.setBackground(AppGlobal.getContext().getResources().getDrawable(resId));
                } else {
                    mImageView.setBackgroundDrawable(AppGlobal.getContext().getResources().getDrawable(resId));
                }
            } catch (Exception e) {
            }
        }
        public int getIndex() {
            return mIndex;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasWindowFocus);
        initView();
    }
}

