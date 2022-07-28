package fr.kyo.sdbm_gui.service;


import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;

import java.util.ArrayList;
import java.util.List;


public class ServiceArticle
{
	private ArrayList<Pays> paysFiltre;
	private ArrayList<Continent> continentFiltre;
	private ArrayList<Fabricant> fabricantFiltre;
	private ArrayList<Couleur> couleurFiltre;
	private ArrayList<Marque> marqueFiltre;
	private ArrayList<Type> typeFiltre;
	private ArrayList<Volume> volumesFiltre;

	public ServiceArticle()
	{
		paysFiltre = DaoFactory.getPaysDAO().getAll();
		paysFiltre.add(0,new Pays(0, "Choisir un pays", new Continent()));

		continentFiltre = DaoFactory.getContinentDAO().getAll();
		continentFiltre.add(0,new Continent(0, "Choisir un continent"));

		fabricantFiltre = DaoFactory.getFabricantDAO().getAll();
		fabricantFiltre.add(0, new Fabricant(0, "Choisir un fabricant"));

		couleurFiltre = DaoFactory.getCouleurDAO().getAll();
		couleurFiltre.add(0, new Couleur(0, "Choisir une couleur"));

		typeFiltre = DaoFactory.getTypeDAO().getAll();
		typeFiltre.add(0, new Type(0, "Choisir un type"));

		volumesFiltre = DaoFactory.getArticleDAO().getVolume();;
		volumesFiltre.add(0, new Volume(0, "Choisir un volume"));

		marqueFiltre = DaoFactory.getMarqueDAO().getAll();
		marqueFiltre.add(0, new Marque(0, "Choisir une marque"));
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

	public List<Article> getFilteredArticles(ArticleSearch articleSearch, int page)
	{
		return DaoFactory.getArticleDAO().getLike(articleSearch, page);
	}

	public List<Volume> getFilteredVolume()
	{
		return volumesFiltre;
	}

	public List<Couleur> getFilteredCouleur()
	{
 		return couleurFiltre;
	}

	public ArrayList<Marque> getFilteredMarque()
	{
		return marqueFiltre;
	}

	public ArrayList<Type> getFilteredType()
	{
		return typeFiltre;
	}

}
