package com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.CashOutTransaction;
import com.frontiertechnologypartners.beganretail.model.CashOutTransactionResponse;
import com.frontiertechnologypartners.beganretail.model.CashoutStatus;
import com.frontiertechnologypartners.beganretail.model.LoginData;
import com.frontiertechnologypartners.beganretail.model.Payment;
import com.frontiertechnologypartners.beganretail.model.PaymentResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.emoney_request.CashOutViewModel;
import com.frontiertechnologypartners.beganretail.ui.payment.PaymentAdapter;
import com.frontiertechnologypartners.beganretail.ui.sales.UOMAdapter;

import org.mmtextview.MMFontUtils;
import org.mmtextview.components.MMTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

import static com.frontiertechnologypartners.beganretail.util.Constant.LOGIN_DATA;

public class EmoneyRequestTransactionActivity extends BaseActivity implements OnRecyclerItemClickListener {

    @BindView(R.id.btn_request_from_date)
    Button btnRequestFromDate;

    @BindView(R.id.btn_request_to_date)
    Button btnRequestToDate;

    @BindView(R.id.btn_cashout_from_date)
    Button btnCashoutFromDate;

    @BindView(R.id.btn_cashout_to_date)
    Button btnCashoutToDate;

    @BindView(R.id.rv_emoney_transaction)
    RecyclerView rvEmoneyTransaction;

    @BindView(R.id.tv_no_transaction)
    MMTextView tvNoTransaction;

    @BindView(R.id.spinner_status)
    AppCompatSpinner spinnerStatus;

    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener fromRequestDateListener;
    private DatePickerDialog.OnDateSetListener toRequestDateListener;
    private DatePickerDialog.OnDateSetListener fromCashoutDateListener;
    private DatePickerDialog.OnDateSetListener toCashoutDateListener;
    private String fromRequestDate = "";
    private String toRequestDate = "";
    private String fromCashoutDate = "";
    private String toCashoutDate = "";
    private Date startRequestDate = null, endRequestDate = null;
    private Date startCashoutDate = null, endCashoutDate = null;
    private CashoutStatusAdapter cashoutStatusAdapter;
    private String spinnerCashOutStatus = "All";
    @Inject
    ViewModelFactory viewModelFactory;

