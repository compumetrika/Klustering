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
    Point centroid = new Point();
    Double entropy = 0.0;
    LinkedList <Point> points = new LinkedList <Point>();
   

    Cluster() {
        centroid.setZero();
        entropy = Double.MAX_VALUE;
    }
        
    Cluster(Point newCentroid) {
        centroid = newCentroid;
    }
       
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

    public void setMaxEntropy() {
        entropy = Double.MAX_VALUE;
    }
    
    public Point getCenteroid(){
        return(centroid);
    }
    
    
    public void addPoint(Point newpoint) {
        //newpoint.print();
        points.add(newpoint);
    }

    public void addPointAndUpdate(Point newpoint) {
        //newpoint.print();
        points.add(newpoint);
        this.update();
    }
    
    
    public void removePoint(Point oldpoint) {
        points.remove(oldpoint);
    }
    
    public void removePointAndUpdate(Point oldpoint) {
        points.remove(oldpoint);
        this.update();
    }    
    
    public void removeAll() {            
        points.clear();
    }        

    public void removeAllAndUpdate() {
        points.clear();
        this.update();
        centroid.setZero();
    }        
    
    
    public void update(){
        this.calculateCentroid();
        this.getEntropy();
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
 

    public void print(){
        
        System.out.println("\nCluster number: "+ ID);
        System.out.println("Centeroid is: ");
        centroid.print();
        System.out.println("Number of elements in cluster = " + points.size());
        System.out.println("Total distance to center = "+ entropy +"\n");
    }

    public void printAll(){
        
        System.out.println("\nCluster number: "+ ID);
        System.out.println("Centeroid is: ");
        centroid.print();
        System.out.println("Number of elements in cluster = " + points.size());
        System.out.println("Total distance to center = "+ entropy +"\n");
        for (Point point : points) {
            point.print();
        }
    }
    
    
}
