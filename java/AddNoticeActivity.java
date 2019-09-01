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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNoticeActivity extends AppCompatActivity
{
    private Button AddNoticeButton;
    private EditText inputNoticeText;
    private ProgressDialog loadingBar;

    private String saveCurrentDate,saveCurrentTime;
    private String productRandomKey;
    private DatabaseReference NoticeRef;
    private String n_Description;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        NoticeRef = FirebaseDatabase.getInstance().getReference().child("Notices");

        loadingBar = new ProgressDialog(this);

        AddNoticeButton = findViewById(R.id.add_notice_button_id);
        inputNoticeText = findViewById(R.id.add_notice_text_id);

        AddNoticeButton.setOnClickListener(new View.OnClickListener()
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
        n_Description = inputNoticeText.getText().toString();

        if (n_Description == null)
        {
            Toast.makeText(this, "Notice Information is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeNoticeInformation();
        }
    }

    private void storeNoticeInformation()
    {
        loadingBar.setTitle("Adding New Notice Information");
        loadingBar.setMessage("Dear Admin Please wait, while we are adding the new Notice Details");
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        SaveNoticeInfoToDatabase();
    }

    private void SaveNoticeInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("id", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", n_Description);

        NoticeRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AddNoticeActivity.this, AdminActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddNoticeActivity.this, "Notice added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddNoticeActivity.this, "Error: "+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
