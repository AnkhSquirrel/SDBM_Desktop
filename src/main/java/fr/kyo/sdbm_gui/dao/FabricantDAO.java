package fr.kyo.sdbm_gui.dao;


import fr.kyo.sdbm_gui.metier.Fabricant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class FabricantDAO extends DAO<Fabricant, Fabricant> {
	public FabricantDAO(Connection connexion) {
		super(connexion);
	}

	@Override
	public Fabricant getByID(int id) {
		return null;
	}


	public ArrayList<Fabricant> getAll() {
		ArrayList<Fabricant> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()) {

			// Determine the column set column

			String strCmd = "SELECT id_fabricant,nom_fabricant from fabricant order by nom_fabricant";
			ResultSet rs = stmt.executeQuery(strCmd);
			while (rs.next()) {
				Fabricant fabricant = new Fabricant(rs.getInt(1), rs.getString(2));
				fabricant.getMarques().addAll(DaoFactory.getMarqueDAO().getByFabricant(fabricant.getId()));
				liste.add(fabricant);
			}
			rs.close();

		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	public Fabricant getByMarque(int marque) {
		Fabricant fabricant;
		try {

			PreparedStatement pStmt = connexion.prepareStatement("SELECT f.ID_FABRICANT, f.NOM_FABRICANT from FABRICANT as f join MARQUE as m on m.ID_FABRICANT = f.ID_FABRICANT where m.ID_MARQUE = ? ");
			pStmt.setInt(1, marque);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()){
				fabricant = new Fabricant(rs.getInt(1), rs.getString(2));
				rs.close();
				return fabricant;
			}
			else
				return new Fabricant(0,"");

		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public ArrayList<Fabricant> getLike(Fabricant objet) {
		return new ArrayList<>();
	}

	@Override
	public boolean insert(Fabricant objet) {
		return false;
	}

	@Override
	public boolean update(Fabricant object) {
		return false;
	}

	@Override
	public boolean delete(Fabricant object) {
		return false;
	}

}