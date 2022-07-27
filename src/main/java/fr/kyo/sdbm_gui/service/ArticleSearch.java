package fr.kyo.sdbm_gui.service;

import fr.kyo.sdbm_gui.metier.*;


public class ArticleSearch {
    private Integer id;
    private String libelle;
    private Integer volume;
    private Float titrageMin;
    private Float titrageMax;
    private Float prixAchat;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Float getTitrageMin() {
        return titrageMin;
    }

    public void setTitrageMin(Float titrageMin) {
        this.titrageMin = titrageMin;
    }

    public Float getTitrageMax() {
        return titrageMax;
    }

    public void setTitrageMax(Float titrageMax) {
        this.titrageMax = titrageMax;
    }

    public Float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
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
