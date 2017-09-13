package ca.cegepvicto.techinfo.p3.structures;

import ca.cegepvicto.techinfo.p3.Trajectoire;

public class DoubleEndedLinkedListTrajectoire {
    private Trajectoire premier;
    private Trajectoire dernier;
    
    /*
    * CONSTRUCTEUR
    *
    * */
    public DoubleEndedLinkedListTrajectoire(){
        premier = null;
    }

    //Regarder si le précédent est null, si oui, il n'y a pas d'enregistrements dans la liste
    public boolean estVide(){
        return (premier == null);
    }
    public void insererPremierePosition(int id, String depart, String arrivee){
        Trajectoire nouvellaTrajectoire = new Trajectoire(id, depart,arrivee);

        if(estVide()){
            dernier = nouvellaTrajectoire;
        }
        nouvellaTrajectoire.setSuivant(premier);
        premier = nouvellaTrajectoire;
    }

    public void insererDernierePosition(int id, String depart, String arrivee){
        Trajectoire nouvellaTrajectoire = new Trajectoire(id, depart,arrivee);

        if(estVide()){
            premier = nouvellaTrajectoire;
        }else {

            dernier.setSuivant(nouvellaTrajectoire);
            nouvellaTrajectoire.setPrecedent(dernier);
        }
        dernier = nouvellaTrajectoire;
    }

    public boolean insererApresCle(int id, String depart,String arrivee, int key){
        Trajectoire nouvellaTrajectoire = new Trajectoire(id, depart,arrivee);
        Trajectoire TrajectoireCourrante = premier;

        //Tant que la clé n'est pas celle que nous cherchons, on continue de chercher
        while(TrajectoireCourrante.getTrajetId() != key){
            TrajectoireCourrante = TrajectoireCourrante.getSuivant();
            //Si nous n'avons pas trouvé clé et que nous sommes à la fin
            if(TrajectoireCourrante == null){
                return false;
            }
        }

        //Si nous avons passé, nous avons trouvé la clé
        //S'il est la fin de la série
        if(TrajectoireCourrante == dernier){
            nouvellaTrajectoire.setSuivant(null);
            dernier = nouvellaTrajectoire;
        }else{ //S'il est en milieu, le nouvel Trajectoire prend les infos de la clé
            //S'occupper de celui d'après
            nouvellaTrajectoire.setSuivant(TrajectoireCourrante.getSuivant());
            TrajectoireCourrante.getSuivant().setPrecedent(nouvellaTrajectoire);
        }
        //S'occuper de celui d'avant
        nouvellaTrajectoire.setPrecedent(TrajectoireCourrante);
        TrajectoireCourrante.setSuivant(nouvellaTrajectoire);
        return true;
    }

    //Effacer un Trajectoire
    public Trajectoire EffacerPremierTrajectoire(){

        Trajectoire TrajectoireReference = premier;
        
        /*Vérifier s'il y a au-moins un enregistrement
        * Si pas vide, mettre le prochain (antérieur) comme le précédent
        * */
        if(!estVide()){
            premier = premier.getSuivant();
        }else{
            System.out.println("La liste est vide");
        }
        return TrajectoireReference;
    }
    /*
    * Afficher la Trajectoire en commençant par la dernière entrée
    * et en terminant par la plus récente entrée et afficher le prochain
    * Trajectoire dans la liste s'il existe
    * */
    public void afficher(){
        Trajectoire laTrajectoire = premier;

        while(laTrajectoire != null){
            laTrajectoire.afficher();
            if(laTrajectoire.getSuivant() != null){
                System.out.println("Prochain enregistrement Trajectoire No : " +
                        laTrajectoire.getSuivant().getDepart() + "-" + laTrajectoire.getSuivant().getArrivee());
            }else{
                System.out.println("Il n'y a pas d'autres enregistrements");
            }
            laTrajectoire = laTrajectoire.getSuivant();
            System.out.println();
        }
    }

    /* Recherche de l'objet par id
    * @param id = id du Trajectoire à touver
    * */
    public Trajectoire trouverParId(int id){
        Trajectoire laTrajectoire = premier;
        if(!estVide()){
            while(laTrajectoire.getTrajetId() != id){
                //Sommes-nous rendus à la fin de la liste
                if(laTrajectoire.getSuivant() == null){
                    //On sort
                    return null;
                }else{ //SI nous avons trouvé un enregistrement, nous y allons avant de poursuivre la boucle
                    laTrajectoire = laTrajectoire.getSuivant();
                }
            }

        }else{ //Si la liste est vide
            System.out.println("La liste est vide");
        }
        return laTrajectoire;
    }

    /*
    * EFFACER un Trajectoire par id.
    *
    * @param id = id du Trajectoire
    *
    * */
    public Trajectoire effacerTrajectoireParId(int id){
        Trajectoire TrajectoireCourrante = premier;
        Trajectoire trajectoirepremier = premier;

        //Tant que la comparaison ne MATCH pas
        while(TrajectoireCourrante.getTrajetId() != id ){
            //Si l'enregistrement prochain n'existe pas
            if(TrajectoireCourrante.getSuivant() == null){
                return null;
            }else{//Sinon on continue à chercher à reculons
                trajectoirepremier = TrajectoireCourrante;
                TrajectoireCourrante = TrajectoireCourrante.getSuivant();
            }
        }

        /*
        * Nous avons trouve le id'"Nous sommes prêt à mettre le next à celui
        * d'avant l'enregistrement.
        * Si la Trajectoire courante est toujours la précédente, cela signifie que
        * nous sommes tombés sur le dernier enregistrement.
        */
        if(TrajectoireCourrante == premier){
            premier = premier.getSuivant();
        }else{ // Sinon, cela implique
            trajectoirepremier.setSuivant(TrajectoireCourrante.getSuivant());
        }
        return TrajectoireCourrante; // renvoie la Trajectoire effacée.
    }
}
