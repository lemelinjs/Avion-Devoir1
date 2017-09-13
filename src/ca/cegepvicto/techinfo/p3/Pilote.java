package ca.cegepvicto.techinfo.p3;
import java.util.LinkedList;

public class Pilote extends MembreVoyage{

    public Pilote last;
    public Pilote first;

    public Pilote(int id, String nom) {
        this.nom = nom;
        this.id = id;
    }

    public void afficher(){
        System.out.println("ID : " + id + " - Nom : " + nom);
    }

    public String toString(){
        return (nom);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
