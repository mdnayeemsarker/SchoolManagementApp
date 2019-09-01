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

import asha.md_nayeem.schoolmanagement.Model.Routines;
import asha.md_nayeem.schoolmanagement.ViewHolder.RoutineViewHolder;

public class RoutineViewActivity extends AppCompatActivity
{
    private DatabaseReference RoutinesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_view);

        RoutinesRef = FirebaseDatabase.getInstance().getReference().child("Routines");


        recyclerView = findViewById(R.id.recycler_class_routine);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Routines> options =
                new FirebaseRecyclerOptions.Builder<Routines>()
                        .setQuery(RoutinesRef, Routines.class)
                        .build();


        FirebaseRecyclerAdapter<Routines, RoutineViewHolder> adapter =
                new FirebaseRecyclerAdapter<Routines, RoutineViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull RoutineViewHolder holder, int position, @NonNull final Routines model)
                    {
                        holder.txtRoutineTitle.setText(model.getTitle());
                        holder.txtRoutineDescription.setText(model.getDescription());
                        holder.txtRoutineDate.setText(model.getDate());
                        holder.txtRoutineTime.setText(model.getTime());
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_routine_layout, parent, false);
                        RoutineViewHolder holder = new RoutineViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
