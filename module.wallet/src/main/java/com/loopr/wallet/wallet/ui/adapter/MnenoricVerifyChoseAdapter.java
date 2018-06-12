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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by snow on 2018/6/12.
 */

public class MnenoricVerifyChoseAdapter extends RecyclerView.Adapter<MnenoricVerifyChoseAdapter.NormalViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mData;

    public MnenoricVerifyChoseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> listData) {
        this.mData = listData;
        notifyDataSetChanged();
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.wallet_mn_verify_chosed_view, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        String value=mData.get(position);
        holder.mNenoricWord.setText(value);
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size()==0)? 0 : mData.size();
    }


    public class NormalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.wallet_mnenoric_chose_tv)
        public TextView mNenoricWord;


        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("MnenoricVerifyChoseAdapter : ","onClick");
                }
            });
        }
    }
}
