package com.example.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

     Button button_profile,button_bus_pass,button_bus_status,button_feedback,button_aboutus,button_payment,button_logout;
     SharedPrefHandler sharedPrefHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefHandler=new SharedPrefHandler(this);






        button_profile=(Button)findViewById(R.id.profile);
        button_bus_pass=(Button)findViewById(R.id.buspass);
        button_bus_status=(Button)findViewById(R.id.pass_status);
        button_feedback=(Button)findViewById(R.id.feedback);
        button_aboutus=(Button)findViewById(R.id.aboutus);
        button_logout=(Button)findViewById(R.id.logout);




        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Profile_page.class);
                startActivity(intent);

            }
        });




        button_bus_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Bus_pass_list.class);
                startActivity(intent);

            }
        });





        button_bus_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Bus_pass_status.class);
                startActivity(intent);

            }
        });




        button_feedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Feedback.class);
                startActivity(intent);

            }
        });







        button_aboutus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),About_us.class);
                startActivity(intent);

            }
        });





        button_payment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Payment.class);
                startActivity(intent);

            }
        });






        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                sharedPrefHandler.setSharedPreferences("login", "false");
                sharedPrefHandler.editor.clear();
                Intent intent=new Intent(getApplication(),Login_page.class);
                startActivity(intent);
                finish();
            }
        });




    }





}