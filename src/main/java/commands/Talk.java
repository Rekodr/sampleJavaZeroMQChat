package commands;

import client.RegistrationInfo;
import communication.MessageSender;
import server.IPresenceService;
import java.rmi.RemoteException;


public class Talk implements ICommand {
    public void execute(Object[] argv) throws RemoteException {

        try {
            RegistrationInfo src = (RegistrationInfo)argv[1];
            String dst_usrname = (String)argv[2];
            String msg = "[ " + src.getUserName() + " ] ";
            String temp = "";
            // build the message.
            for(int i = 3; i < argv.length; i++) {
                temp += (String) argv[i] + " ";
            }

            if(temp.trim().isEmpty()) {
                System.out.println( "Cannot send empty msg." );
                return;
            }

            msg += temp ;

            MessageSender.sendMessage( src, dst_usrname, (IPresenceService)argv[0], msg );

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println( "Please provide receiver and message." );
        }
    }
}
