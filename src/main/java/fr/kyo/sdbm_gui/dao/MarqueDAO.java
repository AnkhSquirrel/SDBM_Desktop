package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MarqueDAO extends DAO<Marque, Marque>
{
	public MarqueDAO(Connection connexion)
	{
		super(connexion);
	}

	@Override
	public Marque getByID(int id) {
		return null;
	}


	@Override
	public ArrayList<Marque> getAll(){
		ArrayList<Marque> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()) {
			String strCmd = "SELECT id_marque, nom_marque from Marque order by nom_marque";
			ResultSet rs = stmt.executeQuery(strCmd);
			while (rs.next()) {
				Marque marque = new Marque();
				marque.setId(rs.getInt(1));
				marque.setLibelle( rs.getString(2));
				liste.add(marque);
			}
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	public ArrayList<Marque> getByFabricant(int fabricant) {
		ArrayList<Marque> liste = new ArrayList<>();
		try {

			PreparedStatement pStmt = connexion.prepareStatement("SELECT ID_MARQUE, NOM_MARQUE from Marque where ID_FABRICANT =  ?  order by NOM_MARQUE");
			pStmt.setInt(1, fabricant);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				liste.add(new Marque(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public ArrayList<Marque> getLike(Marque objet) {
		return new ArrayList<>();
	}

	@Override
	public boolean insert(Marque objet) {
		return false;
	}

	@Override
	public boolean update(Marque object) {
		return false;
	}

	@Override
	public boolean delete(Marque object) {
		return false;
	}

}
