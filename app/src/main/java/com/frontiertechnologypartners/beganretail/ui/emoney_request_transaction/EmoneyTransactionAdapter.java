package com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction;

import android.content.Context;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.CashOutTransaction;
import com.frontiertechnologypartners.beganretail.model.Payment;
import com.frontiertechnologypartners.beganretail.ui.base.GenericRecyclerViewAdapter;
import com.frontiertechnologypartners.beganretail.ui.payment.PaymentViewHolder;

import androidx.annotation.NonNull;

public class EmoneyTransactionAdapter extends GenericRecyclerViewAdapter<CashOutTransaction, OnRecyclerItemClickListener, EmoneyTransactionViewHolder> {

    EmoneyTransactionAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public EmoneyTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmoneyTransactionViewHolder(inflate(R.layout.emoney_transaction_item, parent), getListener());
    }
}
