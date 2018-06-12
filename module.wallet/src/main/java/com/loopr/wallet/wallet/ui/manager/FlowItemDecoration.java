package com.loopr.wallet.wallet.ui.manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by snow on 2018/6/12.
 */

public class FlowItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public FlowItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = space;
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
    }
}
