package fr.kyo.sdbm_gui;

import fr.kyo.sdbm_gui.dao.ArticleDAO;
import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModifieArticle {
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrix;
    @FXML
    private TextField textFieldTitrage;
    @FXML
    private TextField textFieldStock;
    @FXML
    private ComboBox<Couleur> comboBoxCouleur;
    @FXML
    private ComboBox<Volume> comboBoxVolume;
    @FXML
    private ComboBox<Type> comboBoxType;
    @FXML
    private ComboBox<Marque> comboBoxMarque;

    public void createArticle(Article article){
        article.setLibelle(textFieldNom.getText());
        article.setPrixAchat((Float.valueOf(textFieldPrix.getText())));
        article.setVolume(comboBoxVolume.getSelectionModel().getSelectedItem().getVolume());
        article.setTitrage((Float.valueOf(textFieldTitrage.getText())));
        article.setMarque(comboBoxMarque.getSelectionModel().getSelectedItem());
        article.setCouleur(comboBoxCouleur.getSelectionModel().getSelectedItem());
        article.setType(comboBoxType.getSelectionModel().getSelectedItem());
        article.setStock((Integer.parseInt(textFieldStock.getText())));
        DaoFactory.getArticleDAO().insert(article);
    }


}
