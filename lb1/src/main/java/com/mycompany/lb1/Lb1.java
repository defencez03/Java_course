/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lb1;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Василий
 */
public class Lb1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    //
    // Поиск определенного интеграла с f(x) = tg(x) по формуле Трапеций
    //
    public static double TaskForFindIntegral(int startPoint, int finishPoint, double step)
    {
        double result = 0;
        double sum = 0, numForLoop = 0;
        
        //
        // Определение начала интегрирования
        //
        if (startPoint < finishPoint)
        {
            sum = startPoint;
            numForLoop = finishPoint;
        }
        else
        {
            sum = finishPoint;
            numForLoop = startPoint;
        }
        
        //
        // Расчет площадей трапейций, входящих в промежуток интегрирования
        //
        while (sum < numForLoop)
        {
            result += (Math.tan(sum) + Math.tan(sum + step)) * (step / (double)2);
            sum += step;
        }
        
        //
        // Изменение шага, если шаг вышел за промежуток интегрирования
        //
        if (sum < numForLoop)
        {
            step = numForLoop - sum;
            result += (Math.tan(sum) + Math.tan(sum + step)) * (step / (double)2);
        }
        
        return result;
    }
}
