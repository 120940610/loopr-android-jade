package com.loopr.wallet.wallet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by snow on 2018/5/26.
 */

public class MnenoricRecycleViewAdapter extends RecyclerView.Adapter<MnenoricRecycleViewAdapter.NormalViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private SparseArray<String> mData;

    public MnenoricRecycleViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(SparseArray<String> listData) {
        this.mData = listData;
        notifyDataSetChanged();
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.wallet_mnenoric_view, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        int key =mData.keyAt(position);
        String value=mData.get(key);
        holder.mNenoricNum.setText(String.valueOf(key));
        holder.mNenoricWord.setText(value);
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size()==0)? 0 : mData.size();
    }


    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.wallet_mnenoric_num)
        public TextView mNenoricNum;

        @BindView(R2.id.wallet_mnenoric_word)
        public TextView mNenoricWord;


        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("MnenoricRecycleViewAdapter : ","onClick");
                }
            });
        }
    }
}
