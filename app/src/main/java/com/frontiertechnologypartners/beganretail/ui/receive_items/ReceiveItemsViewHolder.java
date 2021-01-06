package com.frontiertechnologypartners.beganretail.ui.receive_items;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItem;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.google.android.material.button.MaterialButton;

import org.mmtextview.components.MMButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveItemsViewHolder extends BaseViewHolder<ReceiveItem, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_receive_date)
    TextView tvReceiveDate;
    @BindView(R.id.tv_receiver_name)
    TextView tvReceiveName;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @BindView(R.id.btn_view_detail)
    MMButton btnViewDetail;

    ReceiveItemsViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(ReceiveItem receiveItem) {
        tvReceiveDate.setText(receiveItem.getCreatedDate());
        tvReceiveName.setText(receiveItem.getReceivedPersonName());
        tvAmount.setText(Html.fromHtml(mContext.getResources().getString(R.string.order_item_cost,
                Util.convertAmountWithSeparator(receiveItem.getTotalAmount()))));
        btnViewDetail.setOnClickListener(v -> getListener().onItemClick(getAdapterPosition()));
    }
}
