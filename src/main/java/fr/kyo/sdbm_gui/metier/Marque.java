package fr.kyo.sdbm_gui.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marque
{

    private IntegerProperty id;
    private StringProperty libelle;
    private Pays pays;
    private Fabricant fabricant;

    public Marque(){
        id = new SimpleIntegerProperty();
        libelle = new SimpleStringProperty();
    }

    public Integer getId()
    {
	return id.get();
    }

    public void setId(Integer id)
    {
	this.id.setValue(id);
    }

    public String getLibelle()
    {
	return libelle.get();
    }

    public void setLibelle(String libelle)
    {
	this.libelle.set(libelle);
    }

    public Pays getPays()
    {
	return pays;
    }

    public void setPays(Pays pays)
    {
	this.pays = pays;
    }

    public Fabricant getFabricant()
    {
	return fabricant;
    }

    public void setFabricant(Fabricant fabricant)
    {
	this.fabricant = fabricant;
    }

    @Override
    public String toString()
    {
	return libelle.getValue();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty libelleProperty() {
        return libelle;
    }
}
