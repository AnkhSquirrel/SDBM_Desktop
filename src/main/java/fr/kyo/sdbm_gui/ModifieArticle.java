package fr.kyo.sdbm_gui;

import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.*;
import fr.kyo.sdbm_gui.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Boolean create ;
    private Article article;
    private GestionArticleController gestionArticleController;
    private Stage modal;


    @FXML
    public void createArticle(){
        try {
            article.setLibelle(textFieldNom.getText());
            article.setPrixAchat((Float.valueOf(textFieldPrix.getText())));
            article.setVolume(comboBoxVolume.getSelectionModel().getSelectedItem().getVolume());
            article.setTitrage((Float.valueOf(textFieldTitrage.getText())));

            article.setMarque(comboBoxMarque.getSelectionModel().getSelectedItem());


            int id = DaoFactory.getFabricantDAO().getByMarque(article.getMarque().getId()).getId();
            article.getMarque().setFabricant(DaoFactory.getFabricantDAO().getByMarque(id));

            article.getMarque().getFabricant().setId(id);

            String lib = DaoFactory.getFabricantDAO().getByMarque(article.getMarque().getId()).getLibelle();
            article.getMarque().getFabricant().setLibelle(lib);



            article.setCouleur(comboBoxCouleur.getSelectionModel().getSelectedItem());
            article.setType(comboBoxType.getSelectionModel().getSelectedItem());
            article.setStock((Integer.parseInt(textFieldStock.getText())));

            if (!( create? DaoFactory.getArticleDAO().insert(article) : DaoFactory.getArticleDAO().update(article) )) {
                Alert alertErrorInsert = new Alert(Alert.AlertType.ERROR);
                alertErrorInsert.setTitle("Erreur");
                alertErrorInsert.setHeaderText("Erreur! Problème lors de l'insertion.");
                alertErrorInsert.showAndWait().ifPresent(btnTypeError -> {
                    if (btnTypeError == ButtonType.OK) {
                        alertErrorInsert.close();
                    }
                });

            } else {
                close();
            }

        } catch (RuntimeException e) {
            Alert alertErrorInput = new Alert(Alert.AlertType.ERROR);
            alertErrorInput.setTitle("Erreur");
            alertErrorInput.setHeaderText("Erreur! Mauvaise donnée");
            alertErrorInput.showAndWait().ifPresent(btnTypeError -> {
                if (btnTypeError == ButtonType.OK) {
                    alertErrorInput.close();
                }
            });
        }


    }

    @FXML
    public void close(){
        gestionArticleController.filterArticle();
        modal.close();
    }

    public void initialize(){
        ServiceArticle serviceArticle = new ServiceArticle();
        comboBoxVolume.setItems(FXCollections.observableArrayList(DaoFactory.getArticleDAO().getVolume()));
        comboBoxMarque.setItems(FXCollections.observableArrayList(DaoFactory.getMarqueDAO().getAll()));
        comboBoxType.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredType()));
        comboBoxCouleur.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredCouleur()));
        comboBoxVolume.getSelectionModel().select(new Volume(0,"Choisir un Volume"));
        comboBoxMarque.getSelectionModel().select(new Marque(0,"Choisir une Marque"));
        comboBoxType.getSelectionModel().selectFirst();
        comboBoxCouleur.getSelectionModel().selectFirst();
    }


    public void setArticle(Article article){
        this.article = article;
        if (article.getId() == 0){
            create = true;
        } else {
            textFieldNom.setText(article.getLibelle());
            textFieldPrix.setText(String.valueOf(article.getPrixAchat()));
            comboBoxVolume.getSelectionModel().select(new Volume(article.getVolume(), String.valueOf(article.getVolume())));
            textFieldTitrage.setText(String.valueOf(article.getTitrage()));
            comboBoxMarque.getSelectionModel().select(article.getMarque());
            if(article.getCouleur().getId() != 0)
                comboBoxCouleur.getSelectionModel().select(article.getCouleur());
            if(article.getType().getId() != 0)
                comboBoxType.getSelectionModel().select(article.getType());
            textFieldStock.setText(String.valueOf(article.getStock()));
            create = false;
        }
    }


    public void setGestionArticleController(GestionArticleController gestionArticleController) {
        this.gestionArticleController = gestionArticleController;
    }

    public void setModal(Stage modal) {
        this.modal = modal;
    }
}
