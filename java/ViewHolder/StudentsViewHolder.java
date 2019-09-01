package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;

public class StudentsViewHolder extends RecyclerView.ViewHolder
{

    public TextView txtStudentName, txtStudentTitle, txtStudentPhone, txtStudentEmail;

    public StudentsViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtStudentName = (TextView) itemView.findViewById(R.id.student_view_name_ID);
        txtStudentTitle = (TextView)itemView.findViewById(R.id.student_view_title_ID);
        txtStudentPhone = (TextView)itemView.findViewById(R.id.student_view_phone_ID);
        txtStudentEmail = (TextView)itemView.findViewById(R.id.student_view_email_ID);
    }
}
