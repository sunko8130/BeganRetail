package com.frontiertechnologypartners.beganretail.ui.payment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseActivity {
    @BindView(R.id.btn_from_date)
    MaterialButton btnFromDate;

    @BindView(R.id.btn_to_date)
    MaterialButton btnToDate;

    @BindView(R.id.rv_payment)
    RecyclerView rvPayment;

    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener fromDateListener;
    private DatePickerDialog.OnDateSetListener toDateListener;
    private String fromDate = "";
    private String toDate = "";
    private Date startDate = null, endDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPayment.setLayoutManager(layoutManager);
        rvPayment.setHasFixedSize(true);
        rvPayment.setItemAnimator(new DefaultItemAnimator());

        //choose date
        chooseDate();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void chooseDate() {
        //from date
        btnFromDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, fromDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        //to date
        btnToDate.setOnClickListener(view -> {
            hideKeyboard();
            new DatePickerDialog(this, toDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        fromDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fromDate();
        };

        toDateListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            toDate();
        };
    }

    private void fromDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromDate = sdf.format(calendar.getTime());
        startDate = calendar.getTime();
        btnFromDate.setText(sdf.format(calendar.getTime()));
    }

    private void toDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toDate = sdf.format(calendar.getTime());
        endDate = calendar.getTime();
        btnToDate.setText(sdf.format(calendar.getTime()));
    }
}