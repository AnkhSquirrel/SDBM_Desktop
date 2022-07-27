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
import org.controlsfx.control.RangeSlider;


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
    private RangeSlider rangeSliderTitrage;
    @FXML
    private ComboBox<Couleur> couleurSearch;
    @FXML
    private ComboBox<Type> typeSearch;
    @FXML
    private ComboBox<Marque> marqueSearch;

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
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        paysSearch.valueProperty().addListener(observable -> filterArticle());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.valueProperty().addListener(observable -> filterArticle());

        volumeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredVolume()));
        volumeSearch.valueProperty().addListener(observable -> filterArticle());

        rangeSliderTitrage.setLowValue(0.5);
        rangeSliderTitrage.setHighValue(26);
        rangeSliderTitrage.lowValueProperty().addListener(observable -> filterArticle());
        rangeSliderTitrage.highValueProperty().addListener(observable -> filterArticle());

        couleurSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredCouleur()));
        couleurSearch.valueProperty().addListener(observable -> filterArticle());

        marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredMarque()));
        marqueSearch.valueProperty().addListener(observable -> filterArticle());

        typeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredType()));
        typeSearch.valueProperty().addListener(observable -> filterArticle());

        articleTable.getSelectionModel().selectedItemProperty().addListener(observable -> showDetail());
    }

    private void showDetail() {
        menuApp.showArticleDetail(articleTable.getSelectionModel().selectedItemProperty().getValue());
    }

    @FXML
    private void reset() {
        continentSearch.getSelectionModel().selectFirst();
        paysSearch.getSelectionModel().selectFirst();
        fabricantSearch.getSelectionModel().selectFirst();
        volumeSearch.getSelectionModel().selectFirst();
        couleurSearch.getSelectionModel().selectFirst();
        marqueSearch.getSelectionModel().selectFirst();
        typeSearch.getSelectionModel().selectFirst();
        rangeSliderTitrage.setLowValue(0.5);
        rangeSliderTitrage.setHighValue(26);
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
        if (volumeSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setVolume(volumeSearch.getSelectionModel().getSelectedItem());
        articleSearch.setTitrageMin((float) rangeSliderTitrage.getLowValue());
        articleSearch.setTitrageMax((float) rangeSliderTitrage.getHighValue());
        if (couleurSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setCouleur(couleurSearch.getSelectionModel().getSelectedItem());
        if (typeSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setType(typeSearch.getSelectionModel().getSelectedItem());
        if (marqueSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setMarque(marqueSearch.getSelectionModel().getSelectedItem());
        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles(articleSearch)));
    }
}
