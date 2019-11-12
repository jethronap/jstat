package ml.clustering;

import tech.tablesaw.api.Table;

import java.util.List;

/**
 * Vanilla KMeans algorithm
 */
public class KMeans<ClusterType> {


    // constructor
    public Kmens(KMeansInput input);

    // cluster the items in the dataset
    // DataSetType, SimilarityType, CentroidInitializerType can also be
    // interface types and not generic types
    public <DataSetType, SimilarityType, CentroidInitializerType> KMeansOutput cluster(DataSetType dataSet,
                                                                               SimilarityType similarityType,
                                                                                       CentroidInitializerType initializer){

        KMeansOutput out = new KMeansOutput();

        // initialize the centroids and assign them to the clusters
        initializer.initialize(this.clusters, dataSet, this.input.k);

        // iterate over the data set
        for(int itr=0; itr<input.numIterations; ++itr){

            // say sth if show iterations is true

            // compute similarities between rows in the given dataSet
            for(row in dataSet.rows()){

                // cluster the row
            }

            // compute new centroids

            // if none of the centroids has changed much i.e
            // old_centroid - new_centroid < this.input.tol
            // algorithm converged
            out.numIterations = ???
            out.converged = ??
            out.tol = maximum difference detected

            // else we continue the iterations
            old_centroids = new_centroids

            // clear the clusters

        }

        return out;
    }

    public List<ClusterType> getClusters(){
        return this.clusters;
    }


    List<ClusterType> clusters;


    KMeansInput input;
}
