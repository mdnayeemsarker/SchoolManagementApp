package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;

public class RoutineViewHolder extends RecyclerView.ViewHolder
{

    public TextView txtRoutineDescription, txtRoutineTitle, txtRoutineDate, txtRoutineTime;
    public ImageView imageView;

    public RoutineViewHolder(@NonNull View itemView) {
        super(itemView);


        imageView = (ImageView) itemView.findViewById(R.id.class_routine_image_id);
        txtRoutineTitle = (TextView) itemView.findViewById(R.id.class_routine_title_id);
        txtRoutineDescription = (TextView) itemView.findViewById(R.id.class_routine_description_id);
        txtRoutineDate = (TextView) itemView.findViewById(R.id.class_routine_date_Id);
        txtRoutineTime = (TextView) itemView.findViewById(R.id.class_routine_time_Id);
    }


}
