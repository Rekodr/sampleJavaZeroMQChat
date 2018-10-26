package commands;

import java.rmi.RemoteException;

public interface ICommand {
   void execute(Object[] arguments) throws RemoteException;
}
