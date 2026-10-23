
package operationPackage;

// Importation de l'interface Serializable
import java.io.Serializable;

// Cette classe représente une opération mathématique.
// Serializable permet de transmettre un objet à travers un flux.
public class Operation implements Serializable {

    // Identifiant de version pour la sérialisation
    private static final long serialVersionUID = 1L;

    // Les données de l'opération
    private double a;          // Premier opérande
    private String operateur;  // Opérateur : +, -, * ou /
    private double b;          // Deuxième opérande

    // Constructeur : initialise les trois données de l'objet
    public Operation(double a, String operateur, double b) {
        this.a = a;
        this.operateur = operateur;
        this.b = b;
    }

    // Retourne le premier opérande
    public double getA() {
        return a;
    }

    // Retourne l'opérateur
    public String getOperateur() {
        return operateur;
    }

    // Retourne le deuxième opérande
    public double getB() {
        return b;
    }
}
