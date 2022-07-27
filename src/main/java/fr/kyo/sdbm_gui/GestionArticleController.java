package fr.kyo.sdbm_gui;

import fr.kyo.sdbm_gui.metier.*;
import fr.kyo.sdbm_gui.service.ArticleSearch;
import fr.kyo.sdbm_gui.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class GestionArticleController {
    // Description de la table
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, Integer> idColumn;
    @FXML
    private TableColumn<Article, String> nomColumn;
    @FXML
    private TableColumn<Article, String> volumeColumn;
    @FXML
    private TableColumn<Article, String> titrageColumn;
    @FXML
    private TableColumn<Article, String> prixAchatColumn;


    // description des champs de recherche
    @FXML
    private TextField libelleSearch;
    @FXML
    private ComboBox<Fabricant> fabricantSearch;
    @FXML
    private ComboBox<Pays> paysSearch;
    @FXML
    private ComboBox<Continent> continentSearch;
    @FXML
    private ComboBox<Integer> volumeSearch;
    @FXML
    private MenuApp menuApp;

    private ServiceArticle serviceArticle;

    // Initialisation de la vue
    @FXML
    private void initialize() {
        serviceArticle = new ServiceArticle();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        titrageColumn.setCellValueFactory(cellData -> cellData.getValue().titrageProperty().asString());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asString());
        prixAchatColumn.setCellValueFactory(cellData -> cellData.getValue().prixAchatProperty().asString());

        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceArticle.getContinentFiltre()));
        continentSearch.getItems().add(0,new Continent(0, "Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        paysSearch.valueProperty().addListener(observable -> filterArticle());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.valueProperty().addListener(observable -> filterArticle());

        volumeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredVolume()));
        volumeSearch.valueProperty().addListener(observable -> filterArticle());

    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        filterArticle();
    }


    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays("", "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);
        filterArticle();
    }

    @FXML
    private void filterArticle() {
        ArticleSearch articleSearch = new ArticleSearch();
        articleSearch.setLibelle(libelleSearch.getText());

        if (paysSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());
        if (continentSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
        if (fabricantSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setFabricant(fabricantSearch.getSelectionModel().getSelectedItem());

        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles()));
    }
}
