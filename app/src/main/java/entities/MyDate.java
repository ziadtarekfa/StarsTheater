package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyDate {

    private final Calendar calendar;

    public MyDate(String movieDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(movieDate);
        this.calendar =  Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);
    }

    public String getDay(){
        return new SimpleDateFormat("dd",Locale.getDefault()).format(calendar.getTime());
    }
    public String getMonth(){
        return new SimpleDateFormat("MMMM",Locale.getDefault()).format(calendar.getTime());
    }

    public String getDateForTicketDetails()  {

        String month = new SimpleDateFormat("MMM",Locale.getDefault()).format(calendar.getTime());

        String year = new SimpleDateFormat("yyyy",Locale.getDefault()).format(calendar.getTime());

        return getDay() + " " + month + " " + year;
    }
}
