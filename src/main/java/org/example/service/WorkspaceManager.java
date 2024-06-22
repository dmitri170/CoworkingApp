package org.example.service;

import org.example.model.Booking;
import org.example.model.ConferenceRoom;
import org.example.model.User;
import org.example.model.Workstation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceManager {//Менеджер рабочих мест
    protected HashMap<Integer, Workstation> workstations = new HashMap<>();
    protected HashMap<Integer, ConferenceRoom> conferenceRooms = new HashMap<>();
    protected List<Booking> bookings = new ArrayList<>();
    protected List<User> users= new ArrayList<>();
    protected User currentUser;

    public void addWorkstation(int workstationId) {
        workstations.put(workstationId, new Workstation());
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
    public void cancelBooking(int bookingId) {
        bookings.removeIf(booking -> booking.getBookingId() == bookingId);
        System.out.println("Booking with ID " + bookingId + " has been cancelled.");
    }
    public void viewBookings() {
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId() +
                    ", User: " + users.get(booking.getUserId()).getUserName() +
                    ", Resource ID: " + booking.getResourceId() +
                    ", Start Time: " + booking.getStartTime() +
                    ", End Time: " + booking.getEndTime());
        }
    }
    // Редактирование существующего бронирования
    public void editBooking(int bookingId, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        Booking booking = bookings.stream()
                .filter(b -> b.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);

        if (booking != null) {
            if (isWorkspaceAvailable(workstations.get(booking.getResourceId()), newStartTime, newEndTime)) {
                booking.setStartTime(newStartTime);
                booking.setEndTime(newEndTime);
                System.out.println("Booking with ID " + bookingId + " has been updated.");
            } else {
                System.out.println("Ошибка: Новые даты конфликтуют с другими бронированиями.");
            }
        } else {
            System.out.println("Ошибка: Бронирование с ID " + bookingId + " не найдено.");
        }
    }
    public List<Workstation> findAvailableWorkstations(LocalDateTime startTime, LocalDateTime endTime) {
        return workstations.values().stream()
                .filter(ws -> isWorkspaceAvailable(ws, startTime, endTime))
                .collect(Collectors.toList());
    }
    public void cancelBookingByAdmin(int bookingId) {
        Booking booking = bookings.stream()
                .filter(b -> b.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);

        if (booking != null) {
            bookings.remove(booking);
            System.out.println("Booking with ID " + bookingId + " has been cancelled by the admin.");
        } else {
            System.out.println("Ошибка: Бронирование с ID " + bookingId + " не найдено.");
        }

    }
    // Функция просмотра списка всех ресурсов (рабочих мест и конференц-залов)
    public void viewAllResources() {
        System.out.println("Список всех доступных ресурсов:");
        workstations.values().forEach(workstation -> System.out.println("Workstation ID: " + workstation.getId()));
        conferenceRooms.values().forEach(room -> System.out.println("Conference Room ID: " + room.getId()));
    }
    public List<Booking> filterBookingsByCriteria(String criteria) {
        return bookings.stream()
                .filter(booking -> bookingMatchesCriteria(booking, criteria))
                .collect(Collectors.toList());
    }

    private boolean bookingMatchesCriteria(Booking booking, String criteria) {
        if (criteria == null || criteria.isEmpty()) {
            return false;
        }

        // Примеры критериев поиска:
        // Поиск по ID пользователя
        if (criteria.startsWith("user:")) {
            int userId = Integer.parseInt(criteria.substring(5));
            return booking.getUserId() == userId;
        }

        // Поиск по ID ресурса
        if (criteria.startsWith("resource:")) {
            int resourceId = Integer.parseInt(criteria.substring(9));
            return booking.getResourceId() == resourceId;
        }
        // Поиск по начальному времени бронирования
        if (criteria.startsWith("startTime:")) {
            LocalDateTime startDate = LocalDateTime.parse(criteria.substring(10));
            return booking.getStartTime().isEqual(startDate);
        }
        // Если критерий не соответствует ни одному из примеров, возвращаем false
        return false;
    }

}
