package fr.kyo.sdbm_gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {
    private Stage primaryStage;

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
            AnchorPane menuLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            GestionMarqueController controller = loader.getController();
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
