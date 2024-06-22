package org.example;

import org.example.service.WorkspaceManager;

import java.io.IOException;
import java.time.LocalDateTime;

public class CoworkingApp {
    public static void main(String[] args) throws IOException {
       // Создание экземпляра WorkspaceManager
       WorkspaceManager workspaceManager = new WorkspaceManager();

       // Регистрация пользователей
       workspaceManager.registerUser("user1", "password1");
       workspaceManager.registerUser("user2", "password2");

       // Вход в систему
       workspaceManager.login("user1", "password1");

       // Добавление рабочего места с ID 1
       workspaceManager.addWorkstation(1);
       workspaceManager.addWorkstation(2);
       workspaceManager.addWorkstation(3);

       // Добавление конференц-зала
       workspaceManager.addConferenceRoom(1);
       workspaceManager.addConferenceRoom(2);
       workspaceManager.addConferenceRoom(3);

       // Просмотр всех ресурсов
       workspaceManager.viewAllResources();

       // Пример бронирования рабочего места
       LocalDateTime startTime = LocalDateTime.now();
       LocalDateTime endTime = LocalDateTime.now().plusHours(2);
       workspaceManager.bookWorkspace(1, startTime, endTime);
       workspaceManager.bookWorkspace(2, startTime, endTime);
       workspaceManager.bookWorkspace(3, startTime, endTime);

       // Просмотр всех бронирований
       workspaceManager.viewBookings();
       System.out.println("---------------------------");
       workspaceManager.registerUser("user1", "password1");
       workspaceManager.addWorkstation(1);
       workspaceManager.bookWorkspace(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

       workspaceManager.registerUser("user2", "password2");
       workspaceManager.addWorkstation(2);
       workspaceManager.bookWorkspace(2, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

       workspaceManager.registerUser("user3", "password3");
       workspaceManager.addWorkstation(3);
       workspaceManager.bookWorkspace(3, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

       System.out.println("All bookings:");
       workspaceManager.viewBookings();

       System.out.println(workspaceManager.filterBookingsByCriteria("user:1").toString());

       // Отмена бронирования
       // workspaceManager.cancelBooking(bookingId); // Замените bookingId на фактический ID бронирования

       // Редактирование бронирования
       // workspaceManager.editBooking(bookingId, newStartTime, newEndTime); // Замените bookingId, newStartTime и newEndTime на конкретные значения

       // Поиск доступных рабочих мест
       // List<Workstation> availableWorkstations = workspaceManager.findAvailableWorkstations(LocalDateTime.now(), LocalDateTime.now().plusHours(3));

       // Отмена бронирования администратором
       // workspaceManager.cancelBookingByAdmin(bookingId); // Замените bookingId на фактический ID бронирования

    }

}