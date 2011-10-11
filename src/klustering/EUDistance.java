/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
import java.lang.Math;
/**
 *
 * @author npalmer4
 */
public class EUDistance implements Distance {

    @Override
    public Double getDistance(Point A, Point B) {
        Double squareSum = Math.pow(A.x-B.x,2)+Math.pow(A.y-B.y,2);
        return( Math.pow(squareSum, 0.5) );
    }
}
