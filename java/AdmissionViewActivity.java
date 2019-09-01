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

import asha.md_nayeem.schoolmanagement.Model.Admission;
import asha.md_nayeem.schoolmanagement.ViewHolder.AdmissionViewHolder;

public class AdmissionViewActivity extends AppCompatActivity
{
    private DatabaseReference AdmissionRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_view);

        AdmissionRef = FirebaseDatabase.getInstance().getReference().child("Admission");

        recyclerView = findViewById(R.id.recycler_admission_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Admission> options =
                new FirebaseRecyclerOptions.Builder<Admission>()
                        .setQuery(AdmissionRef, Admission.class)
                        .build();


        FirebaseRecyclerAdapter<Admission, AdmissionViewHolder> adapter =
                new FirebaseRecyclerAdapter<Admission, AdmissionViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdmissionViewHolder holder, int position, @NonNull final Admission model)
                    {
                        holder.txtAdmissionDescription.setText(model.getDescription());
                        holder.txtAdmissionDate.setText(model.getDate());
                        holder.txtAdmissionTime.setText(model.getTime());
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public AdmissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admission_layout, parent, false);
                        AdmissionViewHolder holder = new AdmissionViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
