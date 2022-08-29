package op.oceanpribor.myping.models;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import op.oceanpribor.myping.MyPing;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class PingResult extends Stage {
        private static boolean reachable;
        private String result="";
        public PingResult(String orderName) {
            init(orderName);
        }

        private void init(String orderName) {
            HBox root = new HBox();
            TextArea textArea = new TextArea();
            reachable = true;

            MyPing.ipList.add(172);
            MyPing.ipList.add(240);
            MyPing.ipList.add(55);
            MyPing.ipList.add(180);

            checkIpList(orderName, (ArrayList<Integer>) MyPing.ipList);

            textArea.setText(result);
            root.getChildren().add(textArea);

            Scene scene = new Scene(root, 300, 250);

            setTitle("Ping order:");
            setScene(scene);
            show();
        }

        private void PingFunc(String ip) {
                     try {
                            InetAddress address = InetAddress.getByName(ip);
                            try {
                                    reachable = address.isReachable(1500);
                            } catch (IOException e) {
                                     e.printStackTrace();
                                }
                        } catch (IOException e) {
                         e.printStackTrace();
                     }
        }


        private void checkIpList(String orderName, ArrayList<Integer> ipList) {
            String ip;
            for (Order order : MyPing.ordersList) {
                if (order.getName().equals(orderName)) {
                    for (Integer curIp : ipList) {
                        ip = order.getIp().substring(0, order.getIp().length() - 1) + curIp;
                        PingFunc(ip);
                        if (reachable == true)
                            result += ip + "     OK\n";
                        else
                            result += ip + "     UNAVAILABLE\n";
                    }
                }
            }
        }
}
