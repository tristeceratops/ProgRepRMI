package com.example.allumetegame.RMI;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RMI du jeu des allumettes
 */
public interface AllumetteInterface extends Remote {
    public int getNbAllumsTotal() throws java.rmi.RemoteException;
    public void updateNbAllums(int joueur, int i) throws java.rmi.RemoteException;
    public void Initialise() throws RemoteException;
    public int getNbAllumsJ1() throws java.rmi.RemoteException;
    public int getNbAllumsJ2() throws java.rmi.RemoteException;
    public int getJoueurJoue() throws RemoteException;
    public void alternerJoueurJoue() throws RemoteException;
    public boolean siJeuFini() throws RemoteException;
}
