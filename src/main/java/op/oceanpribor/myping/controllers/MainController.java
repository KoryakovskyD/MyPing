package op.oceanpribor.myping.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ChoiceBox choiceBox;

    public void handleButtonAction(ActionEvent actionEvent) {

    }

    public void handleChoiceBoxAction(ActionEvent actionEvent) {

        setChoiceBox();
    }

    public void setChoiceBox() {
        choiceBox.setItems(getOrdersList());
    }

    private ObservableList getOrdersList() {
        ArrayList ordersList = new ArrayList();
        ordersList.add("Olymp");
        ordersList.add("Borey-055A");
        return FXCollections.observableList(ordersList);
    }

}