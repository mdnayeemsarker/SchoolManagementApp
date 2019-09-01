package asha.md_nayeem.schoolmanagement.Model;

public class Teacherboard
{
    private String date, description, time;

    public Teacherboard()
    {

    }

    public Teacherboard(String date, String description, String time)
    {
        this.date = date;
        this.description = description;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
