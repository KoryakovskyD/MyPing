package op.oceanpribor.myping;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;

public class MyPing extends Application {
    HashMap<String,String> orderList = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {

        Label lbl = new Label("Выберите заказ:");
        lbl.setFont(new Font(25));

        ChoiceBox choiceBox = new ChoiceBox(setOrdersList());
        choiceBox.setValue("Olymp-G");
        Button button = new Button("Пуск");
        button.setPadding(new Insets(10,50,10,50));
        VBox root = new VBox(lbl, choiceBox, button);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene scene = new Scene(root, 250, 150);
        stage.setTitle("MyPing");
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<String> setOrdersList() {
        try {
            String key=null,
                    value = null;
            FileReader fr = new FileReader(new File("prj.ndb"));
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line  = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                char[] charArray = line.toCharArray();
                if (charArray[0] == '#') continue;
                if (charArray[0] == '[') continue;
                String[] words = line.split(" ");

                for (int i = 0; i <= words.length;i++) {
                    if (i==0) {
                        key=words[0];
                        continue;
                    }
                    if (words[i].isEmpty())
                        continue;
                    else {
                        value = words[i];
                        break;
                    }
                }
                orderList.put(key,value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(orderList.keySet());
    }

    public static void main(String[] args) {
        launch();
    }
}