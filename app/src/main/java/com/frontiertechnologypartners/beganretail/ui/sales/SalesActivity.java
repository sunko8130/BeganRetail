package com.frontiertechnologypartners.beganretail.ui.sales;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerItemClickListener;
import com.frontiertechnologypartners.beganretail.delegate.OnRecyclerMultiItemClickListener;
import com.frontiertechnologypartners.beganretail.model.LoginData;
import com.frontiertechnologypartners.beganretail.model.OrderItem;
import com.frontiertechnologypartners.beganretail.model.ReceiveItem;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemView;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemViewResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemsResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SaleDetailsItem;
import com.frontiertechnologypartners.beganretail.model.SaleNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrder;
import com.frontiertechnologypartners.beganretail.model.SaleOrderDetail;
import com.frontiertechnologypartners.beganretail.model.SaleOrderDetailResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrdersSearchResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.AddNewReceiveItemsAdapter;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemDetailFragment;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsAdapter;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.MessageDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.mmtextview.MMFontUtils;
import org.mmtextview.components.MMTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
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

public class SalesActivity extends BaseActivity implements MessageDialog.OnClickListener, OnRecyclerItemClickListener, OnRecyclerMultiItemClickListener, SaleItemUpdateFragment.ReloadListener {
    @BindView(R.id.btn_from_date)
    Button btnFromDate;

    @BindView(R.id.btn_to_date)
    Button btnToDate;

    @BindView(R.id.spinner_sales_number)
    AppCompatSpinner spinnerSaleNumbers;

    @BindView(R.id.rv_sales)
    RecyclerView rvSales;

    @BindView(R.id.tv_date)
    MMTextView tvDate;

    @BindView(R.id.tv_total_amount)
    MMTextView tvTotalAmount;

    @BindView(R.id.tv_no_sale_order)
    MMTextView tvNoSaleOrder;

    @Inject
    ViewModelFactory viewModelFactory;

    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener fromDateListener;
    private DatePickerDialog.OnDateSetListener toDateListener;
    private String fromDate = "";
    private String toDate = "";
    private Date startDate = null, endDate = null;
    private SalesViewModel salesViewModel;
    private LoginData loginData;
    private int merchantId;
    private String saleNumber = "All";
    private SalesAdapter saleOrderAdapter;
    private TextView tvSaleOderNumber, tvSaleAmount;
    private RecyclerView rvSaleOrders;
    private SaleOrderDetailAdapter saleOrderDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            if (MMFontUtils.isSupportUnicode(this)) {
                getSupportActionBar().setTitle(getString(R.string.sales));
            } else {
                getSupportActionBar().setTitle(Html.fromHtml(MMFontUtils.uni2zg(getString(R.string.sales))));
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        salesViewModel = ViewModelProviders.of(this, viewModelFactory).get(SalesViewModel.class);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvSales.setLayoutManager(layoutManager);
        rvSales.setHasFixedSize(true);
        rvSales.setNestedScrollingEnabled(false);
        rvSales.setItemAnimator(new DefaultItemAnimator());
        saleOrderAdapter = new SalesAdapter(this, this);

        //login data
        loginData = Paper.book().read(LOGIN_DATA);
        if (loginData != null) {
            merchantId = loginData.getId();
        }

        //choose date
        chooseDate();

        //observe sale numbers
        observeSaleNumbers();
        salesViewModel.saleNumbers(merchantId);

        //retrieve all sale orders
        saleOrders();
        observeSaleOrders();
    }

    private void observeSaleOrders() {
        salesViewModel.saleOrdersSearchResponse.observe(this, this::consumeSaleOrders);
    }

    private void consumeSaleOrders(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                SaleOrdersSearchResponse saleOrdersSearchResponse = (SaleOrdersSearchResponse) apiResponse.data;
                if (saleOrdersSearchResponse != null) {
                    int statusCode = saleOrdersSearchResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        if (TextUtils.isEmpty(fromDate) && TextUtils.isEmpty(toDate)) {
                            tvDate.setVisibility(View.GONE);
                        } else {
                            tvDate.setVisibility(View.VISIBLE);
                            tvDate.setText(Html.fromHtml(getString(R.string.search_date, fromDate, toDate)));
                        }
                        //clear date
                        btnFromDate.setText(getString(R.string.receive_date_from));
                        btnToDate.setText(getString(R.string.receive_date_to));
                        fromDate = "";
                        toDate = "";

                        List<SaleOrder> saleOrders = saleOrdersSearchResponse.getData();
                        if (saleOrders != null && saleOrders.size() > 0) {
                            //show total amount
                            double totalAmount = 0;
                            for (SaleOrder saleOrder : saleOrders) {
                                totalAmount += saleOrder.getSalesAmount();
                            }
                            String totalSaleAmount = Util.convertAmountWithSeparator(totalAmount);
                            tvTotalAmount.setText(Html.fromHtml(mActivity.getResources().getString(R.string.total_order_price, totalSaleAmount)));

                            rvSales.setAdapter(saleOrderAdapter);
                            saleOrderAdapter.setItems(saleOrders);
                            tvNoSaleOrder.setVisibility(View.GONE);
                            tvTotalAmount.setVisibility(View.VISIBLE);
                        } else {
                            tvTotalAmount.setVisibility(View.GONE);
                            tvNoSaleOrder.setVisibility(View.VISIBLE);
                        }
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(saleOrdersSearchResponse.getStatus().getMessage());
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

    private void observeSaleNumbers() {
        salesViewModel.saleNumbersResponse.observe(this, this::consumeSaleNumber);
    }

    private void consumeSaleNumber(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                SaleNumbersResponse saleNumbersResponse = (SaleNumbersResponse) apiResponse.data;
                if (saleNumbersResponse != null) {
                    int statusCode = saleNumbersResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        List<String> saleOrders = saleNumbersResponse.getData();
                        if (saleOrders != null && saleOrders.size() > 0) {
                            ArrayAdapter<String> saleNumbersAdapter = new ArrayAdapter<>(
                                    this, R.layout.spinner_item, R.id.spinnerText, saleOrders);
                            spinnerSaleNumbers.setAdapter(saleNumbersAdapter);
                        } else {
                            messageDialog.show();
                            messageDialog.loadingMessage(getString(R.string.no_receive_numbers_alert));
                            messageDialog.setListener(this);
                        }
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(saleNumbersResponse.getStatus().getMessage());
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

    @OnClick(R.id.btn_add_sales_item)
    void addSaleItem() {
        Intent salesIntent = new Intent(SalesActivity.this, AddSalesActivity.class);
        startActivity(salesIntent);
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
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromDate = sdf.format(calendar.getTime());
        startDate = calendar.getTime();
        btnFromDate.setText(sdf.format(calendar.getTime()));
    }

    private void toDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toDate = sdf.format(calendar.getTime());
        endDate = calendar.getTime();
        btnToDate.setText(sdf.format(calendar.getTime()));
    }

    @OnClick(R.id.btn_search)
    void SaleOrdersSearch() {
        tvTotalAmount.setVisibility(View.GONE);
        tvDate.setVisibility(View.GONE);
        rvSales.setAdapter(null);
        saleOrderAdapter.clear();

        saleNumber = spinnerSaleNumbers.getSelectedItem().toString();

        //date compare
        if (startDate != null && endDate != null) {
            if (startDate.compareTo(endDate) > 0) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.date_compare));
            } else {
                saleOrders();
            }
        } else {
            saleOrders();
        }

    }

