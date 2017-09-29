package com.mojafirma.cinema.view;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
