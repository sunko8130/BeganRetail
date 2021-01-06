package com.frontiertechnologypartners.beganretail.ui.payment;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.Payment;
import com.frontiertechnologypartners.beganretail.model.ReceiveItems;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsViewHolder;

import androidx.annotation.NonNull;

public class PaymentAdapter extends GenericRecyclerViewAdapter<Payment, OnRecyclerItemClickListener, PaymentViewHolder> {

    PaymentAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaymentViewHolder(inflate(R.layout.payment_item, parent), getListener());
    }
}
