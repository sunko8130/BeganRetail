package com.frontiertechnologypartners.beganretail.ui.emoney_request;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import org.mmtextview.MMFontUtils;

import static com.frontiertechnologypartners.beganretail.util.Constant.SVA_AMOUNT;

public class EmoneyRequestActivity extends BaseActivity {

    @BindView(R.id.et_request_amount)
    TextInputEditText etRequestAmount;

    private int svaAmount;
    private int request_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoney_request);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            if (MMFontUtils.isSupportUnicode(this)) {
                getSupportActionBar().setTitle(getString(R.string.withdraw_money_title));
            } else {
                getSupportActionBar().setTitle(Html.fromHtml(MMFontUtils.uni2zg(getString(R.string.withdraw_money_title))));
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            svaAmount = extras.getInt(SVA_AMOUNT);
        }
    }

    @OnClick(R.id.btn_withdraw)
    void onClickWithdraw() {

        hideKeyboard();
        request_amount = Integer.parseInt(etRequestAmount.getText().toString());
        if (request_amount > svaAmount) {
            messageDialog.show();
            messageDialog.loadingMessage(getString(R.string.alert_req_amt_greater_than_sav_amount));
        } else {
            EmoneyRequestConfirmFragment requestConfirmFragment = EmoneyRequestConfirmFragment.newInstance(request_amount);
            requestConfirmFragment.show(getSupportFragmentManager(), "emoney request confirm");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}