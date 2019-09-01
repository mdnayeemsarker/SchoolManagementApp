package asha.md_nayeem.schoolmanagement.Model;

public class Spln
{
    private String name, date, time, classname, description, phone;

    public Spln()
    {

    }

    public Spln(String name, String date, String time, String classname, String description, String phone)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.classname = classname;
        this.description = description;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
