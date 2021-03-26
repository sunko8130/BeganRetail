package com.frontiertechnologypartners.beganretail.ui.emoney_request_transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.model.CashoutStatus;
import com.frontiertechnologypartners.beganretail.model.UOM;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class CashoutStatusAdapter extends ArrayAdapter<CashoutStatus> {
    private List<CashoutStatus> cashoutStatusList;

    @BindView(R.id.spinner_text)
    TextView spinnerText;

    public CashoutStatusAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull List<CashoutStatus> cashoutStatusList) {
        super(context, resource, spinnerText, cashoutStatusList);
        this.cashoutStatusList = cashoutStatusList;
    }

    @Override
    public CashoutStatus getItem(int position) {
        return cashoutStatusList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    private View initView(int position) {
        CashoutStatus cashoutStatus = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = null;
        if (inflater != null) {
            v = inflater.inflate(R.layout.dropdown_menu_popup_item, null);
            ButterKnife.bind(this, v);
        }
        if (cashoutStatus != null) {
            spinnerText.setText(cashoutStatus.getValue());
        }
        return v;
    }
}