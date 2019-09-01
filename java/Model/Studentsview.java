package asha.md_nayeem.schoolmanagement.Model;

public class Studentsview
{
    private String sname, stitle, sphone, simage, semail;

    public Studentsview() {
    }

    public Studentsview(String sname, String stitle, String sphone, String simage, String semail)
    {
        this.sname = sname;
        this.stitle = stitle;
        this.sphone = sphone;
        this.simage = simage;
        this.semail = semail;
    }


    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }
}
