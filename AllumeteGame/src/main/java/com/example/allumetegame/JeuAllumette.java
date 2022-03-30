package com.example.allumetegame;

import java.util.Scanner;

public class JeuAllumette {

    public static void play(){
        int nbAllumettes = 17;
        boolean gameOver = false;
        int currentPlayer = 0;
        int nbAllumettesRetire = -1;
        while (!gameOver){
            if (currentPlayer == 0) {
                System.out.println("Au tour du joueur de jouer. Il y a en ce moment " + nbAllumettes + " allumettes. Combien en prend-tu? ");
                do {
                    System.out.println("1 ou 2 ?");
                    Scanner sc = new Scanner(System.in);
                    nbAllumettesRetire = sc.nextInt();

                }while (nbAllumettesRetire != 1 && nbAllumettesRetire !=2);
                nbAllumettes = nbAllumettes - nbAllumettesRetire;
                currentPlayer = 1;
            }
            else {
                System.out.println("Au tour du serveur de joueur. Il y a en ce moment " + nbAllumettes + " allumettes. Combien en prend-t'il? ");
                System.out.println("Il n'en prend qu'une");
                nbAllumettes = nbAllumettes -2;
                currentPlayer = 0;
            }
            if (nbAllumettes <= 0){
                gameOver = true;
            }
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min); // max exclu
    }

    public static int serveurJoue(int nbAllumettes){
        System.out.println("Au tour de votre adversaire. Il y a en ce moment " + nbAllumettes + " allumettes. Combien en prend-t'il? ");
        int rdm;
        do {
            rdm = getRandomNumber(1,3);
        }while (rdm!=1 && rdm!=2);

        if (rdm == 1){
            System.out.println("Il n'en prend qu'une");
            nbAllumettes = nbAllumettes -1;
        }
        else {
            System.out.println("Il en prend deux");
            nbAllumettes = nbAllumettes -2;
        }

        return nbAllumettes;
    }

    public static int joueurJoue(int nbAllumettes){
        System.out.println("A votre tour de jouer. Il y a en ce moment " + nbAllumettes + " allumettes. Combien en prend-tu ? ");
        int rdm;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1 ou 2 ?");
            rdm = sc.nextInt();
        }while (rdm!=1 && rdm!=2);

        if (rdm == 1){
            System.out.println("Tu n'en prend qu'une");
            nbAllumettes = nbAllumettes -1;
        }
        else {
            System.out.println("Tu en prend deux");
            nbAllumettes = nbAllumettes -2;
        }

        return nbAllumettes;
    }

    public static void main(String[] argv){
        while (1==1){
            System.out.println(getRandomNumber(0,2));
        }
    }
}
