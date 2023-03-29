package Frontend;

import Naloga.Booking.Flights.FlightController;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class GUI {
    public static void main(String[] args) {

        ArrayList<String> allId = new ArrayList<>();

        String[][] table = getItems();


        for (String[] strings : table) {
            allId.add(strings[0]);
        }

        JFrame mainWindow = new JFrame("Booking");
        mainWindow.setSize(1200,800);

        String [] a = {"ID", "Origin", "Destination", "flight Line Name", "Number of available Seats"};
        JTable tabela = new JTable(table, a);
        tabela.setBounds(0,0,800, 600);
        tabela.setVisible(true);
        JLabel dropDownLabel = new JLabel("Izberite let ter število sedežev, ki jih želite rezervirati");
        dropDownLabel.setBounds(0, tabela.getRowHeight()*table.length + 10, 300, 20);
        dropDownLabel.setVisible(true);


        mainWindow.add(dropDownLabel);

        //number only input field
        java.text.NumberFormat format = java.text.NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JComboBox<Object> dropdown= new JComboBox<>(allId.toArray());
        dropdown.setVisible(true);
        dropdown.setBounds(0,tabela.getRowHeight()*table.length + 40, 150, 30);

        JFormattedTextField input = new JFormattedTextField(formatter);
        input.setBounds(160, tabela.getRowHeight()*table.length + 40, 60, 30);

        JButton button = new JButton("Rezerviraj");
        button.setBounds(230, tabela.getRowHeight()*table.length + 40, 120, 30);
        button.setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Objects.requireNonNull(dropdown.getSelectedItem()).toString();
                System.out.println(id);
                int numOfSeats = Integer.parseInt(input.getText());
                System.out.println(numOfSeats);
                FlightController post = new FlightController();
                if(post.bookSeat(id, numOfSeats)){
                    String[][] newTable = getItems();
                    for (int i = 0; i < newTable.length; i++) {
                        for (int j = 0; j < newTable[i].length; j++) {
                            tabela.setValueAt(newTable[i][j],i,j);
                        }
                    }

                }
            }
        });
        mainWindow.add(button);

        input.setVisible(true);
        mainWindow.add(input);

        mainWindow.add(dropdown);
        mainWindow.add(tabela);

        mainWindow.setVisible(true);

    }

    public static String[][] getItems(){
        String data = "";
        try {
            //Fetch API data
            URL url = new URL("http://localhost:8080/flights");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            data = br.readLine();
            br.close();
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }

        ArrayList<String> allId = new ArrayList<>();
        JSONParser jp = new JSONParser(data);

        try {
            //Format api data
            ArrayList<Object> dataJson = jp.list();
            String[][] table = new String[dataJson.size()][];

            for (int i = 0; i < dataJson.size(); i++) {
                String obj = dataJson.get(i).toString();
                obj = obj.substring(1, obj.length() - 1);
                table[i] = obj.split(", ");
                for (int j = 0; j < table[i].length; j++) {
                    table[i][j] = table[i][j].split("=")[1];
                }
                allId.add(table[i][0]);
            }

            return table;

        }catch (ParseException e){
            System.out.println(e.getLocalizedMessage());
        }
    return null;
    }


}