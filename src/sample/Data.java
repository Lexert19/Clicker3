package sample;

import java.io.*;
import java.util.Properties;

public class Data {
    public static boolean active = true;
    public static String buttonOnOff = "F2";
    public static int activeSet = 1;
    public static boolean updateSet = false;
    public static String textSet = "";
    public static Properties props = new Properties();

    public static void loadSettings() throws IOException {
        props.load(new FileInputStream("data/settings.properties"));
        String propertiesButtonOnOff = props.getProperty("buttonOnOff");
        if(propertiesButtonOnOff != null)
            buttonOnOff = propertiesButtonOnOff;
        String propertiesActiveSet = props.getProperty("activeSet");
        if(propertiesActiveSet != null)
            activeSet = Integer.parseInt(propertiesActiveSet);
        String fileName = "data/set"+activeSet+".txt";
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = file.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }
        file.close();
        textSet = buffer.toString();
        updateSet = true;
    }

    public static void changeSet(int set) throws IOException {
        activeSet = set;
        props.setProperty("activeSet", String.valueOf(set));
        props.store(new FileWriter("data/settings.properties"), "saved settings");

        String fileName = "data/set"+activeSet+".txt";
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = file.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }
        file.close();
        textSet = buffer.toString();
        updateSet = true;
    }

    public static void save() throws IOException {
        String fileName = "data/set"+activeSet+".txt";
        FileOutputStream fileOut = new FileOutputStream(fileName);
        fileOut.write(textSet.getBytes());
        fileOut.close();
    }

    public static void changeButtonOnOff(String button) throws IOException {
        buttonOnOff=button;
        props.setProperty("buttonOnOff",button);
        props.store(new FileWriter("data/settings.properties"), "saved settings");
    }
}
