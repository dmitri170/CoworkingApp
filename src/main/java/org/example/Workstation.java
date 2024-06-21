package org.example;

public class Workstation {
    private int id;
    private boolean isAvailable;

    public Workstation(int id) {
        this.id = id;
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
