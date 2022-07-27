package fr.kyo.sdbm_gui.service;


import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;

import java.util.ArrayList;



public class ServiceArticle
{
	private ArrayList<Pays> paysFiltre;
	private ArrayList<Continent> continentFiltre;
	private ArrayList<Fabricant> fabricantFiltre;
	private ArrayList<Couleur> couleurFiltre;
	private ArrayList<Marque> marqueFiltre;
	private ArrayList<Type> typeFiltre;
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

		marqueFiltre = DaoFactory.getMarqueDAO().getAll();
		Marque marque = new Marque();
		marque.setId(0);
		marque.setLibelle("Choisir une marque ");
		marqueFiltre.add(0, marque);



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

	public ArrayList<Article> getFilteredArticles(ArticleSearch articleSearch)
	{
		return DaoFactory.getArticleDAO().getLike(articleSearch);
	}

	public ArrayList<Integer> getFilteredVolume()
	{
		return DaoFactory.getArticleDAO().getVolume();
	}

	public ArrayList<Couleur> getFilteredCouleur()
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
