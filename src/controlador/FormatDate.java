
package controlador;

import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FormatDate {
    

    //get string date yymmdd_hhmm
    public String getFormat(Date date) {

        DateFormat dayCalendar = new SimpleDateFormat("dd");
        DateFormat monthCalendar = new SimpleDateFormat("MM");
        DateFormat yearCalendar = new SimpleDateFormat("yyyy");
        DateFormat hourCalendar = new SimpleDateFormat("HH");
        DateFormat minuteCalendar = new SimpleDateFormat("mm");

        String minuteNow = minuteCalendar.format(date);
        
        //int minute=Integer.parseInt(minuteNow);
        //String newMinute=String.valueOf(minute);
        
        //int result = minute/10;
        //String newMinute=String.valueOf(result);
        
        String hourNow = hourCalendar.format(date);
        String dayNow = dayCalendar.format(date);
        String monthNow = monthCalendar.format(date);
        String yearNow = yearCalendar.format(date);
        String formato = yearNow + monthNow + dayNow + hourNow + minuteNow;

        return formato;
    }
    
      //convert "string date" to "Date"
    public Date stringToDate(String dateTxt) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateNow = null;
        try {
            dateNow = dateFormat.parse(dateTxt);
        } catch (ParseException ex) {
            System.out.println("Error: " + ex);
            return dateNow;
        }
        return dateNow;
    }

    //convert "Date" to "string date"
    public String dateToString(Date date) {
        String dateNow = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateNow = dateFormat.format(date);
        return dateNow;
    }

    //get date today;
    public Date getDateToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date dateNow = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String newDate = dateFormat.format(cal.getTime());

        try {
            dateNow = dateFormat.parse(newDate);
        } catch (ParseException ex) {
            System.out.println("Error formato fecha: " + ex);
            return dateNow;
        }
        //Desplegamos la fecha
        return dateNow;

    }


}
