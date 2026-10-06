module org.sysimc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports org.sysimc;
    exports org.sysimc.controller;
    exports org.sysimc.model;
    exports org.sysimc.utils;

    opens org.sysimc to javafx.fxml;
    opens org.sysimc.controller to javafx.fxml;
}
