module op.oceanpribor.myping {
    requires javafx.controls;
    requires javafx.fxml;

    exports op.oceanpribor.myping.views;
    opens op.oceanpribor.myping.views to javafx.fxml;
}