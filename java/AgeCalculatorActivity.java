package asha.md_nayeem.schoolmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgeCalculatorActivity extends AppCompatActivity
{

    private EditText pDay, pMonth, pYear, bDay, bMonth, bYear;
    private TextView showDate, showPrDate, showBrDate;
    int prDay, prMonth, prYear, prhDay = 0, prhMonth = 0, prhYear = 0, brDay, brMonth, brYear, Day, Month, Year;
    private String test = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        pDay = findViewById(R.id.present_date);
        pMonth = findViewById(R.id.present_month);
        pYear = findViewById(R.id.present_year);
        bDay = findViewById(R.id.birth_date);
        bMonth = findViewById(R.id.birth_month);
        bYear = findViewById(R.id.birth_year);

        showDate = findViewById(R.id.show_Id);
        showPrDate = findViewById(R.id.show_pr_Id);
        showBrDate = findViewById(R.id.show_br_Id);

        findViewById(R.id.calculate_Age_Button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPrDate.setVisibility(View.VISIBLE);
                showBrDate.setVisibility(View.VISIBLE);
                showDate.setVisibility(View.VISIBLE);


                prDay = Integer.parseInt(pDay.getText().toString());
                prMonth = Integer.parseInt(pMonth.getText().toString());
                prYear = Integer.parseInt(pYear.getText().toString());
                brDay = Integer.parseInt(bDay.getText().toString());
                brMonth = Integer.parseInt(bMonth.getText().toString());
                brYear = Integer.parseInt(bYear.getText().toString());

                showPrDate.setText("Day == " + prDay + ", Month == " + prMonth + ", Year == " + prYear);
                showBrDate.setText("Day == " + brDay + ", Month == " + brMonth + ", Year == " + brYear + "\n ......................................................");

                pDay.setText("");
                pMonth.setText("");
                pYear.setText("");
                bDay.setText("");
                bMonth.setText("");
                bYear.setText("");

                if (prDay > 31 || brDay > 31)
                {
                    Toast.makeText(AgeCalculatorActivity.this, "You enter Invalid Day,,,, \nPlease enter a valid day...!", Toast.LENGTH_SHORT).show();
                }
                else if (prMonth > 12 || brMonth > 12)
                {
                    Toast.makeText(AgeCalculatorActivity.this, "You enter Invalid Month,,,, \nPlease enter a valid Month...!", Toast.LENGTH_SHORT).show();
                }

                calculateAge(prDay, prMonth, prYear, brDay, brMonth, brYear);

            }
        });
    }

    private void calculateAge(int prDay, int prMonth, int prYear, int brDay, int brMonth, int brYear)
    {
        if (prDay < brDay)
        {
            prhDay = prDay + 30;
            Day = prhDay - brDay;
            int prhhMonth = 0;
            if (prMonth < brMonth)
            {
                prhhMonth = prMonth + 12;
                Month = prhhMonth - brMonth;
                prhMonth = Month - 1;

                Year = prYear - brYear;

                prhYear = Year - 1;
                Toast.makeText(this, "Day == " + Day + ", Month == " + prhMonth + ", Year == " + prhYear, Toast.LENGTH_SHORT).show();
                showDate.setText("Day == " + Day + ", Month == " + prhMonth + ", Year == " + prhYear);
            }
            else
            {
                Month = prMonth - brMonth;
                prhMonth = Month - 1;

                Year = prYear - brYear;
                Toast.makeText(this, "Day == " + Day + ", Month == " + prhMonth + ", Year == " + Year, Toast.LENGTH_SHORT).show();
                showDate.setText("Day == " + Day + ", Month == " + prhMonth + ", Year == " + Year);
            }
        }
        else
        {
            Day = prDay - brDay;

            if (prMonth < brMonth)
            {
                prhMonth = prMonth + 12;
                Month = prhMonth - brMonth;

                Year = prYear - brYear;
                prhYear = Year - 1;
                Toast.makeText(this, "Day == " + Day + ", Month == " + Month + ", Year == " + prhYear, Toast.LENGTH_SHORT).show();
                showDate.setText("Day == " + Day + ", Month == " + Month + ", Year == " + prhYear);
            }
            else
            {
                Month = prMonth - brMonth;

                Year = prYear - brYear;
                Toast.makeText(this, "Day == " + Day + ", Month == " + Month + ", Year == " + Year, Toast.LENGTH_SHORT).show();
                showDate.setText("Day == " + Day + ", Month == " + Month + ", Year == " + Year);
            }
        }
    }
}
