package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.Continent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ContinentDAO extends DAO<Continent, Continent> {
	public ContinentDAO(Connection connexion) {
		super(connexion);
	}

	@Override
	public Continent getByID(int id) {
		return null;
	}


	public ArrayList<Continent> getAll() {
		ArrayList<Continent> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()){

			// Determine the column set column

			String strCmd = "SELECT id_continent,nom_continent from continent order by nom_continent";
			ResultSet rs = stmt.executeQuery(strCmd);
			Continent continentLu ;
			while (rs.next()) {
				continentLu = new Continent(rs.getInt(1), rs.getString(2));
				continentLu.getPays().addAll(DaoFactory.getPaysDAO().getByContinent(continentLu.getId()));
				liste.add(continentLu);
			}
			rs.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	@Override
	public ArrayList<Continent> getLike(Continent objet) {
		return new ArrayList<>();
	}

	@Override
	public boolean insert(Continent objet) {
		return false;
	}

	@Override
	public boolean update(Continent object) {
		return false;
	}

	@Override
	public boolean delete(Continent object) {
		return false;
	}
}