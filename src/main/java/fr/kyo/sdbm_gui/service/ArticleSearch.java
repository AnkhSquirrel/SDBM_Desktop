package fr.kyo.sdbm_gui.service;

import fr.kyo.sdbm_gui.metier.*;


public class ArticleSearch {
    private int id;
    private String nom;
    private Pays pays;
    private Continent continent;
    private Marque marque;
    private Fabricant fabricant;
    private Couleur couleur;
    private Type type;

    public ArticleSearch(){
        pays = new Pays();
        continent = new Continent();
        marque = new Marque();
        fabricant = new Fabricant();
        couleur = new Couleur();
        type = new Type();
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

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
