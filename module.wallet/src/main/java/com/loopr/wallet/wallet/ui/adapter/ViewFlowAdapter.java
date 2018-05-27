package com.loopr.wallet.wallet.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopr.wallet.common.AppGlobal;
import com.loopr.wallet.common.ui.widget.ViewFlow;
import com.loopr.wallet.common.utils.LogUtils;
import com.loopr.wallet.wallet.R;
import com.loopr.wallet.wallet.R2;
import com.loopr.wallet.wallet.entity.Conf;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by snow on 2018/5/27.
 */
public class ViewFlowAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private SparseArray<String> mData;
	private ViewFlow viewFlow;
	private int count=0;

	public ViewFlowAdapter(ViewFlow viewFlow,Context context) {
		this.viewFlow=viewFlow;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(SparseArray<String> listData) {
		this.mData = listData;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return (mData == null || mData.size()==0)? 0 : mData.size();
	}


	@Override
	public Object getItem(int position) {
		int key =mData.keyAt(position);
		String value=mData.get(key);
		return value;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		/*if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.wallet_mn_verify_view, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}*/
		convertView = mInflater.inflate(R.layout.wallet_mn_verify_view, parent, false);
		holder = new ViewHolder(convertView);
		convertView.setTag(holder);

		int num=holder.num+1;
		int key =mData.keyAt(position);
		String value=mData.get(key);
		holder.mWalletVerifyTip.setText(String.format(AppGlobal.getContext().getString(R.string.wallet_mn_verify_tip),num,key));
		holder.mWalletVerifyWord1.setText(value);
		holder.mWalletVerifyWord2.setText("John Doe");
		holder.mWalletVerifyWord3.setText("John Doe");
		holder.mWalletVerifyWord4.setText("John Doe");

		return convertView;
	}


	class ViewHolder {
		@BindView(R2.id.wallet_mn_verify_tip)
		public TextView mWalletVerifyTip;

		@BindView(R2.id.wallet_mn_verify_word1)
		public TextView mWalletVerifyWord1;

		@BindView(R2.id.wallet_mn_verify_word2)
		public TextView mWalletVerifyWord2;

		@BindView(R2.id.wallet_mn_verify_word3)
		public TextView mWalletVerifyWord3;

		@BindView(R2.id.wallet_mn_verify_word4)
		public TextView mWalletVerifyWord4;

		public boolean checked=false;
		public int num=0;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}

		@OnClick(R2.id.wallet_mn_verify_word1)
		public void onVerifyWord1Click(TextView textView){
			if(checked){
				return;
			}
			count++;
			num=count;
			checked=true;
			refreshData();
			viewFlow.snapToNextScreen();
		}

		@OnClick(R2.id.wallet_mn_verify_word2)
		public void onVerifyWord2Click(TextView textView){
			if(checked){
				return;
			}
			count++;
			num=count;
			checked=true;
			refreshData();
			viewFlow.snapToNextScreen();
		}

		@OnClick(R2.id.wallet_mn_verify_word3)
		public void onVerifyWord3Click(TextView textView){
			if(checked){
				return;
			}
			count++;
			num=count;
			checked=true;
			refreshData();
			viewFlow.snapToNextScreen();
		}

		@OnClick(R2.id.wallet_mn_verify_word4)
		public void onVerifyWord4Click(TextView textView){
			if(checked){
				return;
			}
			count++;
			num=count;
			checked=true;
			refreshData();
			viewFlow.snapToNextScreen();
		}
	}

	private void refreshData(){
		int key=((Integer)Conf.random[count]).intValue();
		LogUtils.d("TAG","int: "+count);
		LogUtils.d("TAG","key: "+key);
		LogUtils.d("TAG","value: "+ Conf.mnemonic.get(key-1));
		Conf.mNPart.put(key, Conf.mnemonic.get(key-1));
		setData(Conf.mNPart);
	}

}
