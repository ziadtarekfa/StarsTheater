package entities;

import java.io.Serializable;

public class MovieProfileDate implements Serializable {
    private String month;
    private String day;

    public MovieProfileDate(String month, String day) {
        this.month = month;
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }
}
