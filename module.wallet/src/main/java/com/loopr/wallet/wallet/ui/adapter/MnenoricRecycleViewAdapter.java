package com.loopr.wallet.wallet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zefeng.wzf on 2018/5/26.
 */

public class MnenoricRecycleViewAdapter extends RecyclerView.Adapter<MnenoricRecycleViewAdapter.NormalViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mData;

    public MnenoricRecycleViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> listData) {
        this.mData = listData;
        notifyDataSetChanged();
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.wallet_mnenoric_view, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.mNenoricNum.setText(position+1);
        holder.mNenoricWord.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size()==0)? 0 : mData.size();
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.wallet_mnenoric_num)
        TextView mNenoricNum;

        @BindView(R2.id.wallet_mnenoric_word)
        TextView mNenoricWord;


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
