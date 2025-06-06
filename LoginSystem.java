/*
// AUTHOR : WILLYCE OJWANG GWARA
REG NO :BSE-05-0044/2024
The program executed below consist of the source code for a login system with a password and a username prompt that allows access and provides limited attempts to access it 


*/
package login.systems;
import java.util.Scanner;
import java.io.Console;

public class LoginSystem {
    private static final String CORRECT_USERNAME = "Willyce";
    private static final String CORRECT_PASSWORD = "2427December";
    private static final int MAX_ATTEMPTS = 3;
/*
      Authenticates a user by prompting for login credentials.
      Allows up to three attempts before denying access.
      returns true if authentication is successful, false otherwise.
     */
    public static boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        System.out.println("[<] Login System [>]");

        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            attempts++;
/* prompt user name */
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
/* password input handling */
            String password;
            if (console != null) {
                char[] passwordChars = console.readPassword("Enter password: ");
                password = new String(passwordChars);
            } else {
                System.out.print("Enter password (characters will be visible): ");
                password = scanner.nextLine();
            }
/* validate login credentials */
            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                System.out.println("Login successful. Welcome, " + username + ".");
                scanner.close();
                return true;
            } else {
                printErrorMessage(username, password, attempts);
            }
        }
/* access denied after maximum attempts */
        System.out.println("Maximum login attempts reached. Access denied.");
        scanner.close();
        return false;
    }

    private static void printErrorMessage(String username, String password, int attempts) {
        /* identify which input is correct incorrect */
        if (!username.equals(CORRECT_USERNAME) && !password.equals(CORRECT_PASSWORD)) {
            System.out.println("Both username and password are incorrect.");
        } else if (!username.equals(CORRECT_USERNAME)) {
            System.out.println("Username is incorrect.");
        } else {
            System.out.println("Password is incorrect.");
        }
/* shows remaining attempts */
        int remainingAttempts = MAX_ATTEMPTS - attempts;
        if (remainingAttempts > 0) {
            System.out.println("Attempts remaining: " + remainingAttempts);
        }
    }
/* main method to execute the login process */
    public static void main(String[] args) {
        boolean loggedIn = authenticate();
        if (loggedIn) {
            System.out.println("Proceeding to the system...");
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }
}


