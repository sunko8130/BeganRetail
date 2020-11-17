package com.frontiertechnologypartners.beganretail.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frontiertechnologypartners.beganretail.R;

public class Util {
    public static void customToastMessage(Context context, String message, boolean isLong) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);
        TextView tvCustomToast = view.findViewById(R.id.tv_custom_toast);
        tvCustomToast.setText(message);
        // Toast...
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
