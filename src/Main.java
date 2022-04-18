import javax.swing.*;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DataReader dataReader = new DataReader("data");
        List<Data> trainList = dataReader.getList();

        List<Classifier> perceptronList = new ArrayList<>();
        for (Data data : trainList)
            perceptronList.add(new Classifier(0.001, data.getLanguage()));


        for (int i = 0; i < 1000; i++) {
            for (Data data : trainList) {
                for (Classifier classifier : perceptronList) {
                    classifier.learn(data);
                }
            }
        }

        for (Classifier classifier : perceptronList) {
            classifier.normalizeWeights();
        }

        SwingUtilities.invokeLater(() -> new GUI(perceptronList));


    }
}
