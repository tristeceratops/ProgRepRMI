package main.java.fr.nrbt;

import main.java.fr.nrbt.pendu.EPenduState;
import main.java.fr.nrbt.pendu.IPendu;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main (String[] argv) {
        try {
            int port = 8000;
            IPendu pendu = (IPendu) Naming.lookup(String.format("rmi://localhost:%d/pendu", port));

            /*
            * client -> envoyer la lettre
            * serveur -> vérifier si la lettre est dans le mots
            * serveur -> compléter le mot utilisateur
            * serveur -> vérifier si le mot utilisateur correspond au mot aléatoire
            *           -> oui : gagner
            *           -> non : continuer
            *
            * */

            pendu.initialiser();
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    String.format("Bienvenue dans mon super jeu du pendu !\n" +
                            "Le mot à deviner est %s, entrez une lettre pour commencer à jouer !", pendu.getMotUtilisateur())
            );



            do {
                System.out.println(String.format("Round %d/5", pendu.getRound()));
                pendu.sendLettre(scanner.nextLine().charAt(0));
                System.out.println(pendu.getMotUtilisateur());
            } while (pendu.getState() == EPenduState.EN_COURS);

            if (pendu.getState() == EPenduState.GAGNE) {
                System.out.println("Bravo vous avez gagné !");

            } else {
                System.out.println("Oh zut vous avez perdu ):");

            }




        } catch (Exception e) {
            System.out.println ("HelloClient exception: " + e);
        }
    }
}