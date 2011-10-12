/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
import java.util.LinkedList;
/**
 * Process:
 * Populate the cluster. This will presumably be done externally by simply looping over
 * the "addPoint" method. 
 * 
 * Calculating the center of the cluster should be simple.
 * 
 * 
 * @author npalmer4
 */
public class Cluster {
    Integer ID;
    Point centroid;
    Double entropy = 0.0;
    LinkedList <Point> points = new LinkedList <Point>();
    
    
    public void calculateCentroid(){
        Double xvalue, yvalue;
        int n = points.size();
        xvalue = 0.0;
        yvalue = 0.0;
        for (Point point : points) {
            xvalue += point.x;
            yvalue += point.y;
        }
        centroid.x = xvalue / n;
        centroid.y = yvalue / n;
    }
    
    public void setCenteroid(Point newCentroid){
        centroid = newCentroid;
    }

    public Point getCenteroid(){
        return(centroid);
    }
    
    
    public void addPoint(Point newpoint) {
        //newpoint.print();
        points.add(newpoint);
    }

    public void removePoint(Point oldpoint) {
        points.remove(oldpoint);
    }    
    
    public void Cluster (){
        
    }
    
    public double getEntropy() {
        // This is just summing up all the distances in the cluster. 
        // Distances from the current center, that is. 
        entropy = 0.0;
        
        EUDistance distanceFinder = new EUDistance();
        
        for (Point point : points) {
            entropy += distanceFinder.getDistance(point, centroid);
        }
        
        return(entropy);
    }
 

//    public void print(){
//        System.out.println("(" + x + ", " + y + ")" + " ID: " + clusterID);
//    }

    
    
}
