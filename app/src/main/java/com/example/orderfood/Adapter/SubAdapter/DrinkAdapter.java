package com.example.orderfood.Adapter.SubAdapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
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

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkHolder> {

    public Context context;
    private List<DrinkShop> drinkShops;

    public DrinkAdapter(Context context, List<DrinkShop> drinkShops) {
        this.context = context;
        this.drinkShops = drinkShops;
    }
    public class DrinkHolder extends RecyclerView.ViewHolder{
        TextView name, description, price, discount;
        CircleImageView imageView;
        public DrinkHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.drinkName);
            discount =(TextView)itemView.findViewById(R.id.drinkDiscount);
            price = (TextView)itemView.findViewById(R.id.drinkPrice);
            imageView = (CircleImageView)itemView.findViewById(R.id.imageDrink);
        }
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drinks, parent, false);
        return new DrinkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkHolder holder, int position) {
        holder.name.setText(drinkShops.get(position).getName());
        holder.price.setText(String.valueOf(drinkShops.get(position).getPrice()));
        holder.discount.setText(String.valueOf(drinkShops.get(position).getDiscount()) +"%");
        if(drinkShops.get(position).getUrlImage() != null){
            Log.d("CuChuong","Link image "+drinkShops.get(position).getUrlImage());
            Glide.with(context).load(Uri.parse(drinkShops.get(position).getUrlImage())).centerCrop()
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return drinkShops.size();
    }
}
