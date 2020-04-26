package io;
import tech.tablesaw.io.csv.CsvWriteOptions;
import tech.tablesaw.io.csv.CsvWriter;

import java.io.File;
import java.io.FileWriter;

public class CSVFileWriter {

    public static final char DEFUALT_DELIMITER = ',';
    public static final char DEFAULT_COMMENT = '#';
    public CSVFileWriter(String filename){

        this.filename = filename;
        this.delimiter = CSVFileWriter.DEFUALT_DELIMITER;
        this.commentDelimiter = CSVFileWriter.DEFAULT_COMMENT;

    }

    public void writeColumnNames(String... names){

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filename);

        try{

            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            outputfile.write(this.createColumns(names));
            outputfile.close();
        }
        catch (Exception e){

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
        }

        return builder.toString();
    }

    private char delimiter;
    private char commentDelimiter;
    private String filename;

}
