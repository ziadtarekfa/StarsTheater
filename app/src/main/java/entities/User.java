package entities;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private String profilePicURL;
    private ArrayList<Ticket> tickets = new ArrayList<>();



    public User() {
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }


    public String getName() {
        return name;
    }




}
