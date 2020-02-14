package maths;

import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datastructs.RowType;
import utils.Pair;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.event.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






/**
 * ConfusionMatrix
 */
public class ConfusionMatrix extends JFrame {

    public ConfusionMatrix(List<Pair<Integer, Integer>> classes, List<String> names){

        super("Confusion Matrix");

        if(classes == null){
            throw new IllegalArgumentException("Classes should not be null");
        }

        this.names = names;

        data = new DenseMatrixSet<>(RowType.Type.INTEGER_VECTOR, new RowBuilder(), names.size(), names.size(), -1);

        Map<Integer, List<Integer>> result = new HashMap<>();

        for(int i=0; i<classes.size(); ++i){

            Pair<Integer, Integer> pair = classes.get(i);
            Integer classIdx = pair.first;

            if(result.containsKey(classIdx)){
                    result.get(classIdx).add(classIdx, 1);

            }
            else{

                List<Integer> values = new ArrayList<>(names.size());

                for(int j=0; j<names.size(); ++j){
                    values.add(0);
                }

                result.put(classIdx, values);
            }
        }

    }


    public void print(){


        gradesLabel = new JLabel("Confusion Matrix");
        gradesLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        this.gradesTable = createTable(new MatrixModel(this.names, this.data));

        this.gradesTable.setFillsViewportHeight(true);
        this.gradesTable.setRowHeight(24);

        scroll = new JScrollPane(gradesTable);

        this.contentPane = new JPanel();

        super.setContentPane(this.contentPane);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(700, 600);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    protected JTable createTable(TableModel model) {
        return new JTable(model);
    }


    private List<String> names;
    private DenseMatrixSet<Integer> data;
    private JPanel contentPane;
    private JLabel gradesLabel;
    private JTable gradesTable;
    private JScrollPane scroll;

    private static class MatrixModel implements TableModel
    {
        MatrixModel(List<String> names, DenseMatrixSet<Integer> data){
            this.names = names;
            this.data = data;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
        public void addTableModelListener(TableModelListener l) {}
        public void removeTableModelListener(TableModelListener l) {}

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public Class<?> getColumnClass(int col) {
            return Integer.class;

        }

        public int getRowCount() {
            return data.m();
        }

        public int getColumnCount() {
            return names.size();
        }

        public String getColumnName(int col) {
            return names.get(col);
        }

        public Object getValueAt(int row, int col) {
            return data.getEntry(row, col);
        }

        private List<String> names;
        private DenseMatrixSet<Integer> data;
    }



}
