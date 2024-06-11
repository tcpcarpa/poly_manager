package es.polytex.integracionback.files.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Limit {
    @Id
    private String nom;
    private String codi;
    private String color;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

