/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;

/**
 *
 * @author npalmer4
 */
public class Point {
    public Double x;
    public Double y;
    public Integer clusterID = 5000;
//    public void Point(Double newx, Double newy) {
//        x = newx;
//        y = newy;
//    }
    
    public void setZero() {
        x = 0.0;
        y = 0.0;        
    }

    public void setMax() {
        x = Double.MAX_VALUE;
        y = Double.MAX_VALUE;        
    }

    public void setMin() {
        x = Double.MIN_VALUE;
        y = Double.MIN_VALUE;        
    }

    public void addEpsilon() {
        x += 0.0001;
        y += 0.0001;        
    }

    public void print(){
        System.out.println("(" + x + ", " + y + ")" + " ID: " + clusterID);
    }

}
