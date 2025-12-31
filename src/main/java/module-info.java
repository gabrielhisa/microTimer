module org.microtimer.microtimer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.microtimer.microtimer to javafx.fxml;
    exports org.microtimer.microtimer;
}