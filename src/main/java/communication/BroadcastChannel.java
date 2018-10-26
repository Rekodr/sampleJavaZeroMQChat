package communication;


import client.RegistrationInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.zeromq.ZMQ;

import static java.lang.Thread.sleep;

public class BroadcastChannel implements Runnable {

    private int port = 1999;
    private String host = "";
    private String name;

    public BroadcastChannel(RegistrationInfo reg) throws IOException {
        this.port = reg.getPort();
        this.host = reg.getHost();
        this.name = reg.getUserName();

    }

    public String getAddr() {
        return "tcp://*:5556";
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect(this.getAddr());
        subscriber.subscribe("sub".getBytes());

        String localUserPrefix = "[ " + this.name + " ] ";

        while (!Thread.currentThread ().isInterrupted ()) {
            String addr = subscriber.recvStr ();
            String contents = subscriber.recvStr ();

            if(contents.startsWith( localUserPrefix ) == false) {
                System.out.println(contents);
            }
        }
        subscriber.close();
        context.term();
    }
}
