package com.example.orderfood.Fragment.SubWaiterFrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Adapter.SubAdapter.DrinkAdapter;
import com.example.orderfood.Adapter.WaiterAdapter.Waiter_Drinks_Adapter;
import com.example.orderfood.Model.DrinkShop;
import com.example.orderfood.ViewModel.MyViewModel;
import com.example.zalochat2.R;

import java.util.List;

public class Waiter_List_Drinks extends Fragment {

    public Context context;
    public MyViewModel viewModel;
    public RecyclerView recyclerView;
    public Waiter_List_Drinks(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waiter_list_drinks, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.wtListDrinks);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        //
//        SharedPreferences preferences = context.getSharedPreferences("MyAccount", 0);
//        final String idShop = preferences.getString("id_shop", "");
//        Log.d("CuongChu", "idShop "+idShop);
        String idShop ="CcqcsD6QEzSuQx4WqyL85ruyB2G3";
        viewModel.viewModelGetDrink(idShop).observe(this, new Observer<List<DrinkShop>>() {
            @Override
            public void onChanged(List<DrinkShop> userShops) {
                Waiter_Drinks_Adapter adapter = new Waiter_Drinks_Adapter(context, userShops);
                recyclerView.setAdapter(adapter);
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
