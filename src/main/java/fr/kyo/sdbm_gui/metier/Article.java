package fr.kyo.sdbm_gui.metier;

import javafx.beans.property.*;

public class Article
{

	private Integer id;
	private String libelle;
	private Integer volume;
	private Float titrage;
	private Float prixAchat;
	private Marque marque;
	private Couleur couleur;
	private TypeBiere typeBiere;
	
	public Article()
	{
		couleur = new Couleur();
		typeBiere = new TypeBiere();
		marque = new Marque();
	}

	public Article(Integer id, String libelle)
	{
		this.id = id;
		this.libelle = libelle;
		couleur = new Couleur();
		typeBiere = new TypeBiere();
		marque = new Marque();
	}

	public IntegerProperty idProperty()
	{
		return new SimpleIntegerProperty(id);
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public StringProperty libelleProperty()
	{
		return new SimpleStringProperty(libelle);
	}

	public String getLibelle()
	{
		return libelle;
	}

	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}

	public IntegerProperty volumeProperty()
	{
		return new SimpleIntegerProperty(volume);
	}

	public Integer getVolume()
	{
		return volume;
	}

	public void setVolume(Integer volume)
	{
		this.volume = volume;
	}

	public FloatProperty titrageProperty()
	{
		return new SimpleFloatProperty(titrage);
	}

	public Float getTitrage()
	{
		return titrage;
	}

	public void setTitrage(Float titrage)
	{
		this.titrage = titrage;
	}

	public Couleur getCouleur()
	{
		return couleur;
	}

	public void setCouleur(Couleur couleur)
	{
		this.couleur = couleur;
	}

	public Marque getMarque()
	{
		return marque;
	}

	public void setMarque(Marque marque)
	{
		this.marque = marque;
	}

	public TypeBiere getTypeBiere()
	{
		return typeBiere;
	}

	public void setTypeBiere(TypeBiere typeBiere)
	{
		this.typeBiere = typeBiere;
	}

	public Float getPrixAchat()
	{
		return prixAchat;
	}

	public void setPrixAchat(Float prixAchat)
	{
		this.prixAchat = prixAchat;
	}

	public FloatProperty prixAchatProperty()
	{
		return new SimpleFloatProperty(prixAchat);
	}
}
