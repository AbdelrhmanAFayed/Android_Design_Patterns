package com.example.mvc.favproducts.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvc.R;
import com.example.mvc.allproducts.view.OnAddClick;
import com.example.mvc.model.Product;

import java.util.List;

public class FavProductAdapter extends RecyclerView.Adapter<FavProductAdapter.ViewHolder> {

    Context context ;
    List<Product> products ;
    RecyclerView recAll ;

    onRemoveClick listener;


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle ;
        TextView textPrice ;
        TextView textDesc ;
        RatingBar Bar ;
        ImageView imgView ;
        Button btnRemove ;
        ConstraintLayout constraintLayout ;
        View layout ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView ;
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textDesc = itemView.findViewById(R.id.textDesc);
            Bar = itemView.findViewById(R.id.ratingBar);
            imgView = itemView.findViewById(R.id.imgView);
            btnRemove = itemView.findViewById(R.id.btnRemove);

            constraintLayout = itemView.findViewById(R.id.layout);





        }
    }


    public FavProductAdapter(Context context, List<Product> products, RecyclerView recyclerView, onRemoveClick listener) {
        this.context = context;
        this.listener = listener;
        this.products = products;
        this.recAll = recyclerView;
        this.updateGUI();


    }

    String TAG = "RecyclerView" ;


    public  void updateGUI()
    {
        recAll.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(context);
        recAll.setLayoutManager(manger);
        manger.setOrientation(RecyclerView.VERTICAL);
        recAll.setAdapter(this);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fav_product,parent,false);
        ViewHolder vh = new ViewHolder(v);

        Log.i(TAG,"=========onCreateViewHolder=============");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(products.get(position).imgURL)
                .into(holder.imgView);



        holder.textTitle.setText(products.get(position).getTitle());
        holder.textDesc.setText(products.get(position).getDesc());
        holder.textPrice.setText(products.get(position).getPrice().toString());
        holder.Bar.setRating(products.get(position).getRating().floatValue());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemClick(products.get(position));

            }
        });

        Log.i(TAG,"**************onBindViewHolder******************");


    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }


}
