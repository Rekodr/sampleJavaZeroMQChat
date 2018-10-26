package communication;

import client.RegistrationInfo;
import server.IPresenceService;
import org.zeromq.ZMQ;

import java.rmi.RemoteException;

public class MessageSender implements Runnable{

    private String message;
    private String host;
    private int port;

    public MessageSender(String msg, RegistrationInfo dst_reg) {
        this.message = msg;
        this.port = dst_reg.getPort();
        this.host = dst_reg.getHost();
    }

    public void send() {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect(this.getAddr());

        requester.send(this.message.getBytes(), 0);
        byte[] reply = requester.recv(0);

        requester.close();
        context.term();
    }

    public String getAddr() {
        return "tcp://" + this.host + ":" + this.port;
    }

    public static void sendMessage(RegistrationInfo src, String dst_username, IPresenceService presenceService, String msg) throws RemoteException {

        RegistrationInfo dst_reg = presenceService.lookup( dst_username );

        if(dst_reg == null) {
            System.out.println( "Destination user is not registered." );
            return;
        }

        if(!dst_reg.getStatus()) {
            System.out.println( "Destination user is not available." );
            return;
        }



        MessageSender sender = new MessageSender( msg, dst_reg );
        Thread thread = new Thread( sender );
        thread.start();

    }

    @Override
    public void run() {
        this.send();
    }
}

