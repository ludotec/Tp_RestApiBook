package ste.com.tp_restapi.models;

public class AuthorModel {

    private int id;
    private String firstName;
    private String lastName;
    private String urlImg;

    public AuthorModel(int id, String firstName, String lastName, String urlImg) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.urlImg = urlImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
