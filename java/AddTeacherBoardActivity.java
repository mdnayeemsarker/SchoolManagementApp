package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddTeacherBoardActivity extends AppCompatActivity
{
    private Button AddTeacherBoardButton;
    private EditText inputTeacherBoardText;
    private ProgressDialog loadingBar;

    private String saveCurrentDate,saveCurrentTime;
    private String teacherBoardRandomKey;
    private DatabaseReference TeacherBoardRef;
    private String tb_Description;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_board);
        this.setTitle("School Management Admin Panel");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        TeacherBoardRef = FirebaseDatabase.getInstance().getReference().child("TeacherBoard");

        loadingBar = new ProgressDialog(this);

        AddTeacherBoardButton = findViewById(R.id.add_teacher_board_button_id);
        inputTeacherBoardText = findViewById(R.id.add_teacher_board_text_id);

        AddTeacherBoardButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddNoticeInfo();
            }
        });
    }

    private void AddNoticeInfo()
    {
        tb_Description = inputTeacherBoardText.getText().toString();

        if (tb_Description == null)
        {
            Toast.makeText(this, "Information is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeNoticeInformation();
        }
    }

    private void storeNoticeInformation()
    {
        loadingBar.setTitle("Adding New Teachers Notice");
        loadingBar.setMessage("Dear Admin Please wait, while we are adding the Teachers Board Information");
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        teacherBoardRandomKey = saveCurrentDate + saveCurrentTime;

        SaveNoticeInfoToDatabase();
    }

    private void SaveNoticeInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("id", teacherBoardRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", tb_Description);

        TeacherBoardRef.child(teacherBoardRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AddTeacherBoardActivity.this, AdminActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddTeacherBoardActivity.this, "Information added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddTeacherBoardActivity.this, "Error: "+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
