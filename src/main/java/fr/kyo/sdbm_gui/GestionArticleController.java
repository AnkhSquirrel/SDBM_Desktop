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
import org.controlsfx.control.SearchableComboBox;


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
    private TableColumn<Article, Float> titrageColumn;
    @FXML
    private TableColumn<Article, String> prixAchatColumn;


    // description des champs de recherche
    @FXML
    private TextField libelleSearch;
    @FXML
    private ComboBox<Fabricant> fabricantSearch;
    @FXML
    private SearchableComboBox<Pays> paysSearch;
    @FXML
    private ComboBox<Continent> continentSearch;
    @FXML
    private ComboBox<Volume> volumeSearch;
    @FXML
    private RangeSlider rangeSliderTitrage;
    @FXML
    private ComboBox<Couleur> couleurSearch;
    @FXML
    private ComboBox<Type> typeSearch;
    @FXML
    private SearchableComboBox<Marque> marqueSearch;

    @FXML
    private MenuApp menuApp;

    private ServiceArticle serviceArticle;
    private  ArticleSearch articleSearch;

    private int pages;

    // Initialisation de la vue
    @FXML
    private void initialize() {
        articleSearch = new ArticleSearch();
        pages = 1;
        serviceArticle = new ServiceArticle();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        titrageColumn.setCellValueFactory(cellData -> cellData.getValue().titrageProperty().asObject());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asString());
        prixAchatColumn.setCellValueFactory(cellData -> cellData.getValue().prixAchatProperty().asString());

        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceArticle.getContinentFiltre()));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        paysSearch.getSelectionModel().selectedItemProperty().addListener(observable -> filterGlobal
                ());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.valueProperty().addListener(observable -> filterFabricant());

        volumeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredVolume()));
        volumeSearch.valueProperty().addListener(observable -> filterGlobal());

        rangeSliderTitrage.setLowValue(0.5);
        rangeSliderTitrage.setHighValue(26);
        rangeSliderTitrage.lowValueProperty().addListener(observable -> filterGlobal());
        rangeSliderTitrage.highValueProperty().addListener(observable -> filterGlobal());

        couleurSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredCouleur()));
        couleurSearch.valueProperty().addListener(observable -> filterGlobal());

        marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredMarque()));
        marqueSearch.getSelectionModel().selectedItemProperty().addListener(observable -> filterGlobal());

        typeSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredType()));
        typeSearch.valueProperty().addListener(observable -> filterGlobal());

        articleTable.getSelectionModel().selectedItemProperty().addListener(observable -> showDetail());
    }

    private void showDetail() {
        if(!articleTable.getSelectionModel().isEmpty())
            menuApp.showArticleDetail(articleTable.getSelectionModel().selectedItemProperty().getValue());
    }
    @FXML
    private void createArticle(){
        Article article = new Article();
        article.setId(0);
        menuApp.openModalArticle(article);
    }

    @FXML
    private void reset() {
        libelleSearch.setText("");
        continentSearch.getSelectionModel().selectFirst();
        paysSearch.getSelectionModel().selectFirst();
        fabricantSearch.getSelectionModel().selectFirst();
        volumeSearch.getSelectionModel().selectFirst();
        couleurSearch.getSelectionModel().selectFirst();
        marqueSearch.getSelectionModel().selectFirst();
        typeSearch.getSelectionModel().selectFirst();
        rangeSliderTitrage.setLowValue(0.5);
        rangeSliderTitrage.setHighValue(26);
        filterGlobal();
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        filterArticle();
    }


    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList((continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        }
        paysSearch.getSelectionModel().select(0);
        filterGlobal();
    }

    private void filterFabricant() {
        if (fabricantSearch.getSelectionModel().getSelectedItem() != null
                && (fabricantSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            marqueSearch.setItems(FXCollections.observableArrayList((fabricantSearch.getSelectionModel().getSelectedItem()).getMarques()));

        } else {
            marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredMarque()));
        }
        marqueSearch.getSelectionModel().select(0);
        filterGlobal();
    }

    private void filterGlobal(){
        pages = 1;
        filterArticle();
    }
    @FXML
    public void filterArticle() {
        if((articleSearch.getLibelle() != null && !articleSearch.getLibelle().equals(libelleSearch.getText()) ) || articleSearch.getLibelle() == null && !libelleSearch.getText().isEmpty()){
            articleSearch.setLibelle(libelleSearch.getText());
            pages = 1;
        }
        if (paysSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getPays() != paysSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        if (continentSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getContinent() != continentSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        if (fabricantSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getFabricant() != fabricantSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setFabricant(fabricantSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        if (volumeSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getVolume() != volumeSearch.getSelectionModel().getSelectedItem().getVolume()){
            articleSearch.setVolume(volumeSearch.getSelectionModel().getSelectedItem().getVolume());
            pages = 1;
        }
        if(articleSearch.getTitrageMin() != rangeSliderTitrage.getLowValue() || articleSearch.getTitrageMax() != rangeSliderTitrage.getHighValue()){
            articleSearch.setTitrageMin((float) rangeSliderTitrage.getLowValue());
            articleSearch.setTitrageMax((float) rangeSliderTitrage.getHighValue());
            pages = 1;
        }
        if (couleurSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getCouleur() != couleurSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setCouleur(couleurSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        if (typeSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getType() != typeSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setType(typeSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        if (marqueSearch.getSelectionModel().getSelectedItem() != null && articleSearch.getMarque() != marqueSearch.getSelectionModel().getSelectedItem()){
            articleSearch.setMarque(marqueSearch.getSelectionModel().getSelectedItem());
            pages = 1;
        }
        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles(articleSearch, pages)));
    }

    @FXML
    public void pagePlus(){
        pages++;
        filterArticle();
        if(articleTable.getItems().isEmpty()){
           pageMoin();
        }
    }
    @FXML
    public void pageMoin(){
        if(pages > 1){
            pages--;
            filterArticle();
        }
    }

}
