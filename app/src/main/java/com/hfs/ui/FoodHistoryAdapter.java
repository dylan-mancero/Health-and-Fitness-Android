package com.hfs.ui;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.time.LocalDate;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodHistoryAdapter extends RecyclerView.Adapter<FoodHistoryAdapter.ViewHolder>{
    private static final String TAG = "FoodHistoryAdapter";

    private ArrayList<String> fAmounts=new ArrayList<>();
    private ArrayList<String> fImages= new ArrayList<>();
    private ArrayList<String> fDates = new ArrayList<>();
    private Context fContext;

    public FoodHistoryAdapter(Context con, ArrayList<String> amount, ArrayList<String> images, ArrayList<String> dates) {
       fAmounts = amount;
       fImages = images;
       fDates = dates;
       fContext = con;
    }





    @NonNull
    @Override
    public FoodHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHistoryAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(fContext)
                .asBitmap()
                .load(fImages.get(position))
                .into(holder.image);

        holder.foodAmount.setText(fAmounts.get(position));
        holder.foodDate.setText(fDates.get(position));

        holder.foodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on"+ fAmounts.get(position));
                Toast.makeText(fContext,fAmounts.get(position),Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return fAmounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CircleImageView image;
        TextView foodAmount;
        ConstraintLayout foodLayout;
        TextView foodDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.food_Image);
            foodAmount = itemView.findViewById(R.id.amount_text_view);
            foodLayout=itemView.findViewById(R.id.parent_food);
            foodDate=itemView.findViewById(R.id.food_Date);
        }
    }
}
