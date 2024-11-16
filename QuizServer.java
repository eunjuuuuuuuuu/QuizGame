import java.io.*;
import java.net.*;

public class QuizServer {
    private static final int PORT = 6789; // Port where the server will run
    private static final String[][] QUESTIONS = {
        {"What is the main protocol used for web traffic?", "a) HTTP\nb) FTP\nc) SMTP\nd) DNS", "a"},
        {"What is the standard port number for HTTP?", "a) 443\nb) 21\nc) 80\nd) 25", "c"},
        {"How many bits are there in an IPv4 address?", "a) 256\nb) 128\nc) 64\nd) 32", "d"},
        {"Which protocol provides reliable data transfer: TCP or UDP?", "a) TCP\nb) UDP", "a"},
        {"What is the term for a network device that performs functions like NAT, firewalls, or load balancing beyond basic IP routing?", "a) Router\nb) Switch\nc) Middlebox\nd) Hub", "c"}
    };

    public static void main(String[] args) {
        System.out.println("Quiz Server is running on port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting for a client to connect...");
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        int score = 0;

        for (int i = 0; i < QUESTIONS.length; i++) {
            String question = QUESTIONS[i][0];
            String options = QUESTIONS[i][1];
            String correctAnswer = QUESTIONS[i][2];

            // Send the question and options to the client
            out.println("Q" + (i + 1) + ": " + question);
            out.println(options);
            out.println("Enter your answer (a/b/c/d):");

            // Receive the client's answer
            String clientAnswer = in.readLine();
            out.println("Your answer: " + clientAnswer);

            // Check the answer and provide feedback
            if (clientAnswer.equalsIgnoreCase(correctAnswer)) {
                out.println("Correct!");
                score++;
            } else {
                out.println("Incorrect! The correct answer was: " + correctAnswer);
            }
            out.println(); // Add a blank line to separate questions
        }

        // Send the final score to the client
        out.println("Quiz finished! Your final score is: " + score + "/" + QUESTIONS.length);
    }
}
