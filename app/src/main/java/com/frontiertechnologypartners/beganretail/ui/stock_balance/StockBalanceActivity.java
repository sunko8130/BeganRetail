package com.frontiertechnologypartners.beganretail.ui.stock_balance;

import android.os.Bundle;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StockBalanceActivity extends BaseActivity {

//    @BindView(R.id.spinner_main_category)
//    AppCompatSpinner spinnerMainCategory;
//
//    @BindView(R.id.spinner_sub_category)
//    AppCompatSpinner spinnerSubCategory;

    @BindView(R.id.et_item_code)
    TextInputEditText etItemCode;

    @BindView(R.id.et_item_name)
    TextInputEditText etItemName;

    @BindView(R.id.rv_stock_balance)
    RecyclerView rvStockBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_balance);
        ButterKnife.bind(this);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStockBalance.setLayoutManager(layoutManager);
        rvStockBalance.setHasFixedSize(true);
        rvStockBalance.setItemAnimator(new DefaultItemAnimator());

    }
}