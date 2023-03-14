package com.AndroidFunitureShopApp.model.Categories;

import android.content.Context;
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

public class CategoriesAdapter extends RecyclerView.Adapter<com.AndroidFunitureShopApp.model.Categories.CategoriesAdapter.Viewholder> implements Filterable {
    private static List<Categories> categories;
    private static List<Categories> categoriesCopy;
    private Context mContext;

    public CategoriesAdapter(List<Categories> categoriesList) {
        this.categories = categoriesList;
        this.categoriesCopy = categoriesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoriesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoriesAdapter.Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull com.AndroidFunitureShopApp.model.Categories.CategoriesAdapter.Viewholder holder, int position) {
        holder.tvName.setText(categories.get(position).getName());
        holder.tvTitle.setText(String.valueOf(categories.get(position).getTitle()));
        holder.tvDescription.setText(String.valueOf(categories.get(position).getDescription()));
        Picasso.get().load(categories.get(position).getImageUrl()).into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView tvName;
        public TextView tvTitle;
        public TextView tvDescription;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageUrl = (ImageView) itemView.findViewById(R.id.image_url);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}