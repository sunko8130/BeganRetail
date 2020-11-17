package com.frontiertechnologypartners.beganretail.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.ll_sales)
    LinearLayout layoutSales;

    @BindView(R.id.ll_payment)
    LinearLayout layoutPayment;

    @BindView(R.id.ll_items)
    LinearLayout layoutItems;

    @BindView(R.id.ll_receive_items)
    LinearLayout layoutReceiveItems;

    @BindView(R.id.ll_main_categories)
    LinearLayout layoutMainCategories;

    @BindView(R.id.ll_sub_categories)
    LinearLayout layoutSubCategories;

    @BindView(R.id.ll_selling_price)
    LinearLayout layoutSellingPrice;

    @BindView(R.id.ll_stock_balance)
    LinearLayout layoutStockBalance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutSales.setOnClickListener(this);
        layoutPayment.setOnClickListener(this);
        layoutItems.setOnClickListener(this);
        layoutReceiveItems.setOnClickListener(this);
        layoutMainCategories.setOnClickListener(this);
        layoutSubCategories.setOnClickListener(this);
        layoutSellingPrice.setOnClickListener(this);
        layoutStockBalance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LinearLayout selectedLayout = (LinearLayout) v;

        // clear state
        layoutSales.setSelected(false);
        layoutSales.setPressed(false);
        layoutPayment.setSelected(false);
        layoutPayment.setPressed(false);
        layoutItems.setSelected(false);
        layoutItems.setPressed(false);
        layoutReceiveItems.setSelected(false);
        layoutReceiveItems.setPressed(false);
        layoutMainCategories.setSelected(false);
        layoutMainCategories.setPressed(false);
        layoutSubCategories.setSelected(false);
        layoutSubCategories.setPressed(false);
        layoutSellingPrice.setSelected(false);
        layoutSellingPrice.setPressed(false);
        layoutStockBalance.setSelected(false);
        layoutStockBalance.setPressed(false);

        // change state
        selectedLayout.setSelected(true);
        selectedLayout.setPressed(false);

        switch (v.getId()) {
            case R.id.ll_sales:
//                if (getFragmentManager() != null) {
//                    getFragmentManager().beginTransaction().replace(R.id.frame, new CreateVendorFragment())
//                            .commit();
//                }
                break;
            case R.id.ll_payment:
                break;
            case R.id.ll_items:
                break;
            case R.id.ll_receive_items:
                break;
            case R.id.ll_main_categories:
                break;
            case R.id.ll_sub_categories:
                break;
            case R.id.ll_selling_price:
                break;
            case R.id.ll_stock_balance:
                break;
            default:
                break;
        }
    }
}