package asha.md_nayeem.schoolmanagement;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SingaleGpaActivity extends AppCompatActivity
{

    private EditText inputeEditText;
    int marks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singale_gpa);

        inputeEditText = findViewById(R.id.gpa_input_Id);

        findViewById(R.id.shod_gpa_Id).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                marks = Integer.parseInt(inputeEditText.getText().toString());

                if (marks <= 100)
                {
                    if (/*99 <= marks && */marks >= 80)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 5.00 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 75)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 4.50 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 70)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 4.00 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 60)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 3.50 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 50)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 3.00 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 40)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 2.00 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else if (/*99 <= marks && */marks >= 33)
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Congratulation,,,\nYou got GPA: 1.00 " ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                    else
                    {
                        CharSequence options1[] = new CharSequence[]
                                {
                                        "Sad newa,,,\nYou are fail in the exam." ,
                                };
                        AlertDialog.Builder builderc = new AlertDialog.Builder(SingaleGpaActivity.this);
                        builderc.setTitle("Calculate Option:");

                        builderc.setItems(options1, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    finish();
                                    inputeEditText.setText("");
                                }
                            }
                        });
                        builderc.setCancelable(false);
                        builderc.show();
                    }
                }
                else
                {
                    Toast.makeText(SingaleGpaActivity.this, "You Not enter valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
