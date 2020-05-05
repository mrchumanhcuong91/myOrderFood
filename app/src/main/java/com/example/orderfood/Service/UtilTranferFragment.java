package com.example.orderfood.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.MainActivity;

public class UtilTranferFragment extends BroadcastReceiver {

    public MainActivity activity;

    public UtilTranferFragment(MainActivity activity) {
        this.activity = activity;
    }

    public void registerTranfer(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(OrderState.STATE_NULL);
        intentFilter.addAction(OrderState.STATE_REGISTER);
        intentFilter.addAction(OrderState.STATE_REGISTER_NOW);
        intentFilter.addAction(OrderState.STATE_REGISTER_SUCCESS);
        intentFilter.addAction(OrderState.STATE_REGISTER_FAILED);
        intentFilter.addAction(OrderState.STATE_LOGIN);
        intentFilter.addAction(OrderState.STATE_LOGIN_NOW);
        intentFilter.addAction(OrderState.STATE_LOGIN_SUCCESS);
        intentFilter.addAction(OrderState.STATE_LOGIN_FAILED);

        activity.registerReceiver(this, intentFilter);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int type = -1;
        type = intent.getExtras().getInt("type");
        activity.changeFragment(action, type);
    }
}
