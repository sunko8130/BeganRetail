package com.frontiertechnologypartners.beganretail.ui.pricing;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.SalesItem;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.AddSalesActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesItemAdapter;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.MMEditText;
import com.google.android.material.textfield.TextInputEditText;

import org.mmtextview.components.MMButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class PricingActivity extends BaseActivity implements OnRecyclerItemClickListener {

    private AddPriceItemAdapter addPriceItemAdapter;
    private List<SalesItem> addPriceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        ButterKnife.bind(this);
        addPriceList = new ArrayList<>();
    }

    @OnClick(R.id.btn_search)
    void btnSearch() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_new_price, null);
        dialogBuilder.setView(dialogView);

        AppCompatSpinner spinnerItemName = dialogView.findViewById(R.id.spinner_item_name);
        AppCompatSpinner spinnerUOM = dialogView.findViewById(R.id.spinner_uom);
        TextInputEditText etPrice = dialogView.findViewById(R.id.et_price);
        MMButton btnSave = dialogView.findViewById(R.id.btn_save);
        MMButton btnCancel = dialogView.findViewById(R.id.btn_cancel);
        MMButton btnAddPrice = dialogView.findViewById(R.id.btn_add_price);
        RecyclerView rvSetPriceItems = dialogView.findViewById(R.id.rv_set_price_items);

        //init rv
        rvSetPriceItems.setLayoutManager(new LinearLayoutManager(mActivity));
        rvSetPriceItems.setNestedScrollingEnabled(false);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvSetPriceItems);
        addPriceItemAdapter = new AddPriceItemAdapter(mActivity, this);
        rvSetPriceItems.setAdapter(addPriceItemAdapter);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnAddPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "click", Toast.LENGTH_SHORT).show();
                //hide keyboard
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);

                String price = etPrice.getText().toString();
                if (!TextUtils.isEmpty(price)) {
                    SalesItem salesItem = new SalesItem(price, "price", "");
                    addPriceList.add(salesItem);
                    addPriceItemAdapter.add(salesItem);
                } else {
                    Util.customToastMessage(PricingActivity.this, getString(R.string.save_alert), false);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            SalesItem salesItem = addPriceItemAdapter.getItem(viewHolder.getAdapterPosition());
            addPriceList.remove(salesItem);
            addPriceItemAdapter.remove(salesItem);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(PricingActivity.this, R.color.md_red_600))
                    .addActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();
        }
    };

    @Override
    public void onItemClick(int position) {

    }
}