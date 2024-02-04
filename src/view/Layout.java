package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    private JPanel container;

    public void guiInitilaze(int witdth, int height) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Patika Otel");
        this.setSize(witdth, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    //seçilmiş olan satırın verilen indise göre ilgili sütunundaki veriyi döndürüyor
    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());

    }

    //mouse tıkladığında üstüne geldiği satırı seçmek için yazılmış metot
    //parametre olarak çalışacak tablo verildi.
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int seleceted_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(seleceted_row, seleceted_row);
            }
        });
    }
}