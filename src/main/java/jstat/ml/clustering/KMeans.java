package jstat.ml.clustering;

import jstat.utils.IterativeAlgorithmResult;
import jstat.datastructs.I2DDataSet;
import jstat.datastructs.IVector;
import org.nd4j.linalg.api.ndarray.INDArray;


import java.util.List;

public class KMeans{

    public class Cluster
    {
        public int id;
        public INDArray centroid;
        public List<Integer> points;

        /**
         * Calculate the centroid of this cluster
         * @param dataSet
         */
        public void calculateCentroid(INDArray dataSet){

            for(int i=0; i<points.size(); ++i){
                //centroid.add(i, dataSet.getRow(this.points.get(i)));
            }
        }
    }

    /**
     * Constructor
     *
     * @param input The given input
     */
    public KMeans(KMeansInput input){
        this.input = input;
    }

    /**
     * Cluster the given dataset
     * @param data
     * @return
     */
    public IterativeAlgorithmResult  cluster(final INDArray data){

        // assign the random centroids
        List<INDArray> centroidsOld = this.input.randomGenerator.generate(data, this.input.k);

        while(this.input.iterationContorller.continueIterations()){

            if(this.input.showIterations){
                System.out.println("KMeans iteration: " + this.input.iterationContorller.getCurrentIteration());
            }

            //for each point calculate its distance from the centroids
            for(int p=0; p<data.m(); ++p){

                this.clusterPoint(p, data.getRow(p), this.input.distanceCalculator);
            }

            //recalculate centroids
            double maxDiff = this.input.distanceCalculator.maxValue();
            for(int c=0; c<this.clusters.size(); ++c){

                this.clusters.get(c).calculateCentroid(data);

                double distance = this.input.distanceCalculator.calculate(centroidsOld.get(c), this.clusters.get(c).centroid);

                maxDiff = this.input.distanceCalculator.compareMin(distance, maxDiff);

                //if(distance < maxDiff){
                //    maxDiff = distance;
                //}
            }

            this.input.iterationContorller.updateResidual((double) maxDiff);

            // clear the points for each cluster and assign
            // to old centroids
            for(int c=0; c<this.clusters.size(); ++c){
                this.clusters.get(c).points.clear();
                centroidsOld.set(c, this.clusters.get(c).centroid);
            }

        }

        IterativeAlgorithmResult result =  this.input.iterationContorller.getState();
        return result;
    }

    private void clusterPoint(int pointId, INDArray point){

        double dist = this.input.distanceCalculator.maxValue();
        int clusterIdx = -1;
        for(int c=0; c<clusters.size(); ++c){

            double distance = this.input.distanceCalculator.calculate(point, clusters.get(c).centroid);

            if(this.input.distanceCalculator.compare(distance , dist) == -1){
                distance = dist;
                clusterIdx = c;
            }

        }

        this.clusters.get(clusterIdx).points.add(pointId);

    }

    KMeansInput input;
    List<Cluster> clusters;
}
