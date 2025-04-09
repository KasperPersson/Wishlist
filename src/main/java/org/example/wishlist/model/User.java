package org.example.wishlist.model;

public class User {


    private String name;
    private String uid;
    private String pw;


    public User(String uid, String pw) {
        this.uid = uid;
        this.pw = pw;
    }

    public String getUid() {
        return uid;
    }


    public String getPw() {
        return pw;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
