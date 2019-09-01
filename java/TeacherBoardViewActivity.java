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

import asha.md_nayeem.schoolmanagement.Model.Teacherboard;
import asha.md_nayeem.schoolmanagement.ViewHolder.TeacherboardViewHolder;

public class TeacherBoardViewActivity extends AppCompatActivity
{
    private DatabaseReference TBRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_board_view);

        TBRef = FirebaseDatabase.getInstance().getReference().child("TeacherBoard");

        recyclerView = findViewById(R.id.recycler_tb_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Teacherboard> options =
                new FirebaseRecyclerOptions.Builder<Teacherboard>()
                        .setQuery(TBRef, Teacherboard.class)
                        .build();


        FirebaseRecyclerAdapter<Teacherboard, TeacherboardViewHolder> adapter =
                new FirebaseRecyclerAdapter<Teacherboard, TeacherboardViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final TeacherboardViewHolder holder, int position, @NonNull final Teacherboard model)
                    {
                        holder.txtTBDescription.setText(model.getDescription());
                        holder.txtTBDate.setText(model.getDate());
                        holder.txtTBTime.setText(model.getTime());

                        holder.itemView.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "View"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherBoardViewActivity.this);
                                builder.setTitle("View Options:");

                                builder.setItems(options, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (i == 0)
                                        {
                                            holder.txtTBView.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public TeacherboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tb_layout, parent, false);
                        TeacherboardViewHolder holder = new TeacherboardViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
