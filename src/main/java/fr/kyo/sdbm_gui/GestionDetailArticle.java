package fr.kyo.sdbm_gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GestionDetailArticle {
    @FXML
    Label labelValue;

    @FXML
    GridPane gridPane;

    public void initialize(){
        hideAll();
    }

    public void hideAll(){
        for(Node label : gridPane.getChildren()){
            label.setVisible(false);
        }
    }

    public void showAll(){

    }

}
