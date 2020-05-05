package com.example.orderfood.Fragment.SubFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Adapter.SubAdapter.DrinkAdapter;
import com.example.orderfood.Model.DrinkShop;
import com.example.orderfood.Model.UserShop;
import com.example.orderfood.ViewModel.MyViewModel;
import com.example.orderfood.activities.CreateDrinkMenu;
import com.example.zalochat2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DrinkListFrag extends Fragment {
    public Context context;

    private MyViewModel viewModel;
    private FloatingActionButton fabDrink;
    private RecyclerView recyclerView;
    public DrinkListFrag(Context context) {
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
        View view = inflater.inflate(R.layout.drink_menu_list, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.listDrink);
        fabDrink = (FloatingActionButton)view.findViewById(R.id.fabDrinks);
        //recyclerView
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        //
        SharedPreferences preferences = context.getSharedPreferences("MyAccount", 0);
        final String idShop = preferences.getString("id_shop", "");
        Log.d("CuongChu", "idShop "+idShop);
        viewModel.viewModelGetDrink(idShop).observe(this, new Observer<List<DrinkShop>>() {
            @Override
            public void onChanged(List<DrinkShop> userShops) {
                DrinkAdapter adapter = new DrinkAdapter(context, userShops);
                recyclerView.setAdapter(adapter);
            }
        });
        fabDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateDrinkMenu.class);
                startActivity(intent);
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
