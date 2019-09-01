package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;

public class TeachersViewHolder extends RecyclerView.ViewHolder
{

    public TextView txtTeacherName, txtTeacherTitle, txtTeacherPhone, txtTeacherEmail;

    public TeachersViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtTeacherName = (TextView) itemView.findViewById(R.id.teacher_view_name_ID);
        txtTeacherTitle = (TextView)itemView.findViewById(R.id.teacher_view_title_ID);
        txtTeacherPhone = (TextView)itemView.findViewById(R.id.teacher_view_phone_ID);
        txtTeacherEmail = (TextView)itemView.findViewById(R.id.teacher_view_email_ID);

    }


}
