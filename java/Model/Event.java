package asha.md_nayeem.schoolmanagement.Model;

public class Event
{
    private String date, description, image, pid, time;

    public Event()
    {

    }

    public Event(String date, String description, String image, String pid, String time)
    {
        this.date = date;
        this.description = description;
        this.image = image;
        this.pid = pid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
