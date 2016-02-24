package exp1.sensor.oda114.sensorapp5.post;


import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by ali on 4/25/2014.
 */
public class Post {


    public static void main(String[] args) {
        Post post = new Post();
        Value word = new Value();
        word.setTimeInterval("erisim");
        word.setAccelerometer("id");


        Value word2 = new Value();
        word2.setAccelerometer("erisim2");
        word2.setTimeInterval("id2");
        ;
        ArrayList<Value> wordArrayList = new ArrayList<Value>();
        wordArrayList.add(word);
        wordArrayList.add(word2);
        post.post("http://localhost:9090/jaxrs/students/answer/post", wordArrayList );
    }
    public Post() {

    }


    public  String post(String url, ArrayList<Value> words){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 7000);
            HttpConnectionParams.setSoTimeout(httpclient.getParams(), 5000);
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";
            Values wordss = new Values();
            wordss.setWords(words);
            json = new Gson().toJson(wordss);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json, HTTP.UTF_8);

            // 6. set httpPost Entity
            httpPost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            System.out.println("InputStream" + e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}
