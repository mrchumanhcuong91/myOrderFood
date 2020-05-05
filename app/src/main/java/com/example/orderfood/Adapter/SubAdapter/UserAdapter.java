package com.example.orderfood.Adapter.SubAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Model.UserShop;
import com.example.zalochat2.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    public Context context;
    public List<UserShop> userShops;

    public UserAdapter(Context context, List<UserShop> userShops) {
        this.context = context;
        this.userShops = userShops;
    }
    public class UserHolder extends RecyclerView.ViewHolder{
        TextView nameUser,idUser;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            nameUser = (TextView)itemView.findViewById(R.id.nameUser);
            idUser = (TextView)itemView.findViewById(R.id.idPeople);
        }
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
        holder.idUser.setText(userShops.get(position).getId_people());
        holder.nameUser.setText(userShops.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userShops.size();
    }
}
