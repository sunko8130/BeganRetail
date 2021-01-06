package com.frontiertechnologypartners.beganretail.network;

import com.frontiertechnologypartners.beganretail.model.BalanceResponse;
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
import com.frontiertechnologypartners.beganretail.model.ReceiveItemViewResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveItemsResponse;
import com.frontiertechnologypartners.beganretail.model.ReceiveNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SaleNumbersResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrderDetailResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrdersSaveResponse;
import com.frontiertechnologypartners.beganretail.model.SaleOrdersSearchResponse;
import com.frontiertechnologypartners.beganretail.model.SetSellingPriceResponse;
import com.frontiertechnologypartners.beganretail.model.StateCityResponse;
import com.frontiertechnologypartners.beganretail.model.StockBalanceResponse;
import com.frontiertechnologypartners.beganretail.model.UOMResponse;
import com.frontiertechnologypartners.beganretail.model.UpdateSaleItemResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("b2bstate-list")
    Single<StateCityResponse> stateCity();

    @POST("state-code")
    Single<NRCFormatResponse> nrcFormat(@Body Map<String, Object> body);

    @FormUrlEncoded
    @POST("user/auth")
    Single<LoginResponse> login(@FieldMap Map<String, Object> body);

    @POST("receive-order/receive-no")
    Single<GenerateReceiveNoResponse> generateReceiveNo();

    @FormUrlEncoded
    @POST("delivery-order/order-no-list")
    Single<DeliverNumbersResponse> deliverNumbers(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("delivery-order/delivered-no-list")
    Single<DeliverNumbersResponse> deliveredNumbers(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("delivery-order/list")
    Single<DeliveryOrdersResponse> deliveryOrders(@Field("orderNo") String orderNo);

    @FormUrlEncoded
    @POST("receive-order/receive")
    Single<NewReceiveSaveResponse> newReceiveSave(@Field("receiveNo") String receiveNo,
                                                  @Field("receiveBy") String receiveBy,
                                                  @Field("deliveryOrderNo") String orderNo,
                                                  @Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("receive-order/receive-no-list")
    Single<ReceiveNumbersResponse> receiverNumbers(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("receive-order/receive-list")
    Single<ReceiveItemsResponse> receiveItems(@Field("receiveNo") String receiveNo,
                                              @Field("startDate") String startDate,
                                              @Field("endDate") String endDate,
                                              @Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("receive-order/receive-detail")
    Single<ReceiveItemViewResponse> receiveItemView(@Field("receiveNo") String receiveNo,
                                                    @Field("receivePersonName") String receiverName);

    @POST("category/list")
    Single<CategoryResponse> category();

    @POST("item/list")
    Single<ItemsResponse> items();

    @FormUrlEncoded
    @POST("item/list-by-merchant")
    Single<MerchantItemsResponse> merchantItems(@Field("merchantId") int merchantId);


    @POST("category/list1")
    Single<CategoryResponse> allCategory();

    @FormUrlEncoded
    @POST("item/list-by-merchant1")
    Single<MerchantItemsResponse> allMerchantItems(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("sales-order/item-list")
    Single<ItemsPriceResponse> itemsPriceSearch(@Field("itemCode") String itemCode,
                                                @Field("merchantId") int id);

    @FormUrlEncoded
    @POST("sales-order/set-selling-price")
    Single<SetSellingPriceResponse> setSellingPrice(@Field("id") int id,
                                                    @Field("price") double price);

    @FormUrlEncoded
    @POST("sales-order/get-uom")
    Single<UOMResponse> UOM(@Field("itemCode") String itemCode,
                            @Field("merchantId") int id);

    @POST("sales-order/sales-no")
    Single<GenerateSaleNoResponse> generateSaleNo();

    @FormUrlEncoded
    @POST("sales-order/pre-sale")
    Single<PreSaleResponse> preSale(@Field("itemCode") String itemCode,
                                    @Field("uomId") int uomId,
                                    @Field("merchantId") int merchantId,
                                    @Field("qty") int quantity);

    @POST("sales-order/sale")
    Single<SaleOrdersSaveResponse> saleOrdersSave(@Body Map<String, Object> body);

    @FormUrlEncoded
    @POST("sales-order/total-today-sell-amount")
    Single<BalanceResponse> balance(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("sales-order/sales-list")
    Single<SaleOrdersSearchResponse> saleOrdersSearch(@Field("salesNo") String saleNo,
                                                      @Field("startDate") String startDate,
                                                      @Field("endDate") String endDate,
                                                      @Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("sales-order/sales-no-list")
    Single<SaleNumbersResponse> saleNumbers(@Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("sales-order/sales-detail")
    Single<SaleOrderDetailResponse> saleOrderDetail(@Field("salesNo") String receiveNo,
                                                    @Field("total") double total);

    @FormUrlEncoded
    @POST("merchant-items/balance")
    Single<StockBalanceResponse> stockBalance(@Field("cateId") int categoryId,
                                              @Field("itemId") int itemId,
                                              @Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("merchant-items/search")
    Single<ItemSearchResponse> searchItems(@Field("cateId") int categoryId,
                                           @Field("itemId") int itemId,
                                           @Field("merchantId") int merchantId);

    @FormUrlEncoded
    @POST("payment/search")
    Single<PaymentResponse> payment(@Field("merchantId") int merchantId,
                                    @Field("deliveryOrderNo") String orderNo,
                                    @Field("startDate") String startDate,
                                    @Field("endDate") String endDate);

    @FormUrlEncoded
    @POST("sales-order/delete")
    Single<DeleteSaleItemResponse> deleteSaleItem(@Field("id") int id,
                                                  @Field("itemCode") String orderNo,
                                                  @Field("uomId") int uomId,
                                                  @Field("merchantId") int merchantId,
                                                  @Field("qty") int qty,
                                                  @Field("salesNo") String salesNo,
                                                  @Field("isLast") int isLast);

    @FormUrlEncoded
    @POST("sales-order/update")
    Single<UpdateSaleItemResponse> updateSaleItem(@Field("id") int id,
                                                  @Field("itemCode") String orderNo,
                                                  @Field("uomId") int uomId,
                                                  @Field("merchantId") int merchantId,
                                                  @Field("qty") int qty,
                                                  @Field("salesNo") String salesNo);

}