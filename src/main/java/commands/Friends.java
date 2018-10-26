package commands;

import client.RegistrationInfo;
import server.IPresenceService;
import java.rmi.RemoteException;
import java.util.Vector;

public class Friends implements ICommand {
    public void execute(Object[] argv) throws RemoteException {
        Vector<RegistrationInfo> reg_list = ((IPresenceService)argv[0]).listRegisteredUsers();

        // only one user. meaning you are alone.
        if (reg_list.size() == 1) {
            System.out.println( "No friend available." );
            return;
        }

        for(RegistrationInfo registration : reg_list ) {
            String local_name = ((RegistrationInfo)argv[1]).getUserName();
            String fried_name = registration.getUserName();
            if(!local_name.equals( fried_name )) {
                String status = (registration.getStatus() == true) ? "Available" : "Not available";
                System.out.println(registration.getUserName() + ": " + status);
            }
        }
    }
}

