package fr.kyo.sdbm_gui.service;


import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;

import java.util.ArrayList;
import java.util.List;


public class ServiceArticle
{
	private final ArrayList<Pays> paysFiltre;
	private final ArrayList<Continent> continentFiltre;
	private final ArrayList<Fabricant> fabricantFiltre;
	public ServiceArticle()
	{
		paysFiltre = DaoFactory.getPaysDAO().getAll();
		continentFiltre = DaoFactory.getContinentDAO().getAll();
		fabricantFiltre = DaoFactory.getFabricantDAO().getAll();
		Fabricant fabricant = new Fabricant();
		fabricant.setLibelle("Choisir un fabricant");
		fabricantFiltre.add(0, fabricant);
	}


	public List<Pays> getPaysFiltre()
	{
		return paysFiltre;
	}

	public List<Continent> getContinentFiltre()
	{
		return continentFiltre;
	}


	public List<Fabricant> getFabricantFiltre()
	{
		return fabricantFiltre;
	}

	public List<Article> getFilteredArticles(ArticleSearch articleSearch)
	{
		return DaoFactory.getArticleDAO().getLike(articleSearch);
	}

	public List<Integer> getFilteredVolume()
	{
		return DaoFactory.getArticleDAO().getVolume();
	}

	public List<Couleur> getFilteredCouleur()
	{
 		return DaoFactory.getCouleurDAO().getAll();
	}

	public ArrayList<Marque> getFilteredMarque()
	{
		return DaoFactory.getMarqueDAO().getAll();
	}

	public ArrayList<Type> getFilteredType()
	{
		return DaoFactory.getTypeDAO().getAll();
	}

}
