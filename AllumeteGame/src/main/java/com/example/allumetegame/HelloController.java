package com.example.allumetegame;

import com.example.allumetegame.RMI.AllumetteInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Controlleur du fxml du jeu des allumettes
 */
public class HelloController {

    private AllumetteInterface obj;


    @FXML
    public FlowPane flowPanelJ1;
    @FXML
    public FlowPane flowPanelJ2;
    @FXML
    public Button btn1Joueur1;
    @FXML
    public Button btn2Joueur1;
    @FXML
    public Button btn1Joueur2;
    @FXML
    public Button btn2Joueur2;
    @FXML
    public Label labelAllumJ1;
    @FXML
    public Label labelAllumJ2;
    @FXML
    public Label labelAllumTotal;
    @FXML
    public Label LabelGameOver;

    /**
     * permet d'initialiser la partie
     * @throws RemoteException
     */
    @FXML
    public void initialize() throws RemoteException {
        try {
            int port = 8000;
            this.obj = (AllumetteInterface) Naming.lookup("rmi://localhost:8000/hello");
            this.obj.Initialise();
            HelloApplication.launch();
        } catch (Exception e){
            System.out.println("HelloClient exception: " + e);
        }
        labelAllumTotal.setText(String.valueOf(obj.getNbAllumsTotal()));
        if(obj.getJoueurJoue()== 1){
         btn1Joueur2.setDisable(true);
         btn2Joueur2.setDisable(true);
        }else {
            btn1Joueur1.setDisable(true);
            btn2Joueur1.setDisable(true);
        }
    }

    /**
     * Gère le bouton qui ajoute 1 allumette au joueur 1
     * @param event
     * @throws RemoteException
     */
    @FXML
    public void onClickBtn1J1(ActionEvent event) throws RemoteException {
        obj.updateNbAllums(1,1);
        labelAllumTotal.setText(String.valueOf(obj.getNbAllumsTotal()));
        labelAllumJ1.setText(String.valueOf(obj.getNbAllumsJ1()));

        if (obj.getNbAllumsTotal() != 1){
            btn1Joueur2.setDisable(false);
            btn1Joueur1.setDisable(true);
            btn2Joueur2.setDisable(false);
            btn2Joueur1.setDisable(true);
        }
        else {
            btn1Joueur2.setDisable(false);
            btn1Joueur1.setDisable(true);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(true);
        }
        jeuFini();
    }
    /**
     * Gère le bouton qui ajoute 2 allumettes au joueur 1
     * @param event
     * @throws RemoteException
     */
    @FXML
    public void onClickBtn2J1(ActionEvent event) throws RemoteException {
        obj.updateNbAllums(1,2);
        labelAllumTotal.setText(String.valueOf(obj.getNbAllumsTotal()));
        labelAllumJ1.setText(String.valueOf(obj.getNbAllumsJ1()));
        if (obj.getNbAllumsTotal() != 1){
            btn1Joueur2.setDisable(false);
            btn1Joueur1.setDisable(true);
            btn2Joueur2.setDisable(false);
            btn2Joueur1.setDisable(true);
        }
        else {
            btn1Joueur2.setDisable(false);
            btn1Joueur1.setDisable(true);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(true);
        }
        jeuFini();
    }
    /**
     * Gère le bouton qui ajoute 1 allumette au joueur 2
     * @param event
     * @throws RemoteException
     */
    @FXML
    public void onClickBtn1J2(ActionEvent event) throws RemoteException {
        obj.updateNbAllums(2,1);
        labelAllumTotal.setText(String.valueOf(obj.getNbAllumsTotal()));
        labelAllumJ2.setText(String.valueOf(obj.getNbAllumsJ2()));
        if (obj.getNbAllumsTotal() != 1){
            btn1Joueur2.setDisable(true);
            btn1Joueur1.setDisable(false);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(false);
        }
        else{
            btn1Joueur2.setDisable(true);
            btn1Joueur1.setDisable(false);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(true);
        }
        jeuFini();
    }
    /**
     * Gère le bouton qui ajoute 2 allumettes au joueur 2
     * @param event
     * @throws RemoteException
     */
    @FXML
    public void onClickBtn2J2(ActionEvent event) throws RemoteException {
        obj.updateNbAllums(2,2);
        labelAllumTotal.setText(String.valueOf(obj.getNbAllumsTotal()));
        labelAllumJ2.setText(String.valueOf(obj.getNbAllumsJ2()));
        if (obj.getNbAllumsTotal() != 1){
            btn1Joueur2.setDisable(true);
            btn1Joueur1.setDisable(false);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(false);
        }
        else {
            btn1Joueur2.setDisable(true);
            btn1Joueur1.setDisable(false);
            btn2Joueur2.setDisable(true);
            btn2Joueur1.setDisable(true);
        }
       jeuFini();




    }

    /**
     * met fin au jeu
     * @throws RemoteException
     */
    public void jeuFini() throws RemoteException {
        if (obj.siJeuFini()){
            System.out.println("La partie est terminé");
            btn1Joueur2.setDisable(true);
            btn1Joueur1.setDisable(true);
            btn2Joueur1.setDisable(true);
            btn2Joueur2.setDisable(true);
            LabelGameOver.setText("Le gagnant est le joeur : "+ String.valueOf(obj.getJoueurJoue()));
            if(obj.getJoueurJoue() == 1) {
            }
        }
        else {
            obj.alternerJoueurJoue();
        }
    }

}