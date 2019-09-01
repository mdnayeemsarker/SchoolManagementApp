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

import asha.md_nayeem.schoolmanagement.Model.Notice;
import asha.md_nayeem.schoolmanagement.ViewHolder.NoticeViewHolder;

public class NoticeViewActivity extends AppCompatActivity
{
    private DatabaseReference NoticeRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);

        NoticeRef = FirebaseDatabase.getInstance().getReference().child("Notices");

        recyclerView = findViewById(R.id.recycler_notice_Id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Notice> options =
                new FirebaseRecyclerOptions.Builder<Notice>()
                        .setQuery(NoticeRef, Notice.class)
                        .build();


        FirebaseRecyclerAdapter<Notice, NoticeViewHolder> adapter =
                new FirebaseRecyclerAdapter<Notice, NoticeViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull final Notice model)
                    {
                        holder.txtNoticeDescription.setText(model.getDescription());
                        holder.txtNoticeDate.setText(model.getDate());
                        holder.txtNoticeTime.setText(model.getTime());

                    }

                    @NonNull
                    @Override
                    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_layout, parent, false);
                        NoticeViewHolder holder = new NoticeViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
