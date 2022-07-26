package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.*;

import java.sql.CallableStatement;
import java.sql.Connection;
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

	@Override
	public ArrayList<Marque> getLike(Marque objet) {
		return null;
	}

	@Override
	public Marque getByID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Marque objet)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
