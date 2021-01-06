package com.frontiertechnologypartners.beganretail.ui.pricing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.model.Item;
import com.frontiertechnologypartners.beganretail.model.MerchantItem;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class MerchantItemsAdapter extends ArrayAdapter<MerchantItem> {
    private List<MerchantItem> itemList;

    @BindView(R.id.spinner_text)
    TextView spinnerText;

    public MerchantItemsAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull List<MerchantItem> itemList) {
        super(context, resource, spinnerText, itemList);
        this.itemList = itemList;
    }

    @Override
    public MerchantItem getItem(int position) {
        return itemList.get(position);
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
        MerchantItem item = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = null;
        if (inflater != null) {
            v = inflater.inflate(R.layout.dropdown_menu_popup_item, null);
            ButterKnife.bind(this, v);
        }
        if (item != null) {
            spinnerText.setText(item.getItemName());
        }
        return v;
    }
}