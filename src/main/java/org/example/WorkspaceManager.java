package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkspaceManager {
    private HashMap<Integer, Workstation> workstations = new HashMap<>();
    private HashMap<Integer, ConferenceRoom> conferenceRooms = new HashMap<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<User> users;
    private User currentUser;

    public void registerUser(int id, String username, String password) {
        users.add(new User(username, password));
    }
    public void addWorkstation(int workstationId) {
        workstations.put(workstationId, new Workstation(workstationId));
    }

    public void addConferenceRoom(int roomId) {
        conferenceRooms.put(roomId, new ConferenceRoom(roomId));
    }
    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }
    public void login(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Successfully logged in as " + currentUser.getUserName());
                return;
            }
        }
        System.out.println("Login failed. Invalid username or password.");
    }
    public void bookWorkspace(int workstationId, LocalDateTime startTime, LocalDateTime endTime) {
        Workstation workstation = workstations.get(workstationId);
        if (workstation == null) {
            System.out.println("Ошибка: Рабочее место с ID " + workstationId + " не найдено.");
            return;
        }

        if (!isWorkspaceAvailable(workstation, startTime, endTime)) {
            System.out.println("Ошибка: Рабочее место занято в указанное время.");
            return;
        }

        Booking newBooking = new Booking(currentUser, workstationId, startTime, endTime);
        bookings.add(newBooking);
        System.out.println("Успешно забронировано рабочее место " + workstationId + " для " + currentUser.getUserName() + " с " + startTime + " до " + endTime + ".");
    }
    private boolean isWorkspaceAvailable(Workstation workstation, LocalDateTime startTime, LocalDateTime endTime) {
        for (Booking booking : bookings) {
            if (booking.getResourceId()==workstation.getId()) {
                if (startTime.isBefore(booking.getEndTime()) && endTime.isAfter(booking.getStartTime())) {
                    return false;
                }
            }
        }
        return true;
    }
}
