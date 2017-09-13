package ca.cegepvicto.techinfo.p3.structures;

import ca.cegepvicto.techinfo.p3.Avion;
import ca.cegepvicto.techinfo.p3.Pilote;
import ca.cegepvicto.techinfo.p3.Trajectoire;
import ca.cegepvicto.techinfo.p3.TrajectoireCedulee;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class DoubleEndedLinkedListTrajectoireCedulee {
    private TrajectoireCedulee premier;
    private TrajectoireCedulee dernier;

    
    /*
    * CONSTRUCTEUR
    *
    * */
    public DoubleEndedLinkedListTrajectoireCedulee(){
        premier = null;
    }

    //Regarder si le précédent est null, si oui, il n'y a pas d'enregistrements dans la liste
    public boolean estVide(){
        return (premier == null);
    }
    public void insererPremierePosition(int id, Trajectoire trajectoire, Pilote pilote, Avion avion){
        TrajectoireCedulee nouvelleTrajectoireCedulee = new TrajectoireCedulee(id, trajectoire, pilote, avion);

        if(estVide()){
            dernier = nouvelleTrajectoireCedulee;
        }
        nouvelleTrajectoireCedulee.setSuivant(premier);
        premier = nouvelleTrajectoireCedulee;
    }

    public void insererDernierePosition(int id,Trajectoire trajectoire, Pilote pilote, Avion avion){
        TrajectoireCedulee nouvelleTrajectoireCedulee = new TrajectoireCedulee(id, trajectoire, pilote, avion);

        if(estVide()){
            premier = nouvelleTrajectoireCedulee;
        }else {

            dernier.setSuivant(nouvelleTrajectoireCedulee);
            nouvelleTrajectoireCedulee.setPrecedent(dernier);
        }
        dernier = nouvelleTrajectoireCedulee;
    }

    public boolean insererApresCle(int id,Trajectoire trajectoire,Pilote pilote, Avion avion, int key){
        TrajectoireCedulee nouvelleTrajectoireCedulee = new TrajectoireCedulee(id, trajectoire, pilote, avion);
        TrajectoireCedulee trajectoireCeduleeCourrante = premier;

        //Tant que la clé n'est pas celle que nous cherchons, on continue de chercher
        while(trajectoireCeduleeCourrante.getTrajetId() != key){
            trajectoireCeduleeCourrante = trajectoireCeduleeCourrante.getSuivant();
            //Si nous n'avons pas trouvé clé et que nous sommes à la fin
            if(trajectoireCeduleeCourrante == null){
                return false;
            }
        }

        //Si nous avons passé, nous avons trouvé la clé
        //S'il est la fin de la série
        if(trajectoireCeduleeCourrante == dernier){
            nouvelleTrajectoireCedulee.setSuivant(null);
            dernier = nouvelleTrajectoireCedulee;
        }else{ //S'il est en milieu, le nouvel TrajectoireCedulee prend les infos de la clé
            //S'occupper de celui d'après
            nouvelleTrajectoireCedulee.setSuivant(trajectoireCeduleeCourrante.getSuivant());
            trajectoireCeduleeCourrante.getSuivant().setPrecedent(nouvelleTrajectoireCedulee);
        }
        //S'occuper de celui d'avant
        nouvelleTrajectoireCedulee.setPrecedent(trajectoireCeduleeCourrante);
        trajectoireCeduleeCourrante.setSuivant(nouvelleTrajectoireCedulee);
        return true;
    }

    //Effacer un TrajectoireCedulee
    public TrajectoireCedulee EffacerPremierTrajectoire(){

        TrajectoireCedulee trajectoireCeduleeReference = premier;
        
        /*Vérifier s'il y a au-moins un enregistrement
        * Si pas vide, mettre le prochain (antérieur) comme le précédent
        * */
        if(!estVide()){
            premier = premier.getSuivant();
        }else{
            System.out.println("La liste est vide");
        }
        return trajectoireCeduleeReference;
    }
    /*
    * Afficher la TrajectoireCedulee en commençant par la dernière entrée
    * et en terminant par la plus récente entrée et afficher le prochain
    * TrajectoireCedulee dans la liste s'il existe
    * */
    public void afficher(){
        TrajectoireCedulee laTrajectoireCedulee = premier;

        while(laTrajectoireCedulee != null){
            laTrajectoireCedulee.afficher();
            if(laTrajectoireCedulee.getSuivant() != null){

                System.out.println("Prochain enregistrement TrajectoireCedulee: ");
                System.out.println(laTrajectoireCedulee.getSuivant().toString());
            }else{
                System.out.println("Il n'y a pas d'autres enregistrements");
            }
            laTrajectoireCedulee = laTrajectoireCedulee.getSuivant();
            System.out.println();
        }
    }

    /* Recherche de l'objet par id
    * @param id = id du TrajectoireCedulee à touver
    * */
    public TrajectoireCedulee trouverParId(int id){
        TrajectoireCedulee laTrajectoireCedulee = premier;
        if(!estVide()){
            while(laTrajectoireCedulee.getTrajetId() != id){
                //Sommes-nous rendus à la fin de la liste
                if(laTrajectoireCedulee.getSuivant() == null){
                    //On sort
                    return null;
                }else{ //SI nous avons trouvé un enregistrement, nous y allons avant de poursuivre la boucle
                    laTrajectoireCedulee = laTrajectoireCedulee.getSuivant();
                }
            }

        }else{ //Si la liste est vide
            System.out.println("La liste est vide");
        }
        return laTrajectoireCedulee;
    }

    /*
    * EFFACER un TrajectoireCedulee par id.
    *
    * @param id = id du TrajectoireCedulee
    *
    * */
    public TrajectoireCedulee effacerTrajectoireParId(int id){
        TrajectoireCedulee trajectoireCeduleeCourrante = premier;
        TrajectoireCedulee trajectoirepremier = premier;

        //Tant que la comparaison ne MATCH pas
        while(trajectoireCeduleeCourrante.getTrajetId() != id ){
            //Si l'enregistrement prochain n'existe pas
            if(trajectoireCeduleeCourrante.getSuivant() == null){
                return null;
            }else{//Sinon on continue à chercher à reculons
                trajectoirepremier = trajectoireCeduleeCourrante;
                trajectoireCeduleeCourrante = trajectoireCeduleeCourrante.getSuivant();
            }
        }

        /*
        * Nous avons trouve le id'"Nous sommes prêt à mettre le next à celui
        * d'avant l'enregistrement.
        * Si la TrajectoireCedulee courante est toujours la précédente, cela signifie que
        * nous sommes tombés sur le dernier enregistrement.
        */
        if(trajectoireCeduleeCourrante == premier){
            premier = premier.getSuivant();
        }else{ // Sinon, cela implique
            trajectoirepremier.setSuivant(trajectoireCeduleeCourrante.getSuivant());
        }
        return trajectoireCeduleeCourrante; // renvoie la TrajectoireCedulee effacée.
    }

    public TrajectoireCedulee effacementPossible(Pilote pilote){
        TrajectoireCedulee trajectoireCeduleeCourrante = premier;
        TrajectoireCedulee resultat=premier; //On ne peut pas effacer par défaut
        //Tant que la comparaison ne MATCH pas
        while(trajectoireCeduleeCourrante.getPilote() != pilote ) {
            //Si l'enregistrement prochain n'existe pas on est rendu à la fin
            if (trajectoireCeduleeCourrante.getSuivant() == null) {

                return null;//Le pilote n'a pas été trouvé et on peut effacer
            }else{
                trajectoireCeduleeCourrante = trajectoireCeduleeCourrante.getSuivant();
            }
        }
        return resultat = trajectoireCeduleeCourrante;
    }

    public TrajectoireCedulee effacementPossible(Avion avion){
        TrajectoireCedulee trajectoireCeduleeCourrante = premier;
        TrajectoireCedulee resultat=premier; //On ne peut pas effacer par défaut
        //Tant que la comparaison ne MATCH pas
        while(trajectoireCeduleeCourrante.getAvion() != avion ) {
            //Si l'enregistrement prochain n'existe pas on est rendu à la fin
            if (trajectoireCeduleeCourrante.getSuivant() == null) {
                return null;//pas été trouvé et on peut effacer
            }else{
                trajectoireCeduleeCourrante = trajectoireCeduleeCourrante.getSuivant();
            }
        }
        return resultat = trajectoireCeduleeCourrante;
    }

    public LinkedList<TrajectoireCedulee> trajectoirePourCePilote(Pilote pilote){
        TrajectoireCedulee laTrajectoireCedulee = premier;
        LinkedList<TrajectoireCedulee> listeVols= new LinkedList<TrajectoireCedulee>();

        //Vérifier le premier enregistrement
        if(laTrajectoireCedulee.getPilote() == pilote){
            listeVols.add(laTrajectoireCedulee);
        }
        //Analyser le prochain
        while(laTrajectoireCedulee.getSuivant() != null){
            laTrajectoireCedulee = laTrajectoireCedulee.getSuivant();
            if(laTrajectoireCedulee.getPilote() == pilote){
                listeVols.add(laTrajectoireCedulee);
            }
        }
        return listeVols;
    }
    public LinkedList<TrajectoireCedulee> trajectoirePourCetAvion(Avion avion){
        TrajectoireCedulee laTrajectoireCedulee = premier;
        LinkedList<TrajectoireCedulee> listeVols= new LinkedList<TrajectoireCedulee>();
        //Vérifier pour le premier enregistrement
        if(laTrajectoireCedulee.getAvion() == avion){
            listeVols.add(laTrajectoireCedulee);
        }
        //Parcourir le tableau jusqu'à la fin
        while(laTrajectoireCedulee.getSuivant() != null){
            laTrajectoireCedulee = laTrajectoireCedulee.getSuivant();
            if(laTrajectoireCedulee.getAvion() == avion){
                listeVols.add(laTrajectoireCedulee);
            }
        }
        return listeVols;
    }
}
