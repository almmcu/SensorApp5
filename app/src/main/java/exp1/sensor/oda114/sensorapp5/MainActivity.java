package exp1.sensor.oda114.sensorapp5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

import exp1.sensor.oda114.sensorapp5.post.Person;
import exp1.sensor.oda114.sensorapp5.post.Post;
import exp1.sensor.oda114.sensorapp5.post.Value;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mGyroSensor;
    private Sensor mLineerAccSensor;
    private TextView tv;
    private TextView tv2;
    private TextView txtSaniyeilikMesafe;
    private TextView txtSaniyelikIvme;
    private SensorManager sMgr;
    float angularXMaxSpeedOneSec = 0;
    float angularXMaxSpeed = 0;
    float angularYMaxSpeed = 0;
    float angularZMaxSpeed = 0;
    OutputStreamWriter outputStreamWriter;
    long currentTimeinMilisecoond =0;
    long gecenZamanMiliSecond = 0;
    long dif = 0;
    long timeStart = 0;
    long timeEnd = 0;
    int a = 0;
    int mapIndex = 0;
    private TransparentProgressDialog pd;
    double mesafe = 0;
    Button btn ;
    int cal = 0 ;
    ArrayList<Double> saniyelik = new ArrayList<>();
    ArrayList<Double> saniyelikMesurement = new ArrayList<>();
    ArrayList<Double> saniyelikMesafe = new ArrayList<>();
    ArrayList<ArrayList<Double>> accValueMap = new ArrayList<>();
    JSONArray dataJsonArr = null;
    Value value;
    ArrayList<Value> valueArrayList = new ArrayList<Value>();
    ArrayList<Double> ivmeDegerBirOlcumList = new ArrayList<>();
    ArrayList<Double> saniyeDegerBirOlcumList = new ArrayList<>();
