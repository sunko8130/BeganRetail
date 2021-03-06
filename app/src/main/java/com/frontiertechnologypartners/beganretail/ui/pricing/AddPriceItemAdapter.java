package com.frontiertechnologypartners.beganretail.ui.pricing;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.SalesItem;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesItemViewHolder;

import androidx.annotation.NonNull;

public class AddPriceItemAdapter extends GenericRecyclerViewAdapter<SalesItem, OnRecyclerItemClickListener, AddPriceItemViewHolder> {

    AddPriceItemAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public AddPriceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddPriceItemViewHolder(inflate(R.layout.add_price_item, parent), getListener());
    }
}
