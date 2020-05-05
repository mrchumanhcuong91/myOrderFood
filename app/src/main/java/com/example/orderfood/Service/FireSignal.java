package com.example.orderfood.Service;

import android.content.Context;
import android.content.Intent;

import com.example.orderfood.Constance.OrderState;

public class FireSignal {
    public static void requestFragment(Context context, String newState,int type){
        Intent intent = new Intent(newState);
//        intent.setFlags()
        intent.putExtra("type", type);
        context.sendBroadcast(intent);
    }
}
