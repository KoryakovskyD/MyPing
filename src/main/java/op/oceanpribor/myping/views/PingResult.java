package op.oceanpribor.myping.views;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import op.oceanpribor.myping.controllers.Functions;


public class PingResult extends Stage {
        public PingResult(String orderName) {
            init(orderName);
        }

        private void init(String orderName) {
            HBox root = new HBox();
            TextArea textArea = new TextArea();


            textArea.setText(Functions.checkIpList(orderName));
            root.getChildren().add(textArea);

            Scene scene = new Scene(root, 300, 250);

            setTitle("Ping order:");
            setScene(scene);
            show();
        }
}
