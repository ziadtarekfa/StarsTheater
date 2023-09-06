package entities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import com.example.starstheater.HomeActivity;
import com.example.starstheater.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class Database {
    private final Context context;
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();

    public Database(Context context) {
        this.context = context;
    }

    public void signInUser(String email, String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            else{
                Toast.makeText(context, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void signOutUser(){
        auth.signOut();
        Intent intent = new Intent(context, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
