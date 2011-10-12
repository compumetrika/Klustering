/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author npalmer4
 */
public class testKMeans {
    
    
    
    public static void main(String[] args) throws IOException {
        // This is testing the Data class.
        
        // create a data object.
        Data testData = new Data();
        try {
            testData.getData("test1.csv", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(testData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(testData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Testing the add and print statements.");
        testData.printData();
        
        // create a KMeans object
        KMeans testKluster = new KMeans(testData, 2);
        
        // run the simulation, spit out output:
        testKluster.init();
        testKluster.runClustering();
        testKluster.writeOutput("testClustering.csv");
        
    }

}
