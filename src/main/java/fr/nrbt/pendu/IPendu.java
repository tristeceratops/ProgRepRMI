package main.java.fr.nrbt.pendu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPendu extends Remote {
    void initialiser() throws RemoteException;
    String getMotUtilisateur() throws RemoteException;
    void sendLettre(char c) throws RemoteException;
    EPenduState getState() throws RemoteException;
    int getRound() throws RemoteException;
}
