package Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.todolist.R;

public class Splash_Screen extends AppCompatActivity {

    //DEC:
    private final int Splash_Screen_length=6000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    Intent MainIntent = new Intent(Splash_Screen.this, MainActivity.class);
                    // the MainActivity is the Main page of the appliaction
                    Splash_Screen.this.startActivity(MainIntent);
                    Splash_Screen.this.finish();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },Splash_Screen_length);



    }
}
