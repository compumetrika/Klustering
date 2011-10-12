/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
/**
 *
 * @author npalmer4
 */
public class ManhattanDistance implements Distance {

    @Override
    public Double getDistance(Point A, Point B) {
        return( Math.abs(A.x - B.x) + Math.abs(A.y - B.y) );
    }
}
