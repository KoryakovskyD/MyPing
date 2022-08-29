package op.oceanpribor.myping.models;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingResult extends Stage {
        private static boolean reachable;
        public PingResult(String orderName) {
            init();
        }

        private void init() {
            HBox root = new HBox();
            TextArea textArea = new TextArea();
            reachable = true;
            PingFunc("10.4.6.55");
            if (reachable == true)
                textArea.setText("10.4.6.55 OK");
            else
                textArea.setText("10.4.6.55 UNAVAILABLE");


            root.getChildren().add(textArea);

            Scene scene = new Scene(root, 300, 250);

            setTitle("Ping order:");
            setScene(scene);
            show();
        }

    public static void PingFunc(String ip) {
                 try {
                        InetAddress address = InetAddress.getByName(ip);
                        try {
                                reachable = address.isReachable(1500);
                        } catch (IOException e) {
                                 e.printStackTrace();
                            }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                     e.printStackTrace();
                 }
    }

}
