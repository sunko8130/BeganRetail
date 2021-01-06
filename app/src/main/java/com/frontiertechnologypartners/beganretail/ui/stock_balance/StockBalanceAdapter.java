package com.frontiertechnologypartners.beganretail.ui.stock_balance;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.model.StockBalance;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesViewHolder;

import androidx.annotation.NonNull;

public class StockBalanceAdapter extends GenericRecyclerViewAdapter<StockBalance, OnRecyclerItemClickListener, StockBalanceViewHolder> {

    StockBalanceAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public StockBalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StockBalanceViewHolder(inflate(R.layout.stock_item, parent), getListener());
    }
}
