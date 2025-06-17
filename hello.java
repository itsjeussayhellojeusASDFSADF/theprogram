import java.awt.Desktop; // Required for opening URLs in the default browser
import java.io.IOException; // Required for Desktop.browse() exception handling
import java.net.URI; // Required for URI creation
import java.net.URISyntaxException; // Required for URI creation exception handling
import java.util.Scanner; // Required for user input
import java.util.concurrent.TimeUnit; // Required for TimeUnit.SECONDS.sleep()

public class LockedProgram {

    public static void main(String[] args) {
        // Define the correct unlock code
        final String CORRECT_CODE = "1A 6F 4R 9K 2A";
        // Define the URL to redirect to upon successful unlock
        final String REDIRECT_URL_UNLOCKED = "https://docs.google.com/document/d/1g11xsr7R4EK63QtBN1hifpaADHHTiujdaZCsgjeJfpE/edit?usp=sharing";
        // Define the URL for the hint
        final String HINT_URL = "https://docs.google.com/document/d/1JYyLotzs2ZrWMWrD15gJQyxB4zb6uSIbR45hlNQVWuE/edit?usp=sharing";

        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Secure Lock Program ---");
        System.out.println("Please enter the unlock code.");
        System.out.println("For a hint, visit: " + HINT_URL); // Display the hint link in the console

        // Loop indefinitely until the correct code is entered
        while (true) {
            System.out.print("Enter code: ");
            String enteredCode = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces

            // Check if the entered code matches the correct code (case-sensitive)
            if (enteredCode.equals(CORRECT_CODE)) {
                System.out.println("\nUnlocked! Opening web page in your default browser...");
                try {
                    // Check if the Desktop API is supported on the current platform
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        // Create a URI object from the URL string
                        URI uri = new URI(REDIRECT_URL_UNLOCKED);
                        // Open the URI in the default web browser
                        Desktop.getDesktop().browse(uri);
                    } else {
                        System.out.println("Desktop browsing is not supported on this system.");
                        System.out.println("Please open the following link manually: " + REDIRECT_URL_UNLOCKED);
                    }
                } catch (IOException | URISyntaxException e) {
                    // Catch exceptions that might occur during URI creation or browsing
                    System.err.println("An error occurred while trying to open the web page: " + e.getMessage());
                    System.out.println("Please open the following link manually: " + REDIRECT_URL_UNLOCKED);
                }
                break; // Exit the loop as the code is correct
            } else {
                System.out.println("Incorrect Code. Please try again.");
                try {
                    // Pause execution for 1 second for better user experience
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                    System.err.println("Program sleep interrupted.");
                }
            }
        }

        scanner.close(); // Close the scanner to release system resources
        System.out.println("Program finished.");
    }
}
