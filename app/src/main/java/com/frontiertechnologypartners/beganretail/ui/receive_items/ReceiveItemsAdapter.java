package com.frontiertechnologypartners.beganretail.ui.receive_items;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItem;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class ReceiveItemsAdapter extends GenericRecyclerViewAdapter<ReceiveItem, OnRecyclerItemClickListener, ReceiveItemsViewHolder> {

    ReceiveItemsAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public ReceiveItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiveItemsViewHolder(inflate(R.layout.receive_item, parent), getListener());
    }
}
