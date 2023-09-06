package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket implements Serializable {

    private String owner;
    private String npOrder;
    private String movieDate;
    private String movieName;
    private String movieTime;
    private String movieURL;
    private String seats;
    private int price;

    public Ticket(){
    }
    public String getOwner() {
        return owner;
    }

    public String getNpOrder() {
        return npOrder;
    }

    public String getMovieDate() {

        return movieDate;


    }


    public String getMovieName() {
        return movieName;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public String getSeats() {
        return seats;
    }

    public String getNoOfSeats(){
        int counter = 0;
        for (int i = 0 ; i  <seats.length() ; i++){
            if(seats.charAt(i) == ','){
                counter++;
            }
        }
        return (counter +1 ) + " Seats";
    }

    public String getMovieTimeAndDate() throws ParseException {

        MyDate myDate = new MyDate(this.movieDate);

        return movieTime + " | " + myDate.getDay() + " "  + myDate.getMonth();
    }


    public String getPrice() {
        return price + " LE";
    }

    public String getMovieURL() {
        return movieURL;
    }


}
