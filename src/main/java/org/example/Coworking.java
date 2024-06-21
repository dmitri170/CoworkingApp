package org.example;

public class Coworking {
    public static void main(String[] args) {
        WorkspaceManager workspaceManager = new WorkspaceManager();

        workspaceManager.registerUser("user1", "password1");
        workspaceManager.registerUser("user2", "password2");

        workspaceManager.login("user1", "password1");
    }
}