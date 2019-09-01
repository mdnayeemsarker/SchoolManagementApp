package asha.md_nayeem.schoolmanagement.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import asha.md_nayeem.schoolmanagement.R;

public class EventViewHolder extends RecyclerView.ViewHolder
{
    public TextView txtEventDescription,txtEventDate,txtEventTime;
    public ImageView imageView;
    /*public ZoomControls zoomControls;*/

    public EventViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.event_image_id);
        txtEventDescription = (TextView) itemView.findViewById(R.id.event_description_id);
        txtEventDate = (TextView) itemView.findViewById(R.id.event_date_Id);
        txtEventTime = (TextView) itemView.findViewById(R.id.event_time_Id);
        /*zoomControls = (ZoomControls) itemView.findViewById(R.id.event_zoom_Id);*/
    }
}
