package ca.cegepvicto.techinfo.p3.structures;

import ca.cegepvicto.techinfo.p3.Avion;
import ca.cegepvicto.techinfo.p3.MembreVoyage;

public class DoubleEndedLinkedList {
    public MembreVoyage first;
    public MembreVoyage last;
    private MembreVoyage objetMembreVoyage;

    public void insererPremierePosition(int id, String code){
        Avion nouvelAvion = new Avion(id,code);

        if(estVide()){
            last = nouvelAvion;
        }else {
            first=first.next;
        }
        nouvelAvion.next = first;
        first = nouvelAvion;
    }
    public void insererDernierePosition(int id, String code){
        Avion nouvelAvion = new Avion(id,code);

        if(estVide()){
            first = nouvelAvion;
        }else {

            last.setSuivant(nouvelAvion);
            nouvelAvion.setPrecedent(last);
        }
        last = nouvelAvion;
    }

    public boolean insererApresCle(int id, String code,int key){
        Avion nouvelAvion = new Avion(id,code);
        Avion avionCourrant = first;

        //Tant que la clé n'est pas celle que nous cherchons, on continue de chercher
        while(avionCourrant.getId() != key){
            avionCourrant = avionCourrant.getSuivant();
            //Si nous n'avons pas trouvé clé et que nous sommes à la fin
            if(avionCourrant == null){
                return false;
            }
        }

        //Si nous avons passé, nous avons trouvé la clé
        //S'il est la fin de la série
        if(avionCourrant == last){
            nouvelAvion.setSuivant(null);
            last = nouvelAvion;
        }else{ //S'il est en milieu, le nouvel avion prend les infos de la clé
            nouvelAvion.setSuivant(avionCourrant.getSuivant());
            avionCourrant.getSuivant().setPrecedent(nouvelAvion);
        }

        nouvelAvion.setPrecedent(avionCourrant);
        avionCourrant.setSuivant(nouvelAvion);
        return true;
    }

    public void afficher(){
        Avion avion = first;

        while(avion != null){
            avion.afficher();
            if(avion.getSuivant() != null){
                System.out.println("Prochain avion est  : " + avion.getSuivant().toString());
            }else{
                System.out.println("Il n'y a pas d'autres enregistrements");
            }
            avion = avion.getSuivant();
            System.out.println();
        }
    }
    public Avion trouverParId(int id){
        Avion avion = first;
        if(!estVide()){
            while(avion.getId() != id){
                //Sommes-nous rendus à la fin de la liste
                if(avion.getSuivant() == null){
                    //On sort
                    return null;
                }else{ //SI nous avons trouvé un enregistrement, nous y allons avant de poursuivre la boucle
                    avion = avion.getSuivant();
                }
            }

        }else{ //Si la liste est vide
            System.out.println("La liste est vide");
        }
        return avion;
    }


    public Avion effacerAvionParId(int id){
        Avion avionCourrante = first;
        Avion avionPremiere = first;

        //Tant que la comparaison ne MATCH pas
        while(avionCourrante.getId() != id ){
            //Si l'enregistrement prochain n'existe pas
            if(avionCourrante.getSuivant() == null){
                return null;
            }else{//Sinon on continue à chercher à reculons
                avionPremiere = avionCourrante;
                avionCourrante = avionCourrante.getSuivant();
            }
        }

        /*
        * Nous avons trouve le id'"Nous sommes prêt à mettre le next à celui
        * d'avant l'enregistrement.
        * Si la Pilote courante est toujours la précédente, cela signifie que
        * nous sommes tombés sur le last enregistrement.
        */
        if(avionCourrante == first){
            first = first.getSuivant();
        }else{ // Sinon, cela implique
            avionPremiere.setSuivant(avionCourrante.getSuivant());
        }
        return avionCourrante; // renvoie la Pilote effacée.
    }

    public boolean estVide(){
        return(first == null);
    }

}