    private void saleOrders() {
        salesViewModel.saleOrdersSearch(saleNumber, fromDate, toDate, merchantId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SalesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onOkButtonClick() {
        Intent intent = new Intent(SalesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemClick(int position) {
        SaleOrder saleOrder = saleOrderAdapter.getItem(position);

        SaleItemDetailFragment saleItemDetailFragment = SaleItemDetailFragment.newInstance(saleOrder);
        saleItemDetailFragment.setCancelable(false);
        saleItemDetailFragment.show(getSupportFragmentManager(), "sale detail");

//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
//        LayoutInflater inflater = mActivity.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.sale_order_detail_layout, null);
//        dialogBuilder.setView(dialogView);
//
//        tvSaleOderNumber = dialogView.findViewById(R.id.tv_sale_order_number);
//        tvSaleAmount = dialogView.findViewById(R.id.tv_sales_amount);
//        rvSaleOrders = dialogView.findViewById(R.id.rv_sale_orders);
//        ImageButton btnDelete = dialogView.findViewById(R.id.btn_delete);
//
//        salesViewModel.saleOrderDetail(saleOrder.getSalesNo(), saleOrder.getSalesAmount());
//        observeSaleOrderDetail();
//
//        //init rv
//        rvSaleOrders.setLayoutManager(new LinearLayoutManager(mActivity));
//        rvSaleOrders.setNestedScrollingEnabled(false);
//        saleOrderDetailAdapter = new SaleOrderDetailAdapter(mActivity, this);
//        rvSaleOrders.setAdapter(saleOrderDetailAdapter);
//
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        btnDelete.setOnClickListener(v -> {
//            alertDialog.dismiss();
//        });
    }

    @Override
    public void onClickAction(int pos, int value) {
        SaleOrder saleOrder = saleOrderAdapter.getItem(pos);

        SaleItemUpdateFragment saleItemUpdateFragment = SaleItemUpdateFragment.newInstance(saleOrder);
        saleItemUpdateFragment.setCancelable(false);
        saleItemUpdateFragment.show(getSupportFragmentManager(), "sale update");
    }

    private void observeSaleOrderDetail() {
        salesViewModel.saleOrderDetailResponse.observe(this, this::consumeSaleOrderDetail);
    }

    private void consumeSaleOrderDetail(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                SaleOrderDetailResponse saleOrderDetailResponse = (SaleOrderDetailResponse) apiResponse.data;
                if (saleOrderDetailResponse != null) {
                    int statusCode = saleOrderDetailResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        SaleOrderDetail saleOrderDetail = saleOrderDetailResponse.getData();
                        if (saleOrderDetail != null) {
                            tvSaleOderNumber.setText(saleOrderDetail.getSalesNo());
                            tvSaleAmount.setText(Html.fromHtml(mActivity.getResources().getString(R.string.order_item_price,
                                    Util.convertAmountWithSeparator(saleOrderDetail.getTotal()))));
                            List<SaleDetailsItem> saleDetailsItems = saleOrderDetail.getDetails();
                            if (saleDetailsItems != null && saleDetailsItems.size() > 0) {
                                rvSaleOrders.setAdapter(saleOrderDetailAdapter);
                                saleOrderDetailAdapter.setItems(saleDetailsItems);
                            }
                        }
                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(saleOrderDetailResponse.getStatus().getMessage());
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

    @Override
    public void reload() {
        tvTotalAmount.setVisibility(View.GONE);
        tvDate.setVisibility(View.GONE);
        rvSales.setAdapter(null);
        saleOrderAdapter.clear();
        saleNumber = spinnerSaleNumbers.getSelectedItem().toString();

        //date compare
        if (startDate != null && endDate != null) {
            if (startDate.compareTo(endDate) > 0) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.date_compare));
            } else {
                saleOrders();
            }
        } else {
            saleOrders();
        }
    }
}