    private CashOutViewModel cashOutViewModel;
    private int merchantId;
    private EmoneyTransactionAdapter emoneyTransactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoney_request_transaction);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            if (MMFontUtils.isSupportUnicode(this)) {
                getSupportActionBar().setTitle(getString(R.string.emoney_transaction_title));
            } else {
                getSupportActionBar().setTitle(Html.fromHtml(MMFontUtils.uni2zg(getString(R.string.emoney_transaction_title))));
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        cashOutViewModel = ViewModelProviders.of(this, viewModelFactory).get(CashOutViewModel.class);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvEmoneyTransaction.setLayoutManager(layoutManager);
        rvEmoneyTransaction.setHasFixedSize(true);
        rvEmoneyTransaction.setNestedScrollingEnabled(false);
        rvEmoneyTransaction.setItemAnimator(new DefaultItemAnimator());
        emoneyTransactionAdapter = new EmoneyTransactionAdapter(this, this);

        //choose date
        chooseDate();

        List<CashoutStatus> cashoutStatus = new ArrayList<>();
        CashoutStatus cashoutStatus1 = new CashoutStatus("All", "အားလုံး");
        cashoutStatus.add(cashoutStatus1);

        CashoutStatus cashoutStatus2 = new CashoutStatus("0", "ဆိုင်းငံ့");
        cashoutStatus.add(cashoutStatus2);

        CashoutStatus cashoutStatus3 = new CashoutStatus("1", "ထုတ်ပြီး");
        cashoutStatus.add(cashoutStatus3);

        cashoutStatusAdapter = new CashoutStatusAdapter(
                mActivity, R.layout.spinner_item, R.id.spinnerText, cashoutStatus);
        spinnerStatus.setAdapter(cashoutStatusAdapter);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CashoutStatus cashoutStatus1 = cashoutStatusAdapter.getItem(position);
                spinnerCashOutStatus = cashoutStatus1.getKey();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //login data
        LoginData loginData = Paper.book().read(LOGIN_DATA);
        if (loginData != null) {
            merchantId = loginData.getId();
        }

        cashoutSearch();

        //observe cashout search
        observeCashOutSearch();

    }

    private void observeCashOutSearch() {
        cashOutViewModel.cashoutTransactionResponse.observe(this, this::consumeCashoutSearch);
    }

    private void consumeCashoutSearch(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                CashOutTransactionResponse cashOutTransactionResponse = (CashOutTransactionResponse) apiResponse.data;
                if (cashOutTransactionResponse != null) {
                    int statusCode = cashOutTransactionResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        //clear date
                        btnRequestFromDate.setText("");
                        btnRequestToDate.setText("");
                        fromRequestDate = "";
                        toRequestDate = "";

                        btnCashoutFromDate.setText("");
                        btnCashoutToDate.setText("");
                        fromCashoutDate = "";
                        toCashoutDate = "";

                        List<CashOutTransaction> cashOutTransactions = cashOutTransactionResponse.getList();
                        if (cashOutTransactions != null && cashOutTransactions.size() > 0) {
                            rvEmoneyTransaction.setAdapter(emoneyTransactionAdapter);
                            emoneyTransactionAdapter.setItems(cashOutTransactions);
                            tvNoTransaction.setVisibility(View.GONE);
                        } else {
                            tvNoTransaction.setVisibility(View.VISIBLE);
                        }
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(cashOutTransactionResponse.getStatus().getMessage());
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

    @SuppressLint("ClickableViewAccessibility")
    private void chooseDate() {
        //from date
        btnRequestFromDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, fromRequestDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnCashoutFromDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, fromCashoutDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        //to date
        btnRequestToDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, toRequestDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnCashoutToDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, toCashoutDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        fromRequestDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fromRequestDate();
        };

        fromCashoutDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fromCashoutDate();
        };

        toRequestDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            toRequestDate();
        };

        toCashoutDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            toCashoutDate();
        };
    }

    private void fromRequestDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromRequestDate = sdf.format(calendar.getTime());
        startRequestDate = calendar.getTime();
        btnRequestFromDate.setText(sdf.format(calendar.getTime()));
    }

    private void fromCashoutDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromCashoutDate = sdf.format(calendar.getTime());
        startCashoutDate = calendar.getTime();
        btnCashoutFromDate.setText(sdf.format(calendar.getTime()));
    }

    private void toRequestDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toRequestDate = sdf.format(calendar.getTime());
        endRequestDate = calendar.getTime();
        btnRequestToDate.setText(sdf.format(calendar.getTime()));
    }

    private void toCashoutDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toCashoutDate = sdf.format(calendar.getTime());
        endCashoutDate = calendar.getTime();
        btnCashoutToDate.setText(sdf.format(calendar.getTime()));
    }

    @OnClick(R.id.btn_search)
    void search() {
        rvEmoneyTransaction.setAdapter(null);
        emoneyTransactionAdapter.clear();
        //date compare
        if (startRequestDate != null && endRequestDate != null
                && startCashoutDate == null && endCashoutDate == null) {
            if (startRequestDate.compareTo(endRequestDate) > 0) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.date_compare));
            } else {
                cashoutSearch();
            }
        } else if (startCashoutDate != null && endCashoutDate != null
                && startRequestDate == null && endRequestDate == null) {
            if (startCashoutDate.compareTo(endCashoutDate) > 0) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.date_compare));
            } else {
                cashoutSearch();
            }
        } else {
            cashoutSearch();
        }

    }

    private void cashoutSearch() {
        cashOutViewModel.cashoutSearch(String.valueOf(merchantId), spinnerCashOutStatus,
                fromRequestDate, toRequestDate, fromCashoutDate, toCashoutDate);
    }

    @Override
    public void onItemClick(int position) {

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