package examples.ml.example8;

import maths.ConfusionMatrix;
import java.util.ArrayList;
import java.util.List;

public class Example8 {

    public static void main(String[] args){

            final int SIZE = 165;
            final int N_CLASSES = 2;

            List<Integer> actual = new ArrayList<>();

            for(int i=0; i< SIZE; ++i){

                if(i < 60){
                    actual.add(0);
                }
                else{
                    actual.add(1);
                }
            }

            List<Integer> predicted = new ArrayList<>();

            for(int i=0; i< SIZE; ++i){

                if(i < 50){
                    predicted.add(0);
                }
                else if(i>=50 && i<65){
                    predicted.add(1);
                }
                else if(i>=65 && i<70){
                    predicted.add(0);
                }
                else{
                    predicted.add(1);
                }
            }

        ConfusionMatrix confusionMatrix = new ConfusionMatrix(actual, predicted, N_CLASSES);

        // let's compute some metrics
        System.out.println("TP: "+confusionMatrix.getClassCounts(1));
        System.out.println("TN: "+confusionMatrix.getClassCounts(0));
        System.out.println("FP: "+confusionMatrix.getClassCountsAsOtherClass(0,1));
        System.out.println("FN: "+confusionMatrix.getClassCountsAsOtherClass(1,0));
        System.out.println("Accuracy is: " + confusionMatrix.accuracy());
        System.out.println("Misclassification Rate: " + confusionMatrix.misclassificationRate());
        System.out.println("TP Rate or Recall: " + confusionMatrix.recallClass(1));
        System.out.println("TN Rate or Specificity: " + confusionMatrix.recallClass(0));
        System.out.println("False Positive Rate: " + (double)confusionMatrix.getClassCountsAsOtherClass(0,1)/60.0);
        System.out.println("Precision: " + (double)confusionMatrix.getClassCounts(1)/
                (double) (confusionMatrix.getClassCountsAsOtherClass(0,1) + confusionMatrix.getClassCounts(1)));
        System.out.println("Prevalence: " + (double)(confusionMatrix.getClassCountsAsOtherClass(1,0) +
                confusionMatrix.getClassCounts(1))/(double) confusionMatrix.totalCount());


    }


}
