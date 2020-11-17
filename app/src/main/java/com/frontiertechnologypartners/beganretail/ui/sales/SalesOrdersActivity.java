package com.frontiertechnologypartners.beganretail.ui.sales;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalesOrdersActivity extends BaseActivity {

    @BindView(R.id.btn_sale_date)
    MaterialButton btnSaleDate;

    @BindView(R.id.first_group)
    RadioGroup firstGroup;

    @BindView(R.id.second_group)
    RadioGroup secondGroup;

    @BindView(R.id.status_radio_group)
    RadioGroup statusRadioGroup;

    @BindView(R.id.et_owner_name)
    TextInputEditText etOwnerName;

    @BindView(R.id.et_customer_name)
    TextInputEditText etCustomerName;

    @BindView(R.id.et_tax)
    TextInputEditText etTax;

    @BindView(R.id.et_total_sales_amount)
    TextInputEditText etTotalSalesAmount;

    private boolean isChecking = true;
    private int mCheckedId = R.id.radio_myKyat;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener saleDateListener;
    private String saleDate = "";
    private Date salesDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_orders);
        ButterKnife.bind(this);

        //back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firstGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1 && isChecking) {
                isChecking = false;
                secondGroup.clearCheck();
                mCheckedId = checkedId;
            }
            isChecking = true;
        });

        secondGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1 && isChecking) {
                isChecking = false;
                firstGroup.clearCheck();
                mCheckedId = checkedId;
            }
            isChecking = true;
        });

        //choose date
        chooseDate();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void chooseDate() {
        btnSaleDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, saleDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        saleDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            saleDate();
        };
    }

    private void saleDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        saleDate = sdf.format(calendar.getTime());
        salesDate = calendar.getTime();
        btnSaleDate.setText(sdf.format(calendar.getTime()));
    }

    @OnClick(R.id.btn_sales)
    void btnSales() {
        RadioButton radioButton = (RadioButton) findViewById(mCheckedId);
        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        if (mCheckedId == R.id.radio_myKyat) {
            Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radio_wave) {
            Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radio_bagan_retail) {
            Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radio_cp) {
            Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radio_aya) {
            Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
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