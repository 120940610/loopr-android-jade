package com.loopr.wallet.wallet.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;
import com.loopr.wallet.wallet.ui.event.OnClickMnenoricListener;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by snow on 2018/6/12.
 */

public class MnenoricVerifyListAdapter extends RecyclerView.Adapter<MnenoricVerifyListAdapter.NormalViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mData;

    private OnClickMnenoricListener onClickMnenoricListener=null;

    public void setOnClickMnenoricListener(OnClickMnenoricListener onClickMnenoricListener){
        this.onClickMnenoricListener=onClickMnenoricListener;
    }


    public MnenoricVerifyListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> listData) {
        this.mData = listData;
        notifyDataSetChanged();
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.wallet_mn_verify_list_view, parent, false));
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

        @BindView(R2.id.wallet_mnenoric_list_tv)
        public TextView mNenoricWord;

        @BindDrawable(R2.drawable.wallet_rectangle_black_bg)
        public Drawable mBackgroud;

        @BindColor(R2.color.black)
        public int black;

        @BindColor(R2.color.white)
        public int white;

        boolean checked=false;

        NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checked){
                        return;
                    }
                    mNenoricWord.setBackground(mBackgroud);
                    mNenoricWord.setTextColor(white);
                    LogUtils.d("MnenoricVerifyListAdapter text content: ",mNenoricWord.getText().toString());
                    LogUtils.d("MnenoricVerifyListAdapter : ","onClick");
                    checked=true;
                    if(onClickMnenoricListener!=null){
                        onClickMnenoricListener.onMnemonicClick(mNenoricWord.getText().toString());
                    }
                }
            });
        }
    }
}
