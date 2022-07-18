package com.example.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;;
import android.widget.Button;
import android.widget.TextView;

public class registration extends AppCompatActivity {
 Button btn_Add;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView btn=findViewById(R.id.btn_Add);
        btn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new Intent(registration.this, Bus_pass_list.class));
        }
        });
        }


}








