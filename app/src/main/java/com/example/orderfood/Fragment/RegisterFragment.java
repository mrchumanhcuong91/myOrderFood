package com.example.orderfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.Service.FireSignal;
import com.example.zalochat2.R;

public class RegisterFragment extends Fragment {
    public Context context;

    public RegisterFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register, container, false);
        final EditText nameEdt = (EditText)view.findViewById(R.id.registerName);
        final EditText passEdt = (EditText)view.findViewById(R.id.registerPass);
        Button btnRegister = (Button)view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdt.getText().toString();
                String pass = passEdt.getText().toString();

                SharedPreferences preferences = getContext().getSharedPreferences("MyAccount",0);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("username", name);
                editor.putString("password", pass);
                editor.commit();
                FireSignal.requestFragment(context, OrderState.STATE_REGISTER_NOW, OrderState.STATE_EMPLOYER_REGISTER);
            }
        });

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
    public void onPause() {
        super.onPause();
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
