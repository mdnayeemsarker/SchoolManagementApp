package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

public class UserStudentActivity extends AppCompatActivity implements View.OnClickListener
{
    private CardView TeacherInfo, NoticeBoard, ResultAndAdmission, Event;
    private EditText studentComplainName, studentComplainPhone,studentComplainClass, studentComplainText;
    private Button studentSubmit, CalculateGpa;

    private String saveCurrentDate,saveCurrentTime;
    private String productRandomKey;
    private DatabaseReference StudentComplainRef;
    private ProgressDialog loadingBar;
    private String s_Description, s_Name, s_Phone, s_Class;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_student);

        TeacherInfo = findViewById(R.id.student_cardView_teacher_info_ID);
        NoticeBoard = findViewById(R.id.student_cardView_notice_ID);
        ResultAndAdmission = findViewById(R.id.student_cardView_result_admission_ID);
        Event = findViewById(R.id.student_cardView_event_ID);

        TeacherInfo.setOnClickListener(this);
        NoticeBoard.setOnClickListener(this);
        ResultAndAdmission.setOnClickListener(this);
        Event.setOnClickListener(this);

        StudentComplainRef = FirebaseDatabase.getInstance().getReference().child("StudentComplain");

        loadingBar = new ProgressDialog(this);

        studentComplainName = findViewById(R.id.student_complain_name_Id);
        studentComplainPhone = findViewById(R.id.student_complain_phone_Id);
        studentComplainClass= findViewById(R.id.student_complain_class_Id);
        studentComplainText = findViewById(R.id.student_complain_text_Id);
        studentSubmit = findViewById(R.id.student_complain_button_Id);

        studentSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddStudentComplain();
            }
        });
        findViewById(R.id.Calculator_Id).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CharSequence options1[] = new CharSequence[]
                        {
                                "Single Subject GPA",
                                "Age Calculator"
                        };
                AlertDialog.Builder builderc = new AlertDialog.Builder(UserStudentActivity.this);
                builderc.setTitle("Calculate Option:");

                builderc.setItems(options1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(UserStudentActivity.this, SingaleGpaActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(UserStudentActivity.this, AgeCalculatorActivity.class));
                        }
                    }
                });
                builderc.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_students_logout_Id)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //Add Complain Start
    private void AddStudentComplain()
    {
        s_Description = studentComplainText.getText().toString();
        s_Name = studentComplainName.getText().toString();
        s_Class = studentComplainClass.getText().toString();
        s_Phone = studentComplainPhone.getText().toString();

        if (TextUtils.isEmpty(s_Description))
        {
            Toast.makeText(this, "Complain Details is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(s_Name))
        {
            Toast.makeText(this, "Your name is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(s_Class))
        {
            Toast.makeText(this, "Your Class is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(s_Phone))
        {
            Toast.makeText(this, "Your Phone Number is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeStudentComplainInformation();
        }
    }

    private void storeStudentComplainInformation()
    {
        loadingBar.setTitle("Add New Complain");
        loadingBar.setMessage("Dear Student Please wait, while we are adding the new Complain");
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        SaveStudentComplainInfoToDatabase();
    }

    private void SaveStudentComplainInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("id", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", s_Description);
        productMap.put("name", s_Name);
        productMap.put("classname", s_Class);
        productMap.put("phone", s_Phone);

        StudentComplainRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            loadingBar.dismiss();
                            Toast.makeText(UserStudentActivity.this, "Your Complain is added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UserStudentActivity.this, "Error: "+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //Add Complain End

    //CardView Clicked
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.student_cardView_teacher_info_ID:
                startActivity(new Intent(UserStudentActivity.this, TeachersViewActivity.class));
                break;

            case R.id.student_cardView_notice_ID:
                CharSequence options[] = new CharSequence[]
                        {
                                "View Notice",
                                "View Routine"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(UserStudentActivity.this);
                builder.setTitle("View Notice Board Options:");

                builder.setItems(options, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(UserStudentActivity.this, NoticeViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(UserStudentActivity.this, RoutineViewActivity.class));
                        }
                    }
                });
                builder.show();
                break;

            case R.id.student_cardView_result_admission_ID:
                CharSequence options1[] = new CharSequence[]
                        {
                                "View Result",
                                "View Admission"
                        };
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserStudentActivity.this);
                builder1.setTitle("View Result and Admission Options:");

                builder1.setItems(options1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(UserStudentActivity.this, ResultViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(UserStudentActivity.this, AdmissionViewActivity.class));
                        }
                    }
                });
                builder1.show();
                break;

            case R.id.student_cardView_event_ID:
                startActivity(new Intent(UserStudentActivity.this, EventViewActivity.class));
                break;
        }
    }
}
