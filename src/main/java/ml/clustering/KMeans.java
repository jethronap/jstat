package ml.clustering;

import utils.IterativeAlgorithmResult;
import maths.I2DDataSet;
import maths.IVector;
import maths.functions.distances.DistanceCalculator;
import maths.functions.generators.IRandomGenerator;


import java.util.List;

public class KMeans<PointType, DistanceType>{

    public class Cluster
    {
        public int id;
        public IVector<PointType> centroid;
        public List<Integer> points;

        public <DataSetTp extends I2DDataSet<IVector<PointType>>> void calculateCentorid(DataSetTp dataSet){

            for(int i=0; i<points.size(); ++i){
                //centroid.add(i, dataSet.getRow(this.points.get(i)));
            }
        }
    }

    /**
      * Constructor
     */
    public KMeans(KMeansInput input){

        this.input = input;
    }

    public <DataSetType extends I2DDataSet<IVector<PointType>>,
            SimilarityType extends DistanceCalculator<IVector<PointType>, DistanceType>,
            RandomGeneratorType extends IRandomGenerator<PointType>>
    IterativeAlgorithmResult  cluster(final DataSetType data,
                                            final SimilarityType similarity, final RandomGeneratorType centroidGenerator){

        // assign the random centroids
        List<IVector<PointType>> centroidsOld = centroidGenerator.generate(data, this.input.k);

        while(this.input.iterationContorller.continueIterations()){

            if(this.input.showIterations){
                System.out.println("KMeans iteration: " + this.input.iterationContorller.getCurrentIteration());
            }

            //for each point calculate its distance from the centroids
            for(int p=0; p<data.m(); ++p){

                this.clusterPoint(p, data.getRow(p), similarity);
            }

            //recalculate centroids
            DistanceType maxDiff = similarity.maxValue();
            for(int c=0; c<this.clusters.size(); ++c){

                this.clusters.get(c).calculateCentorid(data);

                DistanceType distance = similarity.calculate(centroidsOld.get(c), this.clusters.get(c).centroid);

                maxDiff = similarity.compareMin(distance, maxDiff);

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

    private <SimilarityType extends DistanceCalculator<IVector<PointType>, DistanceType>> void clusterPoint(int pointId, IVector<PointType> point, final SimilarityType similarity){

        DistanceType dist = similarity.maxValue();
        int clusterIdx = -1;
        for(int c=0; c<clusters.size(); ++c){

            DistanceType distance = similarity.calculate(point, clusters.get(c).centroid);

            if(similarity.compare(distance , dist) == -1){
                distance = dist;
                clusterIdx = c;
            }

        }

        this.clusters.get(clusterIdx).points.add(pointId);

    }

    KMeansInput input;
    List<Cluster> clusters;
}
