package fr.kyo.sdbm_gui.metier;

public class TypeBiere
{
    private int id;
    private String libelle;
    
    public TypeBiere (int id, String libelle)
    {
	this.id = id;
	this.libelle = libelle;
    }
    public TypeBiere()
	{
		
	}
	public Integer getId()
    {
	return id;
    }
    @Override
    public String toString()
    {
	return libelle;
    }
    
}