# Example 1: Load data from an Elasticsearch index

## Contents
* [Overview](#overview)    
* [Import files](#include_files)
* [The main function](#m_func)
* [Results](#results)
* [Source Code](#source_code)

## <a name="overview"></a> Overview

This example shows you how to load data from <a href="https://www.elastic.co/webinars/getting-started-elasticsearch?ultron=[EL]-[B]-[EMEA]-UK+Nordics-Exact&blade=adwords-s&Device=c&thor=elasticsearch&gclid=Cj0KCQiAqNPyBRCjARIsAKA-WFxWxPb0Z_VeyhPFisUxJhVPgMVFqh_M-bnljx9Rtd7dmOJHEc8O_LkaApzEEALw_wcB">Elasticsearch</a>
Thus, this example requires you to have Elasticsearch installed on your machine. 
You also need to add in the ```pom.xml``` file of the project the following
dependencies

```
        <!-- https://mvnrepository.com/artifact/org.elasticsearch/elasticsearch -->
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>6.6.1</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-high-level-client -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>6.6.1</version>
        </dependency>
```
 
Elasticsearch listens to the 9200 port for upcoming HTTP queries by default. Start the Elasticsearch server and point your
browser to http://localhost:9200/ URL. You should be able to see something like the following:

```
{
  "name" : "david-A15",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "Lvn93INcQZKL9ZqJKLI0qg",
  "version" : {
    "number" : "7.6.0",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "7f634e9f44834fbc12724506cc1da681b0c3b1e3",
    "build_date" : "2020-02-06T00:09:00.449973Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}

```

We will assume that there are no indexes in Elasticsearch. Change accordingly if this is not true.
Since there are no indices we need to create a new index and insert some documents into it.
After doing this, it's easy to retrieve the documents by issuing a ```SearchRequest```.
We will store the data into a ```MapDataSet``` however this is not compulsory.

## <a name="m_func"></a> The main function

```
package examples.es.example1;

import datasets.MapDataSet;
import datasets.VectorDouble;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.util.Map;

public class Example1 {

    public static final String INDEX =  "jstat_es_example_1";


    public static void deleteIndex(RestHighLevelClient client) throws IOException{

        DeleteIndexRequest request = new DeleteIndexRequest(Example1.INDEX);
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    protected static void index(String json, RestHighLevelClient client) throws IOException{

        IndexRequest indexRequest = new IndexRequest(Example1.INDEX)
                .type("es_exe1")
                .source(json, XContentType.JSON);

        // create the index
        IndexResponse response = client.index(indexRequest);

    }

    public static void loadElasticsearchDB(RestHighLevelClient client)throws IOException{

        String json = "{" +
                "\"Production\":4.51," +
                "\"Electricity Usage\":2.48" + "}";

        Example1.index(json, client);

        json = "{" +
                "\"Production\":3.58," +
                "\"Electricity Usage\":2.26" + "}";

        Example1.index(json, client);

        json = "{" +
                "\"Production\":4.31," +
                "\"Electricity Usage\":2.47" + "}";

        Example1.index(json, client);
    }

    public static MapDataSet<String,VectorDouble> loadDataSet(RestHighLevelClient client) throws IOException{

        MapDataSet<String, VectorDouble> data = new MapDataSet<>();

        SearchRequest request = new SearchRequest(Example1.INDEX);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // let's retrieve the documents
        SearchHits hits = response.getHits();

        SearchHit[] searchHits = hits.getHits();

        int rowCounter = 0;
        for (SearchHit hit : searchHits) {

            Map<String, Object> hitData = hit.getSourceAsMap();

            VectorDouble row = new VectorDouble(hitData.size());

            int counter=0;
            for(Map.Entry<String, Object> entry: hitData.entrySet()){
                row.set(counter++, (Double)entry.getValue());
            }

            data.add("Row"+rowCounter++, row);
        }

        return data;
    }

    public static void main(String[] args){

        // we need a client to interact with Elasticsearch
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        try {
            
            // create the index
            Example1.loadElasticsearchDB(client);

            // let's now populate the MapDataSet
            Example1.loadDataSet(client);

            // delete the index we created
            Example1.deleteIndex(client);

            client.close();
        }
        catch(IOException e){
            System.out.println("An IOException occurred");
        }
    }
}

    

```

## <a name="results"></a> Results

Upon executing the code above and depending on your configuration you may be
getting something like the following:

```
Feb 25, 2020 5:34:47 PM org.elasticsearch.client.RestClient logResponse
WARNING: request [POST http://localhost:9200/jstat_es_example_1/es_exe1?timeout=1m] returned 1 warnings: [299 Elasticsearch-7.6.0-7f634e9f44834fbc12724506cc1da681b0c3b1e3 "[types removal] Specifying types in document index requests is deprecated, use the typeless endpoints instead (/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id})."]
Feb 25, 2020 5:34:47 PM org.elasticsearch.client.RestClient logResponse
WARNING: request [POST http://localhost:9200/jstat_es_example_1/es_exe1?timeout=1m] returned 1 warnings: [299 Elasticsearch-7.6.0-7f634e9f44834fbc12724506cc1da681b0c3b1e3 "[types removal] Specifying types in document index requests is deprecated, use the typeless endpoints instead (/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id})."]
Feb 25, 2020 5:34:47 PM org.elasticsearch.client.RestClient logResponse
WARNING: request [POST http://localhost:9200/jstat_es_example_1/es_exe1?timeout=1m] returned 1 warnings: [299 Elasticsearch-7.6.0-7f634e9f44834fbc12724506cc1da681b0c3b1e3 "[types removal] Specifying types in document index requests is deprecated, use the typeless endpoints instead (/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id})."]
```

## <a name="source_code"></a> Source Code
