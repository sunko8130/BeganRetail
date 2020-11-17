package com.frontiertechnologypartners.beganretail.ui.pricing;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import androidx.annotation.NonNull;

public class PricingAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, PricingViewHolder> {

    PricingAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public PricingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PricingViewHolder(inflate(R.layout.pricing_item, parent), getListener());
    }
}
