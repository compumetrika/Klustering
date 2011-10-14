/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klustering;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/** Things to do:
 * 
 * 
 * 
 *
 * @author npalmer4
 */
public class KMeans {
    ArrayList<Cluster> clusters = new ArrayList<Cluster>();
    Double entropy;
    ArrayList<Double> entropyHistory = new ArrayList<Double>();
    ArrayList<Double> entropyChangeHistory = new ArrayList<Double>();
    Double epsilon = 0.0001;
    Integer stoppingConditionLookback = 10;
    Integer K, maxIterations;
    Data data = new Data();
    ArrayList <Integer> shuffledIndeces = new ArrayList <Integer>();
    Random random = new Random();

    
    KMeans(Data newData, Integer newK) {
        data = newData;
        K = newK;
        // This will be how I "shuffle" the datapoints without actually shuffling 
        // the data object. Not that it matters enormously, I suppose...
        for (Integer i=0; i<data.points.size(); i++) {
           shuffledIndeces.add(i);
        }
        //Collections.shuffle(shuffledIndeces);
        
        maxIterations = 5000;

    }

    KMeans(Data newData, Integer newK, Integer newMaxIter) {
        data = newData;
        K = newK;
        // This will be how I "shuffle" the datapoints without actually shuffling 
        // the data object. Not that it matters enormously, I suppose...
        for (Integer i=0; i<data.points.size(); i++) {
           shuffledIndeces.add(i);
        }
        //Collections.shuffle(shuffledIndeces);
        
        maxIterations = newMaxIter;

    }
    
    
    KMeans(Data newData, Integer newK, Long seed) {
        data = newData;
        K = newK;
        random.setSeed(seed);
        maxIterations = 5000;
        // This will be how I "shuffle" the datapoints without actually shuffling 
        // the data object. Not that it matters enormously, I suppose...
        for (Integer i=0; i<data.points.size(); i++) {
           shuffledIndeces.add(i);
        }
        //Collections.shuffle(shuffledIndeces);
    }

    KMeans(Data newData, Integer newK, Integer newMaxIter, Long seed) {
        data = newData;
        K = newK;
        random.setSeed(seed);
        maxIterations = newMaxIter;
        // This will be how I "shuffle" the datapoints without actually shuffling 
        // the data object. Not that it matters enormously, I suppose...
        for (Integer i=0; i<data.points.size(); i++) {
           shuffledIndeces.add(i);
        }
        //Collections.shuffle(shuffledIndeces);
    }

    
    public void setMaxIterations(Integer newmax) {
        maxIterations = newmax;
    }    
    
    public void init() {

        Point newCenterPoint, newPoint;
        Integer clusterSize = 0;
        Cluster newCluster;
        
        System.out.println("The number of clusters, K, will be: "+ K);


        entropy = 0.0;
        clusterSize = (int)Math.floor(data.points.size() / K);
        // we want to loop for K-1 clusters, and then in the last cluster, 
        // simply add until there are no more points.

        Integer dataIndex = 0;    
        
        // ----------- trying new thing ------------
        // Choose random centers first:
        for (int i = 0; i < K; i++) {
            newCenterPoint = new Point();
            newCenterPoint.x = random.nextDouble()*(data.maxPoint.x - data.minPoint.x) + data.minPoint.x;
            newCenterPoint.y = random.nextDouble()*(data.maxPoint.y - data.minPoint.y) + data.minPoint.y;

            newCluster = new Cluster();
            newCluster.ID = i;
            newCenterPoint.clusterID = i;
            newCluster.setCenteroid(newCenterPoint);
            newCluster.setMaxEntropy();
            clusters.add(newCluster);
            entropy += newCluster.getEntropy();

        }
        entropyHistory.add(entropy);
 
        // assign all points to the first cluster for the first pass:
        for(Point point : data.points) {
            point.clusterID = 0;
            clusters.get(0).addPoint(point);
        }
        
        // Now loop over all points and add them to the closest cluster.
        this.reassignPoints();
        this.updateClusters();

        for (Cluster cluster : clusters) {
            cluster.print();
        }

    }
    
//    public void initAlternate() {
//        // AH, this is possibly trouble, because all cluster points could end 
//        // up in the center, right? Yeah... tricky. Hmmm. Think about.
//        Point newCenterPoint;
//        
//        // Need to decide how many points go into each cluster. 
//        // One way to do this: 
//        Integer clusterSize = 0;
//        
//        clusterSize = (int)Math.floor(data.points.size() / K);
//        
//        
//        for (int i = 0; i < (K-1); i++) {
//            // we want to loop for K-1 clusters, and then in the last cluster, 
//            // simply add until there are no more points. 
//            
//        }
//
//    }    
    
    
    public void setData(Data newData) {
        data = newData;
    }
    
    public void createClusters() {
        //data = newData;
    }
    
