
package serverPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import operationPackage.Operation;

public class Server2 {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        // 1. Création du serveur sur le port 1235
        ServerSocket serverSocket = new ServerSocket(1235);

        System.out.println("Serveur2 en attente d'un client...");

        // 2. Attendre la connexion du client
        Socket socket = serverSocket.accept();

        System.out.println("Client2 connecté !");

        // 3. Préparer le flux pour recevoir un objet Java
        ObjectInputStream ois =
                new ObjectInputStream(socket.getInputStream());

        // 4. Préparer le flux pour envoyer le résultat au client
        PrintWriter pw =
                new PrintWriter(socket.getOutputStream(), true);

        // 5. Recevoir l'objet envoyé par le client
        // readObject() retourne un Object : on le convertit en Operation
        Operation op = (Operation) ois.readObject();

        // 6. Récupérer les données de l'objet
        double a = op.getA();
        String operateur = op.getOperateur();
        double b = op.getB();

        System.out.println("Premier nombre : " + a);
        System.out.println("Operateur : " + operateur);
        System.out.println("Deuxieme nombre : " + b);

        // 7. Effectuer le calcul demandé
        double resultat;

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
                // Vérifier que le diviseur n'est pas égal à zéro
                if (b == 0) {
                    pw.println("Erreur : division par zero");
                    ois.close();
                    pw.close();
                    socket.close();
                    serverSocket.close();
                    return;
                }

                resultat = a / b;
                break;

            default:
                // L'opérateur n'est pas reconnu
                pw.println("Erreur : operateur invalide");
                ois.close();
                pw.close();
                socket.close();
                serverSocket.close();
                return;
        }

        // 8. Envoyer le résultat au client sous forme de texte
        pw.println(resultat);

        System.out.println("Resultat envoyé au client : " + resultat);

        // 9. Fermer les flux et les sockets
        ois.close();
        pw.close();
        socket.close();
        serverSocket.close();
    }
}
