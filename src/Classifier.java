import java.util.Arrays;
import java.util.List;

public class Classifier {
    private double alpha ;
    private double[] weights;
    private double theta ;
    private String language;


    public Classifier( double alpha,String language){
        weights = setRandomWeights();
        this.alpha=alpha;
        theta = 1.0;
        this.language=language;
    }


    public double[] setRandomWeights(){
        double[] weights = new double[26];
        Arrays.fill(weights,1.0);
        return weights;
    }

    public double getY(Data data){
        double net = 0;
        for(int i =0;i<data.getAttributes().length;i++){
            net+= data.getAttributes()[i]*weights[i];
        }
        return net-theta;
    }

   public void learn(Data data) {
        int d = data.getLanguage().equals(this.language)? 1:0;
        double x = getY(data);
        int y = (x>=this.theta?1:0);
        int error = d - y;

        if(y!=d){
            for (int i = 0; i < weights.length; i++)
                this.weights[i] += alpha * error * data.getAttributes()[i]; //new weights
            this.theta += -error * alpha; //new theta
        }

    }

    public void normalizeWeights(){
        double eucSum=0;
        for(double d : this.weights){
            eucSum+=Math.pow(d,2);
        }

        for(int i =0 ; i< weights.length;i++){
           weights[i] = weights[i]/Math.sqrt(eucSum);

        }
    }
    public String getLanguage(){
        return this.language;
    }

    public double[] getWeights(){
        return this.weights;
    }







}
