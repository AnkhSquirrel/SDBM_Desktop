package fr.kyo.sdbm_gui.dao;

import fr.kyo.sdbm_gui.metier.Article;
import fr.kyo.sdbm_gui.metier.Marque;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, Article> {

    public ArticleDAO(Connection connexion)
    {
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
            String strCmd = "SELECT id_article, nom_article, volume from Article order by nom_article";
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt(1));
                article.setLibelle( rs.getString(2));
                article.setVolume( rs.getInt(3));
                liste.add(article);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Article> getLike(Article objet) {
        return null;
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
