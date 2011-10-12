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

    ArrayList <Point> points = new ArrayList <Point>();
    public Point maxPoint = new Point();
    public Point minPoint = new Point();
    
    public void getData(String pathname, boolean hasHeader) throws FileNotFoundException, IOException {

        Double xnew, ynew;
        String [] nextLine;
        maxPoint.setMin();
        minPoint.setMax();
        Point newpoint = new Point();
        
        CSVReader reader = new CSVReader(new FileReader(pathname));

        if (hasHeader)
            reader.readNext(); // need to dispose of header if it exists.
        
        while ((nextLine = reader.readNext()) != null) {

            xnew = Double.parseDouble(nextLine[0]);
            ynew = Double.parseDouble(nextLine[1]);
            
            // Update the max point and min point.
            maxPoint.x = Math.max(maxPoint.x, xnew);
            maxPoint.y = Math.max(maxPoint.y, ynew);
            minPoint.x = Math.min(minPoint.x, xnew);
            minPoint.y = Math.min(minPoint.y, ynew);

            newpoint = new Point();
            newpoint.x = xnew;
            newpoint.y = ynew;
            points.add(newpoint);

            System.out.println(nextLine[0] + ", "+ nextLine[1]);
        }

        maxPoint.addEpsilon();
    }
    
    
    public void printData(){
        if (points.size() > 0) {
            for (int i = 0; i < points.size(); i++) {
                points.get(i).print();
            }
            System.out.println("The max point in the system is (with epsilon added):");
            maxPoint.print();
            System.out.println("The min point in the system is (with epsilon added):");
            minPoint.print();
        } else {
            System.out.println("No points in the data list!");
        }
    }
    
}
