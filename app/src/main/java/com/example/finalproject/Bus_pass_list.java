package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Bus_pass_list extends AppCompatActivity
{
    Spinner spinner;
    Button button;
    ListView listView;
    SharedPrefHandler sharedPrefHandler;
    ArrayAdapter<String> adapter;
    List<Buspass> productList;
    String[] products;




    ArrayAdapter<String> adapter1;
    List<Buspass> productList1;
    String[] products1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_pass_list);

        sharedPrefHandler=new SharedPrefHandler(this);

        spinner=(Spinner)findViewById(R.id.selct_bus_type);
        button=(Button)findViewById(R.id.select_bus_pass_type);
    listView=(ListView)findViewById(R.id.bus_pass_term);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                String productCode = "";
                String selectedProduct = listView.getItemAtPosition(position).toString(); //first method
                // final String text = ((TextView)view).getText();// second method

                // Get ProductCode between SquareBracket
                Pattern p = Pattern.compile("\\[(.*?)\\]");
                Matcher m = p.matcher(selectedProduct);
                while(m.find()) {
                    productCode = m.group(1);
                }
                // Toast.makeText(getApplicationContext(), productCode, Toast.LENGTH_SHORT).show();

                //set ProductCode SharedPreferences
                sharedPrefHandler.setSharedPreferences("pass_id", productCode);


                //String str_term=listView.getItemAtPosition(position).toString();
//String str_term="001";

                //sharedPrefHandler.setSharedPreferences("pass_id",str_term);

                Intent intent=new Intent(getApplication(),BusPass_details.class);
                startActivity(intent);
                //finish();


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Str_pass_term=spinner.getSelectedItem().toString();
                getPass_list(Str_pass_term);


            }
        });


        getProducts();

    }

    private void getProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Buspass>> call = api.getPasstype();

        call.enqueue(new Callback<List<Buspass>>() {
            @Override
            public void onResponse(Call<List<Buspass>> call, Response<List<Buspass>> response) {
                // List<Product> productList = response.body();
                productList = response.body();

                // Loading Products in Sagar style
                loadProductListView();

                /*
                //Creating an String array for the ListView
                products = new String[productList.size()];

                //looping through all the products and inserting the names inside the string array
                for (int i = 0; i < productList.size(); i++) {
                    products[i] = productList.get(i).getProduct_name();
                }

                //displaying the string array into listview
                lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, products));
                 */
            }

            @Override
            public void onFailure(Call<List<Buspass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getpass_occupation();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        spinner.setAdapter(adapter);
    }






    //Bus Pass List

    private void getPass_list(final  String Str_pass_term) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Buspass>> call = api.getPassTerm(Str_pass_term);

        call.enqueue(new Callback<List<Buspass>>() {
            @Override
            public void onResponse(Call<List<Buspass>> call, Response<List<Buspass>> response) {
                // List<Product> productList = response.body();
                productList1 = response.body();

                // Loading Products in Sagar style
                loadProductListView1();

                /*
                //Creating an String array for the ListView
                products = new String[productList.size()];

                //looping through all the products and inserting the names inside the string array
                for (int i = 0; i < productList.size(); i++) {
                    products[i] = productList.get(i).getProduct_name();
                }

                //displaying the string array into listview
                lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, products));
                 */
            }

            @Override
            public void onFailure(Call<List<Buspass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView1() {
        //Creating an String array for the ListView
        products1 = new String[productList1.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList1.size(); i++) {
            products1[i] = productList1.get(i).getpass_term()+"["+productList1.get(i).getpass_id()+"]";
        }

        adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products1);
        listView.setAdapter(adapter1);
    }



}
