package fr.kyo.sdbm_gui.metier;

public class Couleur
{
    private int id;
    private String libelle;

    public Couleur()
    {

    }

    public Couleur(Integer id, String libelle)
    {
	this.id = id;
	this.libelle = libelle;
    }

    public Integer getId()
    {
	return id;
    }

    public String getLibelle()
	{
		return libelle;
	}

	@Override
    public String toString()
    {
	return libelle;
    }

}
