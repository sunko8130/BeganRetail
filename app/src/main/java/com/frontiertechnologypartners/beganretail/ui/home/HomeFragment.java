package com.frontiertechnologypartners.beganretail.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.model.BalanceResponse;
import com.frontiertechnologypartners.beganretail.model.LoginData;
import com.frontiertechnologypartners.beganretail.model.SVAResponse;
import com.frontiertechnologypartners.beganretail.model.SetSellingPriceResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseFragment;
import com.frontiertechnologypartners.beganretail.ui.category.CategorySearchActivity;
import com.frontiertechnologypartners.beganretail.ui.emoney_request.EmoneyRequestActivity;
import com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction.EmoneyRequestTransactionActivity;
import com.frontiertechnologypartners.beganretail.ui.items.ItemsActivity;
import com.frontiertechnologypartners.beganretail.ui.payment.PaymentActivity;
import com.frontiertechnologypartners.beganretail.ui.pricing.PricingActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.AddNewReceiveItemActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.AddSalesActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesActivity;
import com.frontiertechnologypartners.beganretail.ui.stock_balance.StockBalanceActivity;
import com.frontiertechnologypartners.beganretail.ui.topup.PreTopupActivity;
import com.frontiertechnologypartners.beganretail.ui.topup.PreTopupFragment;
import com.frontiertechnologypartners.beganretail.ui.topup.TopupActivity;
import com.frontiertechnologypartners.beganretail.util.Util;

import javax.inject.Inject;

import static com.frontiertechnologypartners.beganretail.util.Constant.LOGIN_DATA;
import static com.frontiertechnologypartners.beganretail.util.Constant.SVA_AMOUNT;
import static com.frontiertechnologypartners.beganretail.util.Constant.UPDATE_AMOUNT;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.ll_sales)
    LinearLayout layoutSales;

    @BindView(R.id.ll_payment)
    LinearLayout layoutPayment;

    @BindView(R.id.ll_items)
    LinearLayout layoutItems;

    @BindView(R.id.ll_receive_items)
    LinearLayout layoutReceiveItems;

    @BindView(R.id.ll_selling_price)
    LinearLayout layoutSellingPrice;

    @BindView(R.id.ll_stock_balance)
    LinearLayout layoutStockBalance;

    @BindView(R.id.ll_top_up)
    LinearLayout layoutTopUp;

    @BindView(R.id.ll_request_money)
    LinearLayout layoutRequestMoney;

    @BindView(R.id.ll_cashout_list)
    LinearLayout layoutCashoutList;

    @BindView(R.id.tv_today_sales_amount)
    TextView tvTotalSalesAmount;

    @BindView(R.id.tv_user_amount)
    TextView tvUserAmount;

    @Inject
    ViewModelFactory viewModelFactory;
    private HomeViewModel homeViewModel;
    private LoginData loginData;
    private int merchantId;
    private int updateAmount;
    private String userAmount;

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
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        //login data
        loginData = Paper.book().read(LOGIN_DATA);
        if (loginData != null) {
            merchantId = loginData.getId();
        }

        //observe balance
        homeViewModel.balance(merchantId);
        observeBalance();

        homeViewModel.refreshAmount(merchantId);
        observeRefreshAmount();
    }

    private void observeBalance() {
        homeViewModel.balanceResponse.observe(this, this::consumeBalance);
    }

    private void consumeBalance(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                BalanceResponse balanceResponse = (BalanceResponse) apiResponse.data;
                if (balanceResponse != null) {
                    int statusCode = balanceResponse.getStatus().getCode();
                    if (statusCode == 1) {
                        String totalSalesAmount = Util.convertAmountWithSeparator(balanceResponse.getData());
                        tvTotalSalesAmount.setText(Html.fromHtml(getResources().getString(R.string.total_today_sales_amount, totalSalesAmount)));
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

    private void observeRefreshAmount() {
        homeViewModel.svaResponse.observe(this, this::consumeSVAResponse);
    }

    private void consumeSVAResponse(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                SVAResponse refreshAmount = (SVAResponse) apiResponse.data;
                if (refreshAmount != null) {
                    userAmount = Util.convertAmountWithSeparator(refreshAmount.getSva());
                    tvUserAmount.setText(Html.fromHtml(getResources().getString(R.string.user_amount, userAmount)));
                }
                break;
            case ERROR:
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutSales.setOnClickListener(this);
        layoutPayment.setOnClickListener(this);
        layoutItems.setOnClickListener(this);
        layoutReceiveItems.setOnClickListener(this);
        layoutSellingPrice.setOnClickListener(this);
        layoutStockBalance.setOnClickListener(this);
        layoutTopUp.setOnClickListener(this);
        layoutRequestMoney.setOnClickListener(this);
        layoutCashoutList.setOnClickListener(this);
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
        layoutSellingPrice.setSelected(false);
        layoutSellingPrice.setPressed(false);
        layoutStockBalance.setSelected(false);
        layoutStockBalance.setPressed(false);
        layoutTopUp.setSelected(false);
        layoutTopUp.setPressed(false);
        layoutRequestMoney.setSelected(false);
        layoutRequestMoney.setPressed(false);
        layoutCashoutList.setSelected(false);
        layoutCashoutList.setPressed(false);

        // change state
        selectedLayout.setSelected(true);
        selectedLayout.setPressed(false);

        switch (v.getId()) {
            case R.id.ll_sales:
                Intent salesIntent = new Intent(getActivity(), SalesActivity.class);
                startActivity(salesIntent);
                break;
            case R.id.ll_payment:
                Intent paymentIntent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(paymentIntent);
                break;
            case R.id.ll_items:
                Intent searchCategoryIntent = new Intent(getActivity(), ItemsActivity.class);
                startActivity(searchCategoryIntent);
                break;
            case R.id.ll_receive_items:
                Intent receiveItemsIntent = new Intent(getActivity(), ReceiveItemsActivity.class);
                startActivity(receiveItemsIntent);
                break;
            case R.id.ll_selling_price:
                Intent priceIntent = new Intent(getActivity(), PricingActivity.class);
                startActivity(priceIntent);
                break;
            case R.id.ll_stock_balance:
                Intent stockBalanceIntent = new Intent(getActivity(), StockBalanceActivity.class);
                startActivity(stockBalanceIntent);
                break;
            case R.id.ll_request_money:
                Intent requestMoneyIntent = new Intent(getActivity(), EmoneyRequestActivity.class);
                requestMoneyIntent.putExtra(SVA_AMOUNT, Integer.parseInt(userAmount.replace(",", "")));
                startActivity(requestMoneyIntent);
                break;
            case R.id.ll_top_up:
                Intent topupIntent = new Intent(getActivity(), PreTopupActivity.class);
                startActivity(topupIntent);
                break;
            case R.id.ll_cashout_list:
                Intent transactionIntent = new Intent(getActivity(), EmoneyRequestTransactionActivity.class);
                startActivity(transactionIntent);
                break;
            default:
                break;
        }
    }
}