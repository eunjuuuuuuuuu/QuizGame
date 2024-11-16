import java.io.*;
import java.net.*;

public class QuizClient {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 6789;         // Server port number

    public static void main(String[] args) {
        System.out.println("Starting QuizClient...");

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Read messages from the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Send messages to the server
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in)) // Read user input from the console
        ) {
            System.out.println("Connected to server at " + SERVER_IP + ":" + SERVER_PORT);

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                // Display server messages
                if (!serverMessage.startsWith("Enter your answer")) { // Print only messages that are not input prompts
                    System.out.println(serverMessage);
                }

                // Handle answer input prompt
                if (serverMessage.startsWith("Enter your answer")) {
                    System.out.print("> "); // Display input prompt
                    String userInput = consoleInput.readLine(); // Read user input
                    out.println(userInput); // Send user input to the server
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }
}
