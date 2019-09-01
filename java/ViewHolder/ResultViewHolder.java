package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;


public class ResultViewHolder extends RecyclerView.ViewHolder
{
    public TextView txtResultDescription,txtResultDate,txtResultTime;
    public ImageView imageView;

    public ResultViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.result_image_id);
        txtResultDescription = (TextView) itemView.findViewById(R.id.result_description_id);
        txtResultDate = (TextView) itemView.findViewById(R.id.result_date_Id);
        txtResultTime = (TextView) itemView.findViewById(R.id.result_time_Id);
    }
}
