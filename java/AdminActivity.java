package asha.md_nayeem.schoolmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener
{
    private CardView TeacherInfo, NoticeBoard, ResultAndAdmission, Event, StudentInfo, TeacherBoard;
    private Button teacherComplainButton, StudentComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TeacherInfo = findViewById(R.id.admin_cardView_teacher_info_ID);
        NoticeBoard = findViewById(R.id.admin_cardView_notice_ID);
        ResultAndAdmission = findViewById(R.id.admin_cardView_result_admission_ID);
        Event = findViewById(R.id.admin_cardView_event_ID);
        StudentInfo = findViewById(R.id.admin_cardView_student_info_ID);
        TeacherBoard = findViewById(R.id.admin_cardView_teacher_board_ID);

        teacherComplainButton = findViewById(R.id.teacher_complain_view_Id);
        StudentComplain = findViewById(R.id.student_complain_view_Id);


        TeacherInfo.setOnClickListener(this);
        NoticeBoard.setOnClickListener(this);
        ResultAndAdmission.setOnClickListener(this);
        Event.setOnClickListener(this);
        StudentInfo.setOnClickListener(this);
        TeacherBoard.setOnClickListener(this);

        teacherComplainButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminActivity.this, ComplaintViewActivity.class));
            }
        });

        StudentComplain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminActivity.this, StuComplaneViewActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_teacher)
        {
            startActivity(new Intent(this, AddTeacherInfoActivity.class));
        }
        else if (id == R.id.action_students)
        {
            startActivity(new Intent(this, AddStudentInfoActivity.class));
        }
        else if (id == R.id.action_event)
        {
            startActivity(new Intent(this, AddEventActivity.class));
        }
        else if (id == R.id.action_teacher_board)
        {
            startActivity(new Intent(this, AddTeacherBoardActivity.class));
        }
        else if (id == R.id.action_teacher_logout)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (id == R.id.action_notice_board)
        {
            CharSequence options[] = new CharSequence[]
                    {
                            "Add Notice",
                            "Add Routine"
                    };
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
            builder.setTitle("Add Notice Board Options:");

            builder.setItems(options, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    if (i == 0)
                    {
                        startActivity(new Intent(AdminActivity.this, AddNoticeActivity.class));
                    }
                    if (i == 1)
                    {
                        startActivity(new Intent(AdminActivity.this, AddRoutineActivity.class));
                    }
                }
            });
            builder.show();
        }
        else if (id == R.id.action_result)
        {
            CharSequence options[] = new CharSequence[]
                    {
                            "Add Result",
                            "Add Admission"
                    };
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
            builder.setTitle("Add Result and Admission Options:");

            builder.setItems(options, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    if (i == 0)
                    {
                        startActivity(new Intent(AdminActivity.this, AddResultActivity.class));
                    }
                    if (i == 1)
                    {
                        startActivity(new Intent(AdminActivity.this, AddAdmissionActivity.class));
                    }
                }
            });
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.admin_cardView_teacher_info_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(AdminActivity.this, TeachersViewActivity.class));
                break;

            case R.id.admin_cardView_student_info_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(AdminActivity.this, StudentsViewActivity.class));
                break;

            case R.id.admin_cardView_event_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(AdminActivity.this, EventViewActivity.class));
                break;

            case R.id.admin_cardView_teacher_board_ID:
                /*Toast.makeText(this, "You Select Teacher CardView", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(AdminActivity.this, TeacherBoardViewActivity.class));
                break;

            case R.id.admin_cardView_notice_ID:
                CharSequence options[] = new CharSequence[]
                        {
                                "View Notice",
                                "View Routine"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("Notice Board Options:");

                builder.setItems(options, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(AdminActivity.this, NoticeViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(AdminActivity.this, RoutineViewActivity.class));
                        }
                    }
                });
                builder.show();
                break;

            case R.id.admin_cardView_result_admission_ID:
                CharSequence optionsr[] = new CharSequence[]
                        {
                                "View Result",
                                "View Admission"
                        };
                AlertDialog.Builder builderr = new AlertDialog.Builder(AdminActivity.this);
                builderr.setTitle("Result and Admission Options:");

                builderr.setItems(optionsr, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (i == 0)
                        {
                            startActivity(new Intent(AdminActivity.this, ResultViewActivity.class));
                        }
                        if (i == 1)
                        {
                            startActivity(new Intent(AdminActivity.this, AdmissionViewActivity.class));
                        }
                    }
                });
                builderr.show();
                break;
        }
    }
}
