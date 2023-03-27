package Naloga.Booking.Database;


import java.io.*;
import java.util.ArrayList;


public class Database {


    String filePath = new File("").getAbsolutePath();
    String db = filePath + "\\Booking\\src\\main\\java\\Naloga\\Booking\\Database\\Data.txt";


    public Database() {
    }

    public void insert(Field fieldToInsert){
        try {
            BufferedReader br = new BufferedReader(new FileReader(db));
            FileWriter fw = new FileWriter(db, true);

            String word;
            boolean exists = false;
            while((word = br.readLine()) != null){
                if(word.contains(fieldToInsert.getId())){
                    exists = true;
                    System.out.println("Field already exists!");
                    break;
                }
            }
            if(!exists) {
                fw.write(fieldToInsert.serialize());
            }

            fw.close();
            br.close();
        }catch (IOException e){
            System.out.println(e);
        }


    }

    public Field get(String id){
        String field = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.db));
            String cur;
            while((cur = br.readLine()) != null){
                if(cur.contains(id)){
                    field = cur;
                    break;
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }

        String[] fields = field.split(",");

        return new Field(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]));
    }

    public ArrayList<Field> getAll(){
        ArrayList<Field> flights = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.db));
            String flight;

            while ((flight = br.readLine()) != null){
                String[] values = flight.split(",");
                Field f = new Field(values[0], values[1], values[2], values[3], Integer.parseInt(values[4]));
                flights.add(f);
                System.out.println(f);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return flights;
    }

    public boolean setNumOfAvailable(String id, int numberOfSeats){
        boolean finished = false;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.db));
            ArrayList<String> lines = new ArrayList<>();

            String cur;

            while((cur = br.readLine()) != null){
                lines.add(cur);
            }

            br.close();

            FileWriter fw = new FileWriter(this.db);
            BufferedWriter bw = new BufferedWriter(fw);

            int i = 0;
            while(i != lines.size()){
                if(lines.get(i).contains(id)){
                    String[] curVals = lines.get(i).split(",");
                    if(Integer.parseInt(curVals[4]) >= numberOfSeats){
                        int newVal = (Integer.parseInt(curVals[4]) - numberOfSeats);
                        Field a = new Field(curVals[0], curVals[1], curVals[2], curVals[3], newVal);
                        bw.write(a.serialize());
                        finished = true;
                    }else{
                        //TODO
                        //handle display message on client

                        bw.write(lines.get(i) + '\n');
                    }
                }else{
                    bw.write(lines.get(i) + '\n');
                }
                i++;
            }

            bw.flush();
            fw.close();
            bw.close();

        }catch (IOException e){
            System.out.println(e);
        }

        return finished;
    }
}
