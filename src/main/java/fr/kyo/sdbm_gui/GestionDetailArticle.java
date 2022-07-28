package fr.kyo.sdbm_gui;

import fr.kyo.sdbm_gui.metier.Article;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GestionDetailArticle {
    MenuApp menuApp;

    Article article;
    @FXML
    Label labelValue;
    @FXML
    Label titrageValue;
    @FXML
    Label volumeValue;
    @FXML
    Label prixValue;
    @FXML
    Label stockValue;
    @FXML
    Label marqueValue;
    @FXML
    Label fabricantValue;
    @FXML
    Label paysValue;
    @FXML
    Label continentValue;
    @FXML
    Label couleurValue;
    @FXML
    Label typeValue;

    @FXML
    GridPane gridPane;

    public void initialize(){
        hideAll();
    }

    public void setMenuApp(MenuApp menuApp){
        this.menuApp = menuApp;
    }

    public void hideAll(){
        for(Node label : gridPane.getChildren()){
            label.setVisible(false);
        }
    }

    public void showAll(){
        for(Node label : gridPane.getChildren()){
            label.setVisible(true);
        }
    }

    @FXML
    public void updateArticle(){
        menuApp.openModalArticle(article);
    }

    public void showDetail(Article article) {
        this.article = article;
        labelValue.setText(article.getLibelle());
        titrageValue.setText(article.getTitrage().toString());
        volumeValue.setText(article.getVolume().toString());
        prixValue.setText(article.getPrixAchat().toString());
        stockValue.setText(String.valueOf(article.getStock()));
        marqueValue.setText(article.getMarque().getLibelle());
        fabricantValue.setText(article.getMarque().getFabricant().getLibelle());
        paysValue.setText(article.getMarque().getPays().getLibelle());
        continentValue.setText(article.getMarque().getPays().getContinent().getLibelle());
        couleurValue.setText(article.getCouleur().getLibelle());
        typeValue.setText(article.getType().getNom());
        showAll();
    }
    @FXML
    public void deleteArticle(){
        menuApp.deleteContact(article);
    }
}
