package com.example.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api
{
    // String BASE_URL = "https://simplifiedcoding.net/demos/";
    // String BASE_URL = "https://fanciful-petroleum.000webhostapp.com/api/";
    //String BASE_URL = "https://jolty-act.000webhostapp.com/onroad/user_api/";
    String BASE_URL = "http://projectdevelopement.com/buspass/";

    //http://192.168.43.183/onroad/user_api/garage_name.php


    @GET("login.php") //i.e https://fanciful-petroleum.000webhostapp.com/api/user/login?mobileNo=9035292096&password=123456
    Call<IsExist> doLogin(@Query("mobileNo") String username, @Query("password") String password);





    @GET("fetch_profile.php")
    Call<List<User>> getProductByCode(@Query("f1") String string_mno);

    @GET("fetch_pass_type.php")
    Call<List<Buspass>> getPasstype();



    @GET("fetch_pass_term.php")
    Call<List<Buspass>> getPassTerm(@Query("f1") String Str_pass_term);



    @GET("fetch_buspass_details.php")
    Call<List<Buspass>> getBusPassDetails(@Query("f1") String string_pass_id);



    @GET("fetch_buspass_status.php")
    Call<List<BuspassStatus>> getBusPassDetailsreaply(@Query("f1") String string_mno);



    @POST("register.php")
    Call<IsExist> CreateAccount(


            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringaddress,

                @Query("f5") String stringcity,
                @Query("f6") String stringoccupation,
                        @Query("f7") String stringpassword

    );


    @POST("add_bus_apply.php")
    Call<IsExist> CreateAccountApplyPass(
            @Query("f1") String pass_id,
            @Query("f2") String pass_name,
            @Query("f3") String pass_term,
            @Query("f4") String pass_occupation,
            @Query("f5") String pass_amount,
            @Query("f6") String pass_from_place,
            @Query("f7") String pass_to_place,
            @Query("f8") String pass_document_place,
            @Query("f9") String user_name,
            @Query("f10") String mno,
            @Query("f11") String postal_address,
            @Query("f12") String city,
            @Query("f13") String occupation


    );

    @POST("Update_pass.php")
    Call<IsExist> CreateAccountApplyPass_reaply(

            @Query("f1") String pass_id,
            @Query("f2") String user_mno,
            @Query("f3") String from_date,
            @Query("f4") String expired_date



    );


}
