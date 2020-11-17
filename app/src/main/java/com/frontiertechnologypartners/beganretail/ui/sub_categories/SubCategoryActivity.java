package com.frontiertechnologypartners.beganretail.ui.sub_categories;

import android.os.Bundle;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;

import org.mmtextview.components.MMTextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends BaseActivity {

    @BindView(R.id.spinner_sub_category_name)
    AppCompatSpinner spinnerSubCategoryName;

    @BindView(R.id.spinner_main_category_name)
    AppCompatSpinner spinnerMainCategoryName;

    @BindView(R.id.rv_sub_category)
    RecyclerView rvSubCategory;

    @BindView(R.id.tv_total_sub_category_number)
    MMTextView tvTotalSubCategoryNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvSubCategory.setLayoutManager(layoutManager);
        rvSubCategory.setHasFixedSize(true);
        rvSubCategory.setItemAnimator(new DefaultItemAnimator());
    }
}