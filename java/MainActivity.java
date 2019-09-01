package asha.md_nayeem.schoolmanagement;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button Join, Login, verify;
    public static final String CHANNEL_ID =  "asha";
    private static final String CHANNEL_NAME=  "asha";
    private static final String CHANNEL_DEC =  "asha Notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Join = findViewById(R.id.join_ID);
        Login = findViewById(R.id.login_ID);
        verify = findViewById(R.id.veridy_ID);

        Join.setOnClickListener(this);
        Login.setOnClickListener(this);
        verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()== R.id.join_ID)
        {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        else if (v.getId()== R.id.login_ID)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (v.getId()== R.id.veridy_ID)
        {
            startActivity(new Intent(this, TeacherVerifiActivity.class));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DEC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

}
