package com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.CashOutTransaction;
import com.frontiertechnologypartners.beganretail.model.Payment;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewHolder;
import com.frontiertechnologypartners.beganretail.util.Util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmoneyTransactionViewHolder extends BaseViewHolder<CashOutTransaction, OnRecyclerItemClickListener> {

    private Context mContext;

    @BindView(R.id.tv_cashout)
    TextView tvCashout;

    @BindView(R.id.tv_request_date)
    TextView tvRequestDate;

    @BindView(R.id.tv_cashout_date)
    TextView tvCashoutDate;

    @BindView(R.id.tv_status)
    TextView tvStatus;

    @BindView(R.id.req_date_row)
    TableRow reqDateRow;

    @BindView(R.id.cashout_date_row)
    TableRow cashOutDateRow;

    EmoneyTransactionViewHolder(View itemView, OnRecyclerItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(CashOutTransaction transaction) {

        tvCashout.setText(Html.fromHtml(mContext.getResources().getString(R.string.cashout_amount,
                Util.convertAmountWithSeparator(transaction.getCashoutAmount()))));

        if (transaction.getCashoutStatus() == 0) {
            tvStatus.setText(R.string.cashout_pending);
            reqDateRow.setVisibility(View.VISIBLE);
            cashOutDateRow.setVisibility(View.GONE);
        } else {
            tvStatus.setText(R.string.cashout_done);
            reqDateRow.setVisibility(View.GONE);
            cashOutDateRow.setVisibility(View.VISIBLE);
        }

        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        tvRequestDate.setText(sdf.format(transaction.getCreatedDate()));
        tvCashoutDate.setText(sdf.format(transaction.getUpdatedDate()));
    }
}
