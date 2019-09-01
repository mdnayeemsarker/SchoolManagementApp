package asha.md_nayeem.schoolmanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import asha.md_nayeem.schoolmanagement.Model.Event;
import asha.md_nayeem.schoolmanagement.ViewHolder.EventViewHolder;

public class EventViewActivity extends AppCompatActivity
{
    private DatabaseReference EventsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);


        EventsRef = FirebaseDatabase.getInstance().getReference().child("Events");

        recyclerView = findViewById(R.id.recycler_event_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(EventsRef, Event.class)
                        .build();
        
        FirebaseRecyclerAdapter<Event, EventViewHolder> adapter =
                new FirebaseRecyclerAdapter<Event, EventViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final EventViewHolder holder, int position, @NonNull final Event model)
                    {
                        holder.txtEventDescription.setText(model.getDescription());
                        holder.txtEventDate.setText(model.getDate());
                        holder.txtEventTime.setText(model.getTime());
                        /*holder.zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                float x =holder.imageView.getScaleX();
                                float y =holder.imageView.getScaleY();

                                if(x<=5 && y<=5){
                                    holder.imageView.setScaleX((float)x+1);
                                    holder.imageView.setScaleY((float) y+1);
                                }
                            }
                        });

                        holder.zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                float x =holder.imageView.getScaleX();
                                float y =holder.imageView.getScaleY();

                                if(x>1 && y>1){
                                    holder.imageView.setScaleX((float)x-1);
                                    holder.imageView.setScaleY((float) y-1);

                                }
                            }
                        });*/
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);
                        EventViewHolder holder = new EventViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
