package com.hfs.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfs.lib.activity.FinishedActivity;

import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private List<FinishedActivity> activities;
    final private Context context;

    public RecyclerViewAdapter(Context context, List<FinishedActivity> activities){
        this.activities = activities;
        this.context = context;
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

        final FinishedActivity finishedActivity = activities.get(position);

        // TODO: Add images.
        /*
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        */

        holder.name.setText(finishedActivity.getActivity().getName());

        // Shows start times of each activity.
        holder.date.setText(finishedActivity.getStart().toString());

        //On click listener for the items on list...
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final Random rand = new Random();
               Log.d(TAG, "onClick: clicked on: " + finishedActivity.toString());
               //===============================================================================
               //CODE HERE WHAT YOU WOULD LIKE THE APP TO DO ON CLICK OF THE SPECIFIC ACTIVITY_HISTORY ITEM.
               //===============================================================================

               //Adding the popup window on click on activity.
               context.startActivity(new Intent(context, ActivityHistoryPopupActivity.class));

               Toast.makeText(context, finishedActivity.toString(), Toast.LENGTH_SHORT).show();
           }
        });
    }

    //Method to inform the adapter--> How many list items are in your list..., If 'return 0', would not display anything on screen at run-time...
    @Override
    public int getItemCount() {
        if(activities == null){
            return 0;
        } else {
            return activities.size();
        }
    }

    public void setActivities(List<FinishedActivity> activities){
        this.activities = activities;
        notifyDataSetChanged();
    }

    //Declare widgets in here, as takes care of each individual list item.
    protected class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView date;
        RelativeLayout parentLayout;

        //Connecting elements of layout_listitem.xml to the ViewHolder, so it can update correctly.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.image_name);
            date = itemView.findViewById(R.id.image_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
