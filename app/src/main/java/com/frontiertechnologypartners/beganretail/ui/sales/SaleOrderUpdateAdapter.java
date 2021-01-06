package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerMultiItemClickListener;
import com.frontiertechnologypartners.beganretail.model.SaleDetailsItem;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class SaleOrderUpdateAdapter extends GenericRecyclerViewAdapter<SaleDetailsItem, OnRecyclerMultiItemClickListener, SaleOrderUpdateViewHolder> {

    SaleOrderUpdateAdapter(Context context, OnRecyclerMultiItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public SaleOrderUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleOrderUpdateViewHolder(inflate(R.layout.update_sale_item, parent), getListener());
    }
}
