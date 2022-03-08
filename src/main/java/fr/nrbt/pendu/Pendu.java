package main.java.fr.nrbt.pendu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Pendu extends UnicastRemoteObject implements IPendu {
    final int ROUND_MAX = 5;
    private int round;
    private ArrayList<String> listeMotAleatoire;
    private EPenduState state;
    private String motAleatoire;
    private char[] motUtilisateur;
    private char lettre;

    public Pendu() throws RemoteException {
        super();
        /*
        this.motAleatoire = genererMotAleatoire();
        this.motUtilisateur = new char[genererMotAleatoire().length()];

        for (int i=0; i<this.motUtilisateur.length; i++) {
            this.motUtilisateur[i] = '_';
        }*/
    }

    @Override
    public void initialiser() throws RemoteException {
        this.round = 0;
        this.state = EPenduState.EN_COURS;
        this.motAleatoire = genererMotAleatoire();
        this.motUtilisateur = new char[genererMotAleatoire().length()];

        for (int i=0; i<this.motUtilisateur.length; i++) {
            this.motUtilisateur[i] = '_';
        }
    }

    @Override
    public String getMotUtilisateur() throws RemoteException {
        return new String(motUtilisateur);
    }

    @Override
    public void sendLettre(char c) throws RemoteException {
        this.lettre = c;


        verifierEntreeUtilisateur(c);
        verifierPartieGagnee();
        this.round += 1;
    }

    @Override
    public EPenduState getState() throws RemoteException {
        return this.state;
    }

    @Override
    public int getRound() throws RemoteException {
        return this.round;
    }

    private String genererMotAleatoire() {
        // return "aze";

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("mots_pendu.txt")));

            String line = bufferedReader.readLine();

            while (line != null) {
                listeMotAleatoire.add(line);
            }

            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Lecture du fichier mots_pendu.txt impossible.");
        }


        int nombreAleatoire = Math.round(Math.round(listeMotAleatoire.size() - 1));

        return listeMotAleatoire.get(nombreAleatoire);
    }

    private boolean verifierEntreeUtilisateur(char lettre) {
        boolean res = false;

        for (int i=0; i<motAleatoire.length(); i++) {
            if (this.motAleatoire.charAt(i) == lettre) {
                res = true;

                this.motUtilisateur[i] = lettre;
            }
        }

        return res;
    }

    private boolean verifierPartieGagnee() {
        boolean res = false;

        if (new String(motUtilisateur).equals(motAleatoire)) {
            this.state = EPenduState.GAGNE;
            res = false;
        }

        if (this.round == this.ROUND_MAX) {
            this.state = EPenduState.PERDU;
            res = false;
        }

        return res;
    }
}
