package org.example;

public class User {
    private static int idCounter = 1;
    private int id;
    private String userName;
    private String password;

    public User( String userName, String password) {
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
