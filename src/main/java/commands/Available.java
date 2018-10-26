package commands;

import client.RegistrationInfo;
import server.IPresenceService;
import java.rmi.RemoteException;

public class Available implements ICommand {
    public void execute(Object[] argv) throws RemoteException {
        if(((RegistrationInfo)argv[1]).getStatus() == true) {
            System.out.println("Your status is already set to: Available ");
            return;
        }

        ((RegistrationInfo)argv[1]).setStatus(true);
        boolean result = ((IPresenceService)argv[0]).updateRegistrationInfo((RegistrationInfo)argv[1]);
        if(result) {
            System.out.println("Your status was updated to: Available.");
        } else {
            System.out.println("An error occured.");
        }
    }
}

