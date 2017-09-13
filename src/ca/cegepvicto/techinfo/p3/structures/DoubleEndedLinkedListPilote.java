package ca.cegepvicto.techinfo.p3.structures;

import ca.cegepvicto.techinfo.p3.Pilote;

public class DoubleEndedLinkedListPilote extends DoubleEndedLinkedList {
        
    /*
    * CONSTRUCTEUR
    *
    * */
    public DoubleEndedLinkedListPilote(){
        first = null;
    }

    //Regarder si le précédent est null, si oui, il n'y a pas d'enregistrements dans la liste
    public boolean estVide(){
        return (first == null);
    }
    public void insererPremierePosition(int id, String nom){
        Pilote nouveauPilote = new Pilote(id, nom);

        if(estVide()){
            last = nouveauPilote;
        }
        nouveauPilote.next=first;
        first = nouveauPilote;
    }

    public void insererDernierePosition(int id, String nom){
        Pilote nouveauPilote = new Pilote(id,nom);

        if(estVide()){
            first = nouveauPilote;
        }else {

            last.next = nouveauPilote;
            nouveauPilote.previous = last;
        }
        last = nouveauPilote;
    }

    public boolean insererApresCle(int id, String nom,int key){
        Pilote nouveauPilote = new Pilote(id,nom);
        Pilote PiloteCourrant = first;

        //Tant que la clé n'est pas celle que nous cherchons, on continue de chercher
        while(PiloteCourrant.getId() != key){
            PiloteCourrant = PiloteCourrant.;
            //Si nous n'avons pas trouvé clé et que nous sommes à la fin
            if(PiloteCourrant == null){
                return false;
            }
        }

        //Si nous avons passé, nous avons trouvé la clé
        //S'il est la fin de la série
        if(PiloteCourrant == last){
            nouveauPilote.next = null;
            last = nouveauPilote;
        }else{ //S'il est en milieu, le nouvel Pilote prend les infos de la clé
            //S'occupper de celui d'après
            nouveauPilote.setSuivant(PiloteCourrant.getSuivant());
            PiloteCourrant.getSuivant().setPrecedent(nouveauPilote);
        }
        //S'occuper de celui d'avant
        nouveauPilote.setPrecedent(PiloteCourrant);
        PiloteCourrant.setSuivant(nouveauPilote);
        return true;
    }

    //Effacer un pilote
    public Pilote EffacerPremierPilote(){

        Pilote piloteReference = first;
        
        /*Vérifier s'il y a au-moins un enregistrement
        * Si pas vide, mettre le prochain (antérieur) comme le précédent
        * */
        if(!estVide()){
            first = first.getSuivant();
        }else{
            System.out.println("La liste est vide");
        }
        return piloteReference;
    }
    /*
    * Afficher la Pilote en commençant par la dernière entrée
    * et en terminant par la plus récente entrée et afficher le prochain
    * Pilote dans la liste s'il existe
    * */
    public void afficher(){
        Pilote lePilote = first;

        while(lePilote != null){
            lePilote.afficher();
            if(lePilote.getSuivant() != null){
                System.out.println("Prochain enregistrement pilote No : " + lePilote.getSuivant().toString());
            }else{
                System.out.println("Il n'y a pas d'autres enregistrements");
            }
            lePilote = lePilote.getSuivant();
            System.out.println();
        }
    }

    /* Recherche de l'objet par id
    * @param id = id du pilote à touver
    * */
    public Pilote trouverParId(int id){
        Pilote lePilote = first;
        if(!estVide()){
            while(lePilote.getId() != id){
                //Sommes-nous rendus à la fin de la liste
                if(lePilote.getSuivant() == null){
                    //On sort
                    return null;
                }else{ //SI nous avons trouvé un enregistrement, nous y allons avant de poursuivre la boucle
                    lePilote = lePilote.getSuivant();
                }
            }

        }else{ //Si la liste est vide
            System.out.println("La liste est vide");
        }
        return lePilote;
    }

    /*
    * EFFACER un pilote par id.
    *
    * @param id = id du pilote
    *
    * */
    public Pilote effacerPiloteParId(int id){
        Pilote piloteCourrant = first;
        Pilote pilotepremier = first;

        //Tant que la comparaison ne MATCH pas
        while(piloteCourrant.getId() != id ){
            //Si l'enregistrement prochain n'existe pas
            if(piloteCourrant.getSuivant() == null){
                return null;
            }else{//Sinon on continue à chercher à reculons
                pilotepremier = piloteCourrant;
                piloteCourrant = piloteCourrant.getSuivant();
            }
        }

        /*
        * Nous avons trouve le id'"Nous sommes prêt à mettre le next à celui
        * d'avant l'enregistrement.
        * Si la Pilote courante est toujours la précédente, cela signifie que
        * nous sommes tombés sur le last enregistrement.
        */
        if(piloteCourrant == first){
            first = first.getSuivant();
        }else{ // Sinon, cela implique
            pilotepremier.setSuivant(piloteCourrant.getSuivant());
        }
        return piloteCourrant; // renvoie la Pilote effacée.
    }
}
