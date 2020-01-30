package examples;

import dataloader.CsvDataLoader;
import stats.OLSLinearRegression;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class OLSMultipleLinearRegression {

    public static  void main(String[] args ) throws IOException {

        File file = new File("data/car_plant_multi.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);


        OLSLinearRegression regression = new OLSLinearRegression();
        String[] xCols = new String[2];
        xCols[0] = "Production";
        xCols[1] = "Cooling";
        regression.fit(table, xCols, "Electricity Usage");

        double[] coeffs = regression.getCoeffs();
        double intercept = regression.getIntercept();
        System.out.println("Regression coefficients. Intercept: " + intercept + " x1: " + coeffs[1] + " x2: " + coeffs[2]);
    }
}
