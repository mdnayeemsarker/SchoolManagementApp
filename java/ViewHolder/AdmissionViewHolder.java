package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;

public class AdmissionViewHolder extends RecyclerView.ViewHolder
{
    public TextView txtAdmissionDescription,txtAdmissionDate,txtAdmissionTime;
    public ImageView imageView;

    public AdmissionViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.admission_image_id);
        txtAdmissionDescription = (TextView) itemView.findViewById(R.id.admission_description_id);
        txtAdmissionDate = (TextView) itemView.findViewById(R.id.admission_date_Id);
        txtAdmissionTime = (TextView) itemView.findViewById(R.id.admission_time_Id);
    }
}
