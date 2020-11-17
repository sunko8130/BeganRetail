package com.frontiertechnologypartners.beganretail.ui.sales;

import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.model.SalesItem;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.MMEditText;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.mmtextview.MMFontUtils;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class AddSalesActivity extends BaseActivity implements OnRecyclerItemClickListener {

    @BindView(R.id.rv_sales_item)
    RecyclerView rvSalesItem;

    @BindView(R.id.layout_sales)
    LinearLayout layoutSale;

    private SalesItemAdapter salesItemAdapter;
    private List<SalesItem> salesItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        ButterKnife.bind(this);
        salesItemList = new ArrayList<>();

        //init rv
        rvSalesItem.setLayoutManager(new LinearLayoutManager(this));
        rvSalesItem.setHasFixedSize(true);
        rvSalesItem.setItemAnimator(new DefaultItemAnimator());
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvSalesItem);
        salesItemAdapter = new SalesItemAdapter(this, this);
        rvSalesItem.setAdapter(salesItemAdapter);
    }

    @OnClick(R.id.btn_add_sales_item)
    void addSalesItem() {
        addSalesItemView();
    }

    private void addSalesItemView() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_sales_item, null);
        dialogBuilder.setView(dialogView);

        AppCompatSpinner spinnerItemName = dialogView.findViewById(R.id.spinner_item_name);
        MMEditText etQty = dialogView.findViewById(R.id.et_quantity);
        AppCompatSpinner spinnerUOM = dialogView.findViewById(R.id.spinner_uom);
        TextInputEditText etPrice = dialogView.findViewById(R.id.et_price);
        TextInputEditText etAmount = dialogView.findViewById(R.id.et_amount);
        MMButton btnSave = dialogView.findViewById(R.id.btn_save);
        MMButton btnCancel = dialogView.findViewById(R.id.btn_cancel);

        if (MMFontUtils.isSupportUnicode(this)) {
            etQty.setHint(getResources().getString(R.string.quantity));
        } else {
            etQty.setHint(Html.fromHtml(MMFontUtils.uni2zg(getString(R.string.quantity))));
        }

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnSave.setOnClickListener(v -> {
            String qty = etQty.getText().toString();
            String price = etPrice.getText().toString();
            String amount = etAmount.getText().toString();

            if (!TextUtils.isEmpty(qty) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(amount)) {
                SalesItem salesItem = new SalesItem(qty, price, amount);
                salesItemList.add(salesItem);
                salesItemAdapter.add(salesItem);
                alertDialog.dismiss();
            } else {
//                    Snackbar snackbar = Snackbar.make(layoutSale, getString(R.string.save_alert), Snackbar.LENGTH_LONG);
//                    MMFontUtils.applyMMFontToSnackBar(snackbar);
//                    snackbar.show();
                Util.customToastMessage(AddSalesActivity.this, getString(R.string.save_alert), false);
            }
        });

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            SalesItem salesItem = salesItemAdapter.getItem(viewHolder.getAdapterPosition());
            salesItemList.remove(salesItem);
            salesItemAdapter.remove(salesItem);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(AddSalesActivity.this, R.color.md_red_600))
                    .addActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();
        }
    };

    @Override
    public void onItemClick(int position) {
//        SalesItem salesItem = salesItemAdapter.getItem(position);
//        salesItemList.remove(salesItem);
//        salesItemAdapter.remove(salesItem);
    }
}