package com.frontiertechnologypartners.beganretail.ui.category;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;

import butterknife.BindView;

public class CategorySearchViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_main_category_name)
    TextView tvMainCategoryName;

    @BindView(R.id.tv_sub_category_name)
    TextView tvSubCategoryName;

    @BindView(R.id.tv_no_of_items)
    TextView tvNoOfItems;

    @BindView(R.id.tv_out_of_stock)
    TextView tvOutOfStock;

    @BindView(R.id.tv_uom)
    TextView tvUOM;

    CategorySearchViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
    }

    @Override
    public void onBind(ReceiveItems receiveItems) {
    }
}
