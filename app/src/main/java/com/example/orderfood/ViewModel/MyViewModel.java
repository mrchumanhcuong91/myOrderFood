package com.example.orderfood.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orderfood.Model.AuthenUser;
import com.example.orderfood.Model.DrinkShop;
import com.example.orderfood.Model.UserShop;
import com.example.orderfood.Reposity.RepoOrderFood;

import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<AuthenUser> viewModel;
    private MutableLiveData<List<UserShop>> viewUser;
    private MutableLiveData<List<DrinkShop>> viewDrink;
    private MutableLiveData<String> notify;
    private RepoOrderFood orderFood;
    public MyViewModel() {
        orderFood = new RepoOrderFood();
    }

    public MutableLiveData<AuthenUser> viewModelRegister(String name, String password) {
        viewModel = orderFood.RepoRegisterShop(name, password);
        return viewModel;
    }
    public MutableLiveData<AuthenUser> viewModelLogin(String name, String password) {
        viewModel = orderFood.RepoLoginShop(name, password);
        return viewModel;
    }
    //Get
    public MutableLiveData<List<UserShop>> viewModelGetUser(String id_string) {
        viewUser = orderFood.RepoGetShopUser(id_string);
        return viewUser;
    }
    public MutableLiveData<List<DrinkShop>> viewModelGetDrink(String id_string) {
        viewDrink = orderFood.RepoGetShopDrink(id_string);
        return viewDrink;
    }
    //set
    public MutableLiveData<String> viewModelSetUserShop(String idShop, String name, String pass, String id){
        notify = orderFood.repoSetUserShop(idShop, name, pass, id);
        Log.d("CuongChu", "notify "+notify);
        return notify;
    }
    public MutableLiveData<String> viewModelSetDrinkShop(String idShop, String name, String description, String urlImage,
                                                         int discount, int price){
        notify = orderFood.repoSetDrinkShop(idShop, name, description, urlImage, discount, price);
        Log.d("CuongChu", "notify "+notify);
        return notify;
    }
    public MutableLiveData<String> viewModelSetImage(String filePath){
        notify = orderFood.repoSetImage(filePath);
        return notify;
    }
}
