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

import asha.md_nayeem.schoolmanagement.Model.Spln;
import asha.md_nayeem.schoolmanagement.ViewHolder.ScplnViewHolder;

public class StuComplaneViewActivity extends AppCompatActivity
{

    private DatabaseReference SCRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_complane_view);
        SCRef = FirebaseDatabase.getInstance().getReference().child("StudentComplain");

        recyclerView = findViewById(R.id.recycler_sc_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Spln> options =
                new FirebaseRecyclerOptions.Builder<Spln>()
                        .setQuery(SCRef, Spln.class)
                        .build();

        FirebaseRecyclerAdapter<Spln, ScplnViewHolder> adapter =
                new FirebaseRecyclerAdapter<Spln, ScplnViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ScplnViewHolder holder, int position, @NonNull final Spln model)
                    {
                        holder.txtCsDescription.setText(model.getDescription());
                        holder.txtCsDate.setText(model.getDate());
                        holder.txtCsTime.setText(model.getTime());
                        holder.txtCsName.setText("Student Name : "+model.getName());
                        holder.txtCsClass.setText(model.getClassname());
                        holder.txtCsPhone.setText("Phone: "+model.getPhone());

                        holder.itemView.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "View"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(StuComplaneViewActivity.this);
                                builder.setTitle("View Options:");

                                builder.setItems(options, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (i == 0)
                                        {
                                            holder.txtCsView.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ScplnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_complain_layout, parent, false);
                        ScplnViewHolder holder = new ScplnViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
