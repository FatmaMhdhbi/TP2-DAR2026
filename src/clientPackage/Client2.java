
package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import operationPackage.Operation;

public class Client2 {

    public static void main(String[] args) throws IOException {

        // 1. Se connecter au serveur sur le port 1235
        System.out.println("Client2 : connexion au serveur...");

        Socket socket = new Socket("localhost", 1235);

        System.out.println("Client2 connecté !");

        // 2. Créer le flux pour envoyer un objet Java
        ObjectOutputStream oos =
                new ObjectOutputStream(socket.getOutputStream());

        // 3. Créer le flux pour recevoir le résultat du serveur
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // 4. Préparer la lecture des données saisies au clavier
        Scanner scanner = new Scanner(System.in);

        // Saisir le premier opérande
        System.out.print("Donner le premier nombre : ");
        double a = scanner.nextDouble();

        // Saisir l'opérateur
        System.out.print("Donner l'operateur (+, -, *, /) : ");
        String operateur = scanner.next();

        // Saisir le deuxième opérande
        System.out.print("Donner le deuxieme nombre : ");
        double b = scanner.nextDouble();

        // 5. Créer un objet contenant toute l'opération
        Operation op = new Operation(a, operateur, b);

        // 6. Envoyer l'objet au serveur
        oos.writeObject(op);

        // Forcer l'envoi des données restantes du flux
        oos.flush();

        // 7. Recevoir le résultat envoyé par le serveur
        String resultat = br.readLine();

        // 8. Afficher le résultat
        System.out.println("Resultat reçu du serveur : " + resultat);

        // 9. Fermer les flux, la socket et le Scanner
        br.close();
        oos.close();
        socket.close();
        scanner.close();
    }
}
