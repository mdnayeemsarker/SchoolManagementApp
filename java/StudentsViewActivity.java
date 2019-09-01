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

import asha.md_nayeem.schoolmanagement.Model.Studentsview;
import asha.md_nayeem.schoolmanagement.ViewHolder.StudentsViewHolder;

public class StudentsViewActivity extends AppCompatActivity
{
    private DatabaseReference StudentRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_view);

        StudentRef = FirebaseDatabase.getInstance().getReference().child("Students");

        recyclerView = findViewById(R.id.recycler_students_view_ID);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Studentsview> options =
                new FirebaseRecyclerOptions.Builder<Studentsview>()
                        .setQuery(StudentRef, Studentsview.class)
                        .build();

        FirebaseRecyclerAdapter<Studentsview, StudentsViewHolder> adapter =
                new FirebaseRecyclerAdapter<Studentsview, StudentsViewHolder>(options)
                {
                    @Override
                    protected void onBindViewHolder(@NonNull StudentsViewHolder holder, int position, @NonNull final Studentsview model)
                    {
                        holder.txtStudentName.setText("Name : "+model.getSname());
                        holder.txtStudentTitle.setText("Title : "+model.getStitle());
                        holder.txtStudentPhone.setText("Phone : "+model.getSphone());
                        holder.txtStudentEmail.setText("Email : "+model.getSemail());
                        /*Picasso.get().load(model.getImage()).into(holder.imageView);*/

                        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }
                        });*/
                    }

                    @NonNull
                    @Override
                    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_view_layout, parent, false);
                        StudentsViewHolder holder = new StudentsViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
