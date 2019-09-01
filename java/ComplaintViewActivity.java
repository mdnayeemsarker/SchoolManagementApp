package asha.md_nayeem.schoolmanagement;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import asha.md_nayeem.schoolmanagement.Model.Complaint;
import asha.md_nayeem.schoolmanagement.ViewHolder.ComplaintViewHolder;

public class ComplaintViewActivity extends AppCompatActivity
{
    private DatabaseReference TCRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_view);

        TCRef = FirebaseDatabase.getInstance().getReference().child("TeacherComplain");

        recyclerView = findViewById(R.id.recycler_tc_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Complaint> options =
                new FirebaseRecyclerOptions.Builder<Complaint>()
                        .setQuery(TCRef, Complaint.class)
                        .build();

        FirebaseRecyclerAdapter<Complaint, ComplaintViewHolder> adapter =
                new FirebaseRecyclerAdapter<Complaint, ComplaintViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ComplaintViewHolder holder, int position, @NonNull final Complaint model)
                    {
                        holder.txtCtDescription.setText(model.getDescription());
                        holder.txtCtDate.setText(model.getDate());
                        holder.txtCtTime.setText(model.getTime());
                        holder.txtCtName.setText("Teacher Name : " + model.getName());
                        holder.txtCtPhone.setText("Phone : "+model.getPhone());

                        holder.itemView.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "View"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(ComplaintViewActivity.this);
                                builder.setTitle("View Options:");

                                builder.setItems(options, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (i == 0)
                                        {
                                            holder.txtCtView.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_complain_layout, parent, false);
                        ComplaintViewHolder holder = new ComplaintViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
