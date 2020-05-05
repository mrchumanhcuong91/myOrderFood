package com.example.orderfood.Reposity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.orderfood.Model.AuthenUser;
import com.example.orderfood.Model.DrinkShop;
import com.example.orderfood.Model.FirebaseWorking;
import com.example.orderfood.Model.UserShop;

import java.util.List;

public class RepoOrderFood {
    private FirebaseWorking working;
    private MutableLiveData<AuthenUser> registerCallback;
    private MutableLiveData<List<UserShop>> UserCallback;
    private MutableLiveData<List<DrinkShop>> drinkCallback;
    private MutableLiveData<String> notify;
    public RepoOrderFood() {
        working = new FirebaseWorking();
        registerCallback = new MutableLiveData<>();
        UserCallback = new MutableLiveData<>();
        notify = new MutableLiveData<>();
        drinkCallback = new MutableLiveData<>();
    }
    public MutableLiveData<AuthenUser> RepoRegisterShop(String name, String password){
        working.registerShop(name, password, registerCallback);

        return registerCallback;
    }
    public MutableLiveData<AuthenUser> RepoLoginShop(String name, String password){
        working.loginShop(name, password, registerCallback);

        return registerCallback;
    }
    public MutableLiveData<List<UserShop>> RepoGetShopUser(String id_shop){
        working.getUserShop(id_shop, UserCallback);

        return UserCallback;
    }
    public MutableLiveData<List<DrinkShop>> RepoGetShopDrink(String id_shop){
        working.getDrinkShop(id_shop, drinkCallback);

        return drinkCallback;
    }
    public MutableLiveData<String> repoSetUserShop(String idShop, String name, String pass, String id){
        working.writeNewUser(idShop,name, pass ,id, notify);

        return notify;
    }
    public MutableLiveData<String> repoSetDrinkShop(String idShop, String name, String description, String urlImage,
                                                    int discount, int price){
        working.writeNewDrink(idShop,name, description, urlImage, discount, price , notify);

        return notify;
    }
    public MutableLiveData<String> repoSetImage(String filePath){
        working.uploadImage(filePath, notify);

        return notify;
    }
}
