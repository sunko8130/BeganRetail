package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.OrderItem;
import com.frontiertechnologypartners.beganretail.model.SaleDetailsItem;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.receive_items.AddNewReceiveItemsViewHolder;

import androidx.annotation.NonNull;

public class SaleOrderDetailAdapter extends GenericRecyclerViewAdapter<SaleDetailsItem, OnRecyclerItemClickListener, SaleOrderDetailViewHolder> {

    SaleOrderDetailAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public SaleOrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleOrderDetailViewHolder(inflate(R.layout.new_receive_item, parent), getListener());
    }
}
