package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ProcessIncomingRequest implements Runnable {
    private Socket clientSocket;

    public ProcessIncomingRequest(Socket clientSocket) {
        super();
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String message;
        BufferedReader is;

        try {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            message = is.readLine();
            if(message == null) {
                return;
            }
            System.out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

