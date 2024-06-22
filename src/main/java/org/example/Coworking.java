package org.example;

import org.example.service.WorkspaceManager;

import java.io.IOException;
import java.time.LocalDateTime;

public class Coworking {
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

       // Добавление конференц-зала с ID 1
       workspaceManager.addConferenceRoom(1);

       // Просмотр всех ресурсов
       workspaceManager.viewAllResources();

       // Пример бронирования рабочего места
       LocalDateTime startTime = LocalDateTime.now();
       LocalDateTime endTime = LocalDateTime.now().plusHours(2);
       workspaceManager.bookWorkspace(1, startTime, endTime);

       // Просмотр всех бронирований
       workspaceManager.viewBookings();

        // Отмена бронирования
        // workspaceManager.cancelBooking(bookingId); // Замените bookingId на фактический ID бронирования

        // Редактирование бронирования
        // workspaceManager.editBooking(bookingId, newStartTime, newEndTime); // Замените bookingId, newStartTime и newEndTime на конкретные значения

        // Поиск доступных рабочих мест
        // List<Workstation> availableWorkstations = workspaceManager.findAvailableWorkstations(LocalDateTime.now(), LocalDateTime.now().plusHours(3));

        // Отмена бронирования администратором
        // workspaceManager.cancelBookingByAdmin(bookingId); // Замените bookingId на фактический ID бронирования

        // Возможные дополнительные проверки других функций вашего WorkspaceManager

        // Можете продолжить добавлять вызовы других методов для проверки оставшихся функций вашего WorkspaceManager
      //  menu(workspaceManager);
    }

  // public static void menu(WorkspaceManager workspaceManager) {

  //     try {

  //         BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/resources/information.txt"));
  //         while (bufferedReader.ready()){
  //             System.out.println(bufferedReader.readLine());
  //         }
  //         Scanner scanner=new Scanner(System.in);
  //         int numberMenu=scanner.nextInt();
  //         while (numberMenu!=0){
  //             switch (numberMenu){
  //                 case 1:
  //                     workspaceManager.registerUser("user1", "password1");
  //                     break;
  //                 default:
  //                     break;
  //             }
  //         }
  //     }
  //     catch (IOException e)
  //     {
  //         e.printStackTrace();
  //     }

  // }
}