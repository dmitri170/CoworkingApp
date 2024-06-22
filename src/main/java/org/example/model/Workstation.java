package org.example.model;

public class Workstation {//рабочее место
    private int id;
    private static int idCounter = 1;
    private boolean isAvailable;

    public Workstation() {
        this.id = idCounter++;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
