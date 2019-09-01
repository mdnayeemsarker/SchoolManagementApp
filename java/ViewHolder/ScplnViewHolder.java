package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asha.md_nayeem.schoolmanagement.Interface.ItemClickListener;
import asha.md_nayeem.schoolmanagement.R;

public class ScplnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtCsDescription, txtCsDate, txtCsTime, txtCsView, txtCsName, txtCsPhone, txtCsClass;
    private ItemClickListener itemClickListener;

    public ScplnViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtCsDescription = (TextView) itemView.findViewById(R.id.view_sc_text_Id);
        txtCsDate = (TextView) itemView.findViewById(R.id.date_sc_id);
        txtCsTime = (TextView) itemView.findViewById(R.id.time_sc_id);
        txtCsName = (TextView) itemView.findViewById(R.id.name_sc_id);
        txtCsClass = (TextView) itemView.findViewById(R.id.class_sc_id);
        txtCsPhone = (TextView) itemView.findViewById(R.id.phone_sc_id);
        txtCsView = (TextView) itemView.findViewById(R.id.view_sc_Id);
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
