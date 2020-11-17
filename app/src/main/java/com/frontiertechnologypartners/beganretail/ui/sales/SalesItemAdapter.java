package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.model.SalesItem;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class SalesItemAdapter extends GenericRecyclerViewAdapter<SalesItem, OnRecyclerItemClickListener, SalesItemViewHolder> {

    SalesItemAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public SalesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalesItemViewHolder(inflate(R.layout.sales_item_layout, parent), getListener());
    }
}
