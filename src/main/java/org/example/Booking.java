package org.example;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private static int idCounter = 1;
    private int userId;
    private int resourceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking(User userId, int resourceId, LocalDateTime startTime, LocalDateTime endTime) {
        this.bookingId = idCounter++;
        this.userId = userId.getId();
        this.resourceId = resourceId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
