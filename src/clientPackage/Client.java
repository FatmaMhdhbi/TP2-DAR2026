
package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        // Connexion au serveur
        System.out.println("Je suis un client pas encore connecté...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis un client connecté");

        // Envoi des données
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        // Reception du resultat
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        Scanner scanner = new Scanner(System.in);

        // Saisie des données
        System.out.print("Donner le premier nombre : ");
        double a = scanner.nextDouble();

        System.out.print("Donner l'operateur (+, -, *, /) : ");
        String operateur = scanner.next();

        System.out.print("Donner le deuxieme nombre : ");
        double b = scanner.nextDouble();

        // Envoyer les trois parties au serveur
        pw.println(a);
        pw.println(operateur);
        pw.println(b);

        // Recevoir le resultat
        String resultat = br.readLine();

        System.out.println("Resultat reçu du serveur : " + resultat);

        // Fermer les connexions
        br.close();
        pw.close();
        socket.close();
        scanner.close();
    }
}
