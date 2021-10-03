package ua.lviv.iot;

import java.io.*;
import java.util.Arrays;

public class BackupMain {
    public static void main(String[] args) {
        Integer foodPackagesNumber;
        Integer hamstersNumber;
        Hamster[] hamsters;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            foodPackagesNumber = Integer.parseInt(reader.readLine());
            hamstersNumber = Integer.parseInt(reader.readLine());
            hamsters = new Hamster[hamstersNumber];
            String line = reader.readLine();
            int i = 0;
            while (line != null){
                String[] daily_and_avarice = line.split(" ");
                hamsters[i++] = new Hamster(Integer.parseInt(daily_and_avarice[0]),
                        Integer.parseInt(daily_and_avarice[1]));
                line = reader.readLine();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/output.txt"));
            writer.write("We can buy " + buy_hamsters(hamsters, foodPackagesNumber) + " hamsters");

            writer.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int buy_hamsters(Hamster[] hamsters, Integer foodPackagesNumber){
        Integer total_consumption = 0;
        do {
            total_consumption = 0;
            for (int i=0; i<hamsters.length; i++)
                total_consumption += hamsters[i].count_hamster_consumption(hamsters.length-1);
            if (total_consumption > foodPackagesNumber)
                hamsters = remove_most_voracious(hamsters);
        } while (total_consumption > foodPackagesNumber);
        return hamsters.length;
    }

    public static Hamster[] remove_most_voracious(Hamster[] hamsters){
        Integer max_consumption = 0;
        for (int i = 1; i < hamsters.length; i++){
            if(hamsters[max_consumption].count_hamster_consumption(hamsters.length)
                    < hamsters[i].count_hamster_consumption(hamsters.length))
                max_consumption = i;
        }
        return remove_hamster(hamsters, max_consumption);
    }

    public static Hamster[] remove_hamster(Hamster[] hamsters, int index){
        Hamster[] temp = hamsters;
        hamsters = new Hamster[hamsters.length-1];

        for(int i=0; i<hamsters.length; i++)
            if(i < index)
                hamsters[i] = temp[i];
            else
                hamsters[i] = temp[i+1];
        return hamsters;
    }
}
