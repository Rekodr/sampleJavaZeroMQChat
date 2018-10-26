package communication;

import client.RegistrationInfo;
import org.zeromq.ZMQ;
import java.io.IOException;
import static java.lang.Thread.sleep;

public class MessageListener implements Runnable {

    private int port = 1999;
    private String host = "";


    public MessageListener(RegistrationInfo reg) throws IOException {
        this.port = reg.getPort();
        this.host = reg.getHost();
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to clients
        ZMQ.Socket responder = context.socket(ZMQ.REP);
        String addr = "tcp://" + this.host + ":" + this.port;
        responder.bind(addr);

        while (!Thread.currentThread().isInterrupted()) {
            // Wait for next request from the client
            byte[] request = responder.recv(0);
            System.out.println(new String (request));

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Send reply back to client
            String reply = "ok";
            responder.send(reply.getBytes(), 0);
        }
        responder.close();
        context.term();
    }
}

