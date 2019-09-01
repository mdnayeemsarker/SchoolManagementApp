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

public class AddStudentInfoActivity extends AppCompatActivity
{
    private Button addStudentButton;
    private EditText inputStudentName,inputStudentEmail,inputStudentTitle;
    private EditText inputStudentNumber;
    private ProgressDialog loadingBar;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_info);

        loadingBar = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();

        addStudentButton = findViewById(R.id.add_students_button_id);
        inputStudentName = findViewById(R.id.add_students_name_id);
        inputStudentEmail = findViewById(R.id.add_students_email_id);
        inputStudentTitle = findViewById(R.id.add_students_title_id);
        inputStudentNumber = findViewById(R.id.add_students_number_id);

        addStudentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddStudentsInformation();
            }
        });
    }

    private void AddStudentsInformation()
    {
        String sname = inputStudentName.getText().toString();
        String semail = inputStudentEmail.getText().toString();
        String stitle = inputStudentTitle.getText().toString();
        String sphone = inputStudentNumber.getText().toString();

        if (TextUtils.isEmpty(sname))
        {
            Toast.makeText(this, "Please write your Students Name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(semail))
        {
            Toast.makeText(this, "Please write your Students Email Id Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(sphone))
        {
            Toast.makeText(this, "Please write your Students Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(stitle))
        {
            Toast.makeText(this, "Please write your Students Title", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Adding Students Information");
            loadingBar.setMessage("Dear Admin Please wait, while we are adding your Students information...!");
            loadingBar.show();

            ValidatePhoneNumberStudents(sname, semail, sphone, stitle);
        }
    }

    private void ValidatePhoneNumberStudents(final String sname, final String semail, final String sphone, final String stitle)
    {
        auth.createUserWithEmailAndPassword(semail, sphone)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Students").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("sid",userid);
                            hashMap.put("sname",sname);
                            hashMap.put("semail",semail);
                            hashMap.put("stitle",stitle);
                            hashMap.put("sphone", sphone);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(AddStudentInfoActivity.this,AdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                            loadingBar.dismiss();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(AddStudentInfoActivity.this,"You can't added your Students information...!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