    public void reassignPoints() {
        // For each point, find distance from that point to centeroids of each
        // cluster. Assign each point to the closest centeroid. Calculate entropy of system.
        
        // First shuffle indeces
        //Collections.shuffle(shuffledIndeces);
        
        int newClusterID;
        Point thisPoint;
        Double distance, compareDistance;
        EUDistance distanceFinder = new EUDistance();
                
        for (int i=0; i<data.points.size(); i++) {
            
            // select a random point
            thisPoint = data.points.get(shuffledIndeces.get(i));

            newClusterID = this.getClosestCluster(thisPoint);
            
            // Now remove the point from one cluster and add it to the "correct" cluster
            clusters.get(thisPoint.clusterID).removePoint(thisPoint);
            thisPoint.clusterID = newClusterID;
            clusters.get(newClusterID).addPoint(thisPoint);            
            
            
        }
        
    }

    
    public Integer getClosestCluster(Point point) {
        Double minDistance = Double.MAX_VALUE;
        Double newDistance;
        
        Integer newClusterID = 0;
        EUDistance distanceFinder = new EUDistance();

        minDistance = distanceFinder.getDistance(point, clusters.get(newClusterID).centroid);

        for(int i = 1; i < clusters.size(); i++) {
            newDistance = distanceFinder.getDistance(point, clusters.get(i).centroid);

            if (newDistance < minDistance) {
                newClusterID = i;
                minDistance = newDistance;
            }
        }
        
        return newClusterID;
    }
    
    public void clearClusters() {
        for(Cluster cluster : clusters) {
            cluster.points.clear();
        }
    }    
    
    public void updateClusters() {
        for(Cluster cluster : clusters) {
            cluster.update();
        }
    }
    
    public void findCentroids() {
        for(int i=0; i<K; i++) {
            clusters.get(i).calculateCentroid();
        }
    }
    
    public Double updateEntropy() {
        // Update entropies.
        entropy = 0.0;
        for(int i=1; i<K; i++) {
            entropy += clusters.get(i).getEntropy();
        }

        int lastIndex = entropyHistory.size()-1;
        Double difference =  Math.abs(entropyHistory.get(lastIndex) - entropy);
        entropyChangeHistory.add(difference);
        entropyHistory.add(entropy);
        return(entropy);
    }
    
    public void runClustering() {
        /**So need to *now* have a test to see when to stop.
         *Tests:
         *(1) if hit "max iterations"
         *(2) if the max of abs values of change in entropy for past X iterations 
         *    is less than some epsilon.
         * 
        */
        int iterationCounter = 0;
        int lastIndex;
        int lookBack = 1;

        Double maxChange = epsilon; 
        Double oldMaxChange = maxChange + epsilon*4 + 0.001; // just in case someone sets epsilon to zero.
        boolean doneIterating = false;
        while ((iterationCounter < maxIterations) & !doneIterating) {

            this.clearClusters();
            this.reassignPoints();
            this.updateClusters();
//            this.findCentroids();
            this.updateEntropy();
            
            
            if(entropyHistory.size() > this.stoppingConditionLookback) {
                // Then we look back and calculate the maximum change over the last X periods
                lastIndex = entropyChangeHistory.size()-1;
                maxChange = entropyChangeHistory.get(lastIndex);
                
                for(int i=1; i<stoppingConditionLookback; i++) {
                    lastIndex -= 1; 
                    maxChange = Math.max(maxChange, entropyChangeHistory.get(lastIndex));
                }
            }
            doneIterating = Math.abs(maxChange - oldMaxChange) <= epsilon;
            
            System.out.println("maxChange = "+ maxChange);
            if(entropyHistory.size() > this.stoppingConditionLookback)
                oldMaxChange = maxChange;
            
            iterationCounter += 1;
        }
        
        
        System.out.println("Reached the end of clustering algorithm; iterationCounter = "+ iterationCounter); 
   
        
        
    // Now just need to write output...
    }
    
    public void writeOutput(String filename) throws IOException {
        // Write header file to the file:
        String[] lineToWrite = new String[5];
        CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');

        // first write the header:
        lineToWrite[0] = "x";
        lineToWrite[1] = "y";
        lineToWrite[2] = "cluster";
        lineToWrite[3] = "entropy";
        lineToWrite[4] = "isCentroid";
        writer.writeNext(lineToWrite);
        
        // Now loop over ... well, actually, could simply loop over each point 
        // and write it. No need to sort through each cluster...
        // UNLESS, of course, wanted to make sure that clusters wasn't double-
        // adding. BUT, testing should take care of that. 
        System.out.println("Writing output to "+ filename);
        for (Point point : data.points) {
            //point.print();
            lineToWrite[0] = point.x.toString();
            lineToWrite[1] = point.y.toString();
            lineToWrite[2] = point.clusterID.toString();
            lineToWrite[3] = clusters.get(point.clusterID).entropy.toString();
            lineToWrite[4] = "0"; // "no, these are not centroid points..."
            writer.writeNext(lineToWrite);
        }
        
        for (Cluster cluster : clusters) {
            lineToWrite[0] = cluster.centroid.x.toString();
            lineToWrite[1] = cluster.centroid.y.toString();
            lineToWrite[2] = cluster.ID.toString();
            lineToWrite[3] = cluster.entropy.toString();
            lineToWrite[4] = "1"; // "yes, these are centroid points..."
            writer.writeNext(lineToWrite);
        }
        
        // Close file. 
        writer.close();
    }
}
