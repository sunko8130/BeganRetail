package com.frontiertechnologypartners.beganretail.network;

import com.frontiertechnologypartners.beganretail.model.LoginResponse;
import com.frontiertechnologypartners.beganretail.model.NRCFormatResponse;
import com.frontiertechnologypartners.beganretail.model.RegisterResponse;
import com.frontiertechnologypartners.beganretail.model.StateCityResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService2 {

    @FormUrlEncoded
    @POST("user/register")
    Single<RegisterResponse> register(@FieldMap Map<String, Object> body);

    @FormUrlEncoded
    @POST("user/auth")
    Single<LoginResponse> loginResponse(@FieldMap Map<String, Object> body);

//    @FormUrlEncoded
//    @POST("b2b-login")
//    Single<LoginResponse> login(@Field("loginid") String name,
//                                @Field("password") String password);
//
//    @POST("vendor-create")
//    Single<CreateVendorResponse> createVendor(@Body Map<String, Object> body);
//
//    @POST("product-types")
//    Single<ProductTypeResponse> productType();
//
//    @POST("product-by-types")
//    Single<AvailableProductsResponse> availableProducts(@Body Map<String, Object> body);
//
//    @POST("vendor-product-create")
//    Single<CreateVendorProductsResponse> createVendorProducts(@Body Map<String, Object> body);
//
//    @POST("purchase-create")
//    Single<CreatePurchaseResponse> createPurchase(@Body Map<String, Object> body);
//
//    @POST("vendor-list-purchase")
//    Single<VendorsInfoResponse> vendorsInfo();
//
//    @FormUrlEncoded
//    @POST("vendor-list")
//    Single<VendorListResponse> vendorList(@Field("userid") int userId);
//
//    @FormUrlEncoded
//    @POST("purchase-list")
//    Single<PurchaseListResponse> purchaseList(@Field("userid") int userId);
//
//    @FormUrlEncoded
//    @POST("b2b-change-password")
//    Single<ChangePasswordResponse> changePassword(@Field("loginid") String loginName,
//                                                  @Field("currentpassword") String currentPassword,
//                                                  @Field("password") String newPassword);
//
//    @Multipart
//    @POST("b2b-uploadImage")
//    Single<ImageUploadResponse> uploadImage(@Part MultipartBody.Part imageFile,
//                                            @Part("file") RequestBody description);
//
//    @FormUrlEncoded
//    @POST("b2b-forgot-password")
//    Single<ChangePasswordResponse> forgotPassword(@Field("mobile") String mobile);
//
//    @POST("product-types-purchase")
//    Single<PurchaseReportProductResponse> purchaseReportProduct();
//
//    @POST("purchase-report")
//    Single<PurchaseReportResponse> purchaseReport(@Body Map<String, Object> body);

}