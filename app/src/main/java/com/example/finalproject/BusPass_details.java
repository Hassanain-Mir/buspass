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

public class BusPass_details extends AppCompatActivity
{
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;

    String string_pass_id;
    SharedPrefHandler sharedPrefHandler;
    List<Buspass> productList;

    Button apply_pass;

    String pass_id,pass_name,pass_term,pass_occupation,pass_amount,pass_from_place,pass_to_place,pass_document_place,user_name,mno,postal_address,city,occupation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_pass_details);

        sharedPrefHandler=new SharedPrefHandler(this);
        string_pass_id=sharedPrefHandler.getSharedPreferences("pass_id");
        //string_input=sharedPrefHandler.getSharedPreferences("string_sid");

        textView1=(TextView)findViewById(R.id.tv1);
        textView2=(TextView)findViewById(R.id.tv2);
        textView3=(TextView)findViewById(R.id.tv3);
        textView4=(TextView)findViewById(R.id.tv4);
        textView5=(TextView)findViewById(R.id.tv5);
        textView6=(TextView)findViewById(R.id.tv6);

        textView7=(TextView)findViewById(R.id.tv7);
        textView8=(TextView)findViewById(R.id.tv8);
        textView9=(TextView)findViewById(R.id.tv9);

        apply_pass=(Button)findViewById(R.id.apply_pass);


        apply_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pass_id=sharedPrefHandler.getSharedPreferences("pass_id");
                pass_name=sharedPrefHandler.getSharedPreferences("pass_name");
                pass_term=sharedPrefHandler.getSharedPreferences("pass_term");
                pass_occupation=sharedPrefHandler.getSharedPreferences("pass_occupation");
                pass_amount=sharedPrefHandler.getSharedPreferences("pass_amount");
                pass_from_place=sharedPrefHandler.getSharedPreferences("pass_from_place");
                pass_to_place=sharedPrefHandler.getSharedPreferences("pass_to_place");
                pass_document_place=sharedPrefHandler.getSharedPreferences("pass_document_place");
                user_name=sharedPrefHandler.getSharedPreferences("user_name");
                mno=sharedPrefHandler.getSharedPreferences("mno");
                postal_address=sharedPrefHandler.getSharedPreferences("postal_address");
                city=sharedPrefHandler.getSharedPreferences("city");
                occupation=sharedPrefHandler.getSharedPreferences("occupation");



                CreateUserAccount();
                Toast.makeText(BusPass_details.this, "Thank You for Applying for Bus Pass and Kindly Submit Doccument to Bus Pass Office", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
               finish();


            }
        });


        getProductByCode(string_pass_id);


    }

    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.CreateAccountApplyPass(
                pass_id, pass_name,pass_term,pass_occupation,pass_amount,pass_from_place,pass_to_place,pass_document_place,user_name,mno,postal_address,city,occupation
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





    private void getProductByCode(final String string_pass_id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Buspass>> call = api.getBusPassDetails(string_pass_id);

        call.enqueue(new Callback<List<Buspass>>() {
            @Override
            public void onResponse(Call<List<Buspass>> call, Response<List<Buspass>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    textView1.setText("Pass Id : "+productList.get(0).getpass_id());
                    textView2.setText("Pass Name : " + productList.get(0).getpass_name());
                    textView3.setText(" Pass Term : " + productList.get(0).getpass_term());
                    textView4.setText("Occupation : " + productList.get(0).getpass_occupation());
                    textView5.setText("Amount : " + productList.get(0).getpass_amount());
                    textView6.setText("Details : " + productList.get(0).getpass_details());
                    textView7.setText("From Place : " + productList.get(0).getpass_from_place());
                    textView8.setText("To Place : " + productList.get(0).getpass_to_place());
                    textView9.setText("Document Details : " + productList.get(0).getdocument_submission());

                   sharedPrefHandler.setSharedPreferences("pass_id", productList.get(0).getpass_id());
                    sharedPrefHandler.setSharedPreferences("pass_name", productList.get(0).getpass_name());
                    sharedPrefHandler.setSharedPreferences("pass_term", productList.get(0).getpass_term());
                    sharedPrefHandler.setSharedPreferences("pass_occupation", productList.get(0).getpass_occupation());
                    sharedPrefHandler.setSharedPreferences("pass_amount", productList.get(0).getpass_amount());

                    sharedPrefHandler.setSharedPreferences("pass_from_place", productList.get(0).getpass_from_place());
                    sharedPrefHandler.setSharedPreferences("pass_to_place", productList.get(0).getpass_to_place());
                    sharedPrefHandler.setSharedPreferences("pass_document_place", productList.get(0).getdocument_submission());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Buspass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
