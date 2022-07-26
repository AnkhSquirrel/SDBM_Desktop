module fr.kyo.sdbm_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.kyo.sdbm_gui to javafx.fxml;
    exports fr.kyo.sdbm_gui;
}