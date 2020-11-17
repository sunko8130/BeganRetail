package com.frontiertechnologypartners.beganretail.network;

import com.frontiertechnologypartners.beganretail.model.NRCFormatResponse;
import com.frontiertechnologypartners.beganretail.model.RegisterResponse;
import com.frontiertechnologypartners.beganretail.model.StateCityResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NetworkRepository {

    private RetrofitService retrofitService;
    private RetrofitService2 retrofitService2;

    @Inject
    public NetworkRepository(RetrofitService retrofitService,RetrofitService2 retrofitService2) {
        this.retrofitService = retrofitService;
        this.retrofitService2 = retrofitService2;
    }

    public Single<StateCityResponse> stateCity() {
        return retrofitService.stateCity();
    }

    public Single<NRCFormatResponse> nrcFormat(Map<String, Object> body) {
        return retrofitService.nrcFormat(body);
    }

    public Single<RegisterResponse> register(Map<String, Object> body) {
        return retrofitService2.register(body);
    }

//    public Single<LoginResponse> login(String name, String password) {
//        return retrofitService.login(name, password);
//    }
//
//
//    public Single<CreateVendorResponse> createVendors(Map<String, Object> body) {
//        return retrofitService.createVendor(body);
//    }
//
//    public Single<ProductTypeResponse> productType() {
//        return retrofitService.productType();
//    }
//
//    public Single<AvailableProductsResponse> availableProducts(Map<String, Object> body) {
//        return retrofitService.availableProducts(body);
//    }
//
//    public Single<CreateVendorProductsResponse> createVendorProducts(Map<String, Object> body) {
//        return retrofitService.createVendorProducts(body);
//    }
//
//    public Single<VendorsInfoResponse> vendorsInfo() {
//        return retrofitService.vendorsInfo();
//    }
//
//    public Single<CreatePurchaseResponse> createPurchase(Map<String, Object> body) {
//        return retrofitService.createPurchase(body);
//    }
//
//    public Single<VendorListResponse> vendorList(int userId) {
//        return retrofitService.vendorList(userId);
//    }
//
//    public Single<PurchaseListResponse> purchaseList(int userId) {
//        return retrofitService.purchaseList(userId);
//    }
//
//    public Single<ImageUploadResponse> uploadImage(MultipartBody.Part imageFile, RequestBody description) {
//        return retrofitService.uploadImage(imageFile, description);
//    }
//
//    public Single<ChangePasswordResponse> changePassword(String loginName, String currentPassword, String newPassword) {
//        return retrofitService.changePassword(loginName, currentPassword, newPassword);
//    }
//
//    public Single<ChangePasswordResponse> forgotPassword(String mobile) {
//        return retrofitService.forgotPassword(mobile);
//    }
//
//    public Single<PurchaseReportProductResponse> purchaseReportProduct() {
//        return retrofitService.purchaseReportProduct();
//    }
//
//    public Single<PurchaseReportResponse> purchaseReport(Map<String, Object> body) {
//        return retrofitService.purchaseReport(body);
//    }

}
