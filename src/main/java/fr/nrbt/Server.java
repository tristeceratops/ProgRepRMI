package main.java.fr.nrbt;

import main.java.fr.nrbt.pendu.Pendu;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main (String[] argv) {
        try {
            int port = 8000;
            LocateRegistry.createRegistry(port);

            Naming.rebind (String.format("rmi://localhost:%d/pendu", port), new Pendu());

            System.out.println ("Hello Server prêt ! .");
        } catch (Exception e) {
            System.out.println ("Hello Server échec " + e);
        }
    }
}
