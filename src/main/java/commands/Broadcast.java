package commands;

import client.RegistrationInfo;
import server.IPresenceService;
import java.rmi.RemoteException;

public class Broadcast implements ICommand {
    public void execute(Object[] argv) throws RemoteException {
        try {

            IPresenceService service = (IPresenceService)argv[0];

            RegistrationInfo src = (RegistrationInfo)argv[1];
            String msg = "[ " + src.getUserName() + " ] ";
            String temp = "";
            // build the message.
            for(int i = 2; i < argv.length; i++) {
                temp += (String) argv[i] + " ";
            }

            if(temp.trim().isEmpty()) {
                System.out.println( "Cannot send empty msg." );
                return;
            }

            msg += temp ;
            service.broadcast( msg );

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println( "Please provide receiver and message." );
        }
    }
}
