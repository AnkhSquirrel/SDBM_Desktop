package fr.kyo.sdbm_gui;

import fr.kyo.sdbm_gui.metier.Continent;
import fr.kyo.sdbm_gui.metier.Fabricant;
import fr.kyo.sdbm_gui.metier.Marque;
import fr.kyo.sdbm_gui.metier.Pays;
import fr.kyo.sdbm_gui.service.ServiceMarque;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class GestionMarqueController {
    // Description de la table
    @FXML
    private TableView<Marque> articleTable;
    @FXML
    private TableColumn<Marque, Integer> idColumn;
    @FXML
    private TableColumn<Marque, String> nomColumn;


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

    private MenuApp menuApp;

    private ServiceMarque serviceMarque;

    // Initialisation de la vue
    @FXML
    private void initialize() {
        serviceMarque = new ServiceMarque();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());

        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceMarque.getContinentFiltre()));
        continentSearch.getItems().add(0,new Continent(0, "Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceMarque.getPaysFiltre()));
        paysSearch.valueProperty().addListener(observable -> filterMarque());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceMarque.getFabricantFiltre()));
        fabricantSearch.valueProperty().addListener(observable -> filterMarque());

    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        filterMarque();
    }


    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceMarque.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays("", "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);
        filterMarque();
    }

    @FXML
    private void filterMarque() {
        articleTable.setItems(FXCollections.observableArrayList(serviceMarque.getFilteredArticles()));
    }
}
