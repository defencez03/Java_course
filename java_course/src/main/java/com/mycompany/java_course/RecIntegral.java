/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_course;

import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Василий
 */
public class RecIntegral
{   
    private static LinkedList list = new LinkedList(); 
    
    private static int countRow = 0;
    
    // Считывание записи из таблицы
    public static void WritingRecordFromATable(DefaultTableModel table, int numStart) 
    {
        countRow = 0;

        for (int i = numStart; i < table.getRowCount(); i++)
        {     
            double num = 0;
            if (table.getValueAt(i, 3) == null)
                num = 0;
            else
                num = Double.parseDouble(table.getValueAt(i, 3).toString());
                
//            if (table.getValueAt(i, 3) == null ||
//                Double.parseDouble(table.getValueAt(i, 3).toString()) > 1000000 ||
//                Double.parseDouble(table.getValueAt(i, 3).toString()) < 0.000001)
//                throw new MyException("Out of range...");

            list.add(new Object[] {table.getValueAt(i, 0),
                                  table.getValueAt(i, 1),
                                  table.getValueAt(i, 2),
                                  num});      
        }
        
        //table.setRowCount(0);
    }
    
    // Запись данных в таблицу
    public static int PrintDataInTable(DefaultTableModel table, int countElemInTable)
    {   
        for (Object item : list)
        {
            table.insertRow(countElemInTable, (Object[]) item);
            countElemInTable++;
        }   
        
        return countElemInTable;
    }
    
    // Удаление записи
    public static void deleteRecord(DefaultTableModel table, int index)
    {
        for (Object item : list)
        {
            if (index == countRow)
                list.remove(item);
            
            countRow++;
        } 
        
        countRow = 0;
    }
    
    public static void setResult(DefaultTableModel table, int index, double res)
    {
        for (Object item : list)
        {
            if (index == countRow)
            {
                Object[] objs = (Object[]) item;
                objs[3] = res;
                list.set(index, objs);
            }
            
            countRow++;
        } 
        
        countRow = 0;
    }
    
    public static LinkedList getList() { return list; }
    
    public void setList(LinkedList list) { this.list = list; } 
}
