module op.oceanpribor.myping {
    requires javafx.controls;
    requires javafx.fxml;


    opens op.oceanpribor.myping to javafx.fxml;
    exports op.oceanpribor.myping;
    exports op.oceanpribor.myping.controllers;
    opens op.oceanpribor.myping.controllers to javafx.fxml;
}