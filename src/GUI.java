import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class GUI extends JFrame {
    private JButton checkButton;
    private JTextArea textArea;

    private JPanel mainPanel;
    private JPanel answerPanel;
    private JLabel answerLabel;
    private JButton clearButton;
    private List<Classifier> perceptronList;

    public GUI(List<Classifier> perceptronList) {
        this.setPreferredSize(new Dimension(800, 400));
        this.perceptronList = perceptronList;
        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        textArea.setLineWrap(true);
        this.pack();
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerLabel.setText(checkLanguage());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerLabel.setText("Unknown");
                textArea.setText("");
            }
        });
    }


    public String checkLanguage() {
        Data data = new Data("Unknown", textArea.getText());
       // double type = -1;
        String language = "";
        data.normalizeVector();
       /* for (Classifier classifier : perceptronList) {
            System.out.println(classifier.getY(data));
            if (classifier.getY(data) > type) {
               // type = classifier.getY(data);
                language = classifier.getLanguage();
            }
        }*/
        language = perceptronList.stream().max(Comparator.comparingDouble(p -> p.getY(data))).get().getLanguage();
        return language;
    }


}
