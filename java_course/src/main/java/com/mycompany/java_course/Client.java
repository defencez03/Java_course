/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_course;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Василий
 */
public class Client extends Thread
{  
    private static double start;
    private static double finish;
    private static double step;
    private static double res = 0;
    private static String[] results;
    private static String result;
    
    public void setValues(double step, double start, double finish)
    {
        Client.step = step;
        Client.start = start;
        Client.finish = finish;
    }
    
    @Override
    public void run()
    {
        // Запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента      
        try (Socket socket = new Socket("localhost", 80);
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            // Проверяем живой ли канал и работаем если живой           
            if (!socket.isOutputShutdown())
            {               
                while (ois.available() == 0)
                    Thread.sleep(1);
                   
                result = ois.readUTF();
                results = result.split(" ");
                
                step = Double.parseDouble(results[0]);
                start = Double.parseDouble(results[1]);
                finish = Double.parseDouble(results[2]);
                
                try {      
                    res = Integral.TaskForFindIntegral(start, finish, step);
                } catch (MyException ex) {
                    Logger.getLogger(IntRes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                oos.writeUTF(Double.toString(res));
                oos.flush();
                
                Thread.sleep(20);
                
                step = 0; start = 0; finish = 0;      
            }
        } 
        catch (UnknownHostException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }

    }
}