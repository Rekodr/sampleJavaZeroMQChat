package commands;

import client.RegistrationInfo;
import server.IPresenceService;
import java.rmi.RemoteException;

public class Busy implements ICommand {
    public void execute(Object[] argv) throws RemoteException {
        if(((RegistrationInfo)argv[1]).getStatus() == false) {
            System.out.println("Your status is already set to: Not Available ");
            return;
        }

        ((RegistrationInfo)argv[1]).setStatus(false);
        boolean result = ((IPresenceService)argv[0]).updateRegistrationInfo((RegistrationInfo)argv[1]);
        if(result) {
            System.out.println("Your was updated to: Not Available.");
        } else {
            System.out.println("An error occured.");
        }
    }

}

