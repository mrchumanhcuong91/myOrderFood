package com.example.orderfood.Fragment.SubFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.orderfood.Adapter.SubAdapter.UserAdapter;
import com.example.orderfood.Model.UserShop;
import com.example.orderfood.ViewModel.MyViewModel;
import com.example.zalochat2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserListFrag extends Fragment {
    public Context context;
    private MyViewModel viewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    public UserListFrag(Context context) {
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
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        fab = (FloatingActionButton)view.findViewById(R.id.fabUser);
        recyclerView = (RecyclerView)view.findViewById(R.id.listUser);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        //call viewmodel
        SharedPreferences preferences = context.getSharedPreferences("MyAccount", 0);
        final String idShop = preferences.getString("id_shop", "");
        Log.d("CuongChu", "idShop "+idShop);
        viewModel.viewModelGetUser(idShop).observe(this, new Observer<List<UserShop>>() {
            @Override
            public void onChanged(List<UserShop> userShops) {
                Log.d("CuongChu", "userShops size  "+userShops.size());
                UserAdapter adapter = new UserAdapter(context,userShops);
                recyclerView.setAdapter(adapter);
            }
        });
        //add user
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_user, null, false);
                final EditText editName = (EditText)dialogView.findViewById(R.id.edNameUser);

                final EditText editPass = (EditText)dialogView.findViewById(R.id.edPassUser);

                final EditText editId = (EditText)dialogView.findViewById(R.id.edIdUser);

                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setView(dialogView)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = editName.getText().toString();
                                String pass = editPass.getText().toString();
                                String id = editId.getText().toString();
                                viewModel.viewModelSetUserShop(idShop, name, pass, id).
                                        observe(getActivity(),new Observer<String>(){
                                            @Override
                                            public void onChanged(String s) {
                                                Toast.makeText(context, s,Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("Cancel",null).create();
                dialog.show();
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
