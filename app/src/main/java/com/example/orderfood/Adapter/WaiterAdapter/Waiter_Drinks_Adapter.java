package com.example.orderfood.Adapter.WaiterAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfood.Model.DrinkShop;
import com.example.zalochat2.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Waiter_Drinks_Adapter extends RecyclerView.Adapter<Waiter_Drinks_Adapter.WaiterHolder> {
    public Context context;
    public List<DrinkShop> drinkShops;

    public Waiter_Drinks_Adapter(Context context, List<DrinkShop> drinkShops) {
        this.context = context;
        this.drinkShops = drinkShops;
    }

    public class WaiterHolder extends RecyclerView.ViewHolder{
        TextView nameDrinks, price;
        CircleImageView urlImage;
        public WaiterHolder(@NonNull View itemView) {
            super(itemView);
            nameDrinks = (TextView)itemView.findViewById(R.id.wtName);
            price = (TextView)itemView.findViewById(R.id.wtPrice);
            urlImage = (CircleImageView) itemView.findViewById(R.id.foodImage);

        }
    }
    @NonNull
    @Override
    public Waiter_Drinks_Adapter.WaiterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.waiter_item_drink, parent, false);
        return new WaiterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Waiter_Drinks_Adapter.WaiterHolder holder, int position) {
        holder.nameDrinks.setText(drinkShops.get(position).getName());
        holder.price.setText(String.valueOf(drinkShops.get(position).getPrice()));
        if(drinkShops.get(position).getUrlImage() != null){
            Glide.with(context).load(Uri.parse(drinkShops.get(position).getUrlImage())).centerCrop()
                    .into(holder.urlImage);
        }


    }

    @Override
    public int getItemCount() {
        return drinkShops.size();
    }
}
