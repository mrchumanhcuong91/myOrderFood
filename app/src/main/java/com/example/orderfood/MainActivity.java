package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.orderfood.Constance.OrderState;
import com.example.orderfood.Fragment.Fragment_Login_Success;
import com.example.orderfood.Fragment.Progress_Fragment;
import com.example.orderfood.Fragment.RegisterFragment;
import com.example.orderfood.Fragment.SignInFragment;
import com.example.orderfood.Fragment.StartFragment;
import com.example.orderfood.Fragment.WaiterFrag.Waiter_Login_Success_Frag;
import com.example.orderfood.Service.UtilTranferFragment;
import com.example.zalochat2.R;

public class MainActivity extends AppCompatActivity {
    public static String Order_State = OrderState.STATE_NULL;
    UtilTranferFragment tranferFragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //register
        tranferFragment = new UtilTranferFragment(this);
        tranferFragment.registerTranfer();
        //tranfer fragment
        fm = getSupportFragmentManager();
        changeFragment(Order_State, -1);

    }
    public void changeFragment(String state, int type){
        if(state.equals(OrderState.STATE_NULL)){
            FragmentTransaction ft = fm.beginTransaction();
            StartFragment fragment = new StartFragment(getApplicationContext());
            ft.replace(R.id.mainLayout, fragment,"startApplication");
            ft.commit();
//            FragmentTransaction wt = fm.beginTransaction();
//            Waiter_Login_Success_Frag wtf = new Waiter_Login_Success_Frag(getApplicationContext());
//            wt.replace(R.id.mainLayout, wtf,"WaiterLoginSuccess");
//            wt.commit();
//            Order_State = OrderState.STATE_LOGIN;
        }else if(state.equals(OrderState.STATE_REGISTER)){
            FragmentTransaction ft = fm.beginTransaction();
            RegisterFragment fragment = new RegisterFragment(getApplicationContext());
            ft.replace(R.id.mainLayout, fragment,"Register");
            ft.commit();
            Order_State = OrderState.STATE_REGISTER;
        }else if(state.equals(OrderState.STATE_REGISTER_NOW) || state.equals(OrderState.STATE_LOGIN_NOW)) {
            boolean isRegister = false;
            if(state.equals(OrderState.STATE_REGISTER_NOW)){
                isRegister = true;
            }
            Log.d("CuongChu", "type progress" +type);
            FragmentTransaction ft = fm.beginTransaction();
            Progress_Fragment fragment = new Progress_Fragment(getApplicationContext());
            fragment.setTypeAuthen(type);
            ft.replace(R.id.mainLayout, fragment, "ProgressRegister");
            ft.commit();
            Order_State = OrderState.STATE_REGISTER;
        } else if(state.equals(OrderState.STATE_LOGIN)){
            FragmentTransaction ft = fm.beginTransaction();
            SignInFragment fragment = new SignInFragment(getApplicationContext());
            ft.replace(R.id.mainLayout, fragment,"SignIn");
            ft.commit();
            Order_State = OrderState.STATE_LOGIN;
        }else if(state.equals(OrderState.STATE_LOGIN_SUCCESS)){
            Log.d("CuongChu", "type " +type);
            switch (type){
                case OrderState.STATE_EMPLOYER_LOGIN:
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment_Login_Success fragment = new Fragment_Login_Success(getApplicationContext());
                    ft.replace(R.id.mainLayout, fragment,"SignIn");
                    ft.commit();
                    Order_State = OrderState.STATE_LOGIN;
                    break;
                case OrderState.STATE_COOKER_LOGIN:

                    break;
                case OrderState.STATE_WAITER_LOGIN:
                    FragmentTransaction wt = fm.beginTransaction();
                    Waiter_Login_Success_Frag wtf = new Waiter_Login_Success_Frag(getApplicationContext());
                    wt.replace(R.id.mainLayout, wtf,"WaiterLoginSuccess");
                    wt.commit();
                    Order_State = OrderState.STATE_LOGIN;
                    break;

                default:
                    break;
            }

        }
    }
}
