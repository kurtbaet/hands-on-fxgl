open module com.baetscher.handsonfxgl {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires kotlin.stdlib;

//    opens com.baetscher.handsonfxgl to javafx.fxml;
    exports com.baetscher.handsonfxgl;
}
