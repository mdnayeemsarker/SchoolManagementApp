package asha.md_nayeem.schoolmanagement.Model;

public class Teachers
{
    private String name, title, phone, image, email;

    public Teachers()
    {

    }

    public Teachers(String name, String title, String phone, String image, String email)
    {
        this.name = name;
        this.title = title;
        this.phone = phone;
        this.image = image;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
