package exp1.sensor.oda114.sensorapp5.post;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import exp1.sensor.oda114.sensorapp5.R;
import exp1.sensor.oda114.sensorapp5.TransparentProgressDialog;
import exp1.sensor.oda114.sensorapp5.service.Static;

/**
 * Created by ali on 5/8/2014.
 */
public class SignUpActivity extends Activity {
    private TransparentProgressDialog pd;
    ImageButton button ;

    EditText etName,etSurname,etEmail, etPassword, etUsername;
    Person person ;
    String result_gelen ;
    //public final String URL = "http://10.0.2.2:8080/dictionary/signup/post";
    // public final String URL = "http://192.168.1.38:8080/dictionary/signup/post";
    public final String URL = Static.IP + "/dictionary/signup/post";
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        pd = new TransparentProgressDialog(this, R.drawable.spinner_2);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurName);
        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPasswordSignUp);
        etEmail = (EditText) findViewById(R.id.etEmail);
        addListenerOnButton();
    }
    public void addListenerOnButton() {
        button = (ImageButton) findViewById(R.id.btnSignup2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(!etUsername.getText().toString().equals("")
                        && !etPassword.getText().toString().equals("") &&
                        !etName.getText().toString().equals("") &&
                        !etSurname.getText().toString().equals("")) {
                    if (validEmail(etEmail.getText().toString())) {
                        LongOperation mytask = null;
                        mytask = new LongOperation();
                        mytask.execute();
                    } else
                        Toast.makeText(getApplication(), "Please enter a valid email address!!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplication(), "Please fill all the fields!!", Toast.LENGTH_SHORT).show();
            }

        });

    }
    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private class LongOperation extends AsyncTask<String, Void, String>
    {
        protected void onPreExecute()
        {

            pd.show();
            person = new Person();
            person.setName(etName.getText().toString());
            person.setEmail(etEmail.getText().toString());
            person.setPassword(etPassword.getText().toString());
            person.setUsername(etUsername.getText().toString());
            person.setSurnmae(etSurname.getText().toString());
        }

        protected String doInBackground(String... params)
        {
            try
            {
                result_gelen =  POST(URL,person);
                System.out.println(result_gelen);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result)
        {
            if(result_gelen.equals("Person saved : "))
                Toast.makeText(getApplication(),"Successfuly Signed Up",Toast.LENGTH_SHORT).show();

            else Toast.makeText(getApplication(),"There was a mistake when signing up..",Toast.LENGTH_SHORT).show();

            pd.dismiss();
            finish();
        }
    }

    public static String POST(String url, Person person){
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

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", person.getName());
            jsonObject.accumulate("username", person.getUsername());
            jsonObject.accumulate("surnmae", person.getSurnmae());
            jsonObject.accumulate("password", person.getPassword());
            jsonObject.accumulate("email", person.getEmail());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

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
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}