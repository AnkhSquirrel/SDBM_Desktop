package fr.kyo.sdbm_gui.service;


import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;

import java.util.ArrayList;



public class ServiceArticle
{
	private ArrayList<Pays> paysFiltre;
	private ArrayList<Continent> continentFiltre;
	private ArrayList<Fabricant> fabricantFiltre;
	public ServiceArticle()
	{
		paysFiltre = DaoFactory.getPaysDAO().getAll();
		continentFiltre = DaoFactory.getContinentDAO().getAll();
		fabricantFiltre = DaoFactory.getFabricantDAO().getAll();
		Fabricant fabricant = new Fabricant();
		fabricant.setLibelle("Choisir un fabricant");
		fabricantFiltre.add(0, fabricant);
	}


	public ArrayList<Pays> getPaysFiltre()
	{
		return paysFiltre;
	}

	public ArrayList<Continent> getContinentFiltre()
	{
		return continentFiltre;
	}


	public ArrayList<Fabricant> getFabricantFiltre()
	{
		return fabricantFiltre;
	}

	public ArrayList<Article> getFilteredArticles()
	{
		return DaoFactory.getArticleDAO().getAll();
	}

}
