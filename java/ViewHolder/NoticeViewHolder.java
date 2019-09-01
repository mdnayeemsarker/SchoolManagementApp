package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.R;

public class NoticeViewHolder extends RecyclerView.ViewHolder
{
    public TextView txtNoticeDescription, txtNoticeDate, txtNoticeTime;

    public NoticeViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtNoticeDescription = (TextView) itemView.findViewById(R.id.view_notice_text_Id);
        txtNoticeDate = (TextView) itemView.findViewById(R.id.date_notice_id);
        txtNoticeTime = (TextView) itemView.findViewById(R.id.time_notice_id);

    }
}
