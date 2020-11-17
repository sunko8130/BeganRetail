package com.frontiertechnologypartners.beganretail.ui.main_categories;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsViewHolder;

import androidx.annotation.NonNull;

public class MainCategoryAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, MainCategoryViewHolder> {

    MainCategoryAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public MainCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainCategoryViewHolder(inflate(R.layout.main_category_item, parent), getListener());
    }
}
