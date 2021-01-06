package com.frontiertechnologypartners.beganretail.ui.items;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.model.SearchItem;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class ItemsAdapter extends GenericRecyclerViewAdapter<SearchItem, OnRecyclerItemClickListener, ItemsViewHolder> {

    ItemsAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsViewHolder(inflate(R.layout.items_item, parent), getListener());
    }
}
