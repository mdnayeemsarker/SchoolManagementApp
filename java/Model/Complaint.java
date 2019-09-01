package asha.md_nayeem.schoolmanagement.Model;

public class Complaint
{
    private String date, description, name, phone, time;

    public Complaint()
    {

    }

    public Complaint(String date, String description, String name, String phone, String time)
    {
        this.date = date;
        this.description = description;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
