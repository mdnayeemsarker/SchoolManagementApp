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

public class UserTeacherActivity extends AppCompatActivity implements View.OnClickListener
{
    private CardView TeacherInfo, NoticeBoard, ResultAndAdmission, Event, StudentInfo, TeacherBoard;
    private EditText teacherComplainName, teacherComplainPhone, teacherComplainText;
    private Button teacherSubmit;

    private String saveCurrentDate,saveCurrentTime;
    private String productRandomKey;
    private DatabaseReference TeacherComplainRef;
    private ProgressDialog loadingBar;
    private String t_Description,t_Name,t_Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_teacher);

        TeacherInfo = findViewById(R.id.teacher_cardView_teacher_info_ID);
        NoticeBoard = findViewById(R.id.teacher_cardView_notice_ID);
        ResultAndAdmission = findViewById(R.id.teacher_cardView_result_admission_ID);
        Event = findViewById(R.id.teacher_cardView_event_ID);
        StudentInfo = findViewById(R.id.teacher_cardView_student_info_ID);
        TeacherBoard = findViewById(R.id.teacher_cardView_teacher_board_ID);

        TeacherInfo.setOnClickListener(this);
        NoticeBoard.setOnClickListener(this);
        ResultAndAdmission.setOnClickListener(this);
        Event.setOnClickListener(this);
        StudentInfo.setOnClickListener(this);
        TeacherBoard.setOnClickListener(this);

        TeacherComplainRef = FirebaseDatabase.getInstance().getReference().child("TeacherComplain");

        loadingBar = new ProgressDialog(this);

        teacherComplainName = findViewById(R.id.teacher_complain_name_Id);
        teacherComplainPhone = findViewById(R.id.teacher_complain_phone_Id);
        teacherComplainText = findViewById(R.id.teacher_complain_text_Id);
        teacherSubmit = findViewById(R.id.teacher_complain_button_Id);

        teacherSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddTeacherComplain();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teachers, menu);
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
        if (id == R.id.action_teachers_logout_Id)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    // Add Complain Start
    private void AddTeacherComplain()
    {
        t_Description = teacherComplainText.getText().toString();
        t_Name = teacherComplainName.getText().toString();
        t_Phone = teacherComplainPhone.getText().toString();

        if (TextUtils.isEmpty(t_Description))
        {
            Toast.makeText(this, "Complain Details is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(t_Name))
        {
            Toast.makeText(this, "Your name is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(t_Phone))
        {
            Toast.makeText(this, "Your Phone Number is mandatory...!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeTeacherComplainInformation();
        }
    }

    private void storeTeacherComplainInformation()
    {
        loadingBar.setTitle("Add New Complain");
        loadingBar.setMessage("Dear Teacher Please wait, while we are adding the new Complain");
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        SaveTeacherComplainInfoToDatabase();
    }

    private void SaveTeacherComplainInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("id", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", t_Description);
        productMap.put("name", t_Name);
        productMap.put("phone", t_Phone);

        TeacherComplainRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            loadingBar.dismiss();
                            Toast.makeText(UserTeacherActivity.this, "Your Complain added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UserTeacherActivity.this, "Error: "+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Add Complain End

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.teacher_cardView_teacher_info_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(UserTeacherActivity.this, TeachersViewActivity.class));
                break;

            case R.id.teacher_cardView_notice_ID:
                CharSequence options[] = new CharSequence[]
                        {
                                "View Notice",
                                "View Routine"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(UserTeacherActivity.this);
                builder.setTitle("View Notice Board Options:");

                builder.setItems(options, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(UserTeacherActivity.this, NoticeViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(UserTeacherActivity.this, RoutineViewActivity.class));
                        }
                    }
                });
                builder.show();
                break;

            case R.id.teacher_cardView_result_admission_ID:
                CharSequence options1[] = new CharSequence[]
                        {
                                "View Result",
                                "View Admission"
                        };
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserTeacherActivity.this);
                builder1.setTitle("View Result and Admission Options:");

                builder1.setItems(options1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(UserTeacherActivity.this, ResultViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(UserTeacherActivity.this, AdmissionViewActivity.class));
                        }
                    }
                });
                builder1.show();
                break;

            case R.id.teacher_cardView_event_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(UserTeacherActivity.this, EventViewActivity.class));
                break;

            case R.id.teacher_cardView_student_info_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(UserTeacherActivity.this, StudentsViewActivity.class));
                break;

            case R.id.teacher_cardView_teacher_board_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(UserTeacherActivity.this, TeacherBoardViewActivity.class));
                break;
        }
    }
}
