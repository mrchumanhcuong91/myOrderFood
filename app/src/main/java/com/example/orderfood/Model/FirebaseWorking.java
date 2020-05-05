package com.example.orderfood.Model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.orderfood.Constance.OrderState;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FirebaseWorking {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference userShopRef;
    private DatabaseReference drinkShopRef;
    private StorageReference mStoreRef;
    public FirebaseWorking() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStoreRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

    }
    public void registerShop(final String nameShop, final String password, final MutableLiveData<AuthenUser> registerCallback){
        mAuth.createUserWithEmailAndPassword(nameShop, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = task.getResult().getUser();
                            Log.d("CuongChu","registerShop "+ nameShop);
                            AuthenUser userAuth = new AuthenUser(nameShop, password, user.getUid(), OrderState.STATE_REGISTER_SUCCESS);
                            registerCallback.setValue(userAuth);
                        }else {
                            AuthenUser userAuth = new AuthenUser(nameShop, password, null, OrderState.STATE_LOGIN_FAILED);
                            registerCallback.setValue(userAuth);
                        }

                    }
                });

    }
    public void loginShop(final String nameShop, final String password, final MutableLiveData<AuthenUser> registerCallback){
        mAuth.signInWithEmailAndPassword(nameShop, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = task.getResult().getUser();

                            Log.d("CuongChu","registerShop "+ nameShop);
                            AuthenUser userAuth = new AuthenUser(nameShop, password, user.getUid(), OrderState.STATE_LOGIN_SUCCESS);
                            registerCallback.setValue(userAuth);

                        }else {
                            AuthenUser userAuth = new AuthenUser(nameShop, password, null, OrderState.STATE_LOGIN_FAILED);
                            registerCallback.setValue(userAuth);
                        }
                    }
                });

    }
    public void getUserShop(String id_shop, final MutableLiveData<List<UserShop>> registerCallback){
        userShopRef = mDatabase.child("Shop").child(id_shop).child("Users");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserShop> userShops = new ArrayList<>();
                Log.d("CuongChu", "Child count "+dataSnapshot.getChildrenCount());
                if(dataSnapshot.getChildrenCount() != 0) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String id = ds.child("id_people").getValue(String.class);
                        String name = ds.child("name").getValue(String.class);
                        Log.d("CuongChu","name user "+name);
                        String pass = ds.child("password").getValue(String.class);
                        UserShop shop = new UserShop(name, pass, id);
                        userShops.add(shop);
                    }
                }
                registerCallback.setValue(userShops);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        userShopRef.addValueEventListener(eventListener);
    }
    public void getDrinkShop(String id_shop, final MutableLiveData<List<DrinkShop>> registerCallback){
        drinkShopRef = mDatabase.child("Shop").child(id_shop).child("Drinking_Menu");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DrinkShop> userShops = new ArrayList<>();
                Log.d("CuongChu", "Child count "+dataSnapshot.getChildrenCount());
                if(dataSnapshot.getChildrenCount() != 0) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String description = ds.child("description").getValue(String.class);
                        String name = ds.child("name").getValue(String.class);
                        String url = ds.child("urlImage").getValue(String.class);
                        int discount = ds.child("discount").getValue(int.class);
                        Log.d("CuongChu","name "+name);
                        int price = ds.child("price").getValue(int.class);
                        DrinkShop drink = new DrinkShop(name, description, url, discount, price);
                        userShops.add(drink);
                    }
                }
                registerCallback.setValue(userShops);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        drinkShopRef.addValueEventListener(eventListener);
    }
    public void writeNewUser(String idShop, String name, String password, String cmnd, final MutableLiveData<String> notify) {

        UserShop user = new UserShop(name, password, cmnd);
        FirebaseDatabase.getInstance().getReference().child("Shop").child(idShop).child("Users").push().
                setValue(user).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notify.setValue("add User Successfully ");
                        }else {
                            notify.setValue("add User Failed ");
                        }

                    }
                });
    }
    public void writeNewDrink(String idShop, String name, String description, String urlImage,
                              int discount, int price, final MutableLiveData<String> notify) {

        DrinkShop drinkShop = new DrinkShop(name, description, urlImage, discount, price);
        FirebaseDatabase.getInstance().getReference().child("Shop").child(idShop).child("Drinking_Menu").push().
                setValue(drinkShop).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notify.setValue("add User Successfully ");
                        }else {
                            notify.setValue("add User Failed ");
                        }

                    }
            });
    }
    public void uploadImage(String filePath, final MutableLiveData<String> notify) {

        String fileImage = filePath;
        Uri file = Uri.fromFile(new File(fileImage));
        //get image name
        String[] splitResult = fileImage.split("/");
        int index = splitResult.length;
        String fileName = splitResult[index-1];
        Log.d("CuChuong","fileImage "+ fileImage +"fileName "+fileName);
        String subString = "images/";

        final StorageReference sauRef = mStoreRef.child( subString +fileName);
        UploadTask uploadTask = sauRef.putFile(file);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return sauRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    final Uri downloadUri = task.getResult();
                    notify.setValue(downloadUri.toString());
                } else {
                    // Handle failures
                    // ...
                }
            }
        });

    }
}
