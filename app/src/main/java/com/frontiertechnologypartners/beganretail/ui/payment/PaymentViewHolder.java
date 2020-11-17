package com.frontiertechnologypartners.beganretail.ui.payment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_delivery_order_no)
    TextView tvDeliveryOrderNo;

    @BindView(R.id.tv_payment_no)
    TextView tvPaymentNo;

    @BindView(R.id.tv_payment_date)
    TextView tvPaymentDate;

    @BindView(R.id.tv_bal_to_paid)
    TextView tvBalanceToPaid;

    @BindView(R.id.tv_total_amount)
    TextView tvTotalAmount;

    PaymentViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(ReceiveItems receiveItems) {

    }
}
