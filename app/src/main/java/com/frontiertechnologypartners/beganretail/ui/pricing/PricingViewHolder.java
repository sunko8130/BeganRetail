package com.frontiertechnologypartners.beganretail.ui.pricing;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;

import org.mmtextview.components.MMButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricingViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_item_code)
    TextView tvItemCode;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    @BindView(R.id.btn_set_the_price)
    MMButton btnSetPrice;

    @BindView(R.id.btn_price_look)
    MMButton btnPriceLook;


    PricingViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(ReceiveItems receiveItems) {
    }
}
