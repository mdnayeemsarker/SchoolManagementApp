package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddTeacherInfoActivity extends AppCompatActivity
{

    private Button AddTeacherButton;
    private EditText inputTeacherName,inputTeacherEmail,inputTeacherTitle;
    private EditText inputTeacherNumber;
    private ProgressDialog loadingBar;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_info);
        this.setTitle("School Management Admin Panel");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadingBar = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        AddTeacherButton = findViewById(R.id.add_teacher_button_id);
        inputTeacherName = findViewById(R.id.add_teacher_name_id);
        inputTeacherEmail = findViewById(R.id.add_teacher_email_id);
        inputTeacherTitle = findViewById(R.id.add_teacher_title_id);
        inputTeacherNumber = findViewById(R.id.add_teacher_number_id);

        AddTeacherButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddTeacherInfo();
            }
        });

    }


    // Add Teacher Information

    private void AddTeacherInfo()
    {
        String name = inputTeacherName.getText().toString();
        String email = inputTeacherEmail.getText().toString();
        String title = inputTeacherTitle.getText().toString();
        String phone = inputTeacherNumber.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your Teachers Name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your Teachers Email Id Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your Teachers Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(title))
        {
            Toast.makeText(this, "Please write your Teachers Title", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Adding Teachers Information");
            loadingBar.setMessage("Dear Admin Please wait, while we are adding your Teachers Information...!");
            loadingBar.show();

            ValidatePhoneNumberTeacher(name, email, phone, title);
        }
    }

    private void ValidatePhoneNumberTeacher(final String name,final String email, final String phone, final String title)
    {
        auth.createUserWithEmailAndPassword(email,phone)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("AddTeachers").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("name",name);
                            hashMap.put("email",email);
                            hashMap.put("title",title);
                            hashMap.put("phone", phone);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(AddTeacherInfoActivity.this,AdminActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(AddTeacherInfoActivity.this,"Dear Admin, We have added your Teachers Information Successfully...!",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                            loadingBar.dismiss();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(AddTeacherInfoActivity.this,"You can't added your Teachers Information...!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
