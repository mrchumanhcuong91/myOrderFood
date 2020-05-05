package com.example.orderfood.Fragment.WaiterFrag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.orderfood.Adapter.WaiterPagerAdapter;
import com.example.zalochat2.R;
import com.google.android.material.tabs.TabLayout;

public class Waiter_Login_Success_Frag extends Fragment implements TabLayout.OnTabSelectedListener{

    public Context context;
    public ViewPager viewPager;
    public TabLayout layout;
    public Waiter_Login_Success_Frag(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waiter_login_success, container, false);
        viewPager = (ViewPager)view.findViewById(R.id.pagerWaiter);
        layout =(TabLayout)view.findViewById(R.id.wtTab);

        layout.addTab(layout.newTab().setText("Drinks Menu"));
        layout.addTab(layout.newTab().setText("Eating Menu"));
        layout.addTab(layout.newTab().setText("List Order"));
        layout.setTabGravity(TabLayout.GRAVITY_FILL);

        layout.setupWithViewPager(viewPager);
        layout.addOnTabSelectedListener(this);
        WaiterPagerAdapter adapter = new WaiterPagerAdapter(getActivity().getSupportFragmentManager(), 3, context);
        viewPager.setAdapter(adapter);
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
