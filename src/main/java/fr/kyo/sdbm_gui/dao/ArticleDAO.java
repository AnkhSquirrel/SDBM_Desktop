package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.*;
import fr.kyo.sdbm_gui.service.ArticleSearch;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, Article> {

    public ArticleDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Article getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT id_article, nom_article, titrage, volume, prix_achat from Article order by nom_article";

            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt(1));
                article.setLibelle( rs.getString(2));
                article.setTitrage(rs.getFloat(3));
                article.setVolume( rs.getInt(4));
                article.setPrixAchat( rs.getFloat(5));
                liste.add(article);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public ArrayList<Volume> getVolume() {
        ArrayList<Volume> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT distinct volume from Article order by volume";
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Volume volume = new Volume(rs.getInt(1), String.valueOf(rs.getInt(1)));
                liste.add(volume);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public ArrayList<Article> getLike(ArticleSearch articleSearch){
        ResultSet rs;
        ArrayList<Article> liste = new ArrayList<>();
        String procedureStockee = "{call dbo.SP_QBE_VUE_ARTICLE (?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
        {
            cStmt.setString(1, articleSearch.getLibelle());
            cStmt.setInt(2, articleSearch.getVolume());
            cStmt.setFloat(3, articleSearch.getTitrageMin());
            cStmt.setFloat(4, articleSearch.getTitrageMax());
            cStmt.setInt(5, articleSearch.getMarque().getId());
            cStmt.setInt(6, articleSearch.getFabricant().getId());
            cStmt.setInt(7,articleSearch.getPays().getId());
            cStmt.setInt(8,articleSearch.getContinent().getId());
            cStmt.setInt(9, articleSearch.getCouleur().getId());
            cStmt.setInt(10, articleSearch.getType().getId());
            cStmt.setNull(11,Types.INTEGER);
            cStmt.setNull(12,Types.INTEGER);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next())
            {
                // création d'un nouvel article à partir d'une ligne du resultset
                Article newArticle = new Article();
                newArticle.setId(rs.getInt(1));
                newArticle.setLibelle(rs.getString(2));
                newArticle.setTitrage(rs.getFloat(3));
                newArticle.setVolume(rs.getInt(4));
                newArticle.setPrixAchat(rs.getFloat(5));
                newArticle.setStock(rs.getInt(6));
                newArticle.setMarque(new Marque());
                newArticle.getMarque().setId(rs.getInt(17));
                newArticle.getMarque().setLibelle(rs.getString(18));
                newArticle.getMarque().setPays(new Pays(rs.getInt(9), rs.getString(10),new Continent(rs.getInt(7), rs.getString(8))));
                newArticle.getMarque().setFabricant(new Fabricant(rs.getInt(15),rs.getString(16)));
                newArticle.setType(new Type(rs.getInt(11),rs.getString(12)));
                newArticle.setCouleur(new Couleur(rs.getInt(13), rs.getString(14)));
                liste.add(newArticle);
            }
            rs.close();
        }

        // Handle any errors that may have occurred.
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Article> getLike(Article objet) {
        return null;
    }

    @Override
    public boolean insert(Article article) {
        try {
            String requete = "INSERT INTO ARTICLE (NOM_ARTICLE, PRIX_ACHAT, VOLUME, TITRAGE, ID_MARQUE, ID_COULEUR, ID_TYPE, STOCK) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement  preparedStatement = connexion().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1 , article.getLibelle());
            preparedStatement.setFloat(2, article.getPrixAchat());
            preparedStatement.setInt(3, article.getVolume());
            preparedStatement.setFloat(4, article.getTitrage());
            preparedStatement.setInt(5, article.getMarque().getId());
            if (article.getCouleur() == null) {preparedStatement.setNull(6 , Types.INTEGER);}
                else {preparedStatement.setInt(6, article.getCouleur().getId());}
            if (article.getType() == null) { preparedStatement.setNull(7 , Types.INTEGER);}
            else { preparedStatement.setInt(7, article.getType().getId());}
            preparedStatement.setInt(8, article.getStock());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.close();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Article article) {
        try {
            String requete = "UPDATE article SET nom_article = ?, PRIX_ACHAT = ?, VOLUME = ?, TITRAGE = ?, ID_MARQUE = ?, ID_COULEUR = ?, ID_TYPE = ?,  STOCK= ?  WHERE id_article = ?";
            PreparedStatement  preparedStatement = connexion().prepareStatement(requete);
            preparedStatement.setString(1, article.getLibelle());
            preparedStatement.setFloat(2, article.getPrixAchat());
            preparedStatement.setInt(3, article.getVolume());
            preparedStatement.setFloat(4, article.getTitrage());
            preparedStatement.setInt(5, article.getMarque().getId());
            if (article.getCouleur() == null) {preparedStatement.setNull(6 , Types.INTEGER);}
            else {preparedStatement.setInt(6, article.getCouleur().getId());}
            if (article.getType() == null) { preparedStatement.setNull(7 , Types.INTEGER);}
            else { preparedStatement.setInt(7, article.getType().getId());}
            preparedStatement.setInt(8, article.getStock());
            preparedStatement.setInt(9, article.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.close();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Article article) {
        try {
            String requete = "DELETE FROM article WHERE id_article=?";
            PreparedStatement preparedStatement = connexion().prepareStatement(requete);
            preparedStatement.setInt(1, article.getId());
            int rowDeleted = preparedStatement.executeUpdate();
            if (rowDeleted == 0)
                article.setId(0);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
