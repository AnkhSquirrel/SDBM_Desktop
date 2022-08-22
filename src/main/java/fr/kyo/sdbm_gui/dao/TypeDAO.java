package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<Type , Type> {
    public TypeDAO(Connection connexion) {
        super(connexion);
    }


    @Override
    public Type getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Type> getAll() {
            ArrayList<Type> liste = new ArrayList<>();
            try (Statement stmt = connexion.createStatement()) {


                // Determine the column set column

                String strCmd = "SELECT id_type ,nom_type from typebiere order by nom_type";
                ResultSet rs = stmt.executeQuery(strCmd);

                while (rs.next()) {
                    liste.add(new Type(rs.getInt(1), rs.getString(2)));
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
    public ArrayList<Type> getLike(Type objet) {
        return new ArrayList<>();
    }

    @Override
    public boolean insert(Type objet) {
        return false;
    }

    @Override
    public boolean update(Type object) {
        return false;
    }

    @Override
    public boolean delete(Type object) {
        return false;
    }
}

