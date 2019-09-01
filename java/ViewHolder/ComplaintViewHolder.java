package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.Interface.ItemClickListener;
import asha.md_nayeem.schoolmanagement.R;

public class ComplaintViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtCtDescription, txtCtDate, txtCtTime, txtCtView, txtCtName, txtCtPhone;
    private ItemClickListener itemClickListener;

    public ComplaintViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtCtDescription = (TextView) itemView.findViewById(R.id.view_tc_text_Id);
        txtCtDate = (TextView) itemView.findViewById(R.id.date_tc_id);
        txtCtTime = (TextView) itemView.findViewById(R.id.time_tc_id);
        txtCtName = (TextView) itemView.findViewById(R.id.name_tc_id);
        txtCtPhone = (TextView) itemView.findViewById(R.id.phone_tc_id);
        txtCtView = (TextView) itemView.findViewById(R.id.view_tc_Id);
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
