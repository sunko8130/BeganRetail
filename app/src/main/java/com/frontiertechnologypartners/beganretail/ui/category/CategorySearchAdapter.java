package com.frontiertechnologypartners.beganretail.ui.category;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;

import androidx.annotation.NonNull;

public class CategorySearchAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, CategorySearchViewHolder> {

    CategorySearchAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public CategorySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategorySearchViewHolder(inflate(R.layout.sub_category_item, parent), getListener());
    }
}
