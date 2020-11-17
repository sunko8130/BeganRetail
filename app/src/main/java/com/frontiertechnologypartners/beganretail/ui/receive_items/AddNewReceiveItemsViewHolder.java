package com.frontiertechnologypartners.beganretail.ui.receive_items;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;

import org.mmtextview.components.MMButton;

import butterknife.BindView;

public class AddNewReceiveItemsViewHolder extends BaseViewHolder<ReceiveItems, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_receive_no)
    TextView tvReceiveNo;

    @BindView(R.id.tv_receive_date)
    TextView tvReceiveDate;

    @BindView(R.id.tv_distributor)
    TextView tvDistributor;

    @BindView(R.id.tv_no_of_items)
    TextView tvNoOfItems;

    @BindView(R.id.btn_view_detail)
    MMButton btnViewDetail;

    AddNewReceiveItemsViewHolder(View itemView, OnRecyclerItemClickListener listener) {
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
