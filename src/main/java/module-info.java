module org.example.UI {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports org.example.UI;
    opens org.example.UI to javafx.fxml;
}