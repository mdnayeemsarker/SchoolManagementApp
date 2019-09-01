package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.Interface.ItemClickListener;
import asha.md_nayeem.schoolmanagement.R;

public class TeacherboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtTBDescription, txtTBDate, txtTBTime, txtTBView;
    private ItemClickListener itemClickListener;


    public TeacherboardViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtTBDescription = (TextView) itemView.findViewById(R.id.view_tb_text_Id);
        txtTBDate = (TextView) itemView.findViewById(R.id.date_tb_id);
        txtTBTime = (TextView) itemView.findViewById(R.id.time_tb_id);
        txtTBView = (TextView) itemView.findViewById(R.id.view_tb_Id);
    }

    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }


}
