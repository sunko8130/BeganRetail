package com.frontiertechnologypartners.beganretail.network;

import com.frontiertechnologypartners.beganretail.model.BalanceResponse;
import com.frontiertechnologypartners.beganretail.model.CashOutTransactionResponse;
import com.frontiertechnologypartners.beganretail.model.CashoutResponse;
import com.frontiertechnologypartners.beganretail.model.CategoryResponse;
import com.frontiertechnologypartners.beganretail.model.DeleteSaleItemResponse;
import com.frontiertechnologypartners.beganretail.model.DeliverNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.DeliveryOrdersResponse;
import com.frontiertechnologypartners.beganretail.model.GenerateReceiveNoResponse;
import com.frontiertechnologypartners.beganretail.model.GenerateSaleNoResponse;
import com.frontiertechnologypartners.beganretail.model.ItemSearchResponse;
import com.frontiertechnologypartners.beganretail.model.ItemsPriceResponse;
import com.frontiertechnologypartners.beganretail.model.ItemsResponse;
import com.frontiertechnologypartners.beganretail.model.LoginResponse;
import com.frontiertechnologypartners.beganretail.model.MerchantItemsResponse;
import com.frontiertechnologypartners.beganretail.model.NRCFormatResponse;
import com.frontiertechnologypartners.beganretail.model.NewReceiveSaveResponse;
import com.frontiertechnologypartners.beganretail.model.PaymentResponse;
import com.frontiertechnologypartners.beganretail.model.PreSaleResponse;
import com.frontiertechnologypartners.beganretail.model.PreTopUpResponse;
import com.frontiertechnologypartners.beganretail.model.ProvidersResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemViewResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemsResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SVAResponse;
import com.frontiertechnologypartners.beganretail.model.SaleNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrderDetailResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrdersSaveResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrdersSearchResponse;
import com.frontiertechnologypartners.beganretail.model.SetSellingPriceResponse;
import com.frontiertechnologypartners.beganretail.model.StateCityResponse;
import com.frontiertechnologypartners.beganretail.model.StockBalanceResponse;
import com.frontiertechnologypartners.beganretail.model.TopUpResponse;
import com.frontiertechnologypartners.beganretail.model.UOMResponse;
import com.frontiertechnologypartners.beganretail.model.UpdateSaleItemResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class NetworkRepository {

    private RetrofitService retrofitService;

    @Inject
    public NetworkRepository(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    public Single<StateCityResponse> stateCity() {
        return retrofitService.stateCity();
    }

    public Single<NRCFormatResponse> nrcFormat(Map<String, Object> body) {
        return retrofitService.nrcFormat(body);
    }

    public Single<LoginResponse> login(Map<String, Object> fields) {
        return retrofitService.login(fields);
    }

    public Single<GenerateReceiveNoResponse> generateReceiveNo() {
        return retrofitService.generateReceiveNo();
    }

    public Single<DeliverNumbersResponse> deliverNumbers(int id) {
        return retrofitService.deliverNumbers(id);
    }

    public Single<DeliverNumbersResponse> deliveredNumbers(int id) {
        return retrofitService.deliveredNumbers(id);
    }

    public Single<DeliveryOrdersResponse> deliveryOrders(String orderNumber) {
        return retrofitService.deliveryOrders(orderNumber);
    }

    public Single<NewReceiveSaveResponse> newReceiveSave(String receiveNo, String receiveBy, String orderNo, int merchantId) {
        return retrofitService.newReceiveSave(receiveNo, receiveBy, orderNo, merchantId);
    }

    public Single<ReceiveNumbersResponse> receiveNumbers(int id) {
        return retrofitService.receiverNumbers(id);
    }

    public Single<ReceiveItemsResponse> receiveItems(String receiveNo, String startDate, String endDate, int merchantId) {
        return retrofitService.receiveItems(receiveNo, startDate, endDate, merchantId);
    }

    public Single<ReceiveItemViewResponse> receiveItemView(String receiveNo, String receiverName) {
        return retrofitService.receiveItemView(receiveNo, receiverName);
    }

    public Single<CategoryResponse> category() {
        return retrofitService.category();
    }

    public Single<CategoryResponse> allCategory() {
        return retrofitService.allCategory();
    }

    public Single<ItemsResponse> items() {
        return retrofitService.items();
    }

    public Single<MerchantItemsResponse> merchantItems(int id) {
        return retrofitService.merchantItems(id);
    }

    public Single<MerchantItemsResponse> allMerchantItems(int id) {
        return retrofitService.allMerchantItems(id);
    }

    public Single<ItemsPriceResponse> itemsPriceSearch(String itemCode, int id) {
        return retrofitService.itemsPriceSearch(itemCode, id);
    }

    public Single<SetSellingPriceResponse> setSellingPrice(int id, double price) {
        return retrofitService.setSellingPrice(id, price);
    }

    public Single<UOMResponse> UOM(String itemCode, int id) {
        return retrofitService.UOM(itemCode, id);
    }

    public Single<GenerateSaleNoResponse> generateSaleNo() {
        return retrofitService.generateSaleNo();
    }

    public Single<PreSaleResponse> preSale(String itemCode, int uomId, int merchantId, int qty) {
        return retrofitService.preSale(itemCode, uomId, merchantId, qty);
    }

    public Single<SaleOrdersSaveResponse> saleOrdersSave(Map<String, Object> fields) {
        return retrofitService.saleOrdersSave(fields);
    }

    public Single<BalanceResponse> balance(int id) {
        return retrofitService.balance(id);
    }

    public Single<SaleOrdersSearchResponse> saleOrdersSearch(String saleNo,
                                                             String startDate,
                                                             String endDate,
                                                             int merchantId) {
        return retrofitService.saleOrdersSearch(saleNo, startDate, endDate, merchantId);
    }

    public Single<SaleNumbersResponse> saleNumbers(int id) {
        return retrofitService.saleNumbers(id);
    }

    public Single<SaleOrderDetailResponse> saleOrdersDatail(String receiveNo, double price) {
        return retrofitService.saleOrderDetail(receiveNo, price);
    }

    public Single<StockBalanceResponse> stockBalance(int categoryId, int itemId, int merchantId) {
        return retrofitService.stockBalance(categoryId, itemId, merchantId);
    }

    public Single<ItemSearchResponse> searchItems(int categoryId, int itemId, int merchantId) {
        return retrofitService.searchItems(categoryId, itemId, merchantId);
    }

    public Single<PaymentResponse> payment(int merchantId, String orderNo, String startDate, String endDate) {
        return retrofitService.payment(merchantId, orderNo, startDate, endDate);
    }

    public Single<DeleteSaleItemResponse> deleteSaleItem(int id, String itemCode, int uomId, int merchantId, int qty, String salesNo, int isLast) {
        return retrofitService.deleteSaleItem(id, itemCode, uomId, merchantId, qty, salesNo, isLast);
    }

    public Single<UpdateSaleItemResponse> updateSaleItem(int id, String itemCode, int uomId, int merchantId, int qty, String salesNo) {
        return retrofitService.updateSaleItem(id, itemCode, uomId, merchantId, qty, salesNo);
    }


    public Single<ProvidersResponse> getProviders() {
        return retrofitService.getProviders();
    }

    public Single<PreTopUpResponse> preTopUp(String userId, String amount, String provider) {
        return retrofitService.preTopUp(userId, amount, provider);
    }

    public Single<TopUpResponse> topUp(String userId, String provider, String mobile,
                                       String amount, String discountAmount) {
        return retrofitService.topUp(userId, provider, mobile, amount, discountAmount);
    }

    public Single<SVAResponse> refreshAmount(int userId) {
        return retrofitService.refreshAmount(userId);
    }

    public Single<CashoutResponse> cashOut(String userId, String amount) {
        return retrofitService.cashOut(userId, amount);
    }

    public Single<CashOutTransactionResponse> cashOutSearch(String userId, String cashoutStatus,
                                                            String reqStartDate, String reqEndDate,
                                                            String reqDeliStartDate, String reqDeliEndDate) {
        return retrofitService.cashoutSearch(userId, cashoutStatus, reqStartDate, reqEndDate,
                reqDeliStartDate, reqDeliEndDate);
    }

}
