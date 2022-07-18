package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Bus_pass_status extends AppCompatActivity {

   // SharedPrefHandler sharedPrefHandler;
    String string_mno;

    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;

    String string_pass_id;
    SharedPrefHandler sharedPrefHandler;
    List<BuspassStatus> productList;

    Button apply_pass;
    String pass_id,user_mno,from_date,expired_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_pass_status);


        sharedPrefHandler=new SharedPrefHandler(this);

        string_mno=sharedPrefHandler.getSharedPreferences("mno");


        textView1=(TextView)findViewById(R.id.s1);
        textView2=(TextView)findViewById(R.id.s2);
        textView3=(TextView)findViewById(R.id.s3);
        textView4=(TextView)findViewById(R.id.s4);

        apply_pass=(Button)findViewById(R.id.apply_pass_replay);


        apply_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pass_id=sharedPrefHandler.getSharedPreferences("pass_id");
                user_mno=sharedPrefHandler.getSharedPreferences("user_mno");
                from_date=sharedPrefHandler.getSharedPreferences("from_date");
                expired_date=sharedPrefHandler.getSharedPreferences("expired_date");



                CreateUserAccount();
                Toast.makeText(Bus_pass_status.this, "Thank You for Applying for Bus Pass and Kindly Submit Doccument to Bus Pass Office", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
                finish();


            }
        });


        getProductByCode(string_mno);
    }

    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.CreateAccountApplyPass_reaply(
                pass_id, user_mno, from_date, expired_date
        );

        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if(responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if(isSuccess) {

                } else {
                    // Show Creation Failed Message
                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





    private void getProductByCode(final String string_mno)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<BuspassStatus>> call = api.getBusPassDetailsreaply(string_mno);

        call.enqueue(new Callback<List<BuspassStatus>>() {
            @Override
            public void onResponse(Call<List<BuspassStatus>> call, Response<List<BuspassStatus>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    textView1.setText("Pass Id : " + productList.get(0).getpass_id());
                    textView2.setText("User Mobile : " + productList.get(0).getuser_mno());
                    textView3.setText(" From Date : " + productList.get(0).getfrom_date());
                    textView4.setText("Expired Date : " + productList.get(0).getexpired_date());


                    sharedPrefHandler.setSharedPreferences("pass_id", productList.get(0).getpass_id());
                    sharedPrefHandler.setSharedPreferences("user_mno", productList.get(0).getuser_mno());
                    sharedPrefHandler.setSharedPreferences("from_date", productList.get(0).getfrom_date());
                    sharedPrefHandler.setSharedPreferences("expired_date", productList.get(0).getexpired_date());

                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<BuspassStatus>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}