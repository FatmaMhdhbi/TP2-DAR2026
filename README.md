 TP2 — Les sockets en mode connecté TCP : Échange de chaînes de caractères et d’objets

Module : Développement d’applications réparties (DAR)  
Niveau : LSI3  
Année universitaire : 2026–2027  
Étudiante : Fatma Mhadhbi


 Objectif du TP

L’objectif de ce TP est de développer des applications client/serveur en Java en utilisant les sockets TCP. Le travail est divisé en deux parties :

- Partie 1 : Échange de chaînes de caractères entre un client et un serveur pour effectuer des opérations arithmétiques.
- Partie 2 : Échange d’un objet Java sérialisable entre un client et un serveur pour effectuer des opérations arithmétiques.



 Partie 1 — Échange de chaînes de caractères

 1. Objectif

Cette partie consiste à développer une application client/serveur permettant au client d’envoyer les données nécessaires à une opération arithmétique au serveur. Le serveur effectue le calcul et renvoie le résultat au client.

 2. Structure du projet

Les classes utilisées sont :

- clientPackage/Client.java
- serverPackage/Server.java

 3. Fonctionnement

 Côté serveur

- Création d’un ServerSocket sur le port 1234
- Attente de la connexion d’un client avec accept().
- Réception des données envoyées par le client sous forme de chaînes de caractères.
- Conversion des données reçues et exécution de l’opération arithmétique.
- Envoi du résultat au client.

 Côté client

- Connexion au serveur via une socket TCP.
- Saisie du premier nombre, de l’opérateur (`+`, `-`, `*`, `/`) et du deuxième nombre.
- Envoi des données au serveur sous forme de chaînes de caractères.
- Réception et affichage du résultat retourné par le serveur.

 4. Captures d’écran

 Capture 1 — Serveur en attente d’une connexion

<img width="748" height="264" alt="image" src="https://github.com/user-attachments/assets/66d207a3-1251-40d8-b2bb-0d3c78f6a74d" />


 Capture 2 — Client

<img width="590" height="415" alt="image" src="https://github.com/user-attachments/assets/b3e0e86f-6be9-4bc2-87ee-dcc04ecafb1d" />


 Capture 3 — Serveur
 
<img width="676" height="371" alt="image" src="https://github.com/user-attachments/assets/14ab92db-e15e-4b15-ac80-580a3909d3f9" />


 Partie 2 — Échange d’objets Java via les sockets TCP

 1. Objectif

Cette partie consiste à étendre l’application précédente en utilisant la sérialisation Java (Serializable) pour envoyer un objet contenant les données d’une opération arithmétique du client vers le serveur.

Le serveur reçoit cet objet, extrait les données, effectue le calcul et renvoie le résultat au client.

 2. Structure du projet

Les nouvelles classes créées sont :

- clientPackage/Client2.java
- serverPackage/Server2.java
- operationPackage/Operation.java


 3. Fonctionnement

 Classe Operation

- Implémente l’interface Serializable.
- Contient les attributs suivants :
  - a : premier nombre.
  - operateur : opérateur arithmétique.
  - b : deuxième nombre.
- Fournit un constructeur et des méthodes getters pour accéder aux données.

 Côté client — Client2

- Connexion au serveur via une socket TCP sur le port 1235
- Saisie des deux nombres et de l’opérateur.
- Création d’un objet Operation contenant les données saisies.
- Envoi de cet objet au serveur avec ObjectOutputStream.
- Réception et affichage du résultat avec BufferedReader.

 Côté serveur — Server2

- Création d’un ServerSocket sur le port 1235
- Attente de la connexion du client.
- Réception de l’objet Operation avec ObjectInputStream.
- Extraction des deux nombres et de l’opérateur.
- Exécution de l’opération arithmétique.
- Envoi du résultat au client sous forme de chaîne de caractères avec PrintWriter.

 4. Captures d’écran

 Capture 1 — Serveur en attente d’une connexion

<img width="593" height="299" alt="image" src="https://github.com/user-attachments/assets/29a507e3-4b6d-4db3-9126-bea3fe2fb14e" />

 Capture 2 — Client

<img width="584" height="421" alt="image" src="https://github.com/user-attachments/assets/b17ab946-4bc3-43b0-ad28-626c155a873b" />

 Capture 3 — Serveur

<img width="570" height="468" alt="image" src="https://github.com/user-attachments/assets/1c6ea9ae-43b7-4090-b892-ef1b13dd9733" />




 Conclusion

Ce TP nous a permis de comprendre la communication entre un client et un serveur en Java à l’aide des sockets TCP.

Dans la première partie, les données sont échangées sous forme de chaînes de caractères. Dans la deuxième partie, les données de l’opération sont regroupées dans un objet Java sérialisable, envoyé avec `ObjectOutputStream` et reçu avec `ObjectInputStream`. Le serveur retourne ensuite le résultat au client sous forme de texte.

Ces deux approches permettent de comprendre différentes méthodes d’échange de données dans une application répartie.
