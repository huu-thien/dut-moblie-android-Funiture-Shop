package com.AndroidFunitureShopApp.model.Product;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AndroidFunitureShopApp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productsAdapter extends RecyclerView.Adapter<productsAdapter.Viewholder> implements Filterable {
    private static List<Product> products;
    private static List<Product> productsCopy;
    private Context mContext;

    public productsAdapter(List<Product> userList) {
        this.products = userList;
        this.productsCopy = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvName.setText(products.get(position).getName());

        holder.tvOriginalPrice.setText(String.valueOf(products.get(position).getOriginalPrice())+"$");
        holder.tvOriginalPrice.setPaintFlags(holder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tvPrice.setText(String.valueOf(products.get(position).getPrice())+"$");

        //holder.tvDetail.setText(products.get(position).getDetail());
        Picasso.get().load(products.get(position).getImageUrl()).into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView tvName;
        public TextView tvDetail;
        public TextView tvOriginalPrice;
        public TextView tvPrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageUrl = (ImageView) itemView.findViewById(R.id.image_url);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            //tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            tvOriginalPrice = (TextView) itemView.findViewById(R.id.tv_original_price);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);

        }
    }
}
