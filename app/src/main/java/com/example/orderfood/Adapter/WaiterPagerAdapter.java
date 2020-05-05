package com.example.orderfood.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.orderfood.Fragment.SubFragment.FoodListFrag;
import com.example.orderfood.Fragment.SubFragment.UserListFrag;
import com.example.orderfood.Fragment.SubWaiterFrag.Waiter_List_Drinks;

public class WaiterPagerAdapter extends FragmentStatePagerAdapter {

    public Context context;
    public WaiterPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new Waiter_List_Drinks(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Drinks";
            case 1:
                return "Eating";
            case 2:
                return "List Order";

        }
        return "Drinks";
    }
}
