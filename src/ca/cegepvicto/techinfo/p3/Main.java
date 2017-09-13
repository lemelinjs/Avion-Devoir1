package ca.cegepvicto.techinfo.p3;
import ca.cegepvicto.techinfo.p3.structures.DoubleEndedLinkedListAvion;
import ca.cegepvicto.techinfo.p3.structures.DoubleEndedLinkedListPilote;
import ca.cegepvicto.techinfo.p3.structures.DoubleEndedLinkedListTrajectoire;
import ca.cegepvicto.techinfo.p3.structures.DoubleEndedLinkedListTrajectoireCedulee;

import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static DoubleEndedLinkedListTrajectoire trajectoires;
    public static DoubleEndedLinkedListPilote pilotes;
    public static DoubleEndedLinkedListAvion avions;
    public static DoubleEndedLinkedListTrajectoireCedulee cedules;
    private static boolean quitter = false;

    private static Pilote piloteEnModification;
    private static Avion avionEnModification;
    private static String menuEnAction;

    public static void main(String[] args) {
        //Ajouter des pilotes
        pilotes = new DoubleEndedLinkedListPilote();
        pilotes.insererPremierePosition(1,"Robert Lemelin");
        pilotes.insererDernierePosition(5,"Jean-Sébastien Lemelin");
        pilotes.insererPremierePosition(7,"Christopher Boisvert");
        pilotes.insererApresCle(2,"Stéphanie St-Onge",5);

        avions = new DoubleEndedLinkedListAvion();

        avions.insererPremierePosition(18,"AIB58");
        avions.insererApresCle(20, "ACB12",18);
        avions.insererDernierePosition(17,"AIB56");
        avions.insererPremierePosition(19,"AIB59");

        //Toutes trajectoires
        trajectoires = new DoubleEndedLinkedListTrajectoire();
        trajectoires.insererPremierePosition(1,"Montréal", "Québec");
        trajectoires.insererPremierePosition(2,"Japon", "Californie");
        trajectoires.insererPremierePosition(3,"Californie", "Chine");
        trajectoires.insererPremierePosition(4,"Thaïlande", "Haïti");


        cedules = new DoubleEndedLinkedListTrajectoireCedulee();

        cedules.insererPremierePosition(1,
                trajectoires.trouverParId(1),
                pilotes.trouverParId(5),
                avions.trouverParId(18));
        cedules.insererPremierePosition(3,
                trajectoires.trouverParId(2),
                pilotes.trouverParId(2),
                avions.trouverParId(17));
        cedules.insererPremierePosition(6,
                trajectoires.trouverParId(3),
                pilotes.trouverParId(1),
                avions.trouverParId(19));
        cedules.insererPremierePosition(8,
                trajectoires.trouverParId(4),
                pilotes.trouverParId(2),
                avions.trouverParId(20));


        //afficher entete
        afficherTrajectoires();
        menuAccueil();

        //Demander le choix du menu accueil
        DemanderChoix("ac");
    }

    public static void DemanderChoix(String menu){
        while(!quitter){
            System.out.println("Entrez votre choix?");
            Scanner sc = new Scanner(System.in);
            //Atteindre les pages avec choix numérique différent pour chaque menu
            if(estEntier(sc)){
                int choixInt = sc.nextInt();
                atteindrePage(choixInt, menu);
            }else{
                String choix = sc.nextLine();
                atteindrePage(choix);
            }
        }
    }

    private static void afficherTrajectoires(){
        System.out.println("LISTE DES Trajectoires avec Pilote et AVION");
        System.out.println("-------------------------------------------");
        cedules.afficher();
    }
    private static void afficherPilotes(){
        System.out.println("LISTE DES PILOTES ACTIFS");
        System.out.println("------------------------");
        pilotes.afficher();
    }

    private static void afficherAvions(){
        System.out.println("LISTE DES AVIONS ACTIVES");
        System.out.println("------------------------");
        avions.afficher();
    }
    private static void menuAccueil(){
        System.out.println("**************************************************************");
        System.out.println("************************MENU ACCUEIL**************************");
        System.out.println("**************************************************************");
        System.out.println("AC - ACcueil");
        System.out.println("GP - Gestion des Pilotes");
        System.out.println("GA - Gestion des Avions");
        System.out.println("GT - Gestion des Trajets");
        System.out.println("Q  - Quitter");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }
    private static void menuAjoutAvion(){
        //Demander le nouveau nom
        Scanner sc = new Scanner(System.in);
        System.out.println("Le nom de l'avion? : ");
        String nom = sc.nextLine();
        //Trouver le prochain id disponible
        boolean idExiste = true;
        int idCherche = 1;
        int idTrouve = 0;
        while(idExiste){
            if(avions.trouverParId(idCherche) != null){
                idCherche++;
            }else{
                idExiste = false;
                idTrouve=idCherche;
                avions.insererPremierePosition(idTrouve, nom);
            }
        }
    }

    private static void menuAjoutPilote(){
        //Demander le nouveau nom
        Scanner sc = new Scanner(System.in);
        System.out.println("Le nom du pilote? : ");
        String nom = sc.nextLine();
        //Trouver le prochain id disponible
        boolean idExiste = true;
        int idCherche = 1;
        int idTrouve = 0;
        while(idExiste){
            if(pilotes.trouverParId(idCherche) != null){
                idCherche++;
            }else{
                idExiste = false;
                idTrouve=idCherche;
                pilotes.insererPremierePosition(idTrouve, nom);
            }
        }
    }

    private static void menuGP(){
        System.out.println("**************************************************************");
        System.out.println("********************GESTION DES PILOTES***********************");
        System.out.println("**************************************************************");
        System.out.println("AP - Ajouter Pilote");
        System.out.println("SP - Supprimer Pilote");
        System.out.println("ID - POUR MODIFIER");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }

    private static void menuGA(){
        System.out.println("**************************************************************");
        System.out.println("********************GESTION DES AVIONS************************");
        System.out.println("**************************************************************");
        System.out.println("AA - Ajouter Avion");
        System.out.println("SA - Supprimer Avion");
        System.out.println("ID - POUR MODIFIER");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }
    private static void menuGPIndividuel(){
        System.out.println("**************************************************************");
        System.out.println("********************GESTION DU PILOTE*************************");
        System.out.println("**************************************************************");
        System.out.println("INFORMATIONS SUR LE PILOTE");
        System.out.println("---------------__---------");
        System.out.println();
        piloteEnModification.afficher();
        System.out.println("0 - Pour changer son nom");
        System.out.println("SP - Supprimer un Pilote");
        System.out.println("LTP - Lister Trajets Cédulés pour ce pilote");

    }

    private static void menuGAIndividuel(){
        System.out.println("**************************************************************");
        System.out.println("********************GESTION DE L'AVION************************");
        System.out.println("**************************************************************");
        System.out.println("INFORMATIONS SUR L'AVION");
        System.out.println("---------------__---------");
        System.out.println();
        avionEnModification.afficher();
        System.out.println("0 - Pour changer son code");
        System.out.println("SA - Supprimer l'avion");
        System.out.println("LTA - Lister Trajets Cédulés pour cet avion");

    }
    
    private static void menuSupprimerPilote(){
        effacerEcran();
        int idASupprimer=0;
        if(piloteEnModification == null){
            afficherPilotes();
            menuAccueil();
            System.out.println("POUR SUPPRIMER UN PILOTE CHOISISSEZ SON ID");
            System.out.println("C - CANCELLER");
            System.out.println("------------------------------------------");
            System.out.println();
            Scanner sc = new Scanner(System.in);

            //Si l'usager tappe c pour canceller
            if(sc.hasNext("c")){
                atteindrePage("gp");
            }
            else if(obtenirEntier(sc,"Quel est le id à supprimer (entier svp)? : " )){

            }

            idASupprimer = sc.nextInt();
        }else{
            idASupprimer = piloteEnModification.getId();
        }

        //Vérifier s'il est dans une cédule
        TrajectoireCedulee volPilote = cedules.effacementPossible(pilotes.trouverParId(idASupprimer));

        if(volPilote  == null){
         System.out.println("oui on peut effacer");
            if(pilotes.effacerPiloteParId(idASupprimer)!= null){
                System.out.println("Effacé avec succès!");
            }else{
                System.out.println("Entrée innexistante");
            }
        }else{

            System.out.println("**************************************************************");
            System.out.println("************ATTENTION ON NE PEUT EFFACER**********************");
            System.out.println("**************************************************************");
            System.out.println("ON NE PEUT EFFACER PILOTE, IL DOIT PRENDRE LE VOL " + volPilote.getTrajetId());
            System.out.println("INFORMATIONS SUR LE VOL");
            System.out.println("-----------------------");
            System.out.println(volPilote.toString());
        }

        faireUnePause(5000);
        effacerEcran();
    }

    private static void menuSupprimerAvion(){

        int idASupprimer=0;
        if(avionEnModification == null){
            afficherAvions();
            menuAccueil();
            System.out.println("POUR SUPPRIMER UN AVION CHOISISSEZ SON ID");
            System.out.println("C - CANCELLER");
            System.out.println("------------------------------------------");
            System.out.println();
            Scanner sc = new Scanner(System.in);

            //Si l'usager tappe c pour canceller
            if(sc.hasNext("c")){
                atteindrePage("ga");
            }
            else if(obtenirEntier(sc,"Quel est le id à supprimer (entier svp)? : " )){

            }

            idASupprimer = sc.nextInt();
        }else{
            idASupprimer = avionEnModification.getId();
        }

        //Vérifier s'il est dans une cédule
        TrajectoireCedulee volAvion = cedules.effacementPossible(avions.trouverParId(idASupprimer));

        if(volAvion  == null){
            System.out.println("oui on peut effacer");
            if(avions.effacerAvionParId(idASupprimer)!= null){
                System.out.println("Effacé avec succès!");
            }else{
                System.out.println("Entrée innexistante");
            }
        }else{

            System.out.println("**************************************************************");
            System.out.println("************ATTENTION ON NE PEUT EFFACER**********************");
            System.out.println("**************************************************************");
            System.out.println("ON NE PEUT EFFACER L'AVION, IMPLIQUÉ DANS VOL " + volAvion.getTrajetId());
            System.out.println("INFORMATIONS SUR LE VOL");
            System.out.println("-----------------------");
            System.out.println(volAvion.toString());
        }

        faireUnePause(5000);
        effacerEcran();
    }

    private static void menuListeTrajectoiresPilote(){

        LinkedList<TrajectoireCedulee> listeTrajectoiresPilote = new LinkedList<TrajectoireCedulee>();
        listeTrajectoiresPilote = cedules.trajectoirePourCePilote(piloteEnModification);
        if (!listeTrajectoiresPilote.isEmpty() ){
            System.out.println("**************************************************************");
            System.out.println("*****************Trajectoires du PILOTE***********************");
            System.out.println("**************************************************************");
            for(int i=0;i<listeTrajectoiresPilote.size();i++) {

                listeTrajectoiresPilote.get(i).afficher();
            }
        }else{
            System.out.println("**************************************************************");
            System.out.println("*****************Aucune trajectoire pour ce PILOTE************");
            System.out.println("**************************************************************");
        }

    }

    private static void menuListeTrajectoiresAvion(){
        System.out.println("**************************************************************");
        System.out.println("*****************Trajectoires de l'avion***********************");
        System.out.println("**************************************************************");
        LinkedList<TrajectoireCedulee> listeTrajectoiresAvion = new LinkedList<TrajectoireCedulee>();
        listeTrajectoiresAvion = cedules.trajectoirePourCetAvion(avionEnModification);
        for(int i=0;i<listeTrajectoiresAvion.size();i++){
            listeTrajectoiresAvion.get(i).afficher();
        }
    }

    private static void atteindrePage(String choix){
        effacerEcran();
        switch(choix.toLowerCase()){
            case "gp" :
                menuEnAction = "gp";
                afficherPilotes();
                menuAccueil();
                menuGP();
                DemanderChoix("gp");
                break;
            case "ga" :
                menuEnAction = "ga";
                afficherAvions();
                menuAccueil();
                menuGA();
                DemanderChoix("ga");
                break;
            case "aa": //Ajouter avion
                menuAjoutAvion();
                avionEnModification = null;
                afficherAvions();
                menuAccueil();
                menuGA();
                DemanderChoix("ga");
                break;
            case "ac":
                menuEnAction = "ac";
                afficherTrajectoires();
                menuAccueil();
                DemanderChoix("ac");
                break;
            case "ap":
                menuAjoutPilote();
                piloteEnModification = null;
                afficherPilotes();
                menuAccueil();
                menuGP();
                DemanderChoix("gp");
                break;
            case "sp":
                menuSupprimerPilote();
                piloteEnModification = null;
                afficherPilotes();
                menuAccueil();
                menuGP();
                DemanderChoix("gp");
                break;
            case "sa":
                menuSupprimerAvion();
                avionEnModification = null;
                afficherAvions();
                menuAccueil();
                menuGA();
                DemanderChoix("ga");
                break;
            case "ltp":
                if(piloteEnModification != null){
                    menuListeTrajectoiresPilote();
                    piloteEnModification = null;
                }else{
                    System.out.println("**************************************************************");
                    System.out.println("**************IL FAUT CHOISIR UN PILOTE D'ABORD***************");
                    System.out.println("**************************************************************");
                    afficherPilotes();
                    faireUnePause(5000);
                }
                menuAccueil();
                menuGP();
                DemanderChoix("gp");
                break;
            case "lta":
                if(avionEnModification != null){
                    menuListeTrajectoiresAvion();
                    avionEnModification = null;
                }else{
                    System.out.println("**************************************************************");
                    System.out.println("**************IL FAUT CHOISIR UN PILOTE D'ABORD***************");
                    System.out.println("**************************************************************");
                    afficherPilotes();
                    faireUnePause(5000);
                }
                menuAccueil();
                menuGA();
                DemanderChoix("ga");
                break;
            case "q":
                System.out.println("**************************************************************");
                System.out.println("*****************MERCI DE VOTRE VISITE************************");
                System.out.println("**************************************************************");
                faireUnePause(2000);
                quitter = true;
                break;
            default:
                effacerEcran();
                menuAccueil();
                erreurDeSaisie();
                DemanderChoix("ac");
                break;
        }

    }

    private static void atteindrePage(int choix, String menu){
        //Choix d'un pilote
        if ( menu =="gp"){
            //l'afficher à l'écran s'il existe et offrir des
            //options de modification
            piloteEnModification = pilotes.trouverParId(choix);

            if(piloteEnModification  != null){
                atteindrePage(choix,"GPIndividuel");

            }else{
                effacerEcran();
                menuGP();
                erreurDeSaisie();
                faireUnePause(1000);
                afficherPilotes();
                DemanderChoix("gp");
            }

        } else if(menu == "GPIndividuel"){

            if (choix == 0){
                //Demander le nouveau nom
                Scanner sc = new Scanner(System.in);
                System.out.println("Le nouveau nom? : ");
                String nom = sc.nextLine();
                piloteEnModification.setNom(nom);
                piloteEnModification=null;
                effacerEcran();
                afficherPilotes();
                menuAccueil();
                menuGP();
                DemanderChoix("gp");
            }else{
                menuAccueil();
                menuGPIndividuel();
                DemanderChoix("GPIndividuel");
            }
        } else if(menu =="ga"){
            //l'afficher à l'écran s'il existe et offrir des
            //options de modification
            avionEnModification = avions.trouverParId(choix);

            if(avionEnModification  != null){
                atteindrePage(choix,"GAIndividuel");

            }else{
                effacerEcran();
                menuGA();
                erreurDeSaisie();
                faireUnePause(1000);
                afficherAvions();
                DemanderChoix("ga");
            }
        }else if(menu == "GAIndividuel"){

            if (choix == 0){
                //Demander le nouveau nom
                Scanner sc = new Scanner(System.in);
                System.out.println("Le nouveau nom? : ");
                String nom = sc.nextLine();
                avionEnModification.setCode(nom);
                avionEnModification = null;
                effacerEcran();
                afficherAvions();
                menuAccueil();
                menuGA();
                DemanderChoix("ga");
            }else{
                menuAccueil();
                menuGAIndividuel();
                DemanderChoix("GAIndividuel");
            }
        }
    }
    //public static LinkedList<Pilote> getpilotes() {
        //return pilotes;
    //}
    private static void erreurDeSaisie(){
        System.out.println("**************************************************************");
        System.out.println("********************CHOIX INVALIDE****************************");
        System.out.println("**************************************************************");
    }
    private static void effacerEcran(){
        for(int i=0;i<50;i++){
            System.out.println();
        }
    }
    private static boolean tryParse(String valeur){
        try{
            Integer.parseInt(valeur);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    
    private static boolean obtenirEntier(Scanner sc, String question){
        boolean resultat = false;
        while(!sc.hasNextInt()){
            System.out.println(question);
            sc.nextLine();
        }
        return true;
    }

    private static boolean estEntier(Scanner sc){
        if (sc.hasNextInt()){
            return true;
        }else{
            return false;
        }
    }

    private static void faireUnePause(int temps){
        try{
            Thread.sleep(temps);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

}
