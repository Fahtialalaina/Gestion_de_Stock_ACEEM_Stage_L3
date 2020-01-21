/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fandresena
 */
public class TableColorCellRenderer implements TableCellRenderer{
    private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        Component c = RENDERER.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
        
        Object result = jtable.getModel().getValueAt(i, 3);
        String type = result.toString();
        Color color = null;
        Color color2 = null;
            if(type.equals("INVENTAIRE")){
                color = Color.RED;
                color2 = Color.WHITE;
            }
         c.setBackground(color);
         c.setForeground(color2);
        return c;
    }
    
}
