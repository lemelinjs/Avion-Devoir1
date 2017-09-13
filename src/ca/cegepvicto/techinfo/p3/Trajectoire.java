package ca.cegepvicto.techinfo.p3;

public class Trajectoire {
    private int trajetId;
    private String depart;
    private String arrivee;
    private Pilote pilote;
    private Avion avion;
    private Trajectoire suivant;
    private Trajectoire precedent;
    private int id;

    //TODO Changer nom trajetID par ID
    public Trajectoire(int id, String depart, String arrivee) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
    }


    public void afficher(){
        System.out.println("ID : " + trajetId +" | Départ : " + depart +" | Arrivée : " + arrivee +
        " | Pilote : " + pilote.getNom() + " | Code Avion : " + avion.getCode() );
    }

    public String toString(){
        return ("Trajet " + depart + " - " + arrivee);
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId ) {
        this.trajetId = trajetId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Trajectoire getSuivant() {
        return suivant;
    }

    public void setSuivant(Trajectoire suivant) {
        this.suivant = suivant;
    }

    public Trajectoire getPrecedent() {
        return precedent;
    }

    public void setPrecedent(Trajectoire precedent) {
        this.precedent = precedent;
    }

}
