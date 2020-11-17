package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.sub_categories.SubCategoryViewHolder;

import androidx.annotation.NonNull;

public class SalesAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, SalesViewHolder> {

    SalesAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalesViewHolder(inflate(R.layout.sales_item, parent), getListener());
    }
}
