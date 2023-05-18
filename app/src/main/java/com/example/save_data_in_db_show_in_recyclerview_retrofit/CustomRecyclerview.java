package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomRecyclerview extends RecyclerView.Adapter<CustomRecyclerview.ViewHolder>{
    Context context;
    List<Data> arrayList;

    public CustomRecyclerview(Context context, List<Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Data repo = arrayList.get(position);

        holder.name.setText(repo.getName());
        holder.chef.setText("By " + repo.getYear());
        holder.description.setText(repo.getColor());
        holder.price.setText("Price: ₹" + repo.getPantoneValue());
        holder.timestamp.setText(repo.getColor());

//        Glide.with(context)
//                .load(repo.getThumbnail())
//                .into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description, price, chef, timestamp;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            chef = itemView.findViewById(R.id.chef);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            timestamp = itemView.findViewById(R.id.timestamp);

        }
    }
}
