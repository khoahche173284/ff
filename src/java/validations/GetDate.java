/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author trantoan
 */
public class GetDate {
    /**
     * methods will get current date
     *
     * @return current date by format 'yyyy/MM/dd'
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = LocalDate.now().format(formatter);
        return date;
    }
}
