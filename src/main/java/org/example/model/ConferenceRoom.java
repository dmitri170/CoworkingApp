package org.example.model;

public class ConferenceRoom {//конференц зал
    private int id;
    private boolean isAvailable;

    public ConferenceRoom(int id) {
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
