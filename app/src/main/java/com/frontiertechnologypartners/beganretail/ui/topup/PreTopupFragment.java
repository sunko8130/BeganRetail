package com.frontiertechnologypartners.beganretail.ui.topup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.model.LoginData;
import com.frontiertechnologypartners.beganretail.model.Operators;
import com.frontiertechnologypartners.beganretail.model.PreTopUpResponse;
import com.frontiertechnologypartners.beganretail.model.ProvidersResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.ui.base.BaseFragment;
import com.frontiertechnologypartners.beganretail.util.Util;
import com.frontiertechnologypartners.beganretail.widgets.GridSpacingItemDecoration;
import com.frontiertechnologypartners.beganretail.widgets.MMPhoneChecker.MyanmarPhoneNumber;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

import static com.frontiertechnologypartners.beganretail.util.Constant.CONTACT_REQUEST_CODE;
import static com.frontiertechnologypartners.beganretail.util.Constant.DRAWABLE_RIGHT;
import static com.frontiertechnologypartners.beganretail.util.Constant.LOGIN_DATA;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreTopupFragment extends BaseFragment implements View.OnClickListener, OperatorAdapter.OnItemClickListener {

    @BindView(R.id.et_ph_no)
    TextInputEditText etPhoneNo;

    @BindView(R.id.operator_recycler_view)
    RecyclerView operatorRv;

    @BindView(R.id.btn_1000)
    Button btnAmount1000;

    @BindView(R.id.btn_3000)
    Button btnAmount3000;

    @BindView(R.id.btn_5000)
    Button btnAmount5000;

    @BindView(R.id.btn_10000)
    Button btnAmount10000;

    @Inject
    ViewModelFactory viewModelFactory;
    private TopupViewModel topupViewModel;

    private OperatorAdapter operatorCheckboxAdapter;
    private String selectedProvider = "";
    private String selectedAmount = "";
    private String mobileNumber = "";
    private Button selectAmountButton;
    private LoginData loginData;
    private String amount;
    private int merchantId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init
        ButterKnife.bind(this, view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        topupViewModel = ViewModelProviders.of(this, viewModelFactory).get(TopupViewModel.class);

        //read phone contact
        etPhoneNo.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getX() >= (etPhoneNo.getWidth() - etPhoneNo.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    readPhoneContact();
                    return true;
                }
            }
            return false;
        });


        //top up available amount
        operatorRv.setLayoutManager(new GridLayoutManager(mContext, 2));
        operatorRv.addItemDecoration(new GridSpacingItemDecoration(2, Util.dpToPx(mContext, 16), true));
        operatorRv.setHasFixedSize(true);
        operatorRv.setItemAnimator(new DefaultItemAnimator());
        operatorCheckboxAdapter = new OperatorAdapter(getActivity(), this);
        operatorRv.setAdapter(operatorCheckboxAdapter);

        //top up available amount
        btnAmount1000.setOnClickListener(this);
        btnAmount3000.setOnClickListener(this);
        btnAmount5000.setOnClickListener(this);
        btnAmount10000.setOnClickListener(this);


        //login data
        loginData = Paper.book().read(LOGIN_DATA);
        if (loginData != null) {
            merchantId = loginData.getId();
        }


        //load available providers
        topupViewModel.getAvailableProviders();

        observeProvidersData();
        observePreTopUpData();

    }

    private void observeProvidersData() {
        topupViewModel.providersResponse.observe(this, this::consumeResponse);
    }

    private void observePreTopUpData() {
        topupViewModel.preTopUpResponse.observe(this, this::consumePreTopResponse);
    }

    private void consumePreTopResponse(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                PreTopUpResponse preTopUpResponse = (PreTopUpResponse) apiResponse.data;
                if (preTopUpResponse != null) {
                    int statusCode = preTopUpResponse.getStatus().getCode();
                    if (statusCode == 1) {

                        double detectAmount = preTopUpResponse.getAmount();
                        Fragment topUpFragment = TopUpFragment.newInstance(mobileNumber,
                                amount,
                                detectAmount,
                                selectedProvider,
                                String.valueOf(loginData.getId()));

                        if (getFragmentManager() != null) {
                            getFragmentManager().beginTransaction().replace(R.id.frame, topUpFragment)
                                    .commit();
                        }

                    } else {
                        messageDialog.show();
                        messageDialog.loadingMessage(preTopUpResponse.getStatus().getMessage());
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

    private void consumeResponse(ApiResponse<?> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                loadingDialog.show();
                break;
            case SUCCESS:
                loadingDialog.dismiss();
                ProvidersResponse providersResponse = (ProvidersResponse) apiResponse.data;
                if (providersResponse != null) {
                    List<Operators> availableProviders = providersResponse.getData();
                    operatorCheckboxAdapter.setItems(availableProviders);
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

    @OnClick(R.id.btn_continue)
    void onClickContinue() {
        //mobile number
        if (!TextUtils.isEmpty(etPhoneNo.getText())) {
            String phone = etPhoneNo.getText().toString().trim();
            mobileNumber = phone.replaceAll("\\s+", "");
        }

        //pre top up
        if (selectedProvider.equals("")) {
            messageDialog.show();
            messageDialog.loadingMessage(getString(R.string.operator_require_msg));
        } else if (mobileNumber.equals("")) {
            messageDialog.show();
            messageDialog.loadingMessage(getString(R.string.mobile_require_msg));
        } else if (selectedAmount.equals("")) {
            messageDialog.show();
            messageDialog.loadingMessage(getString(R.string.amount_require_msg));
        } else if (mobileNumber.length() < 9) {
            messageDialog.show();
            messageDialog.loadingMessage(getString(R.string.invalid_mobile_no));
        } else {
            MyanmarPhoneNumber myanmarPhoneNumber = new MyanmarPhoneNumber();
            String operatorName = myanmarPhoneNumber.getTelecomName(mobileNumber);
            if (operatorName.equals("Unknown")) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.invalid_mobile_no));
            } else if (!operatorName.equalsIgnoreCase(selectedProvider)) {
                messageDialog.show();
                messageDialog.loadingMessage(getString(R.string.mobile_and_provider_not_match));
            } else {
                amount = selectAmountButton.getText().toString().trim().replace(",", "");
                topupViewModel.getPreTopUpData(String.valueOf(loginData.getId()),
                        amount, selectedProvider
                );
            }
        }
    }

    private void readPhoneContact() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        startActivityForResult(intent, CONTACT_REQUEST_CODE);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Util.showSettingsDialog(getActivity());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uriContact = data.getData();
                retrieveContactNumber(uriContact);
            }
        }
    }

    private void retrieveContactNumber(Uri uriContact) {
        String phNumber = "";
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
        if (uriContact != null) {
            if (getActivity() != null) {
                Cursor cursorPhone = getActivity().getContentResolver().query(
                        uriContact,
                        projection, null, null, null
                );
                if (cursorPhone != null && cursorPhone.moveToFirst()) {
                    phNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    cursorPhone.close();
                }
                etPhoneNo.setText("");
                etPhoneNo.setText(phNumber);
            }

        }
    }

    @Override
    public void onClick(View view) {
        selectAmountButton = (Button) view;

        // clear state
        btnAmount1000.setSelected(false);
        btnAmount1000.setPressed(false);
        btnAmount3000.setSelected(false);
        btnAmount3000.setPressed(false);
        btnAmount5000.setSelected(false);
        btnAmount5000.setPressed(false);
        btnAmount10000.setSelected(false);
        btnAmount10000.setPressed(false);

        // change state
        selectAmountButton.setSelected(true);
        selectAmountButton.setPressed(false);

        selectedAmount = selectAmountButton.getText().toString();

        Toast.makeText(getActivity(), "amount: " + selectAmountButton.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Operators operator = operatorCheckboxAdapter.getItem(position);
        selectedProvider = operator.getOperator();
        Toast.makeText(getContext(), "click " + operator.getOperator(), Toast.LENGTH_SHORT).show();
    }
}
