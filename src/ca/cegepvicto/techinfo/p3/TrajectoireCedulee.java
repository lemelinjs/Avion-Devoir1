package ca.cegepvicto.techinfo.p3;

public class TrajectoireCedulee {

    private int trajetId;
    private Trajectoire trajectoire;
    private Pilote pilote;
    private Avion avion;
    private TrajectoireCedulee suivant;
    private TrajectoireCedulee precedent;

    public TrajectoireCedulee(int id,Trajectoire trajectoire, Pilote pilote, Avion avion) {
        this.trajectoire = trajectoire;
        this.pilote = pilote;
        this.avion = avion;
        this.trajetId = id;
    }

    public void afficher(){
        System.out.println("ID : " + trajetId +" | Départ : " +" | Arrivée : " +
        " | Pilote : " + pilote.getNom() + " | Code Avion : " + avion.getCode() );
    }

    public String toString(){
        return ("Trajet " + trajetId + ", pilote " + pilote +" & avion " + avion );
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId ) {
        this.trajetId = trajetId;
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


    public Trajectoire getTrajectoire() {
        return trajectoire;
    }

    public void setTrajectoire(Trajectoire trajectoire) {
        this.trajectoire = trajectoire;
    }

    public TrajectoireCedulee getSuivant() {
        return suivant;
    }

    public void setSuivant(TrajectoireCedulee suivant) {
        this.suivant = suivant;
    }

    public TrajectoireCedulee getPrecedent() {
        return precedent;
    }

    public void setPrecedent(TrajectoireCedulee precedent) {
        this.precedent = precedent;
    }

}
