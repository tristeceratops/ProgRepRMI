module com.example.allumetegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens com.example.allumetegame to javafx.fxml;
    exports com.example.allumetegame;
    exports com.example.allumetegame.RMI;
}