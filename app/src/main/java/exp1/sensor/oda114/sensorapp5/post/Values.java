package exp1.sensor.oda114.sensorapp5.post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oda114 on 23.2.2016.
 */
public class Values {

   List<Value> answers = new ArrayList<Value>();

    public Values() {
    }

    public List<Value> getWords() {
        return answers;
    }

    public void setWords(ArrayList<Value> words) {
        this.answers = words;
    }
}
