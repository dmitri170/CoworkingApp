package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
