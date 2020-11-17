package com.frontiertechnologypartners.beganretail.ui.receive_items;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class AddNewReceiveItemsAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, AddNewReceiveItemsViewHolder> {

    AddNewReceiveItemsAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public AddNewReceiveItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddNewReceiveItemsViewHolder(inflate(R.layout.new_receive_item, parent), getListener());
    }
}
