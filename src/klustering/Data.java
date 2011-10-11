/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
/**
 *
 * @author npalmer4
 */
public class Data {

    ArrayList <Point> points;
    
    public void getData(String pathname) throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(new FileReader("yourfile.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + nextLine[1] + "etc...");
        }
    }
    
}
