package exp1.sensor.oda114.sensorapp5.post;

import java.util.Comparator;

/**
 * Created by ali on 5/8/2014.
 */
public class Word  implements java.io.Serializable , Comparable<Word>{
    private String id ;
    private  String ingilzce  ;
    private  String turkce  ;
    private String erisim ;
    private String status ;

    public Word() {
    }

    public Word(String id, String ingilzce, String turkce, String erisim, String status) {
        this.id = id;
        this.ingilzce = ingilzce;
        this.turkce = turkce;
        this.erisim = erisim;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngilzce() {
        return ingilzce;
    }

    public void setIngilzce(String ingilzce) {
        this.ingilzce = ingilzce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public String getErisim() {
        return erisim;
    }

    public void setErisim(String erisim) {
        this.erisim = erisim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Word another) {
        String compareIngilizce = ((Word) another).getIngilzce();

        //ascending order
        return this.ingilzce.compareToIgnoreCase(compareIngilizce );
    }
    public static Comparator<Word> FruitNameComparator = new Comparator<Word>() {

        public int compare(Word fruit1, Word fruit2) {

            String fruitName1 = fruit1.getIngilzce().toUpperCase();
            String fruitName2 = fruit2.getIngilzce().toUpperCase();

            //ascending order
            return fruitName1.compareToIgnoreCase(fruitName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}
