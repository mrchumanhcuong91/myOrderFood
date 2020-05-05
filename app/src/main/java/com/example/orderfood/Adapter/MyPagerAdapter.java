package com.example.orderfood.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.orderfood.Fragment.SubFragment.DrinkListFrag;
import com.example.orderfood.Fragment.SubFragment.FoodListFrag;
import com.example.orderfood.Fragment.SubFragment.UserListFrag;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public Context context;
    int count;
    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        Log.d("CuongChu","behavior "+behavior);
        count = behavior;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Log.d("CuongChu","position "+position);
                Fragment userFrag = new UserListFrag(context);
                return userFrag;
            case 1:
                Log.d("CuongChu","position "+position);
                Fragment drinkFrag = new DrinkListFrag(context);
                return drinkFrag;
            case 2:
                Log.d("CuongChu","position "+position);
                Fragment foodFrag = new FoodListFrag(context);
                return foodFrag;
            default:
                    return null;

        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Users";
            case 1:
                return "Drink Menu";
            case 2:
                return "Food Menu";
            default:
                return null;

        }
    }
    @Override
    public int getCount() {
        return count;
    }
}
