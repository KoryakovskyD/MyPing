package op.oceanpribor.myping.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import op.oceanpribor.myping.controllers.Functions;


public class MyPing extends Application {

    @Override
    public void start(Stage stage) {

        Label lbl = new Label("Выберите заказ:");
        lbl.setFont(new Font(25));

        ChoiceBox choiceBox = new ChoiceBox(Functions.setOrdersList());
        choiceBox.setValue("Olymp-G");
        Button button = new Button("Пуск");
        button.setOnAction(e-> {
            new PingResult((String) choiceBox.getValue());
        });
        button.setPadding(new Insets(10,50,10,50));
        VBox root = new VBox(lbl, choiceBox, button);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene scene = new Scene(root, 250, 150);
        stage.setTitle("MyPing");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}