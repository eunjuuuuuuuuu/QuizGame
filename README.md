# QuizGame

This project is a Client-Server Quiz Application built using Java. It demonstrates the use of socket programming and TCP communication to create an interactive quiz game. The server provides quiz questions to the client, evaluates the answers, and calculates the score in real-time.

Client-Server Communication: Implements TCP socket programming for reliable data transfer between the client and server.
Interactive Quiz: The server sends multiple-choice questions to the client, and the client submits answers.
Real-time Feedback: The server validates the answers and provides instant feedback for each question.
Score Tracking: The server calculates and displays the final score at the end of the quiz.

# Example Gameplay

Client Output

Q1: What is the main protocol used for web traffic?
a) HTTP 
b) FTP
c) SMTP
d) DNS
Enter your answer (a/b/c/d):
> a
Your answer: a
Correct!

# Message Protocol

The server and client exchange text-based messages. Below is the format of the messages:

Server → Client:
Sends questions with options:

Q1: What is the main protocol used for web traffic?
a) HTTP
b) FTP
c) SMTP
d) DNS
Enter your answer (a/b/c/d):

Client → Server:
Sends the user's selected option:

a

Server → Client:
Provides feedback:

Correct!





