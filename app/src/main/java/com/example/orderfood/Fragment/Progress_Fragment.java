package com.example.orderfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.Model.AuthenUser;
import com.example.orderfood.Model.UserShop;
import com.example.orderfood.Service.FireSignal;
import com.example.orderfood.ViewModel.MyViewModel;
import com.example.zalochat2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.orderfood.Model.UserFirebase;

import java.util.List;

import static com.example.orderfood.Constance.OrderState.STATE_COOKER_LOGIN;
import static com.example.orderfood.Constance.OrderState.STATE_EMPLOYER_LOGIN;
import static com.example.orderfood.Constance.OrderState.STATE_EMPLOYER_REGISTER;
import static com.example.orderfood.Constance.OrderState.STATE_WAITER_LOGIN;

public class Progress_Fragment extends Fragment {

    public Context context;
    ProgressBar progressBar;
    private SharedPreferences preferences;
    private String userName;
    private String password;
    MyViewModel viewModel;


    int type;

    public int getTypeAuthen() {
        return type;
    }

    public void setTypeAuthen(int type) {
        this.type = type;
    }

    public Progress_Fragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getContext().getSharedPreferences("MyAccount",0);
        userName = preferences.getString("username","");
        password = preferences.getString("password", "");
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signing_animation, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Log.d("CuongChu", "type progress frag" +type);
        switch (type){
            case STATE_WAITER_LOGIN:
            case STATE_COOKER_LOGIN:
                //get all user then compare
                SharedPreferences pre_id = getContext().getSharedPreferences("MyUserShop", 0);
                String id = pre_id.getString("idshop", "");

                final String name = pre_id.getString("username", "");
                final String pass = pre_id.getString("password", "");
                viewModel.viewModelGetUser(id).observe(this, new Observer<List<UserShop>>() {
                    @Override
                    public void onChanged(List<UserShop> userShops) {
                        for(UserShop shop: userShops){
                            String nameUser = shop.getName();
                            String passUser = shop.getPassword();
                            if(nameUser.equals(name) && passUser.equals(pass)){
                                Log.d("CuongChu", "type progress Success " +type);
                                FireSignal.requestFragment(getContext(), OrderState.STATE_LOGIN_SUCCESS, type);
                                break;
                            }
                        }
                    }
                });
                break;
            case STATE_EMPLOYER_LOGIN:
                viewModel.viewModelLogin(userName, password).observe(this, new Observer<AuthenUser>() {
                    @Override
                    public void onChanged(AuthenUser authenUser) {
                        if (authenUser.getState().equals(OrderState.STATE_LOGIN_SUCCESS)){
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("id_shop", authenUser.getUuid());
                            editor.commit();
                            progressBar.setVisibility(View.GONE);
                            FireSignal.requestFragment(getContext(), OrderState.STATE_LOGIN_SUCCESS,STATE_EMPLOYER_LOGIN);
                        }else if(authenUser.getState().equals(OrderState.STATE_LOGIN_FAILED)){
                            progressBar.setVisibility(View.GONE);
                            FireSignal.requestFragment(getContext(), OrderState.STATE_LOGIN_FAILED, STATE_EMPLOYER_LOGIN);

                        }

                    }
                });
                break;
            case STATE_EMPLOYER_REGISTER:
                viewModel.viewModelRegister(userName, password).observe(this, new Observer<AuthenUser>() {
                    @Override
                    public void onChanged(AuthenUser authenUser) {
                        Log.d("CuongChu", "onChanged " + authenUser.getUsername());
                        if (authenUser.getState().equals(OrderState.STATE_REGISTER_SUCCESS)) {
                            progressBar.setVisibility(View.GONE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("id_shop", authenUser.getUuid());
                            editor.commit();
                            Log.d("CuongChu", "idShop authenUser "+authenUser.getUuid());

                            FireSignal.requestFragment(getContext(), OrderState.STATE_REGISTER_SUCCESS, STATE_EMPLOYER_REGISTER);
                        }else if (authenUser.getState().equals(OrderState.STATE_REGISTER_FAILED)) {
                            progressBar.setVisibility(View.GONE);
                            FireSignal.requestFragment(getContext(), OrderState.STATE_REGISTER_FAILED, STATE_EMPLOYER_REGISTER);
                        }
                    }
                });
                break;
        }


        return view;
    };

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
