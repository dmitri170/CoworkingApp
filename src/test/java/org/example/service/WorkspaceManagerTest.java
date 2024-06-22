package org.example.service;

import org.example.model.Booking;
import org.example.model.Workstation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceManagerTest {
    private WorkspaceManager workspaceManager;
    @BeforeEach
    public void setUp() {
        workspaceManager = new WorkspaceManager();
    }
    @Test
    public void testAddWorkstation() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.addWorkstation(1);
        assertNotNull(manager.workstations.get(1));
    }

    @Test
    public void testAddConferenceRoom() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.addConferenceRoom(1);
        assertNotNull(manager.conferenceRooms.get(1));
    }

    @Test
    public void testRegisterUser() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");
        assertEquals(1, manager.users.size());
    }

    @Test
    public void testLogin() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");
        manager.login("testUser", "password123");
        assertNotNull(manager.currentUser);
    }
    @Test
    public void testBookWorkspaceSuccess() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");
        manager.addWorkstation(1);
        manager.login("testUser", "password123");

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        manager.bookWorkspace(1, startTime, endTime);

        assertEquals(1, manager.bookings.size());
    }

    @Test
    public void testBookWorkspaceFailure_WorkstationNotFound() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        manager.bookWorkspace(1, startTime, endTime);

        assertEquals(0, manager.bookings.size());
    }

    @Test
    public void testLoginFailure_InvalidCredentials() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");

        manager.login("testUser", "wrongPassword");

        assertNull(manager.currentUser);
    }

    @Test
    public void testCancelBooking() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.registerUser("testUser", "password123");
        manager.addWorkstation(1);
        manager.login("testUser", "password123");

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        manager.bookWorkspace(1, startTime, endTime);

        int bookingId = manager.bookings.get(0).getBookingId();
        manager.cancelBooking(bookingId);

        assertTrue(manager.bookings.isEmpty());
    }

    @Test
    public void testFindAvailableWorkstations() {
        WorkspaceManager manager = new WorkspaceManager();
        manager.addWorkstation(1);
        manager.addWorkstation(2);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        List<Workstation> availableWorkstations = manager.findAvailableWorkstations(startTime, endTime);

        assertEquals(2, availableWorkstations.size());
    }
    @Test
    public void testEditBooking() {
        workspaceManager.registerUser("testUser", "password123");
        workspaceManager.addWorkstation(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1);
        workspaceManager.login("testUser", "password123");
        workspaceManager.bookWorkspace(1, startTime, endTime);

        Booking booking = workspaceManager.bookings.get(0);
        LocalDateTime newStartTime = startTime.plusHours(1);
        LocalDateTime newEndTime = endTime.plusHours(1);

        workspaceManager.editBooking(booking.getBookingId(), newStartTime, newEndTime);

        assertEquals(newStartTime, booking.getStartTime());
        assertEquals(newEndTime, booking.getEndTime());
    }
}
