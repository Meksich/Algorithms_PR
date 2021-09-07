package ua.lviv.iot;

public class Main {

    public static void main(String[] args) {
//      execute with command java -classpath E:\Jaba\algo-lab1_maven\src\main\java ua.lviv.iot.Main

        String order = args[0];
        String string_array = args[1];

        Container quick_sort = new Container(order, string_array);

        quick_sort.generate_report();
    }
}
