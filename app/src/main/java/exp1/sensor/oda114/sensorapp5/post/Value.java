package exp1.sensor.oda114.sensorapp5.post;

import java.util.Comparator;

/**
 * Created by ali on 5/8/2014.
 */
public class Value implements java.io.Serializable , Comparable<Value>{
    private String accelerometer ;
    private  String timeInterval  ;

    public Value() {
    }

    public String getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(String accelerometer) {
        this.accelerometer = accelerometer;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public int compareTo(Value another) {
        String compareIngilizce = ((Value) another).getAccelerometer();

        //ascending order
        return this.accelerometer.compareToIgnoreCase(compareIngilizce );
    }
    public static Comparator<Value> FruitNameComparator = new Comparator<Value>() {

        public int compare(Value fruit1, Value fruit2) {

            String fruitName1 = fruit1.accelerometer.toUpperCase();
            String fruitName2 = fruit2.getTimeInterval().toUpperCase();

            //ascending order
            return fruitName1.compareToIgnoreCase(fruitName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}
