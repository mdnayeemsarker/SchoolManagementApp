package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    private EditText InputName,InputPhone,InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Registration Page");

        InputName = findViewById(R.id.register_name_input);
        InputPhone = findViewById(R.id.register_phone_number_input);
        InputPassword = findViewById(R.id.register_password_input);

        loadingBar = new ProgressDialog(this);

        findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CharSequence options1[] = new CharSequence[]
                        {
                                "Teacher",
                                "Student"
                        };
                AlertDialog.Builder builderc = new AlertDialog.Builder(RegisterActivity.this);
                builderc.setTitle("Calculate Option:");

                builderc.setItems(options1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            String name = InputName.getText().toString();
                            String phone = InputPhone.getText().toString();
                            String password = InputPassword.getText().toString();

                            if (TextUtils.isEmpty(name))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Name", Toast.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(phone))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Phone Number", Toast.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(password))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Password", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                CreateAccTeacher(name, phone, password);
                            }
                        }

                        if (i == 1)
                        {
                            String name = InputName.getText().toString();
                            String phone = InputPhone.getText().toString();
                            String password = InputPassword.getText().toString();

                            if (TextUtils.isEmpty(name))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Nmae", Toast.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(phone))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Phone Number", Toast.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(password))
                            {
                                Toast.makeText(RegisterActivity.this, "Please write your Password", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                CreateAccStudent(name, phone, password);
                            }

                        }
                    }
                });
                builderc.show();
            }
        });

    }

    private void CreateAccTeacher(String name, String phone, String password)
    {
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait, while we are checking credentials..!");
        loadingBar.show();

        ValidatePhoneNumberTeacher(name, phone, password);
    }

    private void ValidatePhoneNumberTeacher(final String name, final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("UserTeachers").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("UserTeachers").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account creating is Successful...!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network Error,Please try again after some time....!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This "+phone+" already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    private void CreateAccStudent(String name, String phone, String password)
    {
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait, while we are checking credentials..!");
        loadingBar.show();

        ValidatePhoneNumberStudent(name, phone, password);
    }

    private void ValidatePhoneNumberStudent(final String name, final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("UserStudents").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("UserStudents").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account creating is Successful...!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network Error,Please try again after some time....!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This "+phone+" already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

}
