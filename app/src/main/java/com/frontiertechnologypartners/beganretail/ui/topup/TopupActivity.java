package com.frontiertechnologypartners.beganretail.ui.topup;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.model.TopUpResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.MessageDialog;

import org.mmtextview.MMFontUtils;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.frontiertechnologypartners.beganretail.util.Constant.DETECT_AMOUNT;
import static com.frontiertechnologypartners.beganretail.util.Constant.MOBILE_NO;
import static com.frontiertechnologypartners.beganretail.util.Constant.MPT;
import static com.frontiertechnologypartners.beganretail.util.Constant.MYTEL;
import static com.frontiertechnologypartners.beganretail.util.Constant.OOREDOO;
import static com.frontiertechnologypartners.beganretail.util.Constant.PROVIDER;
import static com.frontiertechnologypartners.beganretail.util.Constant.TELENOL;
import static com.frontiertechnologypartners.beganretail.util.Constant.TOP_UP_AMOUNT;
import static com.frontiertechnologypartners.beganretail.util.Constant.USER_ID;

public class TopupActivity extends BaseActivity implements MessageDialog.OnClickListener {

    @BindView(R.id.tv_mobile)
    TextView tvMobile;

    @BindView(R.id.tv_top_up_amount)
    TextView tvTopUpAmount;

    @BindView(R.id.tv_detect_amount)
    TextView tvDetectAmount;

    @BindView(R.id.iv_operator)
    ImageView operatorLogo;

    private double detectAmount;
    private String mobile;
    private String amount;
    private String provider;
    private String userId;
    private String topUpSuccess = "";

    @Inject
    ViewModelFactory viewModelFactory;
    private TopupViewModel topupViewModel;
    private String[] detectTopUpAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            if (MMFontUtils.isSupportUnicode(this)) {
                getSupportActionBar().setTitle(getString(R.string.top_up));
            } else {
                getSupportActionBar().setTitle(Html.fromHtml(MMFontUtils.uni2zg(getString(R.string.top_up))));
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        topupViewModel = ViewModelProviders.of(this, viewModelFactory).get(TopupViewModel.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mobile = extras.getString(MOBILE_NO);
            amount = extras.getString(TOP_UP_AMOUNT);
            detectAmount = extras.getDouble(DETECT_AMOUNT);
            provider = extras.getString(PROVIDER);
            userId = extras.getString(USER_ID);

            //show operator logo
            switch (provider) {
                case MPT:
                    operatorLogo.setImageResource(R.drawable.ic_mpt);
                    break;
                case OOREDOO:
                    operatorLogo.setImageResource(R.drawable.ic_ooredoo);
                    break;
                case TELENOL:
                    operatorLogo.setImageResource(R.drawable.ic_telenol);
                    break;
                case MYTEL:
                    operatorLogo.setImageResource(R.drawable.ic_mytel);
                    break;
            }
        }

        //top up info
        tvMobile.setText(getString(R.string.top_up_mobile, mobile));
        tvTopUpAmount.setText(getString(R.string.top_up_amount, Util.convertAmountWithSeparator(Double.parseDouble(amount))));
        detectTopUpAmount = Util.convertAmountWithSeparator(detectAmount).split("\\.");
        tvDetectAmount.setText(getString(R.string.top_up_detect_amount, detectTopUpAmount[0]));

        observeTopUp();
    }

    private void observeTopUp() {
        topupViewModel.topUpResponse.observe(this, this::consumeTopUpResponse);
    }

    private void consumeTopUpResponse(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                TopUpResponse topUpResponse = (TopUpResponse) apiResponse.data;
                if (topUpResponse != null) {
                    if (topUpResponse.getStatus().getCode() == 1) {
                        topUpSuccess = topUpResponse.getStatus().getMessage();
                        messageDialog.setListener(this);
                        messageDialog.show();
                        messageDialog.loadingMessage(getString(R.string.success_top_up));
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(topUpResponse.getStatus().getMessage());
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


    @OnClick(R.id.btn_continue)
    void onClickContinue() {
        topupViewModel.topUp(userId, provider, mobile, amount, detectTopUpAmount[0]);
    }

    @Override
    public void onOkButtonClick() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        overridePendingTransition(0, 0);
        startActivity(mainIntent);
        overridePendingTransition(0, 0);
    }

}