int sayac = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new TransparentProgressDialog(this, R.drawable.spinner_2);
        try {
            sMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mLineerAccSensor = sMgr.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

            tv= (TextView)findViewById(R.id.txt2);
            tv2= (TextView)findViewById(R.id.txt3);
            txtSaniyelikIvme= (TextView)findViewById(R.id.txtSaniyelikIvme);
            txtSaniyeilikMesafe= (TextView)findViewById(R.id.txtSaniyelikMesafe);
            btn = (Button) findViewById(R.id.btnBaslaBitir);}
        catch (Exception e){
            System.out.println(e);
        }




    }

 /*   @Override
    public void onSensorChanged(SensorEvent event) {

    }*/

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    double degisken=100;

    public void onSensorChanged(SensorEvent event) {
        long temp = System.currentTimeMillis();
        Sensor sensor = event.sensor;

        long timediff = temp - currentTimeinMilisecoond;
            if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION && sayac != 0 ) {
                float angularXSpeed = event.values[0];
                float angularYSpeed = event.values[1];
                float angularZSpeed = event.values[2];//
                gecenZamanMiliSecond +=timediff;
                tv.setText("Angular X speed level is: " + "" + angularXSpeed + "\n\n");
                value = new Value();
                value.setAccelerometer(String.format("%.8f", (double) angularXSpeed));
                value.setTimeInterval(String.format("%.8f", (double) (gecenZamanMiliSecond) / 1000) + " " + String.format("%.8f", (double) (timediff) / 1000));
                valueArrayList.add(value);

            }


       /* long timediff = temp - currentTimeinMilisecoond;
            if (timediff >= 5 && sayac != 0){
                gecenZamanMiliSecond +=timediff;
                if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                    float angularXSpeed = event.values[0];
                    float angularYSpeed = event.values[1];
                    float angularZSpeed = event.values[2];//
                    value = new Value();
                    value.setAccelerometer(String.format("%.8f", (double)angularXSpeed ) );
                    value.setTimeInterval(String.format("%.8f", (double) ( gecenZamanMiliSecond)/1000) + " " + String.format("%.8f", (double) ( timediff)/1000));
                    valueArrayList.add(value);
            }
            }*/
        sayac ++;
            currentTimeinMilisecoond = System.currentTimeMillis();

       /* if (timediff >= 100){



          *//*  if (angularXMaxSpeed < angularXSpeed) angularXMaxSpeed = angularXSpeed;
            if (angularYMaxSpeed < angularYSpeed) angularYMaxSpeed = angularYSpeed;
            if (angularZMaxSpeed < angularZSpeed) angularZMaxSpeed = angularZSpeed;*//*


            *//*if (angularXSpeed < 0.150) angularXSpeed = 0 ;
            if (angularYSpeed < 0.270) angularYSpeed = 0 ;
            if (angularZSpeed < 0.2) angularZSpeed = 0 ;*//*
          *//*  double b = (0.5 * angularXSpeed * 0.1 * 0.1/4);
            if (b< 0) b = -b;
            a += ( b / 1000) ;*//*

                tv.setText("Angular X speed level is: " + "" + angularXSpeed + "\n\n"
                                + "Angular Y speed level is: " + "" + angularYSpeed + "\n\n"
                                + "Angular Z speed level is: " + "" + angularZSpeed
                                + "\n\n"
*//*
                            "Angular X speed level is: " + "" + angularXMaxSpeed + "\n\n"
                            // 0.033320963 -
                            + "Angular Y speed level is: " + "" + angularYMaxSpeed + "\n\n"
                            // 0.12496567 -
                            + "Angular Z speed level is: " + "" + angularZMaxSpeed

                            + "\n\n\n\n\n\n" +
                    ( a )
                    // 0.060460567 -*//*


                );


               // ivmeDegerBirOlcumList.add((double) angularXSpeed);// göndermek için alınan ivme değerleri
              //  saniyeDegerBirOlcumList.add((double) timediff);// göndermek için alınan ivme değerleri


                saniyelik.add( (double) angularXSpeed);
                a++;
                dif += timediff;
                angularXMaxSpeedOneSec += angularXSpeed ;
                if (dif >= 1000)
                {
                    angularXMaxSpeedOneSec /= a;
                    tv2.setText("\n\n\n\n" + angularXMaxSpeedOneSec  + "\n" +
                            "\n" +
                            " mesafe\n" +
                            (0.5*angularXMaxSpeedOneSec *angularXMaxSpeedOneSec)*100 );
                    dif = 0 ;
                    double tempp = (0.5*angularXMaxSpeedOneSec *angularXMaxSpeedOneSec);
                    if (tempp < 0) tempp = - tempp;
                    try {
                        mesafe = mesafe + tempp *100 ;
                        saniyelikMesurement.add(tempp*100);// saniyelik alınan mesafeler saklanılıyor.
                        saniyelikMesurement.add((double) angularXMaxSpeedOneSec);
                        accValueMap.add( saniyelik);
                        saniyelikMesafe.add(tempp * 100);
                        angularXMaxSpeedOneSec = 0 ;
                        saniyelik = new ArrayList<>();
                        a = 0 ;
                        mapIndex ++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }

                    System.out.println(accValueMap);
                }
                currentTimeinMilisecoond = System.currentTimeMillis();
            }*/

       /* else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float angularXGyro = event.values[0];
            float angularYGyro = event.values[1];
            float angularZGyro = event.values[2];//
        }
*/


    }

    public void baslaBitir (View view){

        if (cal % 2 == 0 )
        {
            btn.setText("Bitir");
            sMgr.registerListener(this, mLineerAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
            timeStart = System.currentTimeMillis();

        }
        else {
            timeEnd = System.currentTimeMillis();
            sayac = 0;
            btn.setText("Basla");
            sMgr.unregisterListener((SensorEventListener) this);
            tv2.setText("\n\nSon mesafe   " +mesafe
                    +"\n Integer Value:  " + (int)mesafe);
            int a1 = (int)mesafe;

            int i = 1;

            System.out.println(accValueMap);
            String mesafeler = "";
            for (i = 0; i < saniyelikMesurement.size() ; i++) {
                mesafeler += ((i + 1) + ". saniye  "+saniyelikMesurement.get(i++) + "  " + saniyelikMesurement.get(i) + "\n");
            }
            txtSaniyeilikMesafe.setText(mesafeler);
            saniyelikMesurement.clear();
            i = 1;
            mesafeler = "\n";
            for (ArrayList<Double> accList:accValueMap) {
                mesafeler += i +". saniye:\n ";
                for (double accValalue:accList
                        ) {

                    mesafeler += "   " +accValalue;
                }
                mesafeler += "\n";
                i++;
            }
            txtSaniyelikIvme.setMovementMethod(new ScrollingMovementMethod());
            txtSaniyelikIvme.setText(mesafeler);
            accValueMap.clear();
            saniyelikMesafe.clear();
            mesafe = 0;
            mapIndex = 0;
            System.out.println(valueArrayList);
            LongOperation mytask = null;
            mytask = new LongOperation();
            mytask.execute();

        }


        cal++;
    }


    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
       /* sMgr.registerListener((SensorEventListener) this, mLineerAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sMgr.registerListener((SensorEventListener) this, mGyroSensor, SensorManager.SENSOR_DELAY_NORMAL);*/
    }

    @Override
    protected void onPause() {
        // important to unregister the sensor when the activity pauses.
        super.onPause();

        try {
            sMgr.unregisterListener((SensorEventListener) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class LongOperation extends AsyncTask<String, Void, String>
    {
        protected void onPreExecute()
        {

            pd.show();

        }

        protected String doInBackground(String... params)
        {
            try
            {
                Post post = new Post();
                //post.post("http://localhost:9090/jaxrs/students/answer/post",valueArrayList);
                Value value = new Value();
                value.setTimeInterval(String.format("%.8f", (double)  (timeEnd - timeStart) /1000 ) );
                value.setAccelerometer("00,00");
                valueArrayList.add(value);
                post.post("http://10.37.151.198:9090/jaxrs/students/answer/post",valueArrayList);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result)
        {
            /*if(result_gelen.equals("Person saved : "))
                Toast.makeText(getApplication(), "Successfuly Signed Up", Toast.LENGTH_SHORT).show();

            else Toast.makeText(getApplication(),"There was a mistake when signing up..",Toast.LENGTH_SHORT).show();*/
valueArrayList.clear();
            gecenZamanMiliSecond = 0;
            pd.dismiss();
        }
    }
}
