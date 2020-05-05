package com.example.orderfood.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.orderfood.ViewModel.MyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zalochat2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateDrinkMenu extends AppCompatActivity {
    public int ATTACH_CODE = 2020;
    MyViewModel viewModel;
    EditText editName, editPrice, editDis, editDes, editUrl;
    CircleImageView imageView;
    Button addDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_drink);
        imageView =(CircleImageView)findViewById(R.id.previewImage);
        editName = (EditText)findViewById(R.id.edNameDrink);
        editPrice = (EditText)findViewById(R.id.edPriceDrink);
        editDis = (EditText)findViewById(R.id.edDiscountDrink);
        editDes = (EditText)findViewById(R.id.edDesDrink);
        editUrl = (EditText)findViewById(R.id.edUrlDrink);
        Button btnBrowser = (Button)findViewById(R.id.browerImage);
        Button btnAdd = (Button)findViewById(R.id.addDrink);
        //myaccount
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyAccount", 0);
        final String idShop = preferences.getString("id_shop", "");
        Log.d("CuongChu", "idShop "+idShop);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.viewModelSetDrinkShop(idShop, editName.getText().toString(),
                        editDis.getText().toString(), editUrl.getText().toString(),Integer.parseInt(editPrice.getText().toString()),
                        Integer.parseInt(editDis.getText().toString()))
                        .observe(CreateDrinkMenu.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(CreateDrinkMenu.this, s, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
            }
        });
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), ATTACH_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ATTACH_CODE){
            if(resultCode == RESULT_OK){
                String path = null;
                Uri uri = data.getData();
//                Log.d("CuChuong", "String  uri "+uri.toString() +"authority "+uri.getAuthority());
                String authority = uri.getAuthority();
                if(authority.equals("media")){
                    String projection = MediaStore.Images.Media.DATA;
                    Cursor cursor = getContentResolver().query(uri, new String[]{projection},null, null,null);
                    cursor.moveToFirst();
                    do{
                        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        path = cursor.getString(index);
                    }while (cursor.moveToNext());
                }
                Log.d("CuChuong", "String  uri "+path);

                viewModel.viewModelSetImage(path).observe(CreateDrinkMenu.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        editUrl.setText(s);
                        Log.d("CuChuong", "Firebase  image "+s);
                        Glide.with(CreateDrinkMenu.this).load(Uri.parse(s)).centerCrop().into(imageView);
                    }
                });

            }
        }
    }
}
