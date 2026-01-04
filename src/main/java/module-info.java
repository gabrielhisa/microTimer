module org.microtimer.microtimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.microtimer to javafx.fxml;
    exports org.microtimer;
}