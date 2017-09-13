package ca.cegepvicto.techinfo.p3;

import java.util.LinkedList;

public class Avion extends MembreVoyage{
    private int id;
    private String code;

    public Avion(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public void afficher(){
        System.out.println("ID : " + id + " - Code : " + code);
    }

    public String toString(){
        return (code);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

}
