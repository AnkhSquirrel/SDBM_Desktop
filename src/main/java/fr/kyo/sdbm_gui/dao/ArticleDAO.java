package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.*;
import fr.kyo.sdbm_gui.service.ArticleSearch;
import fr.kyo.sdbm_gui.metier.Article;

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

    public ArrayList<Integer> getVolume() {
        ArrayList<Integer> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT distinct volume from Article order by volume";
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                liste.add(rs.getInt(1));
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Article> getLike(ArticleSearch articleSearch){
        ResultSet rs;
        ArrayList<Marque> liste = new ArrayList<>();
        String procedureStockee = "{call dbo.SP_QBE_VUE_ARTICLE (?,?,?,?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
        {
            cStmt.setString(1, articleSearch.getLibelle());
            cStmt.setInt(2, articleSearch.getFabricant().getId());
            cStmt.setString(3, articleSearch.getPays().getId());
            cStmt.setInt(4, articleSearch.getContinent().getId());

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next())
            {
                // création d'un nouvel article à partir d'une ligne du resultset
                Marque newMarque = new Marque();
                newMarque.setId(rs.getInt(1));
                newMarque.setLibelle(rs.getString(2));
                newMarque.setPays(new Pays(rs.getString(3), rs.getString(4), new Continent(rs.getInt(5), rs.getString(6))));
                newMarque.setFabricant(new Fabricant());
                newMarque.getFabricant().setId(rs.getInt(7));
                newMarque.getFabricant().setLibelle(rs.getString(8));
                liste.add(newMarque);
            }
            rs.close();
    }

    @Override
    public boolean insert(Article objet) {
        return false;
    }

    @Override
    public boolean update(Article object) {
        return false;
    }

    @Override
    public boolean delete(Article object) {
        return false;
    }
}
