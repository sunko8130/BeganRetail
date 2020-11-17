package com.frontiertechnologypartners.beganretail.ui.sub_categories;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.main_categories.MainCategoryViewHolder;

import androidx.annotation.NonNull;

public class SubCategoryAdapter extends GenericRecyclerViewAdapter<ReceiveItems, OnRecyclerItemClickListener, SubCategoryViewHolder> {

    SubCategoryAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(inflate(R.layout.sub_category_item, parent), getListener());
    }
}
