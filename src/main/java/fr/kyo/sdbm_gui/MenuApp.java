package fr.kyo.sdbm_gui;


import fr.kyo.sdbm_gui.dao.ArticleDAO;
import fr.kyo.sdbm_gui.dao.DaoFactory;
import fr.kyo.sdbm_gui.metier.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {
    private Stage primaryStage;
    GestionDetailArticle gestionDetailArticle;
    GestionArticleController gestionArticleController;
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion des Marques");
        showMarque();
    }
    private void showMarque() {
        try {
            // Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionArticle.fxml"));
            AnchorPane menuLayout = loader.load();

            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(MenuApp.class.getResource("DetailsArticle.fxml"));
            AnchorPane detailLayout = loader1.load();

            BorderPane borderPane = new BorderPane();
            borderPane.setLeft(menuLayout);
            borderPane.setRight(detailLayout);

            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);

            gestionDetailArticle = loader1.getController();
            gestionDetailArticle.setMenuApp(this);

            gestionArticleController = loader.getController();
            gestionArticleController.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showArticleDetail(Article article){
        gestionDetailArticle.showDetail(article);
    }

    public void openModalArticle(Article article) {
        Stage modal = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuApp.class.getResource("editArticle.fxml"));
            AnchorPane modalPane = fxmlLoader.load();
            ModifieArticle modifieArticle = fxmlLoader.getController();

            modifieArticle.setArticle(article);
            modifieArticle.setGestionArticleController(gestionArticleController);
            modifieArticle.setModal(modal);

            modal.setScene(new Scene(modalPane));
            modal.setResizable(false);
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initOwner(primaryStage);

            modal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer");
        alert.setHeaderText("Voulez vous vraiment supprimer l'article?");
        alert.setContentText(article.getLibelle() + " " + article.getVolume());
        alert.showAndWait().ifPresent(btnType -> {
            if (btnType == ButtonType.OK) {
                DaoFactory.getArticleDAO().delete(article);
                gestionArticleController.filterArticle();
            }
        });

    }
}
