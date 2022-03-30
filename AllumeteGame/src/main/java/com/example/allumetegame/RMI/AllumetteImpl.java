package com.example.allumetegame.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe implementant l'interface pour la methode RMI pour le jeu des allumettes.
 * Gere le nombre d'allumettes de chaque joueur,
 * le nombre d'allumettes au centre,
 * l'ordre du jeu,
 * et savoir quand le jeu est finis et qui a gagne.
 *
 */
public class AllumetteImpl extends UnicastRemoteObject implements AllumetteInterface{

    private int nbAllumsTotal;
    private int nbAllumsJ1;
    private int nbAllumsJ2;
    private int joueurJoue;

    protected AllumetteImpl() throws RemoteException {
        super();
    }

    /**
     * Vérifie que la partie est fini
     * @return boolean
     * @throws RemoteException
     */
   @Override
    public boolean siJeuFini() throws RemoteException{
        return  this.nbAllumsTotal== 0;
    }

    /**
     * renvoie le nombre d'allumette au centre
     * @return int
     * @throws RemoteException
     */
    @Override
    public int getNbAllumsTotal() throws RemoteException{
        return this.nbAllumsTotal;
    }

    /**
     * Initialise la partie de jeu
     * @throws RemoteException
     */
    @Override
    public void Initialise() throws RemoteException {
        this.nbAllumsTotal = ObtenirNombreImpairAleatoire(15,28);
        this.nbAllumsJ1 = 0;
        this.nbAllumsJ2 = 0;
        this.joueurJoue = ObtenirNombreAleatoire(1,3);
    }

    /**
     * renvoie le nombre d'allumette du joueur 1
     * @return int
     * @throws RemoteException
     */
    @Override
    public int getNbAllumsJ1() throws RemoteException {
        return this.nbAllumsJ1;
    }

    /**
     * renvoie le nombre d'allumette du joueur 1
     * @return int
     * @throws RemoteException
     */
    @Override
    public int getNbAllumsJ2() throws RemoteException {
        return this.nbAllumsJ2;
    }

    /**
     * Met à jour les allumettes en ajoutant i(1 ou 2) allumette(s) à un joueur(1 ou 2) et en retiant la même valeur au centre
     * @param joueur
     * @param i
     * @throws RemoteException
     */
    @Override
    public void updateNbAllums  (int joueur, int i) throws RemoteException{
        if (joueur == 1){
            if (i == 1 || i == 2)
            {
                this.nbAllumsTotal = this.nbAllumsTotal - i;
                this.nbAllumsJ1 = this.nbAllumsJ1 + i;
            }
        } else if (joueur == 2){
            if (i == 1 || i == 2)
            {
                this.nbAllumsTotal = this.nbAllumsTotal - i;
                this.nbAllumsJ2 = this.nbAllumsJ2 + i;
            }
        }else {
            System.out.println("Erreur : verifier argument dans updateNbAllums");
        }
    }

    /**
     * Renvoie un nombre impair aléatoire dans une borne donnée (max exclu)
     * @param min
     * @param max
     * @return int
     * @throws RemoteException
     */
    public int ObtenirNombreImpairAleatoire(int min, int max) throws RemoteException {
        this.nbAllumsTotal = (int) ((Math.random() * (max - min)) + min); // max exclu
        if (this.nbAllumsTotal % 2 == 0) {
            return this.nbAllumsTotal-1;
        }
        else {
            return this.nbAllumsTotal;
        }
    }

    /**
     * renvoie le joueur qui joue actuellemnt
     * @return int
     * @throws RemoteException
     */
    @Override
    public int getJoueurJoue() throws RemoteException{
        return this.joueurJoue;
    }

    /**
     * donne la main à l'autre joueur
     * @throws RemoteException
     */
    @Override
    public void alternerJoueurJoue() throws RemoteException{
        if (this.joueurJoue == 1){
            this.joueurJoue = 2;
        }
        else {
            this.joueurJoue = 1;
        }
    }

    /**
     * Renvoie un nombre aléatoire dans une borne donnée (max exclu)
     * @param min
     * @param max
     * @return int
     * @throws RemoteException
     */
    public int ObtenirNombreAleatoire(int min, int max) throws RemoteException{
        return (int) ((Math.random() * (max - min)) + min); // max exclu
    }
}
