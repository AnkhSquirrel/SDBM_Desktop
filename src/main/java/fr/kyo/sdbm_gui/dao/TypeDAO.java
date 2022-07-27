package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.TypeBiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<TypeBiere , TypeBiere> {
    public TypeDAO(Connection connexion) {
        super(connexion);
    }


    @Override
    public TypeBiere getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<TypeBiere> getAll() {
        {
            ArrayList<TypeBiere> liste = new ArrayList<>();
            try (Statement stmt = connexion.createStatement()) {


                // Determine the column set column

                String strCmd = "SELECT id_type ,nom_type from typebiere order by nom_type";
                ResultSet rs = stmt.executeQuery(strCmd);

                while (rs.next()) {
                    liste.add(new TypeBiere(rs.getInt(1), rs.getString(2)));
                }
                rs.close();
            }
            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
            return liste;
        }

    }

    @Override
    public ArrayList<TypeBiere> getLike(TypeBiere objet) {
        return null;
    }

    @Override
    public boolean insert(TypeBiere objet) {
        return false;
    }

    @Override
    public boolean update(TypeBiere object) {
        return false;
    }

    @Override
    public boolean delete(TypeBiere object) {
        return false;
    }
}

