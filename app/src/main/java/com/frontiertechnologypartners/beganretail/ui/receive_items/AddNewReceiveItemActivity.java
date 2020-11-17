package com.frontiertechnologypartners.beganretail.ui.receive_items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.frontiertechnologypartners.beganretail.R;
import com.frontiertechnologypartners.beganretail.ui.base.BaseActivity;

public class AddNewReceiveItemActivity extends BaseActivity {
    @BindView(R.id.rv_new_receive_items)
    RecyclerView rvNewReceiveItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_receive_item);
        ButterKnife.bind(this);

        //recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNewReceiveItems.setLayoutManager(layoutManager);
        rvNewReceiveItems.setHasFixedSize(true);
        rvNewReceiveItems.setItemAnimator(new DefaultItemAnimator());
    }
}