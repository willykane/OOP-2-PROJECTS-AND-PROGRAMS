/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package login.systems;
import java.util.Scanner;
import java.io.Console;

public class LoginSystem {
    private static final String CORRECT_USERNAME = "Willyce";
    private static final String CORRECT_PASSWORD = "2427December";
    private static final int MAX_ATTEMPTS = 3;

    public static boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        System.out.println("[<] Login System [>]");

        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            attempts++;

            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            String password;
            if (console != null) {
                char[] passwordChars = console.readPassword("Enter password: ");
                password = new String(passwordChars);
            } else {
                System.out.print("Enter password (characters will be visible): ");
                password = scanner.nextLine();
            }

            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                System.out.println("Login successful. Welcome, " + username + ".");
                scanner.close();
                return true;
            } else {
                printErrorMessage(username, password, attempts);
            }
        }

        System.out.println("Maximum login attempts reached. Access denied.");
        scanner.close();
        return false;
    }

    private static void printErrorMessage(String username, String password, int attempts) {
        if (!username.equals(CORRECT_USERNAME) && !password.equals(CORRECT_PASSWORD)) {
            System.out.println("Both username and password are incorrect.");
        } else if (!username.equals(CORRECT_USERNAME)) {
            System.out.println("Username is incorrect.");
        } else {
            System.out.println("Password is incorrect.");
        }

        int remainingAttempts = MAX_ATTEMPTS - attempts;
        if (remainingAttempts > 0) {
            System.out.println("Attempts remaining: " + remainingAttempts);
        }
    }

    public static void main(String[] args) {
        boolean loggedIn = authenticate();
        if (loggedIn) {
            System.out.println("Proceeding to the system...");
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }
}


