package com.frontiertechnologypartners.beganretail.network;

import com.frontiertechnologypartners.beganretail.model.NRCFormatResponse;
import com.frontiertechnologypartners.beganretail.model.RegisterResponse;
import com.frontiertechnologypartners.beganretail.model.StateCityResponse;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    @POST("b2bstate-list")
    Single<StateCityResponse> stateCity();

    @POST("state-code")
    Single<NRCFormatResponse> nrcFormat(@Body Map<String, Object> body);

    @POST("user/register")
    Single<RegisterResponse> register(@Body Map<String, Object> body);

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