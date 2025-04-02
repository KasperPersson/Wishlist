package org.example.wishlist.model;

public class User {

    private int userID;
    private String name;
    private String mail;
    private String username;
    private String password;
    private String uid;
    private String pw;


    public User(int userID, String name, String mail, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public User(String uid, String pw) {
        this.uid = uid;
        this.pw = pw;
    }

    public String getUid() {
        return uid;
    }

    public void setUid() {
        this.uid = uid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw() {
        this.pw = pw;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
