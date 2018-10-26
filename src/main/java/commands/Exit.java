package commands;

import client.RegistrationInfo;
import server.IPresenceService;
import java.rmi.RemoteException;

public class Exit implements ICommand {
    public void execute(Object[] argv) throws RemoteException {
        String username = ((RegistrationInfo)argv[1]).getUserName();
        ((IPresenceService)argv[0]).unregister(username);
        System.out.println("You have unregister yourself. Bye.");
        System.exit(0);
    }
}


