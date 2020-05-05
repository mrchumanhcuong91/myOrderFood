package com.example.orderfood.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.orderfood.Adapter.MyPagerAdapter;
import com.example.orderfood.MainActivity;
import com.example.zalochat2.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment_Login_Success extends Fragment  implements TabLayout.OnTabSelectedListener {
    public Context context;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    public ViewPager viewPager;
    public MyPagerAdapter adapter;
    public TabLayout tabLayout;
    public Fragment_Login_Success(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_success, container, false);
        //Toolbar
        Toolbar toolbar =(Toolbar)view.findViewById(R.id.mainToolbar);
        toolbar.setTitle("Order Food");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //add tab name :https://www.simplifiedcoding.net/android-tablayout-example-using-viewpager-fragments/
        viewPager =(ViewPager)view.findViewById(R.id.pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Drink Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Eating Menu"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(),3, context);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        //DrawerLayout
        drawerLayout = (DrawerLayout)view.findViewById(R.id.mainDrawer);
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.action_settings,
                R.string.action_settings){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //show fragment navigator
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //set image btn
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_ic_master);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

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
        Log.d("CuongChu","tab select "+tab.getPosition());
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
