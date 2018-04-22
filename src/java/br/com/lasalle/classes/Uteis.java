/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author danie
 */
public class Uteis {

        
    public static float DateTimeStringToSeconds(String date)
    {   
        String[] exploded = date.split(" ");
        return Uteis.TimeToSeconds(exploded[1]);
    }
    
    public static float TimeToSeconds(String timeString)
    {
        String[] time = timeString.split(":");

        int seconds = Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60;
        
        return seconds;        
    }
}
