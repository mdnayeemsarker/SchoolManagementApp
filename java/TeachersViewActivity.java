package asha.md_nayeem.schoolmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import asha.md_nayeem.schoolmanagement.Model.Teachers;
import asha.md_nayeem.schoolmanagement.ViewHolder.TeachersViewHolder;

public class TeachersViewActivity extends AppCompatActivity
{
    private DatabaseReference TeacherRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_view);
        this.setTitle("School Management");

        TeacherRef = FirebaseDatabase.getInstance().getReference().child("AddTeachers");

        recyclerView = findViewById(R.id.recycler_teacher_view_ID);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Teachers> options =
                new FirebaseRecyclerOptions.Builder<Teachers>()
                        .setQuery(TeacherRef, Teachers.class)
                        .build();

        FirebaseRecyclerAdapter<Teachers, TeachersViewHolder> adapter =
                new FirebaseRecyclerAdapter<Teachers, TeachersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull TeachersViewHolder holder, int position, @NonNull final Teachers model)
                    {
                        holder.txtTeacherName.setText("Name : "+model.getName());
                        holder.txtTeacherTitle.setText("Title : "+model.getTitle());
                        holder.txtTeacherPhone.setText("Phone : "+model.getPhone());
                        holder.txtTeacherEmail.setText("Email : "+model.getEmail());
                    }

                    @NonNull
                    @Override
                    public TeachersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_view_layout, parent, false);
                        TeachersViewHolder holder = new TeachersViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
