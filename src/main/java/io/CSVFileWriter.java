package io;
import tech.tablesaw.io.csv.CsvWriteOptions;
import tech.tablesaw.io.csv.CsvWriter;
import utils.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileWriter {

    public static final char DEFUALT_DELIMITER = ',';
    public static final char DEFAULT_COMMENT = '#';

    public CSVFileWriter(String filename){

        this.filename = filename;
        this.delimiter = CSVFileWriter.DEFUALT_DELIMITER;
        this.commentDelimiter = CSVFileWriter.DEFAULT_COMMENT;
        File file = new File(filename);

        try {
            this.outputfile = new FileWriter(file);
        }
        catch(IOException e){

        }
    }

    /**
     *
     * @param names List of strings representing the column names
     */
    public void writeColumnNames(String... names){

        try{
            this.outputfile.write(this.createColumns(names));
            //this.outputfile.close();
        }
        catch (IOException e){

        }
    }

    /**
     *
     * @param row Write a row of doubles
     */
    public void writeRow(Double...row){

        try{
            this.outputfile.write(this.createRow(row));
            this.outputfile.close();
        }
        catch (IOException e){

        }
    }

    public void writeRow(Integer idx, Double value){

        try{

            String rowVal = idx.toString() + this.delimiter + value.toString() + "\n";
            this.outputfile.write(rowVal);
            //this.outputfile.close();
        }
        catch (IOException e){

        }
    }

    public void writeRow(Pair<Integer, Double> row){

        try{

            String rowVal = row.first.toString() + this.delimiter + row.second.toString();
            this.outputfile.write(rowVal);
            this.outputfile.close();
        }
        catch (IOException e){

        }
    }

    public void writeDoubleRow(List<Double> row){

        try{
            this.outputfile.write(this.createDoubleRow(row));
            this.outputfile.close();
        }
        catch (IOException e){

        }

    }

    /**
     *
     * @param names List of names that correspond to the collumns
     * @return
     */
    private String createColumns(String... names){

        StringBuilder builder = new StringBuilder(names.length);

        int counter = 0;
        builder.append(this.commentDelimiter);
        for(String name:names){
            builder.append(name);

            if(counter != names.length - 1){
                builder.append(this.delimiter);
            }
            counter++;
        }

        return builder.toString() + "\n";
    }

    private String createRow(Double... row){

        StringBuilder builder = new StringBuilder(row.length);

        int counter = 0;
        for(Double item:row){

            builder.append(item.toString());

            if(counter != row.length - 1){
                builder.append(this.delimiter);
            }
            counter++;

        }

        return builder.toString();
    }

    private String createDoubleRow(List<Double> row){

        StringBuilder builder = new StringBuilder(row.size());

        int counter = 0;
        for(Double item:row){

            builder.append(item.toString());

            if(counter != row.size() - 1){
                builder.append(this.delimiter);
            }
            counter++;

        }

        return builder.toString();
    }

    private char delimiter;
    private char commentDelimiter;
    private String filename;
    private FileWriter outputfile;

}
