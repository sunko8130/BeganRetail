package com.frontiertechnologypartners.beganretail.ui.sales;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;

public class SalesViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_sales_order_number)
    TextView tvSalesOrderNumber;

    @BindView(R.id.tv_sales_order_date)
    TextView tvSalesOrderDate;

    @BindView(R.id.tv_sales_amount)
    TextView tvCustomerName;

    @BindView(R.id.btn_view_detail)
    MaterialButton btnViewDetail;

    SalesViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
    }

    @Override
    public void onBind(ReceiveItems receiveItems) {
        btnViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
