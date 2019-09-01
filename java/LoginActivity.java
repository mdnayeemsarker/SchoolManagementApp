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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import asha.md_nayeem.schoolmanagement.Model.Users;

public class LoginActivity extends AppCompatActivity
{
    private EditText InputNumber, InputPassword;
    private Button LoginButton;
    private String parentDbName = "Students";
    private ProgressDialog loadingBar;
    private Intent intent;

    private TextView Students,Teachers, AdminTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login Page");

        InputNumber = findViewById(R.id.login_phone_number_input);
        InputPassword = findViewById(R.id.login_password_input);
        LoginButton = findViewById(R.id.login_btn);


        Students = findViewById(R.id.student_link);
        Teachers = findViewById(R.id.teacher_link);
        AdminTeachers = findViewById(R.id.admin_teacher_link);

        Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login Students");
                Students.setVisibility(View.INVISIBLE);
                Teachers.setVisibility(View.VISIBLE);
                parentDbName = "UserStudents";
            }
        });

        Teachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login Teachers");
                Students.setVisibility(View.VISIBLE);
                Teachers.setVisibility(View.INVISIBLE);
                parentDbName = "UserTeachers";
            }
        });

        AdminTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login Admin");
                Students.setVisibility(View.VISIBLE);
                Teachers.setVisibility(View.INVISIBLE);
                parentDbName = "AdminTeacher";
            }
        });

        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LoginUser();
            }
        });

    }

    private void LoginUser()
    {
        String phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking credentials..!");
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            if (parentDbName.equals("AdminTeacher"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome Here, you are logged in successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                intent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("UserTeachers"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome Here, you are logged in successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                intent = new Intent(LoginActivity.this, UserTeacherActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("UserStudents"))
                            {
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                intent = new Intent(LoginActivity.this, UserStudentActivity.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Password is incorrect..!", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this "+phone+" Number do not exists...!!" + "\nyou need to create a new account..!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
