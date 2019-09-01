package asha.md_nayeem.schoolmanagement.Model;

public class Routines
{
    private String date, description, image, id, time, title;

    public Routines()
    {

    }

    public Routines(String date, String description, String image, String id, String time, String title)
    {
        this.date = date;
        this.description = description;
        this.image = image;
        this.id = id;
        this.time = time;
        this.title = title;
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
        return id;
    }

    public void setPid(String pid) {
        this.id = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
