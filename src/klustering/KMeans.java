/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 *
 * @author npalmer4
 */
public class KMeans {
    ArrayList<Cluster> c;
    Double energy;
    Double epsilon;
//    public void KMeans(LinkedList <Points> data, Integer K) {
    public void init(LinkedList <Point> data, Integer K) {
        // create K clusters
        // for points in data:
        //   - add point to random cluster
    }
    
    public Double iteration() {
        energy = 0.0;
        /*
         * for every cluster:
         *       reassign points
         * 
         * for every cluster:
         *       calculate Centroid
         *
         * for every cluster:
         *       energy += getEnergy();
         */
        return(energy);
    }
    
    
}
