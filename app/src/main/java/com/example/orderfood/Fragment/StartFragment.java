package com.example.orderfood.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.Service.FireSignal;
import com.example.zalochat2.R;

public class StartFragment extends Fragment implements View.OnClickListener {
    public Context context;

    public StartFragment(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_layout, container, false);
        TextView textYes = (TextView)view.findViewById(R.id.hadShop);
        TextView textNo = (TextView)view.findViewById(R.id.noShop);
        textYes.setOnClickListener(this);
        textNo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.hadShop){
            FireSignal.requestFragment(context, OrderState.STATE_LOGIN, -1);
        }else if(v.getId() == R.id.noShop){
            FireSignal.requestFragment(context, OrderState.STATE_REGISTER, -1);

        }
    }
}
