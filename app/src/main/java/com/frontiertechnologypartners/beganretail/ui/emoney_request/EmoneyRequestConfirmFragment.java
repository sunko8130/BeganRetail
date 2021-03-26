package com.frontiertechnologypartners.beganretail.ui.emoney_request;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.model.CashoutResponse;
import com.frontiertechnologypartners.beganretail.model.LoginData;
import com.frontiertechnologypartners.beganretail.model.LoginResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseDialogFragment;
import com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction.EmoneyRequestTransactionActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.MessageDialog;

import org.mmtextview.MMFontUtils;
import org.mmtextview.components.MMTextView;

import javax.inject.Inject;

import static com.frontiertechnologypartners.beganretail.util.Constant.ALREADY_LOGIN;
import static com.frontiertechnologypartners.beganretail.util.Constant.LOGIN_DATA;

public class EmoneyRequestConfirmFragment extends BaseDialogFragment implements MessageDialog.OnClickListener {

    @BindView(R.id.tv_request_amount)
    MMTextView tvRequestAmount;

    private static final String REQUEST_AMOUNT = "request_amount";

    private int requestAmount;
    private View rootView;
    private int merchantId;

    @Inject
    ViewModelFactory viewModelFactory;

    private CashOutViewModel cashOutViewModel;

    public EmoneyRequestConfirmFragment() {
        // Required empty public constructor
    }

    public static EmoneyRequestConfirmFragment newInstance(int requestAmount) {
        EmoneyRequestConfirmFragment fragment = new EmoneyRequestConfirmFragment();
        Bundle args = new Bundle();
        args.putInt(REQUEST_AMOUNT, requestAmount);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setView(rootView)
                .setCancelable(false)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_emoney_request_confirm, null, false);
        ButterKnife.bind(this, rootView);
        if (getArguments() != null) {
            requestAmount = getArguments().getInt(REQUEST_AMOUNT);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cashOutViewModel = ViewModelProviders.of(this, viewModelFactory).get(CashOutViewModel.class);

        //login data
        LoginData loginData = Paper.book().read(LOGIN_DATA);
        if (loginData != null) {
            merchantId = loginData.getId();
        }

        tvRequestAmount.setText(Html.fromHtml(getString(R.string.confirm_request_amount,
                Util.convertAmountWithSeparator(requestAmount))));

        //observe cashout
        observeCashout();

    }

    private void observeCashout() {
        cashOutViewModel.cashoutResponse.observe(this, this::consumeCashout);
    }

    private void consumeCashout(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                CashoutResponse cashoutResponse = (CashoutResponse) apiResponse.data;
                if (cashoutResponse != null) {
                    int statusCode = cashoutResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        messageDialog.setListener(this);
                        messageDialog.show();
                        messageDialog.loadingMessage(getString(R.string.success_cashout));
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(cashoutResponse.getStatus().getMessage());
                    }
                }
                break;
            case ERROR:
                loadingDialog.dismiss();
                if (apiResponse.message != null) {
                    messageDialog.show();
                    messageDialog.loadingMessage(apiResponse.message);
                }
                break;

            default:
                break;
        }
    }

    @OnClick(R.id.btn_cancel)
    void cancel() {
        dismiss();
    }

    @OnClick(R.id.btn_confirm)
    void onClickConfirm() {
        cashOutViewModel.cashout(String.valueOf(merchantId), String.valueOf(requestAmount));
    }

    @Override
    public void onOkButtonClick() {
        dismiss();
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}