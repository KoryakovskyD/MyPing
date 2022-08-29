package op.oceanpribor.myping.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import op.oceanpribor.myping.models.Order;


import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Functions {
    public static List<Integer> ipList = new ArrayList();
    public static final List<Order> ordersList = new ArrayList<>();
    public static final ArrayList<String> nameOrders = new ArrayList<>();

    public static boolean PingFunc(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            try {
                return address.isReachable(1500);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String checkIpList(String orderName) {
        String ip,
                result="";
        ipList = createIpList(orderName);
        for (Order order : ordersList) {
            if (order.getName().equals(orderName)) {
                for (Integer curIp : ipList) {
                    ip = order.getIp().substring(0, order.getIp().length() - 1) + curIp;
                    if (Functions.PingFunc(ip))
                        result += ip + "        OK\n";
                    else
                        result += ip + "        UNAVAILABLE\n";
                }
            }
        }
        return result;
    }

    public static List<Integer> createIpList(String orderName) {
        try {
            FileReader fr = new FileReader("prj.ndb");
            BufferedReader reader = new BufferedReader(fr);
            String line,
                    lastOctetList;
            String[] tmpIpList;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                char[] charArray = line.toCharArray();
                if (charArray[0] == '#') continue;
                if (charArray[0] == '[') continue;

                char tmpChar;
                boolean flag=true;
                String name = "";
                for (int i = 0; i < charArray.length; i++) {

                    tmpChar=charArray[i];
                    if(Character.isLetter(tmpChar) || tmpChar == '_' || tmpChar == '-' || Character.isDigit(tmpChar) || flag == true)  {
                        name += tmpChar;
                        if (name.equals(orderName)) {
                            flag = false;
                            name = "";
                        }
                        continue;
                    }

                    if (charArray[i] == '{') {
                        lastOctetList = line.substring(i + 1, line.length() - 1);
                        tmpIpList = lastOctetList.split(",");

                        ipList.clear();
                        ipList.add(55);
                        for (String curIp : tmpIpList) {
                            ipList.add(Integer.valueOf(curIp));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ipList;
    }

    public static ObservableList<String> setOrdersList() {
        try {
            String key=null,
                    value = null;
            FileReader fr = new FileReader("prj.ndb");
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
                ordersList.add(new Order(key,value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Order curOrder : ordersList) {
            nameOrders.add(curOrder.getName());
        }

        return FXCollections.observableArrayList(nameOrders);
    }
}
