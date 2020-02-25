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
