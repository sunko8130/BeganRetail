package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.model.SalesItem;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.google.android.material.button.MaterialButton;

import org.mmtextview.components.MMTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesItemViewHolder extends BaseViewHolder<SalesItem, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    @BindView(R.id.tv_uom)
    TextView tvUOM;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.tv_cost)
    TextView tvCost;

    @BindView(R.id.tv_no_of_items)
    TextView tvNoOfItems;

    SalesItemViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    @Override
    public void onBind(SalesItem salesItem) {
        tvNoOfItems.setText(salesItem.getQuantity());
        tvPrice.setText(salesItem.getPrice());
        tvCost.setText(salesItem.getAmount());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListener().onItemClick(getAdapterPosition());
            }
        });
    }
}
