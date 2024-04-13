package com.example.application.views;

public class Session {
    private static String loggedInUsername;

    public static void login(String username) {
        loggedInUsername = username;
    }

    public static String getUsername() {
        return loggedInUsername;
    }
}
