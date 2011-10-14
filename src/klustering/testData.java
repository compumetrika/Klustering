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
public class testData {
    
    
    public static void main(String[] args) {
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
        
        
    }
}
