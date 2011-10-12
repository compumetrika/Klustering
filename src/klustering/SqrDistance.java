/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
/**
 *
 * @author npalmer4
 */
public class SqrDistance implements Distance {

    @Override
    public Double getDistance(Point A, Point B) {
        return( Math.pow(A.x - B.x,2) + Math.pow(A.y - B.y,2) );
    }
}
