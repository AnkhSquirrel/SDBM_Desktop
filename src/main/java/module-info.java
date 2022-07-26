module fr.kyo.sdbm_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens fr.kyo.sdbm_gui to javafx.fxml;
    exports fr.kyo.sdbm_gui;

    opens fr.kyo.sdbm_gui.dao to javafx.fxml;
    exports fr.kyo.sdbm_gui.dao;

    opens fr.kyo.sdbm_gui.metier to javafx.fxml;
    exports fr.kyo.sdbm_gui.metier;
}