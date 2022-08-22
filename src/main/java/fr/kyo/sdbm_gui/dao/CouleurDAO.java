package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.Couleur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur, Couleur>{
    public CouleurDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Couleur getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Couleur> getAll() {
            ArrayList<Couleur> liste = new ArrayList<>();
            try (Statement stmt = connexion.createStatement()) {


                // Determine the column set column

                String strCmd = "SELECT id_couleur ,nom_couleur from couleur order by nom_couleur";
                ResultSet rs = stmt.executeQuery(strCmd);

                while (rs.next()) {
                    liste.add(new Couleur(rs.getInt(1), rs.getString(2)));
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
    public ArrayList<Couleur> getLike(Couleur objet) {
        return new ArrayList<>();
    }

    @Override
    public boolean insert(Couleur objet) {
        return false;
    }

    @Override
    public boolean update(Couleur object) {
        return false;
    }

    @Override
    public boolean delete(Couleur object) {
        return false;
    }


}
