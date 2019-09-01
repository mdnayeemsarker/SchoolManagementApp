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

import asha.md_nayeem.schoolmanagement.Model.Results;
import asha.md_nayeem.schoolmanagement.ViewHolder.ResultViewHolder;

public class ResultViewActivity extends AppCompatActivity
{
    private DatabaseReference ResultsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        ResultsRef = FirebaseDatabase.getInstance().getReference().child("Results");

        recyclerView = findViewById(R.id.recycler_result_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Results> options =
                new FirebaseRecyclerOptions.Builder<Results>()
                        .setQuery(ResultsRef, Results.class)
                        .build();


        FirebaseRecyclerAdapter<Results, ResultViewHolder> adapter =
                new FirebaseRecyclerAdapter<Results, ResultViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ResultViewHolder holder, int position, @NonNull final Results model)
                    {
                        holder.txtResultDescription.setText(model.getDescription());
                        holder.txtResultDate.setText(model.getDate());
                        holder.txtResultTime.setText(model.getTime());
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout, parent, false);
                        ResultViewHolder holder = new ResultViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
