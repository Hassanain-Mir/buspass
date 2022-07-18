package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create_new_account extends AppCompatActivity
{
    EditText editText_name,editText_mno,editText_email,editText_postal_address,editText_city_name,editText_occupation,editText_password;
     Button button_submit;
    String stringname,stringmno,stringemail,stringaddress,stringcity,stringoccupation,stringpassword;
    SharedPrefHandler sharedPrefHandler;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePatter = "[a-zA-Z]+";
    String pattern = "[6-9][0-9]{10}";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        sharedPrefHandler=new SharedPrefHandler(this);

        editText_name=(EditText)findViewById(R.id.create_account_username);
        editText_mno=(EditText)findViewById(R.id.create_account_mno);
        editText_email=(EditText)findViewById(R.id.create_account_address);

        editText_postal_address=(EditText)findViewById(R.id.create_account_postal_address);
        editText_city_name=(EditText)findViewById(R.id.create_account_city_name);
        editText_occupation=(EditText)findViewById(R.id.create_account_occupation);


        editText_password=(EditText)findViewById(R.id.create_account_password);

        button_submit=(Button)findViewById(R.id.create_account_submit);



        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                stringname=editText_name.getText().toString();
                stringmno=editText_mno.getText().toString();
                stringemail=editText_email.getText().toString();

                stringaddress=editText_postal_address.getText().toString();
                stringcity=editText_city_name.getText().toString();
                stringoccupation=editText_occupation.getText().toString();

                stringpassword=editText_password.getText().toString();



                sharedPrefHandler.setSharedPreferences("user_name",stringname);
                sharedPrefHandler.setSharedPreferences("mno",stringmno);
                sharedPrefHandler.setSharedPreferences("postal_address",stringaddress);
                sharedPrefHandler.setSharedPreferences("city",stringcity);
                sharedPrefHandler.setSharedPreferences("occupation",stringoccupation);



                sharedPrefHandler.setSharedPreferences("name",stringname);
                sharedPrefHandler.setSharedPreferences("mno",stringmno);
                sharedPrefHandler.setSharedPreferences("email",stringemail);

                sharedPrefHandler.setSharedPreferences("umno", stringmno);
                sharedPrefHandler.setSharedPreferences("upass", stringpassword);

                if (!stringname.matches(namePatter) || stringemail.isEmpty() || !stringemail.matches(emailPattern) ||  stringmno.length()<10 || stringmno.matches(pattern))
                {

                    Toast.makeText(Create_new_account.this, "Enter Valid Information", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    CreateUserAccount();

                    Intent intent = new Intent(getApplication(), Login_page.class);
                    startActivity(intent);
                    Toast.makeText(Create_new_account.this, "Account Create Success", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.CreateAccount(
                stringname,stringmno,stringemail,stringaddress,stringcity,stringoccupation,stringpassword
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

                    showCreateSuccessToast();

                } else {
                    // Show Creation Failed Message
                    showCreateFailedToast();
                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showCreateFailedToast() {
        Toast.makeText(this, "OOPS,   Create action failed!", Toast.LENGTH_LONG).show();
    }

    private void showCreateSuccessToast() {
        Toast.makeText(this, "  created successfully.", Toast.LENGTH_LONG).show();
    }

    private void showEditFailedToast() {
        Toast.makeText(this, "OOPS,   Edit action failed!", Toast.LENGTH_LONG).show();
    }

    private void showEditSuccessToast() {
        Toast.makeText(this, "  updated successfully.", Toast.LENGTH_LONG).show();
    }


}

