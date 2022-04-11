import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Data {
    private String language;
    private String text;
    private Map<Character, Long> charMap;
    private double[] attributes;
    //? text czy mapa?
    public Data(String language, String text) {
        this.language = language;
        this.text = text;
        this.attributes= getAttributes(text);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getAttributes(String text) {
        double[] attr = new double[26];

        int k=0;
        for (int i = 'a'; i <= 'z'; i++) {
            char letter = (char) i;
            long count = text.chars().filter(c -> c == letter).count();
            attr[k++] = count;
        }
       return attr;
    }

    public void normalizeVector(){
        double eucSum=0;
        for(double d : this.attributes){
            eucSum+=Math.pow(d,2);
        }

        for(int i =0 ; i< attributes.length;i++){
            attributes[i] = attributes[i]/Math.sqrt(eucSum);
        }

    }



   /* public String toString(){
        return this.language +"------------------------------------" +this.text;
    }*/
}
