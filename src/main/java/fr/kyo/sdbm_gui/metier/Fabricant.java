package fr.kyo.sdbm_gui.metier;

import java.util.ArrayList;
import java.util.List;

public class Fabricant
{
    private int id;
    private String libelle;
    private ArrayList<Marque> marques;

    public Fabricant() {
        this.id = 0;
        this.libelle = "Fabricant";
        marques = new ArrayList<>();
    }
    public Fabricant(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        marques = new ArrayList<>();
    }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getLibelle()
    {
        return libelle;
    }
    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
    }
    @Override
    public String toString()
    {
	return libelle;
    }

    public List<Marque> getMarques()
    {
        return marques;
    }
}
