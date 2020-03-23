package com.hfs.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfs.ui.fragments.ActivityHistoryFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mImageDates = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> imageDates, ArrayList<String> images){
        mImageNames = imageNames;
        mImageDates = imageDates;
        mImages = images;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Gets called every time a new item is appended to the list in layout.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        //On click listener for the items on list...
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

               //===============================================================================
               //CODE HERE WHAT YOU WOULD LIKE THE APP TO DO ON CLICK OF THE SPECIFIC ACTIVITY_HISTORY ITEM.
               //===============================================================================
               Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
           }
        });
    }

    //Method to inform the adapter--> How many list items are in your list..., If 'return 0', would not display anything on screen at run-time...
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    //Declare widgets in here, as takes care of each individual list item.
    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        TextView imageDate;
        RelativeLayout parentLayout;

        //Connecting elements of layout_listitem.xml to the ViewHolder, so it can update correctly.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            imageDate = itemView.findViewById(R.id.image_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
