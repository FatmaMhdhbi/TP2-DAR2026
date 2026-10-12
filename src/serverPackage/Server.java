
package serverPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // Creation du serveur
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente de la connexion d'un client");

        // Attendre un client
        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecté");

        // Reception des données
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // Envoi des données
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        // Recevoir les trois parties de l'operation
        double a = Double.parseDouble(br.readLine());
        String operateur = br.readLine();
        double b = Double.parseDouble(br.readLine());

        System.out.println("Premier nombre : " + a);
        System.out.println("Operateur : " + operateur);
        System.out.println("Deuxieme nombre : " + b);

        // Calcul du resultat
        double resultat = 0;

        switch (operateur) {
            case "+":
                resultat = a + b;
                break;

            case "-":
                resultat = a - b;
                break;

            case "*":
                resultat = a * b;
                break;

            case "/":
                if (b != 0) {
                    resultat = a / b;
                } else {
                    System.out.println("Erreur : division par zero");
                    pw.println("Erreur : division par zero");
                    br.close();
                    pw.close();
                    socket.close();
                    serverSocket.close();
                    return;
                }
                break;

            default:
                System.out.println("Operateur invalide");
                pw.println("Erreur : operateur invalide");
                br.close();
                pw.close();
                socket.close();
                serverSocket.close();
                return;
        }

        // Envoyer le resultat au client
        pw.println(resultat);
        System.out.println("Resultat envoyé au client : " + resultat);

        // Fermer les connexions
        br.close();
        pw.close();
        socket.close();
        serverSocket.close();
    }
}
