package com.frontiertechnologypartners.beganretail.ui.stock_balance;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;

public class StockBalanceViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_item_code)
    TextView tvItemCode;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    @BindView(R.id.tv_total_in)
    TextView tvTotalIn;

    @BindView(R.id.tv_total_out)
    TextView tvTotalOut;

    @BindView(R.id.tv_balance)
    TextView tvBalance;


    StockBalanceViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
    }

    @Override
    public void onBind(ReceiveItems receiveItems) {
    }
}
