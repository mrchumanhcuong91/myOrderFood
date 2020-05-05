package com.example.orderfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.Service.FireSignal;
import com.example.zalochat2.R;


public class SignInFragment extends Fragment implements View.OnClickListener {
    public Context context;
    EditText nameText, passText,idShop;
    RadioButton btnEmployer, btnWaiter, btnCooker;
    public SignInFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        nameText = (EditText)view.findViewById(R.id.username);
        passText = (EditText)view.findViewById(R.id.password);
        idShop = (EditText)view.findViewById(R.id.idShop);

        btnEmployer =(RadioButton)view.findViewById(R.id.employer);
        btnWaiter =(RadioButton)view.findViewById(R.id.waiter);
        btnCooker =(RadioButton)view.findViewById(R.id.cooker);

        btnCooker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    idShop.setVisibility(View.VISIBLE);
                else
                    idShop.setVisibility(View.GONE);
            }
        });
        btnWaiter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    idShop.setVisibility(View.VISIBLE);
                else
                    idShop.setVisibility(View.GONE);
            }
        });
        Button button = (Button)view.findViewById(R.id.btnSignIn);
        button.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        String name = nameText.getText().toString();
        String pass = passText.getText().toString();
        String id = idShop.getText().toString();

        SharedPreferences pre_id = getContext().getSharedPreferences("MyUserShop", 0);
        SharedPreferences.Editor editor_id = pre_id.edit();
        editor_id.putString("idshop", id);
        editor_id.putString("username", name);
        editor_id.putString("password", pass);
        editor_id.commit();

        if(v.getId() == R.id.btnSignIn){
            if(btnEmployer.isChecked()) {
                Log.d("CuongChu", "Employer  ");

                SharedPreferences preferences = getContext().getSharedPreferences("MyAccount", 0);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("username", name);
                editor.putString("password", pass);
                editor.commit();
                FireSignal.requestFragment(context, OrderState.STATE_LOGIN_NOW, OrderState.STATE_EMPLOYER_LOGIN);
            }else if(btnWaiter.isChecked()){
                Log.d("CuongChu", "Waiter  ");

                FireSignal.requestFragment(context, OrderState.STATE_LOGIN_NOW, OrderState.STATE_WAITER_LOGIN);
            }else if(btnCooker.isChecked()){
                Log.d("CuongChu", "Cooker  ");

                FireSignal.requestFragment(context, OrderState.STATE_LOGIN_NOW, OrderState.STATE_COOKER_LOGIN);
            }
        }
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
